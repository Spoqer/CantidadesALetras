package com.auebo.utils.cantidadesaletras;

public class Tester {
    public static void main(String[] args) {
        int i = 1000;
        for (i = 1000000; i < 1000001; i=i+1) {

            System.out.printf("%d: %s\n", i, CantidadesALetras.convertirImporteALetras(i,0) );
        }

    }
}
