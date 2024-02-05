package com.jellis.nand2tetris.assembler;

import static com.google.common.base.Preconditions.checkArgument;

record LCommand(String symbol) implements Command {
    static LCommand parse(String command) {
        checkArgument(command.charAt(0) == '(');
        checkArgument(command.charAt(command.length() - 1) == ')');
        return new LCommand(command.substring(1, command.length() - 1));
    }

    @Override
    public Command translate(SymbolTable table) {
        throw new IllegalStateException("LCommands are pseudo-commands have should have already been processed");
    }

    @Override
    public String assemble() {
        throw new IllegalArgumentException("LCommands are pseudo-commands and should have already been processed");
    }
}
