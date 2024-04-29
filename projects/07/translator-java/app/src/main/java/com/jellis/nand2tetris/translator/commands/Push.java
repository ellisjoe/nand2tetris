package com.jellis.nand2tetris.translator.commands;

import com.google.common.collect.ImmutableList;

import java.util.List;

public record Push(String filename, String segment, int index) implements Command {
    @Override
    public List<String> assembly() {
        return ImmutableList.<String>builder()
                .addAll(loadIntoD(segment, index))
                .addAll(pushD())
                .build();
    }

    private List<String> loadIntoD(String segment, int index) {
        return switch (segment) {
            case "argument", "local", "this", "that", "temp", "pointer" -> {
                String baseAddress = segmentToBaseAddress(segment);
                yield ImmutableList.<String>builder()
                        .add("@" + index) // A = index
                        .add("D=A")       // D = index
                        .add("@" + baseAddress) // A = baseAddress
                        // Resolve virtual segment if necessary eg: A = RAM[baseAddress]
                        .addAll(NO_LOOKUP.contains(segment) ? List.of() : List.of("A=M"))
                        .add("A=A+D")     // A = baseAddress + index
                        .add("D=M")       // D = RAM[baseAddress + index]
                        .build();
            }
            case "static" -> List.of("@" + filename + "." + index, "D=M");
            case "constant" -> List.of("@" + index, "D=A");
            default -> throw new IllegalStateException("Unexpected value: " + segment);
        };
    }

}
