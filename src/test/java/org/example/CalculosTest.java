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
        List<Double> listaPorcentagem = new ArrayList<>(); // Volume inicial será essas porcentagens do volume máximo.
        listaPorcentagem.add(1.0);
        listaPorcentagem.add(0.75);
        listaPorcentagem.add(0.65);
        listaPorcentagem.add(0.50);
        listaPorcentagem.add(0.30);

        for (String s : listaValoresUsinas) {
            System.out.println("Usina " + usinaIndex);

            for(Double porcentagem : listaPorcentagem){
                double geracaoUsina = getGeracaoUsina(s, porcentagem);
                System.out.println("Cálculo feito para " + porcentagem + "% do volume máximo da usina " + usinaIndex );
                System.out.println("A geração hidráulica da usina " + usinaIndex + " é: " + geracaoUsina + " MV/mês");
                System.out.println();
            }
            usinaIndex++;

            double geracaoUsina100 = getGeracaoUsina(s, 1.0);
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

    private double getGeracaoUsina(String parametros, double porcentagem) {
        UsinasVO usina = getValoresUsina(parametros, porcentagem);

        double[] pcv = {usina.getPcv0(), usina.getPcv1(), usina.getPcv2(), usina.getPcv3(), usina.getPcv4()};
        double geracaoUsina = calculos.geracaoHidraulicaUsina(pcv, usina.getVmax(), usina.getVmin(), usina.getCfuga(), usina.getCphid(), usina.getProdesp(), usina.getPinst(), usina.getTeif(), usina.getIph(), 0.65);
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
        usina.setVmax(valores.get(0) * porcentagem);
        usina.setVmin(valores.get(1) * porcentagem);
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
        listaValoresUsinas.add("17725;4669;568.0898;0.014506;-0.000001202799;5.830299E-11;-1.1245E-15;0.00904;671;1599;1192;520.72;0.98");
        listaValoresUsinas.add("12792;2412;752.1499;0.0122835;-0.00000125688;7.852509E-11;-1.97863E-15;0.008978;755;2977;510;696.25;1.68");
        listaValoresUsinas.add("1120;974;684.7029;-0.004019;-0.000000793603;0.0000000278513;-1.42009E-11;0.008834;173;3036;408;624.13;0.72");
        listaValoresUsinas.add("1500;470;545.8928;0.06470865;-0.0000323717;0.000000007393357;0;0.008845;4327;2342;375;516.68;0.69");
        listaValoresUsinas.add("17027;4573;471.1648;0.00728054;-0.000000560989;2.59776E-11;-4.845359E-16;0.008958;4611;3592;2082;434.51;0.75");
        listaValoresUsinas.add("460;460;433.3584;0;0;0;0;0.008568;1494;4152;34;401.23;0.59");
        listaValoresUsinas.add("12540;7000;358.3289;0.00861726;-0.0000008842659;5.293249E-11;-1.24196E-15;0.009104;1134;5581;1710;327.05;0.81");
        return listaValoresUsinas;
    }

}