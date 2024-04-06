package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class calculosTest {

    private Calculos calculos;
    private EngolimentoMaximos engolimentoMaximos;

    // Adicione um método de setup para inicializar as variáveis antes dos testes
    @BeforeEach
    void setUp() {
        calculos = new Calculos(); // Supondo que Calculos é a classe que contém o método que você deseja testar
        engolimentoMaximos = new EngolimentoMaximos(); // Supondo que EngolimentoMaximos é a classe que você está testando
    }


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




    }
}