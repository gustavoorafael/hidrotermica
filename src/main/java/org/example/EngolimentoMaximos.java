package org.example;

public class EngolimentoMaximos {

    public calculos calculos;

    public double geracaoHidraulicaUsina(double[] pcv, double vmax, double vmin, double cfuga, double cphid, double p, double porcentagem, double pinst, double teifh, double iph) {
        double cotaMax = calculos.calcularCotaMax(pcv, vmax);
        double cotaMin = calculos.calcularCotaMin(pcv, vmin);
        double cotaMed = calculos.calculaCotaMed(cotaMax, cotaMin, vmax, vmin);
        System.out.println("COTAMED: " + cotaMed + "m");

        double heq = calculos.calculaHEQ(cotaMed, cfuga, cphid);
        System.out.println("HEQ: " + heq + "m");

        double prodEquivalente = calculos.calculaProdutibilidadeEquivalente(heq, p);
        System.out.println("Produtibilidade Equivalente: " + prodEquivalente + "MW/(m^3/s)");

        double prodVolumeUtil = calculos.prodVolumeUtil(vmax, vmin, porcentagem);
        System.out.println("Produtibilidade associada à altura relativa a " + porcentagem + "% do volume útil da usina: " + prodVolumeUtil + "hm^3");

        double cotaMontante = calculos.calculaCotaMontante(pcv, prodVolumeUtil);
        System.out.println("A cota montante é: " + cotaMontante + "m");

        double alturaQueda = calculos.calculaAlturaEmMetros(cotaMontante, cfuga, cphid);
        System.out.println("A altura da queda em metros é: " + alturaQueda + "m");

        double produtibilidadeQueda = calculos.calculaProdutibilidadeAltura(p, alturaQueda); // p = produtibilidade especifica
        System.out.println("A produtibilidade associada a altura da queda em metros é: " + produtibilidadeQueda + "MW/(m^3/s)");

        double engolimentoMaximo = calculos.calculaEngolimentoMaximo(pinst, teifh, iph, prodEquivalente);
        System.out.println("O engolimento máximo é: " + engolimentoMaximo + "m^3/s");

        double geracaoHid = engolimentoMaximo* p;
        System.out.println("A geração hidráulica dessa usina é: " + geracaoHid + "MV/mês");
        return geracaoHid;
    }

}
