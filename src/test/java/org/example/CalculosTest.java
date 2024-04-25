package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class CalculosTest {
    private Calculos calculos;

    /** Dados necessários para calcular a geração hidráulica das usinas:
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
    void calculoGeracaoHidraulica() {
        double geracaoResultante = 0.0;
        List<String> listaValoresUsinas = preencheLista();
        int usinaIndex = 1;
        List<Integer> listaPorcentagem = new ArrayList<>(); // Volume inicial será essas porcentagens do volume máximo.
        listaPorcentagem.add(100);
        listaPorcentagem.add(75);
        listaPorcentagem.add(65);
        listaPorcentagem.add(50);
        listaPorcentagem.add(30);

        for (String s : listaValoresUsinas) {
            System.out.println("Usina " + usinaIndex);

            for(int porcentagem : listaPorcentagem){
                double geracaoUsina = getGeracaoUsina(s, porcentagem);
                System.out.println("Cálculo feito para " + porcentagem + "% do volume máximo da usina " + usinaIndex );
                System.out.println("A geração hidráulica da usina " + usinaIndex + " é: " + geracaoUsina + " MV/mês");
                System.out.println();
            }
            usinaIndex++;

            double geracaoUsina100 = getGeracaoUsina(s, 100);
            geracaoResultante += geracaoUsina100;
            System.out.println();
            System.out.println("A geração hidráulica resultante das usinas EMBORCACAO, NOVA PONTE, MIRANDA, CORUMBA I, ITUMBIARA, CACHOEIRA DOURADA e SAO SIMAO é: " + geracaoResultante + " MV/mês");
            System.out.println();
        }

        double energiaArmazenadaMaxima = calculos.calculaEnergiaArmazenadaMaxima(2.592);
        System.out.println("A energia armazenada máxima é: " + energiaArmazenadaMaxima + "MW/mês");
        System.out.println();

        double energiaControlavel = calculos.calculaEnergiaControlavel();
        System.out.println("A energia armazenada máxima é: " + energiaControlavel + "MW/mês");
        System.out.println();
        System.out.println("A energia fio d'água é: 13.2822MW/mês");
    }

    private double getGeracaoUsina(String parametros, int porcentagem) {
        UsinasVO usina = getValoresUsina(parametros, porcentagem);

        double[] pcv = {usina.getPcv0(), usina.getPcv1(), usina.getPcv2(), usina.getPcv3(), usina.getPcv4()};
        double geracaoUsina = calculos.geracaoHidraulicaUsina(pcv, usina.getVmax(), usina.getVmin(), usina.getCfuga(), usina.getCphid(), usina.getProdesp(), usina.getPinst(), usina.getTeif(), usina.getIph(), 0.65, porcentagem);
        return geracaoUsina;
    }

    private static UsinasVO getValoresUsina(String parametros, double porcentagem) {
        String[] valoresStr = parametros.split(";");
        List<Double> valores = new ArrayList<>();
        for (String valorStr : valoresStr) {
            double valor = Double.parseDouble(valorStr);
            if (valor == 0.0) {
                valor = 0.000001;
            }
            valores.add(valor);
        }

        UsinasVO usina = new UsinasVO();
        usina.setVmax(valores.get(0) * (porcentagem/100));
        usina.setVmin(valores.get(1) );
        usina.setPcv0(valores.get(2));
        usina.setPcv1(valores.get(3));
        usina.setPcv2(valores.get(4));
        usina.setPcv3(valores.get(5));
        usina.setPcv4(valores.get(6));
        usina.setProdesp(valores.get(7));
        usina.setTeif(valores.get(8));
        usina.setIph(valores.get(9));
        usina.setPinst(valores.get(10));
        usina.setCfuga(valores.get(11));
        usina.setCphid(valores.get(12));

        if (usina.getVmax() == usina.getVmin()) {
            usina.setVmax(usina.getVmax() + 0.000001);
        }
        return usina;
    }

    private static List<String> preencheLista() {
        List<String> listaValoresUsinas = new ArrayList<>();
        listaValoresUsinas.add("17725;4669;0.56809E3;0.14506E-1;-0.12028E-5;0.58303E-10;-0.11245E-14;0.008731;2.92;12.12;1192;521.9;1.27");
        listaValoresUsinas.add("12792;2412;0.75215E3;0.12284E-1;-0.12569E-5;0.78525E-10;-0.19786E-14;0.009223;2.53;8.09;510;696.0;0.93");
        listaValoresUsinas.add("1120;974;0.6847E3;-0.40190E-2;-0.79360E-6;0.27851E-7;-0.14201E-10;0.008829;2.53;8.09;408;625.2;2.4");
        listaValoresUsinas.add("1500;470;0.54589E3;0.64709E-1;-0.3237E-4;0.73934E-8;0;0.008828;2.53;8.09;375;518.9;1.24");
        listaValoresUsinas.add("17027;4573;0.47116E3;0.72805E-2;-0.56099E-6;0.25978E-10;-0.48454E-15;0.008829;2.92;12.12;2280;435.6;0.75");
        listaValoresUsinas.add("460;460;0.43412E3;0;0;0;0;0.008730;2.31;7.37;658;401.1;0.59");
        listaValoresUsinas.add("12540;7000;0.35833E3;0.86173E2;-0.88427E-6;0.52932E-10;-0.12420E-14;0.009025;2.92;12.12;1710;328.1;0.81");
        return listaValoresUsinas;
    }

}