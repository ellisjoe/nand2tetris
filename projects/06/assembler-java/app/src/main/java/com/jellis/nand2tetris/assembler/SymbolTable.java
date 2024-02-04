package com.jellis.nand2tetris.assembler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class SymbolTable {
    private AtomicInteger memoryOffset = new AtomicInteger(16);
    private final Map<String, Integer> symbolToAddress = new HashMap<>();

    public SymbolTable() {
        symbolToAddress.put("SP", 0);
        symbolToAddress.put("LCL", 1);
        symbolToAddress.put("ARG", 2);
        symbolToAddress.put("THIS", 3);
        symbolToAddress.put("THAT", 4);

        for (int i = 0; i <= 15; i++) {
            symbolToAddress.put("R" + i, i);
        }

        symbolToAddress.put("SCREEN", 16384);
        symbolToAddress.put("KBD", 24576);
    }

    public void add(String symbol, Integer address) {
        symbolToAddress.put(symbol, address);
    }

    /**
     * Returns the address associated with the symbol if a mapping exists. Else creates a mapping for a new variable
     * with the next lowest memory address.
     */
    public Integer getOrCreateVariable(String symbol) {
        return symbolToAddress.computeIfAbsent(symbol, $ -> memoryOffset.getAndIncrement());
    }
}
