package com.test;

import com.logic.KolmogorovSmirnov;

import java.util.ArrayList;

public class TestKS {
    public static void main(String[] args) {

        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0.907358); list.add(0.451980); list.add(0.504590); list.add(0.315761); list.add(0.679818);
        list.add(0.456431); list.add(0.702114); list.add(0.494662); list.add(0.525931); list.add(0.464109);
        list.add(0.170918); list.add(0.197608); list.add(0.088933); list.add(0.330435); list.add(0.459992);
        list.add(0.554531); list.add(0.340813); list.add(0.004574); list.add(0.201495); list.add(0.500830);
        list.add(0.558182); list.add(0.484343); list.add(0.668186); list.add(0.732096); list.add(0.150426);
        list.add(0.380580); list.add(0.561029); list.add(0.112763); list.add(0.444414); list.add(0.264395);
        list.add(0.224170); list.add(0.132070); list.add(0.115270); list.add(0.319913); list.add(0.346433);
        list.add(0.604587); list.add(0.487568); list.add(0.420049); list.add(0.327748); list.add(0.634632);
        list.add(0.753705); list.add(0.236357); list.add(0.731344); list.add(0.202040); list.add(0.438610);
        list.add(0.558711); list.add(1.000000); list.add(0.808971); list.add(0.999726); list.add(0.000000);

        /*0.907358	0.451980	0.504590	0.315761	0.679818
        0.456431	0.702114	0.494662	0.525931	0.464109
        0.170918	0.197608	0.088933	0.330435	0.459992
        0.554531	0.340813	0.004574	0.201495	0.500830
        0.558182	0.484343	0.668186	0.732096	0.150426
        0.380580	0.561029	0.112763	0.444414	0.264395

        0.224170	0.132070	0.115270	0.319913	0.346433
        0.604587	0.487568	0.420049	0.327748	0.634632
        0.753705	0.236357	0.731344	0.202040	0.438610
        0.558711	1.000000	0.808971	0.999726	0.000000*/



        KolmogorovSmirnov smirnov = new KolmogorovSmirnov(95, list);
        smirnov.calculateFinalValue();
        smirnov.calculateFrequency();
        smirnov.calculateFrequencyAcumulated();
        smirnov.calculatedGetProbability();
        smirnov.calculatedFrequencyExpected();
        smirnov.calculatedProbabilityExpected();
        smirnov.calculatedDiference();


       for (int i = 0; i < smirnov.getListInterval().size(); i++) {
            System.out.println("No" + i+1 + " - inicial: " +smirnov.getListInterval().get(i).getInitialValue() +  "  -  final: " +
                    smirnov.getListInterval().get(i).getFinalValue() + " Frecuancia: " + " - " + smirnov.getListInterval().get(i).getFrequencyGet()
            + " Frrecuencia A: " + " - " + smirnov.getListInterval().get(i).getAccumulatedFrequency() + " probabilidad obtenida: " + " - "
            + smirnov.getListInterval().get(i).getProbabilityGet() + " - " + " Frecuencia esperada: " + smirnov.getListInterval().get(i).getExpectedFrequency()
            + " - " + " Probabiliadad esperada: " + smirnov.getListInterval().get(i).getExpectedProbability() + " - " + "Diferencia prob: " +
                    smirnov.getListInterval().get(i).getDifProb());
        }
        System.out.println("DMAX: " + smirnov.calculatedDMAX() + "DMAXP: " + smirnov.calculatedDMAXP());
        System.out.println(smirnov.isPseudo());


        //System.out.println(smirnov.isPseudo());


    }
}
