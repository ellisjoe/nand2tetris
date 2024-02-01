// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen
// by writing 'black' in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen by writing
// 'white' in every pixel;
// the screen should remain fully clear as long as no key is pressed.

//// Replace this comment with your code.


// R0 is the current SCREEN offset to fill
// R1 is the current value to fill pixels with, either black (-1) or white (0)
// R2 is the new value to fill pixels with

@SCREEN
D=A
@R0
M=D // R0 is the current offset

@R1
M=0 // R1 default to white

@R2
M=0 // R2 default to white

(BEGIN)

@24576 // Keyboard address
D=M

@FILLWHITE
D;JEQ // If key not pressed, goto FILLWHITE

@FILLBLACK
0;JMP // IF key pressed, goto FILLBLACK

// -----------

(FILLWHITE)
@R2
M=0 // R2 = 0

D=M
@R1
D=M-D // D = R2 - R1

@CONTINUEFILL
D;JEQ // If R1 == R2, goto CONTINUEFILL

@STARTFILL
0;JMP

// --------

(FILLBLACK)
@R2
M=-1 // R2 = -1

D=M
@R1
D=M-D // D = R2 - R1

@CONTINUEFILL
D;JEQ // If R1 == R2, goto CONTINUEFILL

@STARTFILL
0;JMP

// --------

(STARTFILL)

@R2
D=M // D = R2

@R1
M=D // R1 = R2

@SCREEN
D=A
@R0
M=D // R0 is the current offset

(CONTINUEFILL)
@R0
D=M // D = R0

@24576
D=D-A // D = R0 - 16384 (SCREEN) - 8,192 (SIZE)

@BEGIN
D;JGE // If R0 - 24576 >= 0 goto BEGIN

@R1
D=M

@R0
A=M
M=D // Set current pixel offset to -1 (1111111111111111)


@R0
D=M
M=D+1 // Increment R0
// M=M+1 ??

@BEGIN
0;JMP // Loop

//@R0
//D=A
//D=D+1
//
//A=A+1
//A=M // A = offset
//
//@R0
//M=A+1


