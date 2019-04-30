package com.logic;

/**
 * @author Walter
 */
public class CongruenceLinear {
    public int b;
    private int a;
    private int m;
    private int Xo;
    private int iteration;

    /**
     * este metodo es el constructor de la clase
     *
     * @param a
     * @param b
     * @param m
     * @param xo
     * @param iteration
     */
    public CongruenceLinear(int a, int b, int m, int xo, int iteration) {
        super();
        this.a = a;
        this.b = b;
        this.m = m;
        this.Xo = xo;
        this.iteration = iteration;
    }

    /**
     * gets y sets
     *
     * @return
     */
    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getXo() {
        return Xo;
    }

    public void setXo(int xo) {
        Xo = xo;
    }


}
