package com.nekokittygames.j6502;

/**
 * Created by nekosune on 01/07/14.
 */
public abstract class M6502 {


    /* 6502 status flags:           */
    /**
     * 1: Carry Occured
     */
    public static byte C_FLAG = 0x01;
    /**
     * 1: Result is zero
     */
    public static byte Z_FLAG = 0x02;
    /**
     * 1: Interupts disabled
     */
    public static byte I_FLAG = 0x04;
    /**
     * 1: Decimal mode
     */
    public static byte D_FLAG = 0x08;
    /**
     * Break [0 on stk after int]
     */
    public static byte B_FLAG = 0x10;
    /**
     * Always 1
     */
    public static byte R_FLAG = 0x20;
    /**
     * 1: Overflow occured
     */
    public static byte V_FLAG = 0x40;
    /**
     * 1: Result is negative
     */
    public static int N_FLAG = 0x80;


    public static byte Cycles[] =new byte[]
    {
                7,6,2,8,3,3,5,5,3,2,2,2,4,4,6,6,
                2,5,2,8,4,4,6,6,2,4,2,7,5,5,7,7,
                6,6,2,8,3,3,5,5,4,2,2,2,4,4,6,6,
                2,5,2,8,4,4,6,6,2,4,2,7,5,5,7,7,
                6,6,2,8,3,3,5,5,3,2,2,2,3,4,6,6,
                2,5,2,8,4,4,6,6,2,4,2,7,5,5,7,7,
                6,6,2,8,3,3,5,5,4,2,2,2,5,4,6,6,
                2,5,2,8,4,4,6,6,2,4,2,7,5,5,7,7,
                2,6,2,6,3,3,3,3,2,2,2,2,4,4,4,4,
                2,6,2,6,4,4,4,4,2,5,2,5,5,5,5,5,
                2,6,2,6,3,3,3,3,2,2,2,2,4,4,4,4,
                2,5,2,5,4,4,4,4,2,4,2,5,4,4,4,4,
                2,6,2,8,3,3,5,5,2,2,2,2,4,4,6,6,
                2,5,2,8,4,4,6,6,2,4,2,7,5,5,7,7,
                2,6,2,8,3,3,5,5,2,2,2,2,4,4,6,6,
                2,5,2,8,4,4,6,6,2,4,2,7,5,5,7,7
    };

    static int ZNTable[] = new int[]
    {
                Z_FLAG,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
                N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,N_FLAG,
    };

    byte A, P, X, Y, S;     /** CPU registers and program counter   */
    Pair PC; /** Program counter */

    int IPeriod, ICount; /* Set IPeriod to number of CPU cycles */
    /* between calls to Loop6502()         */
    byte IRequest;      /* Set to the INT_IRQ when pending IRQ */
    byte AfterCLI;      /* Private, don't touch                */
    int IBackup;        /* Private, don't touch                */
    byte IAutoReset;    /* Set to 1 to autom. reset IRequest   */
    byte TrapBadOps;    /* Set to 1 to warn of illegal opcodes */
    char Trap;          /* Set Trap to address to trace from   */
    byte Trace;         /* Set Trace=1 to start tracing        */


    /**
     * This function can be used to reset the registers before
     * starting execution with Run6502(). It sets registers to
     * their initial values.
     */
    public void Reset6502()
    {

    }

    /**
     *  This function will execute given number of 6502 cycles.
     * @param RunCycles Number of cycles to run
     * @return Number of cycles left (possibly negative)
     */
    public int Exec6502(int RunCycles)
    {
       return -1;
    }

    /**
     * This function will generate interrupt of a given type.
     * @param Type Type of interupt
     */
    public void Int6502(byte Type)
    {

    }

    /**
     * This function will run 6502 code until Loop6502() call
     * returns INT_QUIT
     * @return PC at which stopped
     */
    public char Run6502()
    {
        return (char)0;
    }

    /**
     * Writes to memory address
     * @param Addr Address to write to
     * @param Value Value to write
     */
    public abstract void Wr6502(char Addr,byte Value);

    /**
     * Reads from memory address
     * @param Addr address to read from
     * @return Value read
     */
    public abstract byte Rd6502(char Addr);

    /**
     * Reads Opcode from memory address
     * short circuits some checks
     * @param Addr Address to read from
     * @return Opcode read
     */
    public abstract byte Op6502(char Addr);

    /**
     * When Trace!=0, it is called after each command executed by
     * the CPU, and given the 6502 registers. Emulation exits
     * if Debug6502() returns 0.
     * @return 0 to exit emulation
     */
    public byte Debug6502()
    {
        return 0;
    }

    /**
     * 6502 emulation calls this function periodically to
     * check if the system hardware requires any interrupts.
     *
     * @return Interupt nterupt to throw if any
     */
    public Interupt Loop6502()
    {
        return Interupt.INT_NONE;
    }

    /**
     * Emulation calls this function when it encounters an
     * unknown opcode. This can be used to patch the code to
     * emulate BIOS calls, such as disk and tape access. The
     * function should return 1 if the exception was handled,
     * or 0 if the opcode was truly illegal.
     * @param Op unknown opcode
     * @return <c>1</c> if handled or <c>0</c>if was invalid
     */
    public abstract byte Patch6502(byte Op);


    void MC_Ab(Pair loc)
    {
        M_LDWORD(loc);
    }

    void MC_Zp(Pair loc)
    {
        loc.setW((char) Op6502(PC.incW()));
    }

    void MC_Zx(Pair loc)
    {
        loc.setW((char) ((char)(Op6502(PC.incW()))+this.X));
    }

    void MC_Zy(Pair loc)
    {
        loc.setW((char) ((char)(Op6502(PC.incW()))+this.Y));
    }

    void MC_Ax(Pair loc)
    {
        M_LDWORD(loc);
        loc.setW((char) (loc.getW()+this.X));
    }

    void MC_Ay(Pair loc)
    {
        M_LDWORD(loc);
        loc.setW((char) (loc.getW()+this.Y));
    }


    void MC_Ix(Pair loc, Pair K)
    {
        K.setW((char) ((byte)Op6502(PC.incW())+this.X));
        loc.setL((char) Op6502(K.incW()));loc.setH((char) Op6502(K.getW()));
    }

    void MC_Iy(Pair loc, Pair K)
    {
        K.setW((char) ((byte)Op6502(PC.incW())));
        loc.setL((char) Op6502(K.incW()));loc.setH((char) Op6502(K.getW()));
        loc.setW((char) (loc.getW()+this.Y));
    }


    byte MR_Ab(Pair J)
    {
        MC_Ab(J);
        return Rd6502(J.getW());
    }

    byte MR_Im()
    {
        return Op6502(PC.incW());
    }

    byte MR_Zp(Pair J)
    {
        MC_Zp(J);
        return Rd6502(J.getW());
    }

    byte MR_Zx(Pair J)
    {
        MC_Zx(J);
        return Rd6502(J.getW());
    }
    byte MR_Zy(Pair J)
    {
        MC_Zy(J);
        return Rd6502(J.getW());
    }
    byte MR_Ax(Pair J)
    {
        MC_Ax(J);
        return Rd6502(J.getW());
    }
    byte MR_Ay(Pair J)
    {
        MC_Ay(J);
        return Rd6502(J.getW());
    }
    byte MR_IX(Pair J,Pair K)
    {
        MC_Ix(J,K);
        return Rd6502(J.getW());
    }
    byte MR_Iy(Pair J,Pair K)
    {
        MC_Iy(J,K);
        return Rd6502(J.getW());
    }
    void M_LDWORD(Pair loc)
    {
        loc.setL((char) Op6502(PC.incW()));
        loc.setH((char)(Op6502(PC.incW())));
    }

}
