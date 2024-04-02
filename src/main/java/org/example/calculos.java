package org.example;

public class calculos {

    public static void main(String[] args) {
        double[] pcv = {0.75215e3, 0.12284e-1, -0.12569e-5, 0.78525e-10, -0.19786e-14};
        double vmax = 12792.0;
        double vmin = 2412.0;

        double cotaMax = calcularCotaMax(pcv, vmax);
//        System.out.println("CotaMax: " + cotaMax);

        double cotaMin = calcularCotaMin(pcv, vmin);
//        System.out.println("CotaMin: " + cotaMin);

        double cotaMed = calculaCotaMed(cotaMax, cotaMin, vmax, vmin);
        System.out.println("COTAMED: " + cotaMed + "m");

        double cfuga = 696.0; // cota do canal de fuga M
        double cphid = 0.93; //perda hidrelétrica
        double heq = calculaHEQ(cotaMed, cfuga, cphid);
        System.out.println("HEQ: " + heq + "m");

        double p = 0.009223; //Produtibilidade específica
        double prodEquivalente = calculaProdutibilidadeEquivalente(heq, p);
        System.out.println("Produtibilidade Equivalente: " + prodEquivalente + "MW/(m^3/s)");

        double porcentagem = 0.65; //porcentagem do volume util
        double prodVolumeUtil = prodVolumeUtil(vmax, vmin, porcentagem);
        System.out.println("Produtibilidade associada à altura relativa a " + porcentagem + "% do volume útil da usina: " + prodVolumeUtil + "hm^3");

        double cotaMontante = calculaCotaMontante(pcv, prodVolumeUtil);
        System.out.println("A cota montante é: " + cotaMontante + "m");

        double alturaQueda = calculaAlturaEmMetros(cotaMontante, cfuga, cphid);
        System.out.println("A altura da queda em metros é: " + alturaQueda + "m");

        double produtibilidadeQueda = calculaProdutibilidadeAltura(p, alturaQueda); // p = produtibilidade especifica
        System.out.println("A produtibilidade associada a altura da queda em metros é: " + produtibilidadeQueda + "MW/(m^3/s)");


        double pinst = 510.0; //Potência Instalada
        double teifh = 2.53; // Porcentagem teifh
        double iph = 8.09; // Porcentagem iph
        double engolimentoMaximo = calculaEngolimentoMaximo(pinst, teifh, iph, prodEquivalente);
        System.out.println("O engolimento máximo é: " + engolimentoMaximo + "m^3/s");
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

    private static double prodVolumeUtil(double vmax, double vmin, double porcentagem) {
        return porcentagem*(vmax - vmin) + vmin;
    }

    public static double calculaCotaMontante(double[] pcj, double prodVolumeUtil) {
        double cotaMontante = 0.0;
        for (int i = 0; i < pcj.length; i++) {
            cotaMontante = cotaMontante + pcj[i] * Math.pow(prodVolumeUtil, i) ;
        }
        return cotaMontante;
    }

    // Calculo da altura da queda

    // se o coeficiente de perda for dado em metros(m)
    private static double calculaAlturaEmMetros(double cotaMontante, double cfuga, double cphid) {
        return (cotaMontante - cfuga) - cphid;
    }
    // se o coeficiente de perda for dado em percentual(%)
    private static double calculaProdutibilidadeAltura(double porcentagem, double alturaQueda) {
        return porcentagem*alturaQueda;
    }

    private static double calculaEngolimentoMaximo(double pinst, double teifh, double iph, double prodEquivalente) {
        return (pinst* ((1.0 - (teifh/100.0)) * (1.0 - (iph/100.0))))/prodEquivalente;
    }

}