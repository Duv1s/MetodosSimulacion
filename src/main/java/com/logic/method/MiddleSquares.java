package com.logic.method;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class MiddleSquares {
    private BigInteger seed;
    private int iterations;
    private int seedSize;
    private ArrayList<BigInteger> extracts;
    private final int maxDigits;

    /**
     * Constructor
     *
     * @param seed       Semilla
     * @param iterations cantidad de números a generar
     */
    public MiddleSquares(BigInteger seed, int iterations) {
        this.seed = seed;
        this.iterations = iterations;
        this.seedSize = seed.toString().length();
        this.extracts = new ArrayList<>();
        this.maxDigits = calcMaxDigits();
    }

    /**
     * Constructor
     * Si no se establece un maximo de iteraciones, se establecen como el maximo posible de Integers
     *
     * @param seed semilla
     */
    public MiddleSquares(BigInteger seed) {
        this.seed = seed;
        this.iterations = Integer.MAX_VALUE;
        this.seedSize = seed.toString().length();
        this.extracts = new ArrayList<>();
        this.maxDigits = calcMaxDigits();
    }
    /**
     * Método que calcula el máximo de digitos posbile a partir de la semilla
     * @return extensión maxima de la semilla
     */
    private int calcMaxDigits() {
        String maxNumber = "";
        for (int i = 0; i < seedSize; i++) {
            maxNumber += "9";
        }
        return new BigInteger(maxNumber).pow(2).toString().length();
    }

    public void generateIn(ObservableList<Double> list) {
        extracts.clear();
        BigInteger pow = seed.pow(2);
        BigInteger extract = extract(pow);
        int a = extract.intValue();
        Platform.runLater(() -> list.add(Double.parseDouble("0." + a)));
        for (int i = 0; i < iterations - 1; i++) {
            pow = extract.pow(2);
            extract = extract(pow);
            if (extracts.contains(extract)) {//Si encuentra el valor extraido en la lista de extraidos significa que hay un ciclo e interrumpe la ejecucion
                break;
            }
            extracts.add(extract);
            int s = extract.intValue();
            Platform.runLater(() -> list.add(Double.parseDouble("0." + s)));
        }
    }


    private StringBuilder completeXiSquare(BigInteger number){
        StringBuilder xiSquare = new StringBuilder(number.toString());
        while (xiSquare.length() < maxDigits) {
            xiSquare.insert(0, "0");
        }
        return xiSquare;
    }

    /**
     * Método que permite extraer los numeros del centro una vez se tiene el valor de xi al cuadrado
     * @param number xi al cuadrado.
     * @return Valor del centro a partir del xi al cuadrado
     */
    private BigInteger extract(BigInteger number) {
        StringBuilder xiSquare = completeXiSquare(number);
        int start = (xiSquare.length() - seedSize) / 2;
        return new BigInteger(xiSquare.substring(start, start + (seedSize)));
    }


}
