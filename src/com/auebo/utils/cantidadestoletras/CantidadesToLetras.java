package com.auebo.utils.cantidadestoletras;

public class CantidadesToLetras {
    private enum Decenas{
        Cero(0),
        Diez(1),
        Veinte(2),
        Treinta(3),
        Cuarenta(4),
        Cincuenta(5),
        Sesenta(6),
        Setenta(7),
        Ochenta(8),
        Noventa(9);

        public final int valor;

        Decenas(int valor) {
            this.valor = valor;
        }

        /**
         * Retorna el nombre con letras de una unidad.
         * @param digito    representa una cantidad de unidades
         * @return          la representación escrita con letras
         */
        static String convertir(int digito) {
            //Si el número es mayor a 9 o inferior a 0, hubo un error
            if (digito < 0 || digito > 9) {
                System.out.println("No se pueden convertir decenas: Digito fuera de rango.");
                return "-";
            }

            //Busca el valor que corresponda
            for (Decenas decena : Decenas.values()) {
                if (digito == decena.valor) {
                    return decena.toString();
                }
            }

            //Esta última rama nunca debería de suceder
            System.out.println("No se pueden convertir decenas: No se encontro correspondencia.");
            return "~";
        }

        @Override
        public String toString() {
            if (this.valor == 0) {
                return "";
            }
            return super.toString();
        }
    }

    private enum Unidades {
        Cero(0),
        Uno(1),
        Dos(2),
        Tres(3),
        Cuatro(4),
        Cinco(5),
        Seis(6),
        Siete(7),
        Ocho(8),
        Nueve(9);

        public final int valor;   //No se puede cambiar

        Unidades(int valor) {
            this.valor = valor;
        }

        /**
         * Retorna el nombre con letras de una unidad.
         * @param digito    representa una cantidad de unidades
         * @return          la representación escrita con letras
         */
        static String convertir(int digito) {
            //Si el número es mayor a 9 o inferior a 0, hubo un error
            if (digito < 0 || digito > 9) {
                System.out.println("No se pueden convertir unidades: Digito fuera de rango.");
                return "-";
            }

            //Busca el valor que corresponda
            for (Unidades unidad : Unidades.values()) {
                if (digito == unidad.valor) {
                    return unidad.toString();
                }
            }

            //Esta última rama nunca debería de suceder
            System.out.println("No se pueden convertir unidades: No se encontro correspondencia.");
            return "~";
        }

        @Override
        public String toString() {
            if (this.valor == 0) {
                return "";
            }
            return super.toString();
        }
    }

    /**
     * Convierte un número entero a letras, por ejemplo 319 a "Trescientos Diecinueve" usando un algoritmo recursivo.
     * @param numero    el número a convertir
     * @return          el número escrito (importe con letra)
     */
    public static String convertirImporte(int numero, int ordenDeMagnitud) {
        int digitoEvaluado;
        int digitoSiguiente;
        int ordenSiguiente;
        StringBuilder importeEnLetras = new StringBuilder();

        /*Cuando el orden de magnitud es múltiplo de 3, se agrupan dos dígitos porque hay
        algunos valores que se traducen de dos en dos por ejemplo 11, 12, 23 etc. se escriben "once", "doce",
        "veintitrés" etc.
        Esto sucede por ejemplo en "once mil millones" o "veintidós mil".*/
        if (ordenDeMagnitud % 3 == 0) {             //0,3,6,9...
            digitoEvaluado = numero % 100;
            digitoSiguiente = numero / 100;
            ordenSiguiente = 2;

            importeEnLetras.append(convertirValorEntreCeroYCien(digitoEvaluado));

        } else {                                        //Cuando el orden de magnitud NO ES múltiplo de 3
            digitoEvaluado = numero % 10;
            digitoSiguiente = numero / 10;
            ordenSiguiente = 1;
            //TODO:Lógica
        }

        //Revisa el dígito siguiente para saber si ya terminó
        if (digitoSiguiente == 0) {
            //Terminó, retorna el resultado.
            return importeEnLetras.toString();
        } else {
            //Yo <3 recursividad
            return convertirImporte(digitoSiguiente,ordenDeMagnitud + ordenSiguiente) + importeEnLetras;
        }
    }

    private static String convertirValorEntreCeroYCien(int digitoEvaluado) {
        int unidades = digitoEvaluado % 10;
        int decenas = digitoEvaluado / 10;

        if (unidades == 0) {
            return Decenas.convertir(decenas);
        } else if (decenas == 0 || decenas >= 3) {
            String y = "";
            if (decenas != 0) {
                y = " y ";
            }
            return Decenas.convertir(decenas) + y + Unidades.convertir(unidades) + " ";
        } else {
            if (digitoEvaluado <= 15) {
                switch (unidades) {
                    case 1:
                        return "Once ";
                    case 2:
                        return "Doce ";
                    case 3:
                        return "Trece ";
                    case 4:
                        return "Catorce ";
                    case 5:
                        return "Quince ";
                }
            } else if (decenas == 1) {
                return "Dieci" + Unidades.convertir(unidades).toLowerCase() + " ";
            } else {
                return "Veinti" + Unidades.convertir(unidades).toLowerCase() + " ";

            }
        }
        return "";
    }
}
