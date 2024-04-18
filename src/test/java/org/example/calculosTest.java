package org.example;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class calculosTest {

    private Calculos calculos;
    private EngolimentoMaximos engolimentoMaximos;
    @BeforeEach
    void setUp() {
        calculos = new Calculos();
        engolimentoMaximos = new EngolimentoMaximos();
    }


    @Test
    void calculoUsina1() {
        double[] pcv = {0.75215e3, 0.12284e-1, -0.12569e-5, 0.78525e-10, -0.19786e-14}; // polinomios cota volume
        double vmax = 12792.0; //volume máximo da usina
        double vmin = 2412.0; //volume mínimo da usina
        double cfuga = 696.0; // cota do canal de fuga M
        double cphid = 0.93; //perda hidrelétrica
        double p = 0.009223; //Produtibilidade específica
        double pinst = 510.0; //Potência Instalada
        double teifh = 2.53; // Porcentagem teifh
        double iph = 8.09; // Porcentagem iph
        double engolimentoUsina1 = engolimentoMaximos.geracaoHidraulicaUsina(pcv, vmax, vmin, cfuga, cphid, p, pinst, teifh, iph);
    }
}