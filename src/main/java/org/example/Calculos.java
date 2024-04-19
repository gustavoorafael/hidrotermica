package org.example;

public class Calculos {

    public Calculos() {
    }

    public double geracaoHidraulicaUsina(double[] pcv, double vmax, double vmin, double cfuga, double cphid, double p, double pinst, double teifh, double iph, double porcentagem) {
        double cotaMax = calcularCotaMax(pcv, vmax);
        double cotaMin = calcularCotaMin(pcv, vmin);
        double cotaMed = calculaCotaMed(cotaMax, cotaMin, vmax, vmin);
        System.out.println("COTAMED: " + cotaMed + " m" );

        double heq = calculaHEQ(cotaMed, cfuga, cphid);
        System.out.println("Altura de queda equivalente: " + heq + " m" );

        double prodEquivalente = calculaProdutibilidadeEquivalente(heq, p);
        System.out.println("Produtibilidade equivalente: " + prodEquivalente);

        double prodVolumeUtil = prodVolumeUtil(vmax, vmin, porcentagem);
        System.out.println("Produtibilidade associada à altura relativa a " + porcentagem + "% do volume útil da usina: " + prodVolumeUtil + "hm^3");

        double cotaMontante = calculaCotaMontante(pcv, prodVolumeUtil);
        System.out.println("A cota montante é: " + cotaMontante + "m");

        double alturaQueda = calculaAlturaEmMetros(cotaMontante, cfuga, cphid);
        System.out.println("A altura da queda em metros é: " + alturaQueda + "m");

        double produtibilidadeQueda = calculaProdutibilidadeAltura(p, alturaQueda); // p = produtibilidade especifica
        System.out.println("A produtibilidade associada a altura da queda em metros é: " + produtibilidadeQueda + "MW/(m^3/s)");

        double engolimentoMaximo = calculaEngolimentoMaximo(pinst, teifh, iph, prodEquivalente);
        double geracaoHid = engolimentoMaximo* p;

//        double fator = 2.592;
//        double energiaArmazenadaMaxima = calculaEnergiaArmazenadaMaxima(fator);
//        System.out.println("A energia armazenada máxima é: " + energiaArmazenadaMaxima + "MW/mês");
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

    static double calculaAlturaEmMetros(double cotaMontante, double cfuga, double cphid) {
        return (cotaMontante - cfuga) - cphid;
    }
    // se o coeficiente de perda for dado em percentual(%)
    static double calculaProdutibilidadeAltura(double porcentagem, double alturaQueda) {
        return porcentagem*alturaQueda;
    }

    public static double calculaEngolimentoMaximo(double pinst, double teifh, double iph, double prodEquivalente) {
        return (pinst* ((1.0 - (teifh/100.0)) * (1.0 - (iph/100.0))))/prodEquivalente;
    }

    private static double calculaEnergiaArmazenadaMaxima(double fator) {
        double vUtil1 = 12792.0 - 2412.0;
        double vUtil2 = 1120.0 - 974.0;
        double vUtil3 = 1500.0 - 470.0;
        double vUtil4 = 17725.0 - 4669.0;
        double vUtil5 = 17027.0 - 4573.0;
        double vUtil6 = 12540.0 - 7000.0;

        double p1 = 0.9426 ;
        double p2 = 0.6100;
        double p3 = 0.6453;
        double p4 = 0.2826;
        double p5 = 0.6093;
        double p6 = 0.5733;
        double p7 = 1.0369;

        return (1/fator)*( (p1+p2+p3+p4+p5)*(p2+p3+p4+p5)*(p6+p3+p4+p5)*(p7+p3+p4+p5));


    }


}
