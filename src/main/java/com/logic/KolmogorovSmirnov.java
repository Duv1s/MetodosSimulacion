package com.logic;

import com.logic.utils.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class KolmogorovSmirnov {
    private int aceptatio;
    private double alpha;
    private int numbersAmount;
    private double MAX;
    private double MIN;
    private double medium;
    private List<Double> listNumber;
    private static final double NUMBER_INTERVALS = 10.0;
    private ArrayList<Interval> listInterval;



    public KolmogorovSmirnov(int aceptatio, List<Double> listNumber) {
        Interval interval = new Interval();
        this.listInterval = new ArrayList<>();
        this.aceptatio = aceptatio;
        this.listNumber = listNumber;
        this.alpha = (100 - aceptatio)/100.0;
        this.medium = average();
        this.numbersAmount = count();
        this.MAX = MAX();
        this.MIN = MIN();
        addList();
    }

    public boolean isPseudo(){
        return calculatedDMAX() < calculatedDMAXP();
    }

    public double calculatedDMAXP(){
     return 1.36/(Math.sqrt(this.numbersAmount));
    }

    /**
     * metodo para calcular la diferencia maxima
     */
    public double calculatedDMAX(){
        double aux =0.0;
        for (Interval interval: this.listInterval
             ) {
            if(interval.getDifProb() > aux){
                aux= interval.getDifProb();
            }
        }
        return aux;
    }

    /**
     * metodo para calcular la diferencia
     */
    public void calculatedDiference(){
        for (Interval interval: this.listInterval
             ) {
            interval.setDifProb(Math.abs(interval.getExpectedProbability() - interval.getProbabilityGet()));
        }
    }

    /**
     * metodo para generar la probabilidad esperada
     */
    public void calculatedProbabilityExpected(){
        double aux = 0.1;
        for (Interval interval: this.listInterval) {
            interval.setExpectedProbability(aux);
            aux = aux + 0.1;
        }
    }

    /**
     * metodo que calcula la frecuencia esperada
     */
    public void calculatedFrequencyExpected(){
        for (Interval interval: this.listInterval) {
            interval.setExpectedFrequency(count()/NUMBER_INTERVALS);
        }
    }

    /**
     * metodo que permite calcular la probabilidad obtenida
     */
    public void calculatedGetProbability(){
        for (Interval interval: this.listInterval) {
            interval.setProbabilityGet(interval.getAccumulatedFrequency()/count());
        }
    }

    /**
     * este metodo permite calcular la frecuencia acumulada
     */
    public void calculateFrequencyAcumulated(){
        this.listInterval.get(0).setAccumulatedFrequency(this.listInterval.get(0).getFrequencyGet());
        for (int i = 1; i <this.listInterval.size() ; i++) {
            this.listInterval.get(i).setAccumulatedFrequency(this.listInterval.get(i).getFrequencyGet() +
                    this.listInterval.get(i-1).getAccumulatedFrequency());
        }
    }

    /**
     * este metodo permite calcular la frecuencia de los datos en los inyervalos
     */
    public void calculateFrequency(){
        for (Interval interval: this.listInterval) {
            for (Double aDouble: this.listNumber) {
                if (aDouble < interval.getFinalValue() && aDouble >= interval.getInitialValue()){
                    interval.setFrequencyGet(interval.getFrequencyGet()+1);
                }
            }
        }
    }

    /**
     * metodo que permite calcular el valor inicial y final de los intervalos
     */
    public void calculateFinalValue(){
        double aux2= 0.0;
        for (int i = 0; i < listInterval.size()-1; i++) {
          double aux = listInterval.get(i).getInitialValue();
            aux2 = aux + ((this.MAX - this.MIN) / NUMBER_INTERVALS);
                listInterval.get(i).setFinalValue(aux2);
                listInterval.get(i + 1).setInitialValue(aux2);
        }
        aux2 = listInterval.get(listInterval.size()-1).getInitialValue() + ((this.MAX - this.MIN) / NUMBER_INTERVALS);
        listInterval.get(listInterval.size()-1).setFinalValue(aux2);
    }

    private void addList() {
        for (int i = 0; i < NUMBER_INTERVALS ; i++) {
            this.listInterval.add(new Interval());
        }
        listInterval.get(0).setInitialValue(this.MIN);
    }

    /**
     * metodo que devuelve la media
     * @return
     */
    private Double average() {
        return listNumber.stream().mapToDouble(a->a).average().orElse(Double.NaN);
    }

    /**
     * metodo que permite contar la cantidad de elementos de un vector
     * @return
     */
    private int count() {
        return this.listNumber.size();
    }

    /**
     * metodo que permite identificar el numero mayor de un vector
     * @return
     */
    private double MAX() {
        return listNumber.stream().mapToDouble(a->a).max().orElse(Double.NaN);
    }

    /**
     * metodo que permite identificar el numero menor de un vector
     * @return
     */
    private double MIN() {
        return listNumber.stream().mapToDouble(a->a).min().orElse(Double.NaN);
    }

    public int getAceptatio() {
        return aceptatio;
    }

    public void setAceptatio(int aceptatio) {
        this.aceptatio = aceptatio;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public int getNumbersAmount() {
        return numbersAmount;
    }

    public void setNumbersAmount(int numbersAmount) {
        this.numbersAmount = numbersAmount;
    }

    public double getMAX() {
        return MAX;
    }

    public void setMAX(double MAX) {
        this.MAX = MAX;
    }

    public double getMIN() {
        return MIN;
    }

    public void setMIN(double MIN) {
        this.MIN = MIN;
    }

    public double getMedium() {
        return medium;
    }

    public void setMedium(double medium) {
        this.medium = medium;
    }

    public List<Double> getListNumber() {
        return listNumber;
    }

    public void setListNumber(ArrayList<Double> listNumber) {
        this.listNumber = listNumber;
    }

    public ArrayList<Interval> getListInterval() {
        return listInterval;
    }

    public void setListInterval(ArrayList<Interval> listInterval) {
        this.listInterval = listInterval;
    }
}
