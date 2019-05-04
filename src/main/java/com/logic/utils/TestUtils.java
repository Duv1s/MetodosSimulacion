package com.logic.utils;

import com.logic.KolmogorovSmirnov;
import com.logic.methodTest.MeanTest;
import com.logic.methodTest.VarianceTest;

import java.util.List;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class TestUtils {

    /**
     * Realiza una prueba de medias
     *
     * @param list              lista de números a evaluar
     * @param acceptationGrades grados de aceptación
     * @return true si pasa la prueba, false en caso contrario
     */
    public static boolean testMeans(List<Double> list, int acceptationGrades) {
        return new MeanTest(list, acceptationGrades).isValid();
    }

    /**
     * Realiza una prueba de varianzas
     *
     * @param list              lista de números a evaluar
     * @param acceptationGrades grados de aceptación
     * @return true si pasa la prueba, false en caso contrario
     */
    public static boolean testVariances(List<Double> list, int acceptationGrades) {
        return new VarianceTest(list, acceptationGrades).isValid();
    }

    /**
     * Realiza una prueba de K-S
     * @param list lista de números a evaluar
     * @param acceptationGrades grados de aceptación
     * @return true si pasa la prueba, false en caso contrario
     */
    public static boolean testKS(List<Double> list, int acceptationGrades) {
        KolmogorovSmirnov ks = new KolmogorovSmirnov(acceptationGrades, list);
        ks.calculateFinalValue();
        ks.calculateFrequency();
        ks.calculateFrequencyAcumulated();
        ks.calculatedGetProbability();
        ks.calculatedFrequencyExpected();
        ks.calculatedProbabilityExpected();
        ks.calculatedDiference();
        ks.calculatedDMAX();
        ks.calculatedDMAXP();
        return ks.isPseudo();
    }

    /**
     * Realiza la prueba de medias, varianzas y K-S
     * @param list lista de números a evaluar
     * @param acceptationGrades grados de aceptación
     * @return true si pasa todas las pruebas, false en caso contrario
     */
    public static boolean passAllTests(List<Double> list, int acceptationGrades) {
        return testMeans(list, acceptationGrades) && testVariances(list, acceptationGrades) && testKS(list, acceptationGrades);
    }
}
