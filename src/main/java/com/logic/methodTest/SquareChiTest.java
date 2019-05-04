package com.logic.methodTest;

import com.logic.utils.SimpleInterval;

import java.util.ArrayList;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class SquareChiTest {
    private ArrayList<Double> listNumber;
    private double aceptationNumber;
    private int amountIntervals;
    private double max;
    private double min;
    private ArrayList<SimpleInterval> intervalList;
    private ArrayList<Integer> oFreq;
    private double eFreq;
    private ArrayList <Double> squareChiList;
    private double summationChi;

    public SquareChiTest(int amountIntervals, double aceptationNumber, ArrayList<Double> listNumber){
        this.amountIntervals = amountIntervals;
        this.aceptationNumber = aceptationNumber;
        this.listNumber = listNumber;
        this.oFreq = new ArrayList<>();
        this.squareChiList = new ArrayList<>();
    }

    public void processSquareChi(){
        setMaxAndMin();
        setIntervals();
        setFrequency();
        calculateSquareChi();
    }

    private void setMaxAndMin() {
        this.max = getMax();
        this.min = getMin();
    }

    private double getMax() {
        double aux = this.listNumber.get(0);
        for (int i = 1; i < this.listNumber.size(); i++) {
            if(this.listNumber.get(i) > aux)
                aux = this.listNumber.get(i);
        }
        return aux;
    }

    private double getMin() {
        double aux = this.listNumber.get(0);
        for (int i = 1; i < this.listNumber.size(); i++) {
            if(this.listNumber.get(i) < aux)
                aux = this.listNumber.get(i);
        }
        return aux;
    }

    private void setIntervals() {
        SimpleInterval aux;
        this.intervalList.add(new SimpleInterval(this.min, (this.min + (this.max - this.max) / 8)));
        for (int i = 1; i < this.amountIntervals; i++){
            aux = this.intervalList.get(i - 1);
            this.intervalList.add(new SimpleInterval(aux.getHigherLimit(), (aux.getHigherLimit() + (aux.getHigherLimit() - aux.getLowerLimit()))));
        }
    }

    private void setFrequency() {
        countFrequency();
        this.eFreq = this.listNumber.size() / this.amountIntervals;
    }

    private void countFrequency() {
        int frecuency = 0;
        for (SimpleInterval interval : this.intervalList) {
            for (Double number : this.listNumber) {
                if(interval.getLowerLimit() <= number && interval.getHigherLimit() >= number)
                    frecuency++;
            }
            this.oFreq.add(frecuency);
            frecuency = 0;
        }
    }

    private void calculateSquareChi() {
        for (Integer frecuency : this.oFreq) {
            this.squareChiList.add((Math.pow((frecuency - this.eFreq), 2)) / this.eFreq);
        }
        for (Double number : this.squareChiList) {
            this.summationChi += number;
        }
    }


}
