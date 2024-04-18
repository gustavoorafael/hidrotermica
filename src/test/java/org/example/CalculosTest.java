package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculosTest {
    private Calculos calculos;

    /** Dados necessários para calcular a geração hidráulica da usina:
     * Volume máximo
     * Volume mínimo
     * PCV - Polinômios Cota x Volume
     * Produtibilidade específica
     * Porcentagem TEIF
     * Porcentagem IPH
     * Canal de Fuga Médio
     * Potência instalada
     * Perda hidrelétrica
     */

    @BeforeEach
    void setUp() {
        calculos = new Calculos();
    }

    @Test
    void calculoUsina1() {
        UsinasVO usina1 = getUsina1();
        double[] pcv = {usina1.getPcv0(), usina1.getPcv1(), usina1.getPcv2(), usina1.getPcv3(), usina1.getPcv4()};
        double geracaoUsina1 = calculos.geracaoHidraulicaUsina(pcv, usina1.getVmax(), usina1.getVmin(), usina1.getCfuga(), usina1.getCphid(), usina1.getProdesp(), usina1.getPinst(), usina1.getTeif(), usina1.getIph());


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
