package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

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
        double geracaoResultante = 0.0;
        List<String> listaValoresUsinas = preencheLista();

        int usinaIndex = 1;

        for (String s : listaValoresUsinas) {
            double geracaoUsina = getGeracaoUsina(s);
            geracaoResultante += geracaoUsina;
            System.out.println("A geração hidráulica da usina " + usinaIndex + " é: " + geracaoUsina + " MV/mês");
            usinaIndex++;
        }
        System.out.println("A geração hidráulica resultante das usinas do Sudeste é: " + geracaoResultante + " MV/mês");

    }

    private double getGeracaoUsina(String parametros) {
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
        usina.setVmax(valores.get(0));
        usina.setVmin(valores.get(1));
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

        double[] pcv = {usina.getPcv0(), usina.getPcv1(), usina.getPcv2(), usina.getPcv3(), usina.getPcv4()};
        double geracaoUsina = calculos.geracaoHidraulicaUsina(pcv, usina.getVmax(), usina.getVmin(), usina.getCfuga(), usina.getCphid(), usina.getProdesp(), usina.getPinst(), usina.getTeif(), usina.getIph());
        return geracaoUsina;
    }

    private static List<String> preencheLista() {
        List<String> listaValoresUsinas = new ArrayList<>();
        listaValoresUsinas.add("792;120;892.97;0.062089;-0.00011041;0.0000001247;-5.5512E-11;0.008767;0.365;2.044;46;885.73;0.09");
        listaValoresUsinas.add("11;11;885.6586;0;0;0;0;0.008649;1.123;5.602;25;856.85;0.63");
        listaValoresUsinas.add("304;304;807.9131;0;0;0;0;0.00901;1.96;2.304;180;768.13;0.39");
        listaValoresUsinas.add("22950;5733;735.2458;0.00349658;-0.000000197437;6.917049E-12;-9.77365E-17;0.008996;1271;1939;912;672.2;0.0001");
        listaValoresUsinas.add("4040;1540;641.7749;0.008088168;-0.000000369824;-7.110519E-11;9.123737E-15;0.008657;3132;2.81;80;621.1;1.14");
        listaValoresUsinas.add("1423;1423;620.4571;0;0;0;0;0.00889;983;2814;1050;558.4;0.9");
        listaValoresUsinas.add("450;450;557.4398;0;0;0;0;0.008944;4471;15.13;424;512.84;0.5");
        listaValoresUsinas.add("480;480;512.0068;0;0;0;0;0.008996;548;2054;210;494.91;0.17");
        listaValoresUsinas.add("2244;2244;494.3829;0;0;0;0;0.008996;3387;3538;380;466.63;0.17");
        listaValoresUsinas.add("1524;1524;466.4599;0;0;0;0;0.009071;7812;1362;320;444.05;0.23");
        listaValoresUsinas.add("555;51;816.3469;0.19769;-0.0005735699;0.0000009690375;-6.329368E-10;0.008558;145;5034;41.2;751.19;0.87");
        listaValoresUsinas.add("14;14;664.4825;0;0;0;0;0.008602;66;3049;108.8;576.55;0.73");
        listaValoresUsinas.add("25;25;572.4828;0;0;0;0;0.008599;306;2577;32;546.61;0.19");
        listaValoresUsinas.add("6150;890;417.8938;0.0111714;-0.000002293769;2.909948E-10;-1.4826E-14;0.00907;1133;2118;1440;383.65;0.82");
        listaValoresUsinas.add("11025;5856;352.0298;0.004995856;-0.000000274164;7.09831E-12;0;0.008825;144;2654;1396.2;327.18;0.75");
        listaValoresUsinas.add("1781.61;430.05;775.0463;0.02639726;-0.00001062692;0.000000001987292;0;0.009025;5301;5853;52.6;755.3;1.2");
        listaValoresUsinas.add("5199;1752;682.6038;0.0487297;-0.00001593879;0.0000000026708;-1.70707E-13;0.008762;0.22;1106;212.6;674.94;0.69");
        listaValoresUsinas.add("9010;2186;704.9268;0.0123037;-0.00000171284;1.43866E-10;-4.85006E-15;0.008633;2533;8091;150;675.6;2.2");
        listaValoresUsinas.add("17725;4669;568.0898;0.014506;-0.000001202799;5.830299E-11;-1.1245E-15;0.00904;671;1599;1192;520.72;0.98");
        listaValoresUsinas.add("12792;2412;752.1499;0.0122835;-0.00000125688;7.852509E-11;-1.97863E-15;0.008978;755;2977;510;696.25;1.68");
        listaValoresUsinas.add("1120;974;684.7029;-0.004019;-0.000000793603;0.0000000278513;-1.42009E-11;0.008834;173;3036;408;624.13;0.72");
        listaValoresUsinas.add("241.13;228.27;594.02;0.35164;-0.0021575;0.0000073589;-0.0000000095971;0.008995;587;2016;240;565.14;1.84");
        listaValoresUsinas.add("879;878;527.3599;0.11044;-0.00018921;0.00000019327;-7.4543E-11;0.009077;2382;2154;210;518.28;0.52");
        listaValoresUsinas.add("3708;2936.6;789.3335;0.03966617;-0.00001496053;0.000000003071849;-2.366004E-13;0.008746;1051;2.37;129.2;772.33;1.59");
        listaValoresUsinas.add("1500;470;545.8928;0.06470865;-0.0000323717;0.000000007393357;0;0.008845;4327;2342;375;516.68;0.69");
        listaValoresUsinas.add("17027;4573;471.1648;0.00728054;-0.000000560989;2.59776E-11;-4.845359E-16;0.008958;4611;3592;2082;434.51;0.75");
        listaValoresUsinas.add("460;460;433.3584;0;0;0;0;0.008568;1494;4152;34;401.23;0.59");
        listaValoresUsinas.add("12540;7000;358.3289;0.00861726;-0.0000008842659;5.293249E-11;-1.24196E-15;0.009104;1134;5581;1710;327.05;0.81");
        listaValoresUsinas.add("21060;8232;293.9248;0.00360059;-0.000000184615;5.87764E-12;-7.503619E-17;0.00865;907;3.69;704;281.08;0.54");
        listaValoresUsinas.add("3135;569;432.7839;0.0149645;-0.00000670742;0.00000000175977;-1.69823E-13;0.008845;121;7.77;140.8;427.66;0.41");
        listaValoresUsinas.add("544;544;427.2259;0;0;0;0;0.008333;363;6918;143.1;404.62;0.43");
        listaValoresUsinas.add("985;985;403.8362;0;0;0;0;0.008438;225;7989;131.4;382.84;0.41");
        listaValoresUsinas.add("7408;5280;369.6938;-0.0005249989;0.00000108299;-1.6016E-10;7.927737E-15;0.008798;169;4156;264;357.95;0.37");
        listaValoresUsinas.add("2720;2720;357.7169;0;0;0;0;0.009087;126;2851;347.4;326.66;0.43");
        listaValoresUsinas.add("13372;9923;293.2058;0.005496006;-0.000000392702;1.73305E-11;-3.101849E-16;0.008846;936;3774;807.5;280.02;0.83");
        listaValoresUsinas.add("34432;25467;294.1809;0.00205514;-0.0000000566282;9.703115E-13;-6.69962E-18;0.008829;912;3706;704;281.1;2.35");
        listaValoresUsinas.add("3354;3354;279.6819;0;0;0;0;0.008751;2153;4533;1551.2;258.48;0.63");
        listaValoresUsinas.add("14400;14400;239.17;0.002497;-0.00000012596;3.0481E-12;-2.570999E-17;0.008928;157;3625;1540;237.66;0.43");
        listaValoresUsinas.add("7008;3843;542.5549;0.006367009;-0.000000675152;5.394289E-11;-1.89938E-15;0.008875;179;1238;101;532.23;0.41");
        listaValoresUsinas.add("84;84;531.4909;0;0;0;0;0.009024;355;4405;80;505.63;0.3");
        listaValoresUsinas.add("8795;5754;437.4719;0.0066313;-0.000000375179;1.06241E-11;0;0.008791;48;1325;103.5;398.77;2.19");
        listaValoresUsinas.add("45;45;384.1715;0;0;0;0;0.008581;83;2696;74;366.65;0.23");
        listaValoresUsinas.add("151;151;365.9388;0;0;0;0;0.009089;198;2662;72;351.57;0.21");
        listaValoresUsinas.add("212;212;350.9478;0;0;0;0;0.009124;349;2151;82.5;334.4;0.22");
        return listaValoresUsinas;
    }

}