package com.logic.method.operation;

import com.logic.method.CongruenceLinear;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class OperationCongruenceLinear {
    private CongruenceLinear congruence;
    private ArrayList<Integer> listXi;
    private ArrayList<Double> listUi;
    private int Xi;
    private double Ui;


    /**
     * constructor de la clase
     *
     * @param congruence_linear
     */
    public OperationCongruenceLinear(CongruenceLinear congruence_linear) {
        this.congruence = congruence_linear;
        this.Xi = congruence_linear.getXo();
        listUi = new ArrayList<>();
        listXi = new ArrayList<>();
    }

    /**
     * este metodo permite calcular un Xi seg�n los parametros de entrada, a,b y m, este valor se guarda en un vector
     */
    private void calculateModuloXi() {
        this.Xi = (congruence.getA() * this.Xi + congruence.b) % congruence.getM();
        listXi.add(this.Xi);
    }

    /**
     * este metodo permite calcular los Ui seg�n un datos Xi y el valor de m - 1, este valor se guarda en un vector
     */
    private void calculateUi(ObservableList<Double> doubles) {
        this.Ui = (this.Xi) / (congruence.getM() - 1.0);
        doubles.add(Ui);
        listUi.add(Ui);
    }

    /**
     * este metodo permite generar tantas iteraciones  de los metos de calcular Xi y Ui como deseee el usuario
     */
    public void iteration(ObservableList<Double> doubles) {
        for (int i = 0; i < congruence.getIteration(); i++) {
            calculateModuloXi();
            calculateUi(doubles);
        }
    }


    /**
     * set y gets
     *
     * @return
     */
    public CongruenceLinear getCongruence() {
        return congruence;
    }

    public void setCongruence(CongruenceLinear congruence) {
        this.congruence = congruence;
    }

    public ArrayList<Integer> getListXi() {
        return listXi;
    }

    public void setListXi(ArrayList<Integer> listXi) {
        this.listXi = listXi;
    }

    public ArrayList<Double> getListUi() {
        return listUi;
    }

    public void setListUi(ArrayList<Double> listUi) {
        this.listUi = listUi;
    }

    public int getXi() {
        return Xi;
    }

    public void setXi(int xi) {
        Xi = xi;
    }

    public double getUi() {
        return Ui;
    }

    public void setUi(Float ui) {
        Ui = ui;
    }


}
