// Multiplies 2 numbers in R0 and R1, a places the result into R2.

@0
D=A
@R2
M=D // R2 = 0

@R0
D=M
@END
D;JEQ // If R0 == 0 goto END

@R1
D=M
@END
D;JEQ // If R1 == 0 goto END

(LOOP)
@R0
D=M
@END
D;JLE // If R0 <= 0 goto END

@R1
D=M // D = R1

@R2
M=D+M // R2 += R1

@R0
M=M-1 // R0 -= 1

@LOOP
0;JMP // Loop

(END)
@END
0;JMP // Infinite loop
