package com.logic.method.operation;

import com.logic.utils.TestUtils;

import java.util.ArrayList;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class OperationUniformDistribution {

    private int limitA;
    private int limitB;
    private int quantityNumber;
    private ArrayList<Double> listNumber;
    private ArrayList<Double> listNumberPseudo;
    private boolean numFinded = false;

    public OperationUniformDistribution(int limitA, int limitB, int quantityNumber) {
        this.limitA = limitA;
        this.limitB = limitB;
        this.quantityNumber = quantityNumber;
        listNumber = new ArrayList<>();
        listNumberPseudo = new ArrayList<>();
    }

    public void generatePseudo() {
        for (Double aDouble : this.listNumber) {
            this.listNumberPseudo.add(limitA + ((limitB - limitA) * aDouble));
        }
    }

    /**
     * metodo que permite generar numero pseudoaleatorios de manera uniforme
     */
    public void generateNumber() {
        while (!numFinded) {
            listNumber.clear();
            for (int i = 0; i < quantityNumber; i++) listNumber.add(Math.random());
            numFinded = TestUtils.passAllTests(listNumber, 95);
        }
    }
    public ArrayList<Double> getListNumber() {
        return listNumber;
    }

    public ArrayList<Double> getListNumberPseudo() {
        return listNumberPseudo;
    }
}
