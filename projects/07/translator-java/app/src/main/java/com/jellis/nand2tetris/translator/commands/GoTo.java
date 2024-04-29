package com.jellis.nand2tetris.translator.commands;

import java.util.List;

public record GoTo(String filename, String functionName, String label) implements Command {
    @Override
    public List<String> assembly() {
        String symbol = Label.toSymbol(filename, functionName, label);
        return List.of("@" + symbol, "0;JMP");
    }
}
