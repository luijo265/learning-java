package com.platzi.functional._05_sam;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

// Single Abstract Method
public class AgeUtils {

    /*
    @FunctionalInterface
    interface BiConsumer {
        void accept();
    }
    */

    public static void main(String[] args) {

        Function<Integer, String> addCeros = x -> x < 10 ? "0"+x : String.valueOf(x);

        TriFunction<Integer, Integer, Integer, LocalDate> parseDate =
                (day, month, year) ->
                        LocalDate.parse(
                            year+"-"+addCeros.apply(month)+"-"+addCeros.apply(day)
                        );

        TriFunction<Integer, Integer, Integer, Integer> calculateAge =
                (day, month, year) ->
                        Period.between(
                            LocalDate.now(),
                            parseDate.apply(day, month, year)
                        )
                        .getYears();

        System.out.println(calculateAge.apply(4, 6, 1991));
    }

    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

}
