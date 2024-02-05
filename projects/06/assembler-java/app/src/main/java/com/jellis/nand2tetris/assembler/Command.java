package com.jellis.nand2tetris.assembler;

sealed interface Command permits ACommand, LCommand, CCommand {
    static Command parse(String command) {
        return switch (command.charAt(0)) {
            case '@' -> ACommand.parse(command);
            case '(' -> LCommand.parse(command);
            default -> CCommand.parse(command);
        };
    }

    /**
     * Translate any ACommands that have non-numeric symbols into their numeric symbols defined by the given table.
     * @param table
     * @return
     */
    Command translate(SymbolTable table);

    /**
     * Produce the hack binary instruction for this command.
     */
    String assemble();
}
