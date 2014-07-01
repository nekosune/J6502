package com.nekokittygames.j6502;

/**
 * Pair of either two bytes, or one word
 * Created by nekosune on 01/07/14.
 */
public class Pair {
    private char h;
    private char l;

    /**
     * Creates a new Pair from two bytes
     * @param h High byte
     * @param l Low Byte
     */
    public Pair(char h, char l) {
        this.h = h;
        this.l = l;
    }

    /**
     * Creates anew pair from one word
     * @param w Short
     */
    public Pair(char w) {
        this.setW(w);
    }

    /**
     * gets Low Byte
     * @return Low byte
     */
    public char getL() {
        return l;
    }

    /**
     * Sets Low Byte
     * @param l byte to set
     */
    public void setL(char l) {
        this.l = l;
    }

    /**
     * Gets High Byte
     * @return High byte
     */
    public char getH() {
        return h;
    }

    /**
     * Sets High byte
     * @param h Byte to set as High
     */
    public void setH(char h) {
        this.h = h;
    }


    /**
     * Gets the word
     * @return word
     */
    public char getW() {
        char W;
        W = (char) (h << 8);
        W ^= l;
        return W;
    }

    /**
     * Sets a word
     * @param w word to set
     */
    public void setW(char w) {
        l = (char) ((w & 0x00FF) & 0xFF);
        h = (char) ((w >> 8) & 0xFF);
    }


    /**
     * simulates W++
     * @return W before increment
     */
    public char incW()
    {
        char c=getW();
        setW((char) (c+1));
        return c;
    }

}
