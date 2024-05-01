package com.jellis.nand2tetris.translator.commands;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.IntStream;

public record Function(String filename, String functionName, int nVars) implements Command {
    @Override
    public List<String> assembly() {
        return ImmutableList.<String>builder()
                .add("(" + symbol(filename, functionName) + ")")
                .add("D=0")
                .addAll(IntStream.range(0, nVars).boxed().flatMap($ -> pushD().stream()).toList())
                .build();
    }

    public static String symbol(String filename, String functionName) {
        return filename + "$" + functionName;
    }
}
