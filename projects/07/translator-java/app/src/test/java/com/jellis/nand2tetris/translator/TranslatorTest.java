package com.jellis.nand2tetris.translator;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {
    @Test
    void test() {
//        Path vmPath = Path.of("../../StackArithmetic/SimpleAdd/SimpleAdd.vm");
//        Path vmPath = Path.of("../../StackArithmetic/StackTest/StackTest.vm");
//        Path vmPath = Path.of("../../MemoryAccess/PointerTest/PointerTest.vm");
        Path vmPath = Path.of("../../MemoryAccess/StaticTest/StaticTest.vm");
//        Path vmPath = Path.of("../../MemoryAccess/BasicTest/BasicTest.vm");
        Path asmPath = replaceExtension(vmPath, ".asm");
        Translator.translate(vmPath, asmPath);
    }

    Path replaceExtension(Path vmPath, String newExtension) {
        String hackPath = vmPath.getFileName().toString().replace(".vm", newExtension);
        return vmPath.resolveSibling(hackPath);
    }
}