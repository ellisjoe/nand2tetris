package com.jellis.nand2tetris.assembler;

enum Dest {
    NULL("000"), M("001"), D("010"), MD("011"),
    A("100"), AM("101"), AD("110"), AMD("111");

    private final String binary;

    Dest(String binary) {
        this.binary = binary;
    }

    public String binary() {
        return binary;
    }
}
