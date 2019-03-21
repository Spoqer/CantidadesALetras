package com.auebo.utils.cantidadesaletras;

public class Tester {
    public static void main(String[] args) {
        int i = 1999000011;
        System.out.printf("%d: %s\n", i, NumerosALetras.convertirImporteALetras(i) );
        for (i = 201008101; i < 201008102; i=i+1) {

            System.out.printf("%d: %s\n", i, NumerosALetras.convertirImporteALetras(i) );
        }

    }
}
