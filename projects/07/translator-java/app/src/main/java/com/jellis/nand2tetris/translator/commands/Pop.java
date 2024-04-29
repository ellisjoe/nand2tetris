package com.jellis.nand2tetris.translator.commands;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Set;

public record Pop(String filename, String segment, int index) implements Command {

    @Override
    public List<String> assembly() {
        return ImmutableList.<String>builder()
                .addAll(popInto("@R13")) // R13 = pop()
                .addAll(segment.equals("static") ? List.of("@" + filename + "." + index, "D=A") : storageAddressInD(segment, segmentToBaseAddress(segment), index))
                .add("@R14")
                .add("M=D")  // R14 = base + index
                .add("@R13")
                .add("D=M")  // D = pop()
                .add("@R14")
                .add("A=M")  // A = base + index
                .add("M=D")  // RAM[base + index] = pop()
                .build();
    }

    private static List<String> storageAddressInD(String segment, String baseAddress, int index) {
        return ImmutableList.<String>builder()
                .add("@" + index) // A = index
                .add("D=A")       // D = index
                .add("@" + baseAddress) // A = baseAddress
                // Resolve virtual segment if necessary
                .addAll(NO_LOOKUP.contains(segment) ? List.of() : List.of("A=M"))
                .add("D=A+D")     // D = baseAddress + index
                .build();
    }
}
