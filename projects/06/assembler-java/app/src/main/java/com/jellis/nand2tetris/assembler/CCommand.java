package com.jellis.nand2tetris.assembler;

record CCommand(Dest dest, String comp, Jump jmp) implements Command {
    static CCommand parse(String command) {
        int semicolon = command.indexOf(';');
        Jump jump = semicolon == -1 ? Jump.NULL : Jump.valueOf(command.substring(semicolon + 1));

        String destAndComp = command.substring(0, semicolon == -1 ? command.length() : semicolon);

        int equals = destAndComp.indexOf('=');
        Dest dest = equals == -1 ? Dest.NULL : Dest.valueOf(destAndComp.substring(0, equals));

        String comp = destAndComp.substring(equals == -1 ? 0 : equals + 1);

        return new CCommand(dest, comp, jump);
    }

    @Override
    public Command translate(SymbolTable table) {
        return this;
    }

    @Override
    public String assemble() {
        String a = comp.contains("M") ? "1" : "0";
        String compBits = switch (comp) {
            case          "0" -> "101010";
            case          "1" -> "111111";
            case         "-1" -> "111010";
            case          "D" -> "001100";
            case     "A", "M" -> "110000";
            case         "!D" -> "001101";
            case   "!A", "!M" -> "110001";
            case         "-D" -> "001111";
            case   "-A", "-M" -> "110011";
            case        "D+1" -> "011111";
            case "A+1", "M+1" -> "110111";
            case        "D-1" -> "001110";
            case "A-1", "M-1" -> "110010";
            case "D+A", "D+M" -> "000010";
            case "D-A", "D-M" -> "010011";
            case "A-D", "M-D" -> "000111";
            case "D&A", "D&M" -> "000000";
            case "D|A", "D|M" -> "010101";
            default -> throw new IllegalStateException("Unexpected value: " + comp);
        };
        return "111" + a + compBits + dest.binary() + jmp.binary();
    }
}
