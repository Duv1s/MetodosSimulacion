package com.logic.methodTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class PokerTest {
    private ArrayList<Double> listNumber;
    private ArrayList<Double> listNumberNormalized;
    private double aceptationNumber;
    private int DF, OP, TP, ST, FH, P, Q;
    private ArrayList<Double> listOfProbabilities;
    private double max;
    private double min;
    private double summationProbabilities;

    public PokerTest(double aceptationNumber, ArrayList<Double> numberList) {
        this.aceptationNumber = aceptationNumber;
        this.listNumber = numberList;
        this.listNumberNormalized = new ArrayList<>();
        this.listOfProbabilities =  new ArrayList<>();
        this.DF = 0;
        this.OP = 0;
        this.TP = 0;
        this.ST = 0;
        this.FH = 0;
        this.P = 0;
        this.Q = 0;
    }

    public void processPokerTest() {
        setMaxAndMin();
        normalizeNumbers();
        countDecimals();
        calculateProbabilities();
    }

    private void setMaxAndMin() {
        this.max = getMax();
        this.min = getMin();
    }

    private double getMax() {
        double aux = this.listNumber.get(0);
        for (int i = 1; i < this.listNumber.size(); i++) {
            if (this.listNumber.get(i) > aux)
                aux = this.listNumber.get(i);
        }
        return aux;
    }

    private double getMin() {
        double aux = this.listNumber.get(0);
        for (int i = 1; i < this.listNumber.size(); i++) {
            if (this.listNumber.get(i) < aux)
                aux = this.listNumber.get(i);
        }
        return aux;
    }

    private void normalizeNumbers() {
        for (Double number : this.listNumber) {
            this.listNumberNormalized.add(new BigDecimal((number - this.min) / (this.max - this.min)).setScale(5, RoundingMode.UP).doubleValue());
        }
    }

    private void countDecimals() {
        for (Double number : this.listNumberNormalized) {
            ArrayList<String> types = new ArrayList<>();
            ArrayList<Integer> listOfDecimals = getListOfDecimals(String.valueOf(number).substring(2));
            if (listOfDecimals.size() > 1) {
                int times = 1;
                for (int i = 0; i < listOfDecimals.size(); i++) {
                    int decimal = listOfDecimals.get(i);
                    listOfDecimals.remove(i);
                    for (int j = 0; j < listOfDecimals.size(); j++) {
                        if (decimal == listOfDecimals.get(j)) {
                            listOfDecimals.remove(j);
                            times++;
                            j = -1;
                        }
                    }
                    if (times != 1) {
                        types.add(classifyAmmountOfTimes(times));
                        times = 1;
                    }
                    i = -1;
                }
            } else {
                types.add(classifyAmmountOfTimes(5));
            }
            switch (types.size()) {
                case 0:
                    this.DF++;
                    break;
                case 1:
                    if (types.get(0).equals("O"))
                        this.OP++;
                    if (types.get(0).equals("S"))
                        this.ST++;
                    if (types.get(0).equals("P"))
                        this.P++;
                    if (types.get(0).equals("Q"))
                        this.Q++;
                    break;
                case 2:
                    if (types.get(0).equals("O") && types.get(1).equals("O"))
                        this.TP++;
                    if (types.get(0).equals("O") && types.get(1).equals("S"))
                        this.FH++;
                    if (types.get(0).equals("S") && types.get(1).equals("O"))
                        this.FH++;
                    break;
            }
        }
}

    private String classifyAmmountOfTimes(int times) {
        switch (times) {
            case 2:
                return "O";
            case 3:
                return "S";
            case 4:
                return "P";
            case 5:
                return "Q";
        }
        return null;
    }

    private ArrayList<Integer> getListOfDecimals(String decimals) {
        ArrayList<Integer> listOfDecimals = new ArrayList<>();
        for (int i = 0; i < decimals.length(); i++) {
            listOfDecimals.add(Integer.parseInt(decimals.charAt(i) + ""));
        }
        return listOfDecimals;
    }

    private void calculateProbabilities() {
        fillListOfProbabilities();
        this.summationProbabilities += (this.listOfProbabilities.get(0) - this.DF);
        this.summationProbabilities += (this.listOfProbabilities.get(1) - this.OP);
        this.summationProbabilities += (this.listOfProbabilities.get(2) - this.TP);
        this.summationProbabilities += (this.listOfProbabilities.get(3) - this.ST);
        this.summationProbabilities += (this.listOfProbabilities.get(4) - this.FH);
        this.summationProbabilities += (this.listOfProbabilities.get(5) - this.P);
    }

    private void fillListOfProbabilities() {
        this.listOfProbabilities.add(0.3024 * this.listNumber.size());
        this.listOfProbabilities.add(0.5040 * this.listNumber.size());
        this.listOfProbabilities.add(0.1080 * this.listNumber.size());
        this.listOfProbabilities.add(0.0720 * this.listNumber.size());
        this.listOfProbabilities.add(0.0090 * this.listNumber.size());
        this.listOfProbabilities.add(0.0045 * this.listNumber.size());
    }

    public double getSummationProbabilities() {
        return summationProbabilities;
    }
}
