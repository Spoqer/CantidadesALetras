package com.auebo.utils.cantidadesaletras;

public class CantidadesALetras {
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
            return super.toString() + " ";
        }

    }
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
         * Retorna el nombre con letras de una decena.
         * @param digito    representa una cantidad de decenas
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
            return super.toString() + " ";
        }
    }

    /**
     * Convierte un número entero a letras, por ejemplo 319 a "Trescientos Diecinueve" usando un algoritmo recursivo.
     * @param numero    el número a convertir
     * @return          el número escrito (importe con letra)
     */
    public static String convertirImporteALetras(int numero, int ordenDeMagnitud) {
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

            //Para no escribir "uno" en mil y evitar "Uno Mil", excepto cuando sea algo com 201 000 "doscientos un mil"
            if (digitoEvaluado != 1 || ((ordenDeMagnitud/3)%2 == 0) || digitoSiguiente != 0) {
                importeEnLetras.append(convertirValorEntreCeroYCien(digitoEvaluado));
            }

            if (importeEnLetras.length()>0 && digitoEvaluado == 1 && ordenDeMagnitud != 0){
                //Le quita la o a Uno cuando son miles, millones, etc.
                importeEnLetras.deleteCharAt(importeEnLetras.length() - 2);
            }

            importeEnLetras.append(ordenDeMagnitudALetras(digitoEvaluado, ordenDeMagnitud));

            //Para que no diga "un millones" o "un billones" sino "un millon" o "un billon"
            if (digitoSiguiente == 0 && digitoEvaluado == 1 && (ordenDeMagnitud/3) % 2 == 0 && importeEnLetras.length() > 0) {
                importeEnLetras.delete(importeEnLetras.length() - 3, importeEnLetras.length() - 1);
            }

        } else {                                        //Cuando el orden de magnitud NO ES múltiplo de 3
            digitoEvaluado = numero % 10;
            digitoSiguiente = numero / 10;
            ordenSiguiente = 1;

            if (ordenDeMagnitud % 3 == 1) {
                //Decenas
                importeEnLetras.append(Decenas.convertir(digitoEvaluado));
            } else {
                //Cientos
                importeEnLetras.append(convertirCientos(digitoEvaluado));
            }
        }

        //Revisa el dígito siguiente para saber si ya terminó
        if (digitoSiguiente == 0) {
            //Terminó, retorna el resultado.
            return importeEnLetras.toString();
        } else {

            int ordenDeMagnitudSiguiente = ordenDeMagnitud + ordenSiguiente;
            StringBuilder resultado = new StringBuilder();
            resultado.append(convertirImporteALetras(digitoSiguiente,ordenDeMagnitudSiguiente));
            //Para que cuando sea 100, no diga "ciento", sino sólo "cien"
            if ((ordenDeMagnitudSiguiente % 3 == 2 || ordenDeMagnitudSiguiente == 0)
                    && digitoEvaluado == 0 && digitoSiguiente % 10 == 1) {
                resultado.delete(resultado.length() - 3, resultado.length() - 1);
            }

            resultado.append(importeEnLetras);
            //Yo <3 recursividad
            return resultado.toString();
        }
    }

    private static String convertirCientos(int digitoEvaluado) {
        StringBuilder importeEnLetras = new StringBuilder();
        if (digitoEvaluado == 1) {
            importeEnLetras.append("Ciento ");
        } else if (digitoEvaluado == 5) {
            importeEnLetras.append("Quinientos ");
        } else if (digitoEvaluado == 7) {
            importeEnLetras.append("Sete ");
        } else if (digitoEvaluado == 9) {
            importeEnLetras.append("Nove ");
        } else {
            importeEnLetras.append(Unidades.convertir(digitoEvaluado));
        }
        if (importeEnLetras.length() != 0 && digitoEvaluado != 5 && digitoEvaluado != 1) {
            importeEnLetras.deleteCharAt(importeEnLetras.length() - 1);
            importeEnLetras.append("cientos ");
        }
        return importeEnLetras.toString();
    }

    private static String ordenDeMagnitudALetras(int digitoEvaluado, int ordenDeMagnitud) {
        String ordenDeMagnitudEnLetras = "";

        if (ordenDeMagnitud % 3 != 0) {
            System.out.println("Orden de magnitud incorrecto: no es multiplo de 3.");
            return "";
        }

        int potenciaDeMil = ordenDeMagnitud / 3;

        if (potenciaDeMil % 2 == 1) {
            ordenDeMagnitudEnLetras = "Mil ";
        }

        switch (potenciaDeMil) {
            case 2:
            case 3:
                return ordenDeMagnitudEnLetras + "Millones ";
            case 4:
            case 5:
                return ordenDeMagnitudEnLetras + "Billones ";
            case 6:
            case 7:
                return ordenDeMagnitudEnLetras + "Trillones ";

        }

        return ordenDeMagnitudEnLetras;
    }

    private static String convertirValorEntreCeroYCien(int digitoEvaluado) {
        int unidades = digitoEvaluado % 10;
        int decenas = digitoEvaluado / 10;

        if (unidades == 0) {
            return Decenas.convertir(decenas);
        } else if (decenas == 0 || decenas >= 3) {
            String y = "";
            if (decenas != 0) {
                y = "y ";
            }
            return Decenas.convertir(decenas) + y + Unidades.convertir(unidades);
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
                return "Dieci" + Unidades.convertir(unidades).toLowerCase();
            } else {
                return "Veinti" + Unidades.convertir(unidades).toLowerCase();

            }
        }
        return "";
    }
}
