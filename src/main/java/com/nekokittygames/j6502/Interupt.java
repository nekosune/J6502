package com.nekokittygames.j6502;

/**
 * Interupts needed by the cpu
 * Created by nekosune on 01/07/14.
 */
public enum Interupt {

    /**
     * No Interupt Required
     */
    INT_NONE(0),
    /**
     * Standard IRQ Interupt
     */
    INT_IRQ(1),
    /**
     * Non Maskable Interupt
     */
    INT_NMI(2),
    /**
     * Exit emulation
     */
    INT_QUIT(3);
    private final byte id;
    Interupt(int id){this.id=(byte)id;}
    public byte getValue(){return id;}
}
