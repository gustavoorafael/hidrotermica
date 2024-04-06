package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculosTest {

    public calculos calculos;
    public EngolimentoMaximos engolimentoMaximos;


    @Test
    void calculoUsina1() {
        // nome da usina
        double[] pcv = {0.75215e3, 0.12284e-1, -0.12569e-5, 0.78525e-10, -0.19786e-14}; // polinomios cota volume
        double vmax = 12792.0; //volume máximo da usina
        double vmin = 2412.0; //volume mínimo da usina
        double cfuga = 696.0; // cota do canal de fuga M
        double cphid = 0.93; //perda hidrelétrica
        double p = 0.009223; //Produtibilidade específica
        double porcentagem = 0.65; //porcentagem do volume util
        double pinst = 510.0; //Potência Instalada
        double teifh = 2.53; // Porcentagem teifh
        double iph = 8.09; // Porcentagem iph
        double engolimentoUsina1 = engolimentoMaximos.geracaoHidraulicaUsina(pcv, vmax, vmin, cfuga, cphid, p, porcentagem, pinst, teifh, iph);
        System.out.println("A geração hidráulica dessa usina é: " + engolimentoUsina1 + "MV/mês");




    }
}