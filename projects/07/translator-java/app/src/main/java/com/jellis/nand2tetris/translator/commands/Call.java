package com.jellis.nand2tetris.translator.commands;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public record Call(String filename, String functionName, String functionToCall, int nArgs) implements Command {
    private static AtomicInteger count = new AtomicInteger();

    @Override
    public List<String> assembly() {
        String returnSymbol = Label.toSymbol(filename, functionName, "ret" + count.getAndIncrement());
        return ImmutableList.<String>builder()
                // Generate a push label
                .add("@" + returnSymbol)
                .add("D=A")
                .addAll(pushD())
                // Save frame
                .addAll(push("@LCL"))
                .addAll(push("@ARG"))
                .addAll(push("@THIS"))
                .addAll(push("@THAT"))
                // Reposition ARG
                .add("@SP")
                .add("D=M")
                .add("@5")
                .add("D=D-A")
                .add("@" + nArgs)
                .add("D=D-A")
                .add("@ARG")
                .add("M=D")
                // Reposition LCL
                .add("@SP")
                .add("D=M")
                .add("@LCL")
                .add("M=D")
                // Transfer control
                .add("@" + Function.symbol(Splitter.on(".").splitToList(functionToCall).get(0), functionToCall))
                .add("0;JMP")
                // Inject return address
                .add("(" + returnSymbol + ")")
                .build();
    }

    private List<String> push(String address) {
        return ImmutableList.<String>builder()
                .add(address)
                .add("D=M")
                .addAll(pushD())
                .build();
    }
}
