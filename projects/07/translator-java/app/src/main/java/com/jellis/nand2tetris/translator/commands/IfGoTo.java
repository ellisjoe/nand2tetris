package com.jellis.nand2tetris.translator.commands;

import com.google.common.collect.ImmutableList;

import java.util.List;

public record IfGoTo(String filename, String functionName, String label) implements Command {
    @Override
    public List<String> assembly() {
        String labelSymbol = Label.toSymbol(filename, functionName, label);
        return ImmutableList.<String>builder()
                .addAll(popD())
                .add("@" + labelSymbol)
                .add("D;JNE")
                .build();
    }
}
