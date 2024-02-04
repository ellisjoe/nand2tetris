package com.jellis.nand2tetris.assembler;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class AssemblerTest {
    @Test
    void testParse() throws IOException {
//        Path asmPath = Path.of("../../add/Add.asm");
//        Path asmPath = Path.of("../../max/Max.asm");
//        Path asmPath = Path.of("../../rect/Rect.asm");
        Path asmPath = Path.of("../../pong/Pong.asm");
        Path hackPath = asmToHackPath(asmPath);
        Assembler.assemble(asmPath, hackPath);
    }

    Path asmToHackPath(Path asmPath) {
        String hackPath = asmPath.getFileName().toString().replace(".asm", "-1.hack");
        return asmPath.resolveSibling(hackPath);
    }
}