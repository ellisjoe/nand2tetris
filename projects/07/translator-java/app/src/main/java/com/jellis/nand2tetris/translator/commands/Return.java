package com.jellis.nand2tetris.translator.commands;

import com.google.common.collect.ImmutableList;

import java.util.List;

public record Return() implements Command {
    @Override
    public List<String> assembly() {
        return ImmutableList.<String>builder()
                // LCL into R13
                .add("@LCL")
                .add("D=M")
                .add("@R13")
                .add("M=D")
                // Return address into R14
                .addAll(frameValueInto("@R13", 5, "@R14"))
                // Reposition return value
                .addAll(popD())
                .add("@ARG")
                .add("A=M")
                .add("M=D")
                // Reposition SP
                .add("@ARG")
                .add("A=M")
                .add("D=A+1") // D=ARG+1
                .add("@SP")
                .add("M=D")   // SP=ARG+1
                // Restore THAT
                .addAll(frameValueInto("@R13", 1, "@THAT"))
                // Restore THIS
                .addAll(frameValueInto("@R13", 2, "@THIS"))
                // Restore ARG
                .addAll(frameValueInto("@R13", 3, "@ARG"))
                // Restore LCL
                .addAll(frameValueInto("@R13", 4, "@LCL"))
                // Go to return address
                .add("@R14")
                .add("A=M")
                .add("0;JMP")
                .build();
    }

    private static List<String> frameValueInto(String frameVar, int spacesBack, String dest) {
        return ImmutableList.<String>builder()
                .addAll(frameValueToD(frameVar, spacesBack))
                .add(dest)
                .add("M=D")
                .build();

    }

    private static List<String> frameValueToD(String frameVar, int spacesBack) {
        return ImmutableList.<String>builder()
                .add("@" + spacesBack)
                .add("D=A")   // D=spacesBack
                .add(frameVar)
                .add("D=M-D") // D=frame-spacesBack
                .add("A=D")   // A=frame-spacesBack
                .add("D=M")   // D=RAM[frame-spacesBack]
                .build();
    }
}
