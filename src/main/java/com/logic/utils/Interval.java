package com.logic.utils;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class Interval {
    private double initialValue;
    private double finalValue;
    private int frequencyGet;
    private double accumulatedFrequency;
    private double probabilityGet;
    private double expectedFrequency;
    private double expectedProbability;
    private double difProb;

    public Interval() {
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }

    public int getFrequencyGet() {
        return frequencyGet;
    }

    public void setFrequencyGet(int frequencyGet) {
        this.frequencyGet = frequencyGet;
    }

    public double getAccumulatedFrequency() {
        return accumulatedFrequency;
    }

    public void setAccumulatedFrequency(double accumulatedFrequency) {
        this.accumulatedFrequency = accumulatedFrequency;
    }

    public double getProbabilityGet() {
        return probabilityGet;
    }

    public void setProbabilityGet(double probabilityGet) {
        this.probabilityGet = probabilityGet;
    }

    public double getExpectedFrequency() {
        return expectedFrequency;
    }

    public void setExpectedFrequency(double expectedFrequency) {
        this.expectedFrequency = expectedFrequency;
    }

    public double getExpectedProbability() {
        return expectedProbability;
    }

    public void setExpectedProbability(double expectedProbability) {
        this.expectedProbability = expectedProbability;
    }

    public double getDifProb() {
        return difProb;
    }

    public void setDifProb(double difProb) {
        this.difProb = difProb;
    }
}
