package com.jellis.nand2tetris.assembler;

enum Jump {
    NULL("000"), JGT("001"), JEQ("010"), JGE("011"),
    JLT("100"), JNE("101"), JLE("110"), JMP("111");

    private final String binary;

    Jump(String binary) {
        this.binary = binary;
    }

    public String binary() {
        return binary;
    }
}
