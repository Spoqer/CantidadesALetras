package com.auebo.utils.cantidadestoletras;

public class Tester {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.printf("%d: %s\n", i, CantidadesToLetras.convertirImporte(i,0) );
        }
    }
}
