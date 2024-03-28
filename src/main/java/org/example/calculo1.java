package org.example;

public class calculo1 {

    public static void main(String[] args) {
        double[] pcv = {0.75215e3, 0.12284e-1, -0.12569e-5, 0.78525e-10, -0.19786e-14};
        double vmax = 12792.0;
        double vmin = 2412.0;
        int n = 1;

        double cotaMax = calcularCotaMax(pcv, vmax, n);
        System.out.println("CotaMax: " + cotaMax);

        double cotaMin = calcularCotaMin(pcv, vmin, n);
        System.out.println("CotaMin: " + cotaMin);

        double resultadoFinal = calculaResultadoFinal(cotaMax, cotaMin, vmax, vmin);
        System.out.println("Resultado Final: " + resultadoFinal);
    }

    public static double calcularCotaMax(double[] pcj, double vmax, int n) {
        double resultadoMax = 0.0;
        for (int i = 0; i < pcj.length; i++) {
            resultadoMax = resultadoMax + pcj[i] * Math.pow(vmax, i + 1) / (i + 1);
        }
        return resultadoMax;
    }

    public static double calcularCotaMin(double[] pcj, double vmin, int n) {
        double resultadoMin = 0.0;
        for (int i = 0; i < pcj.length; i++) {
            resultadoMin = resultadoMin + pcj[i] * Math.pow(vmin, i + 1) / (i + 1);
        }
        return resultadoMin;
    }
    public static double calculaResultadoFinal(double cotaMax, double cotaMin, double vmax, double vmin) {
        return (cotaMax - cotaMin)/(vmax - vmin);
    }

}
