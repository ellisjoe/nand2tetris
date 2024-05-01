package com.jellis.nand2tetris.translator.commands;

import java.util.List;

public record Call(String filename, String functionName, int nArgs) implements Command {
    @Override
    public List<String> assembly() {
        return null;
    }
}
