// Push[segment=constant, index=3030]
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1
// Pop[segment=pointer, index=0]
@SP
M=M-1
A=M
D=M
@R13
M=D
@0
D=A
@THIS
A=M
D=A+D
@R14
M=D
@R13
D=M
@R14
A=M
M=D
// Push[segment=constant, index=3040]
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1
// Pop[segment=pointer, index=1]
@SP
M=M-1
A=M
D=M
@R13
M=D
@1
D=A
@THIS
A=M
D=A+D
@R14
M=D
@R13
D=M
@R14
A=M
M=D
// Push[segment=constant, index=32]
@32
D=A
@SP
A=M
M=D
@SP
M=M+1
// Pop[segment=this, index=2]
@SP
M=M-1
A=M
D=M
@R13
M=D
@2
D=A
@THIS
A=M
D=A+D
@R14
M=D
@R13
D=M
@R14
A=M
M=D
// Push[segment=constant, index=46]
@46
D=A
@SP
A=M
M=D
@SP
M=M+1
// Pop[segment=that, index=6]
@SP
M=M-1
A=M
D=M
@R13
M=D
@6
D=A
@THAT
A=M
D=A+D
@R14
M=D
@R13
D=M
@R14
A=M
M=D
// Push[segment=pointer, index=0]
@0
D=A
@THIS
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// Push[segment=pointer, index=1]
@1
D=A
@THIS
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// Add[]
@SP
M=M-1
A=M
D=M
@R13
M=D
@SP
M=M-1
A=M
D=M
@R14
M=D
@R13
D=M
@R14
D=M+D
@SP
A=M
M=D
@SP
M=M+1
// Push[segment=this, index=2]
@2
D=A
@THIS
A=M
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// Sub[]
@SP
M=M-1
A=M
D=M
@R13
M=D
@SP
M=M-1
A=M
D=M
@R14
M=D
@R13
D=M
@R14
D=M-D
@SP
A=M
M=D
@SP
M=M+1
// Push[segment=that, index=6]
@6
D=A
@THAT
A=M
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// Add[]
@SP
M=M-1
A=M
D=M
@R13
M=D
@SP
M=M-1
A=M
D=M
@R14
M=D
@R13
D=M
@R14
D=M+D
@SP
A=M
M=D
@SP
M=M+1
