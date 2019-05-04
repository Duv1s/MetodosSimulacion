package com.logic.utils;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class SimpleInterval {
    private double lowerLimit, higherLimit;

    public SimpleInterval(double lowerLimit, double higherLimit) {
        this.lowerLimit = lowerLimit;
        this.higherLimit = higherLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public double getHigherLimit() {
        return higherLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public void setHigherLimit(double higherLimit) {
        this.higherLimit = higherLimit;
    }
}
