package com.nekokittygames.j6502;

/**
 * Created by nekosune on 01/07/14.
 */
public class Pair {
    public char h;
    public char l;

    public Pair(char h,char l)
    {
        this.h=h;
        this.l=l;
    }
    public Pair(char w)
    {
        this.setW(w);
    }
    public char getL() {
        return l;
    }

    public void setL(char l) {
        this.l = l;
    }
    public char getH()
    {
        return h;
    }
    public void setH(char h)
    {
        this.h=h;
    }


    public char getW() {
        char W=0;
        W=(char)(h<<8);
        W^=l;
        return W;
    }

    public void setW(char w) {
        l=(char)((w&0x00FF)&0xFF);
        h=(char)((w >> 8)&0xFF);
    }

}
