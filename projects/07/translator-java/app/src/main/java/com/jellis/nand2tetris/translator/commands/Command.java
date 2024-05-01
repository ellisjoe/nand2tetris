package com.jellis.nand2tetris.translator.commands;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public sealed interface Command permits Add, And, Call, Eq, Function, GoTo, Gt, IfGoTo, Label, Lt, Neg, Not, Or, Pop, Push, Return, Sub {
    Set<String> NO_LOOKUP = Set.of("pointer", "temp");

    List<String> assembly();

    default List<String> debugAssembly() {
        return ImmutableList.<String>builder()
                .add("// " + toString())
                .addAll(assembly())
                .build();
    }

    static Command parse(String filename, String functionName, String command) {
        List<String> commandAndArgs = Splitter.on(" ").splitToList(command);

        return switch (commandAndArgs.get(0)) {
            case "push"     -> new Push(filename, commandAndArgs.get(1), Integer.parseInt(commandAndArgs.get(2)));
            case "pop"      -> new Pop(filename, commandAndArgs.get(1), Integer.parseInt(commandAndArgs.get(2)));
            case "add"      -> new Add();
            case "sub"      -> new Sub();
            case "neg"      -> new Neg();
            case "eq"       -> new Eq();
            case "gt"       -> new Gt();
            case "lt"       -> new Lt();
            case "and"      -> new And();
            case "or"       -> new Or();
            case "not"      -> new Not();
            case "label"    -> new Label(filename, functionName, commandAndArgs.get(1));
            case "if-goto"  -> new IfGoTo(filename, functionName, commandAndArgs.get(1));
            case "goto"     -> new GoTo(filename, functionName, commandAndArgs.get(1));
            case "function" -> new Function(filename, commandAndArgs.get(1), Integer.parseInt(commandAndArgs.get(2)));
            case "return"   -> new Return();
            default -> throw new IllegalArgumentException("Unknown command: " + commandAndArgs.get(0));
        };
    }

    AtomicInteger jumpCount = new AtomicInteger();

    default List<String> comparisonOp(String comp) {
        int jumpNum = jumpCount.getAndIncrement();
        String jumpAddress = comp.toUpperCase() + jumpNum;
        String endAddress = "END" + jumpNum;
        return ImmutableList.<String>builder()
                .addAll(binaryOp("-")) // x - y
                .addAll(popD())        // D = x - y
                .add("@" + jumpAddress)
                .add("D;" + comp) // if D <comp> 0, then jump to COMP
                .add("@0")        // A = 0
                .add("D=A")       // D = 0
                .addAll(pushD())  // push(0)
                .add("@" + endAddress)
                .add("0;JMP")     // jump to END
                .add("("+ jumpAddress + ")")
                .add("@1")        // A = 1
                .add("A=-A")      // A = -1
                .add("D=A")       // D = -1
                .addAll(pushD())  // push(-1)
                .add("(" + endAddress +")")
                .build();
    }

    default List<String> unaryOp(String op) {
        return ImmutableList.<String>builder()
                .addAll(popD())
                .add("D=" + op + "D")
                .addAll(pushD())
                .build();
    }

    default List<String> binaryOp(String op) {
        return ImmutableList.<String>builder()
                .addAll(popInto("@R13"))
                .addAll(popInto("@R14"))
                .add("@R13")
                .add("D=M")
                .add("@R14")
                .add("D=M" + op + "D")
                .addAll(pushD())
                .build();
    }

    default List<String> popInto(String memoryName) {
        return ImmutableList.<String>builder()
                .addAll(popD())
                .add(memoryName) // A=location
                .add("M=D")   // RAM[location]=D
                .build();
    }

    default List<String> popD() {
        return ImmutableList.<String>builder()
                .add("@SP")   // A=0
                .add("M=M-1") // RAM[0]--
                .add("A=M")   // A=RAM[0] (memory to pop)
                .add("D=M")   // D=RAM[RAM[0]]
                .build();
    }

    default List<String> pushD() {
        return ImmutableList.<String>builder()
                .add("@SP")   // A=0
                .add("A=M")   // A=RAM[0]
                .add("M=D")   // RAM[RAM[0]]=D
                .add("@SP")   // A=0
                .add("M=M+1") // RAM[0]++
                .build();
    }

    default String segmentToBaseAddress(String segment) {
        return switch (segment) {
            case "argument" -> "ARG";
            case "local"    -> "LCL";
            case "this"     -> "THIS";
            case "that"     -> "THAT";
            case "pointer"  -> "THIS"; // pointer[0] == THIS, pointer[1] == THAT
            case "temp"     -> "5";
            default -> throw new IllegalStateException("Unexpected value: " + segment);
        };
    }
}
