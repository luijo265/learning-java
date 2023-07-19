package com.platzi.functional._04_functional;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class StringFunctions {

    public static void main(String[] args) {
        UnaryOperator<String> quote = text -> "\""+text+"\"";
        UnaryOperator<String> addMark = text -> text+"!";

        System.out.println(quote.apply("Hola Luis Jose"));
        System.out.println(addMark.apply("Hola Luis Jose"));

        BiFunction<Integer, Integer, Integer> multiplication =
                (x, y) -> x * y;

        System.out.println(multiplication.apply(2,3));

        BiFunction<String, Integer, String> leftPad =
                (text, number) -> String.format("%"+number+"s", text);

        System.out.println(leftPad.apply("Java", 10));

    }

}
