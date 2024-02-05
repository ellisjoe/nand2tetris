package com.jellis.nand2tetris.assembler;

sealed interface Command permits ACommand, LCommand, CCommand {
    static Command parse(String command) {
        return switch (command.charAt(0)) {
            case '@' -> ACommand.parse(command);
            case '(' -> LCommand.parse(command);
            default -> CCommand.parse(command);
        };
    }

    Command translate(SymbolTable table);

    String assemble();
}
