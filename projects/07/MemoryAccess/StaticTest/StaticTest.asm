// Push[segment=constant, index=111]
@111
D=A
@SP
A=M
M=D
@SP
M=M+1
// Push[segment=constant, index=333]
@333
D=A
@SP
A=M
M=D
@SP
M=M+1
// Push[segment=constant, index=888]
@888
D=A
@SP
A=M
M=D
@SP
M=M+1
// Pop[segment=static, index=8]
@SP
M=M-1
A=M
D=M
@R13
M=D
@Static.8
D=A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
// Pop[segment=static, index=3]
@SP
M=M-1
A=M
D=M
@R13
M=D
@Static.3
D=A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
// Pop[segment=static, index=1]
@SP
M=M-1
A=M
D=M
@R13
M=D
@Static.1
D=A
@R14
M=D
@R13
D=M
@R14
A=M
M=D
// Push[segment=static, index=3]
@Static.3
D=M
@SP
A=M
M=D
@SP
M=M+1
// Push[segment=static, index=1]
@Static.1
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
// Push[segment=static, index=8]
@Static.8
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
