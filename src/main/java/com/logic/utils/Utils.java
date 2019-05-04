package com.logic.utils;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
public class Utils {
    
    public static final NormalDistribution NORMAL_DISTRIBUTION = new NormalDistribution();

    /**
     * Verifica si la seed ingresada es válida
     *
     * @param seed semilla
     * @return true si es válida, false en caso contrario
     */
    public static boolean isValidSeed(String seed) {
        if (!isNumber(seed)) return false;
        int seedSize = new BigInteger(seed).toString().length();
        return seedSize >= 4 && seedSize % 2 == 0;
    }

    /**
     * Verifíca si la cantidad de iteraciones es valida
     * @param iterate cantidad de iteraciones
     * @return true si es válido, false en caso contrario
     */
    public static boolean isValidIterationsNumber(String iterate) {
        if (!isNumber(iterate)) return false;
        return Integer.parseInt(iterate)>0;
    }

    public static List<String> convertDoubleToString(List<Double> nums) {
        List<String> strings = new ArrayList<>();
        nums.forEach(e -> strings.add(e.toString()));
        return strings;
    }

    public static double[] convertDoubleToDoubleArray(List<Double> nums) {
        double[] ds = new double[nums.size()];
        for (int i = 0; i < nums.size(); i++) ds[i] = ds[i] = nums.get(i);
        return ds;
    }

    /**
     * verifica si la cadena es un numero
     * @param num cadena
     * @return true si es válido, false en caso contrario
     */
    private static boolean isNumber(String num) {
        if (num == null) return false;
        if (num.isEmpty()) return false;
        return num.chars().allMatch(Character::isDigit);
    }
}
