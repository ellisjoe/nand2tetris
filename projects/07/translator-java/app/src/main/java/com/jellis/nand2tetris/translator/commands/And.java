package com.jellis.nand2tetris.translator.commands;

import com.google.common.collect.ImmutableList;

import java.util.List;

public record And() implements Command {
    @Override
    public List<String> assembly() {
        return binaryOp("&");
    }
}
