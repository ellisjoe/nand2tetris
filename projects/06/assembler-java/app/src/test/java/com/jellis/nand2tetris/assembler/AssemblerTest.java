package com.jellis.nand2tetris.assembler;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AssemblerTest {
    @Test
    void testPong() {
        runTest(Path.of("../../pong/Pong.asm"));
    }

    @Test
    void testAdd() {
        runTest(Path.of("../../add/Add.asm"));
    }

    @Test
    void testMax() {
        runTest(Path.of("../../max/Max.asm"));
    }
    @Test
    void testRect() {
        runTest(Path.of("../../rect/Rect.asm"));
    }

    private void runTest(Path asmPath) {
        Path comparePath = replaceExtension(asmPath, ".hack");
        Path outPath = replaceExtension(asmPath, "-1.hack");

        try {
            Assembler.assemble(asmPath, outPath);

            assertThat(outPath).hasSameTextualContentAs(comparePath);
        } finally {
            outPath.toFile().delete();
        }
    }

    Path replaceExtension(Path asmPath, String newExtension) {
        String hackPath = asmPath.getFileName().toString().replace(".asm", newExtension);
        return asmPath.resolveSibling(hackPath);
    }
}