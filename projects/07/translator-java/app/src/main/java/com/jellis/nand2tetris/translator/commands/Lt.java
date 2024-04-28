package com.jellis.nand2tetris.translator.commands;

import java.util.List;

public record Lt() implements Command {
    @Override
    public List<String> assembly() {
        return comparisonOp("JLT");
    }
}
