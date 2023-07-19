package com.platzi.functional._08_lambda;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Sintaxis {

    public static void main(String[] args) {
        List<String> cursos = NombresUtils.getList("Jva", "functional");

        cursos.forEach(curso -> System.out.println(curso));

        usarZero(() -> 2);
        usarBiFunctional((valor1, valor2) -> valor1 * valor2);
        usarPredicate(edad -> edad > 18);

        usarNada(() -> {});
    }

    static void usarZero(ZeroArgumentos zeroArgumentos){}

    static void usarBiFunctional(BiFunction<Integer, Integer, Integer> biFunction){}
    static void usarPredicate(Predicate<Integer> predicate){}

    static void usarNada(NoHaceNada noHaceNada){}

    @FunctionalInterface
    interface ZeroArgumentos {
        int get();
    }

    @FunctionalInterface
    interface NoHaceNada {
        void nada();
    }

}
