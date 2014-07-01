package com.nekokittygames.j6502;

import junit.framework.TestCase;
import org.junit.Assert;

public class PairTest extends TestCase {

    public void testGetL() throws Exception {
        Pair pair = new Pair((char) 1, (char) 2);
        Assert.assertEquals("Simple Get broke", 2, pair.getL());
        pair.setW((char) 0x9012);
        Assert.assertEquals("W set broke", 0x12, pair.getL());
    }

    public void testSetL() throws Exception {
        Pair pair = new Pair((char) 1, (char) 2);
        pair.setL((char) 3);
        Assert.assertEquals("Simple Set broke", 3, pair.getL());
        Assert.assertEquals("W set broke", 0x103, pair.getW());
    }

    public void testGetH() throws Exception {
        Pair pair = new Pair((char) 1, (char) 2);
        Assert.assertEquals("Simple Set broke", 1, pair.getH());
        pair.setW((char) 0x9012);
        Assert.assertEquals("W set broke", 0x90, pair.getH());

    }

    public void testSetH() throws Exception {
        Pair pair = new Pair((char) 1, (char) 2);
        pair.setH((char) 3);
        Assert.assertEquals("Simple Set broke", 3, pair.getH());
        Assert.assertEquals("W set broke", 0x302, pair.getW());
    }

    public void testGetW() throws Exception {
        Pair pair = new Pair((char) 1, (char) 2);
        Assert.assertEquals("W get broke", 0x102, pair.getW());
    }

    public void testSetW() throws Exception {
        Pair pair = new Pair((char) 1, (char) 2);
        pair.setW((char) 0x303);
        Assert.assertEquals("W get broke", 0x303, pair.getW());
        Assert.assertEquals("H get broke", 0x03, pair.getH());
        Assert.assertEquals("L get broke", 0x03, pair.getL());
    }

    public void testIncW() throws Exception
    {
        Pair pair = new Pair((char) 1, (char) 2);
        pair.setW((char) 0x303);
        char c=pair.incW();
        Assert.assertEquals("incW returns original W",0x303,c);
        Assert.assertEquals("W is incremented after incW",0x304,pair.getW());
    }
}