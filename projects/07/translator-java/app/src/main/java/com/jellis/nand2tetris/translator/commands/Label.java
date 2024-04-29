package com.jellis.nand2tetris.translator.commands;

import java.util.List;

public record Label(String filename, String functionName, String label) implements Command {
    @Override
    public List<String> assembly() {
        return List.of("(" + toSymbol(filename, functionName, label) + ")");
    }

    public static String toSymbol(String filename, String functionName, String label) {
        return String.format("%s.%s$%s", filename, functionName, label);
    }
}
