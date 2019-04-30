package com.test;

import org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;


/**
 *
 * @author Yuliamz
 */
public class TestDistributions {
    
    public static void main(String[] args) {
//        NormalDistribution distribution = new NormalDistribution();
//        System.out.println(distribution.cumulativeProbability(0.975));
//        System.out.println(distribution.inverseCumulativeProbability(0.975));
        KolmogorovSmirnovDistribution distribution1 = new KolmogorovSmirnovDistribution(5);
        System.out.println(distribution1.cdf(0.02));

        KolmogorovSmirnovTest kolmogorovSmirnovTest = new KolmogorovSmirnovTest();
        System.out.println(kolmogorovSmirnovTest.cdf(0.02, 5));
        System.out.println(kolmogorovSmirnovTest.cdfExact(2.0,5));
    }
}
