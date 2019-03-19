package com.auebo.utils.cantidadesaletras;

public class Tester {
    public static void main(String[] args) {
        int i = 1000;
        System.out.printf("%d: %s\n", i, CantidadesALetras.convertirImporteALetras(i,0) );
        for (i = 21008000; i < 21008001; i=i+1) {

            System.out.printf("%d: %s\n", i, CantidadesALetras.convertirImporteALetras(i,0) );
        }

    }
}
