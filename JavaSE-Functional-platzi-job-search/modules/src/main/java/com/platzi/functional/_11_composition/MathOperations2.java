package com.platzi.functional._11_composition;

import java.util.function.Function;

public class MathOperations2 {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyBy3 = x -> {
            System.out.println("Multiplicando por 3 a:"+x);
            return x * 3;
        };
        Function<Integer, Integer> add1MultiplyBy3 =
                multiplyBy3.compose(y -> {
                    System.out.println("Le agregare 1 a:"+y);
                    return y + 1;
                });
        Function<Integer, Integer> andSquery =
                add1MultiplyBy3.andThen(x -> {
                    System.out.println("Estoy elevando al cuadro a:"+x);
                    return x * x;
                });

        System.out.println(add1MultiplyBy3.apply(5));
        System.out.println(andSquery.apply(5));

    }
}
