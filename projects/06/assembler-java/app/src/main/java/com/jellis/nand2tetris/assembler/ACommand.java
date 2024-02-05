package com.jellis.nand2tetris.assembler;

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;

record ACommand(String symbol) implements Command {
    static ACommand parse(String command) {
        checkArgument(command.charAt(0) == '@');
        return new ACommand(command.substring(1));
    }

    @Override
    public Command translate(SymbolTable table) {
        // If the symbol is an integer, there's nothing to translate
        if (symbol.matches("\\d+")) {
            return this;
        } else {
            Integer address = table.getOrCreateVariable(symbol);
            return new ACommand(address.toString());
        }
    }

    public String assemble() {
        String binaryString = Integer.toBinaryString(Integer.parseInt(symbol));
        int pad = 16 - binaryString.length();
        return Strings.repeat("0", pad) + binaryString;
    }
}
