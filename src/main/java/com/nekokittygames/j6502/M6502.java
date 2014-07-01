package com.nekokittygames.j6502;

/**
 * Created by nekosune on 01/07/14.
 */
abstract class M6502 {
    public static int INT_NONE = 0;      /* No interrupt required        */
    public static int INT_IRQ = 1;        /* Standard IRQ interrupt       */
    public static int INT_NMI = 2;        /* Non-maskable interrupt       */
    public static int INT_QUIT = 3;       /* Exit the emulation           */

    /* 6502 status flags:           */
    public static int C_FLAG = 0x01;      /* 1: Carry occured             */
    public static int Z_FLAG = 0x02;      /* 1: Result is zero            */
    public static int I_FLAG = 0x04;      /* 1: Interrupts disabled       */
    public static int D_FLAG = 0x08;      /* 1: Decimal mode              */
    public static int B_FLAG = 0x10;      /* Break [0 on stk after int]   */
    public static int R_FLAG = 0x20;      /* Always 1                     */
    public static int V_FLAG = 0x40;      /* 1: Overflow occured          */
    public static int N_FLAG = 0x80;      /* 1: Result is negative        */

    byte A, P, X, Y, S;     /* CPU registers and program counter   */
    Pair PC;

    int IPeriod, ICount; /* Set IPeriod to number of CPU cycles */
    /* between calls to Loop6502()         */
    byte IRequest;      /* Set to the INT_IRQ when pending IRQ */
    byte AfterCLI;      /* Private, don't touch                */
    int IBackup;        /* Private, don't touch                */
    byte IAutoReset;    /* Set to 1 to autom. reset IRequest   */
    byte TrapBadOps;    /* Set to 1 to warn of illegal opcodes */
    char Trap;          /* Set Trap to address to trace from   */
    byte Trace;         /* Set Trace=1 to start tracing        */

}
