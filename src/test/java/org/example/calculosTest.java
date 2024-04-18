package org.example;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class calculosTest {

    private Calculos calculos;
    private EngolimentoMaximos engolimentoMaximos;
    private UsinasVO usina;
    @BeforeEach
    void setUp() {
        calculos = new Calculos();
        engolimentoMaximos = new EngolimentoMaximos();
    }


    @Test
    void calculoUsina1() {
        UsinasVO usina1 = getUsina1();
        double[] pcv = {usina1.getPcv0(), usina1.getPcv1(), usina1.getPcv2(), usina1.getPcv3(), usina1.getPcv4()};
        double geracaoUsina1 = engolimentoMaximos.geracaoHidraulicaUsina(pcv, usina1.getVmax(), usina1.getVmin(), usina1.getCfuga(), usina1.getCphid(), usina1.getProdesp(), usina1.getPinst(), usina1.getTeif(), usina1.getIph());


        double geracaoResultante = geracaoUsina1 + 1.0;
        System.out.println("A geração hidráulica resultante é:" + geracaoResultante);

    }

    private static UsinasVO getUsina1() {
        UsinasVO usina = new UsinasVO();
        usina.setVmax(792.0);
        usina.setVmin(120.0);
        usina.setPcv0(892.97);
        usina.setPcv1(0.062089);
        usina.setPcv2(-0.00011041);
        usina.setPcv3(0.0000001247);
        usina.setPcv4(-0.000000000055512);
        usina.setProdesp(0.008767);
        usina.setTeif(0.365);
        usina.setIph(2.044);
        usina.setPinst(46.0);
        usina.setCfuga(885.73);
        usina.setCfugacphid(0.09);
        return usina;
    }
}

//    double[] pcv = {0.75215e3, 0.12284e-1, -0.12569e-5, 0.78525e-10, -0.19786e-14}; // polinomios cota volume
//    double vmax = 12792.0; //volume máximo da usina
//    double vmin = 2412.0; //volume mínimo da usina
//    double cfuga = 696.0; // cota do canal de fuga M
//    double cphid = 0.93; //perda hidrelétrica
//    double p = 0.009223; //Produtibilidade específica
//    double pinst = 510.0; //Potência Instalada
//    double teifh = 2.53; // Porcentagem teifh
//    double iph = 8.09; // Porcentagem iph
//    double engolimentoUsina1 = engolimentoMaximos.geracaoHidraulicaUsina(pcv, vmax, vmin, cfuga, cphid, p, pinst, teifh, iph);