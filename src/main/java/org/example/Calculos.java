package org.example;

public class Calculos {

    public Calculos() {
    }

    public double geracaoHidraulicaUsina(double[] pcv, double vmax, double vmin, double cfuga, double cphid, double p, double pinst, double teifh, double iph) {
        double cotaMax = calcularCotaMax(pcv, vmax);
        double cotaMin = calcularCotaMin(pcv, vmin);
        double cotaMed = calculaCotaMed(cotaMax, cotaMin, vmax, vmin);
        double heq = calculaHEQ(cotaMed, cfuga, cphid);
        double prodEquivalente = calculaProdutibilidadeEquivalente(heq, p);
        double engolimentoMaximo = calculaEngolimentoMaximo(pinst, teifh, iph, prodEquivalente);
        double geracaoHid = engolimentoMaximo* p;
        return geracaoHid;
    }

    public static double calcularCotaMax(double[] pcj, double vmax) {
        double resultadoMax = 0.0;
        for (int i = 0; i < pcj.length; i++) {
            resultadoMax = resultadoMax + pcj[i] * Math.pow(vmax, i + 1) / (i + 1);
        }
        return resultadoMax;
    }

    public static double calcularCotaMin(double[] pcj, double vmin) {
        double resultadoMin = 0.0;
        for (int i = 0; i < pcj.length; i++) {
            resultadoMin = resultadoMin + pcj[i] * Math.pow(vmin, i + 1) / (i + 1);
        }
        return resultadoMin;
    }

    public static double calculaCotaMed(double cotaMax, double cotaMin, double vmax, double vmin) {
        return (cotaMax - cotaMin)/(vmax - vmin);
    }

    public static double calculaProdutibilidadeEquivalente(double heq, double p) {
        return heq * p;
    }
    public static double calculaHEQ(double cotaMed, double cfuga, double cphid) {
        return (cotaMed - cfuga) - cphid;
    }

    public static double calculaEngolimentoMaximo(double pinst, double teifh, double iph, double prodEquivalente) {
        return (pinst* ((1.0 - (teifh/100.0)) * (1.0 - (iph/100.0))))/prodEquivalente;
    }

}
