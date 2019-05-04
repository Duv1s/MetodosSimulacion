package com.test;

import com.logic.method.operation.OperationUniformDistribution;

import java.util.ArrayList;

public class TestUniformDistribution {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>();
        OperationUniformDistribution uniformDistribution = new OperationUniformDistribution(7,16,15);

        uniformDistribution.getListNumber().add(0.1214);
        uniformDistribution.getListNumber().add(0.4267);
        uniformDistribution.getListNumber().add(0.1379);
        uniformDistribution.getListNumber().add(0.7385);
        uniformDistribution.getListNumber().add(0.8432);
        uniformDistribution.getListNumber().add(0.5801);
        uniformDistribution.getListNumber().add(0.0100);
        uniformDistribution.getListNumber().add(0.3703);
        uniformDistribution.getListNumber().add(0.7205);
        uniformDistribution.getListNumber().add(0.5427);
        uniformDistribution.getListNumber().add(0.3795);
        uniformDistribution.getListNumber().add(0.7389);
        uniformDistribution.getListNumber().add(0.1266);
        uniformDistribution.getListNumber().add(0.6429);
        uniformDistribution.getListNumber().add(0.8298);
        uniformDistribution.generatePseudo();
        System.out.println("Número aleatorio: " + uniformDistribution.getListNumber());
        System.out.println(" pseudoaleatorio: " + uniformDistribution.getListNumberPseudo());


    }
}
