package com.platzi.functional._04_functional;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.function.Function;
import java.util.function.Predicate;

public class MathFunctions {

    public static void main(String[] args) {

        Function<Integer, Integer> square = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x*x;
            }
        };

        System.out.println(square.apply(5));
        System.out.println(square.apply(9));

        Function<Integer, Boolean> isOdd = numero -> ((numero % 2) == 1);

        Predicate<Integer> isEven = numero -> numero % 2 == 0;

        isEven.test(5);

        Predicate<Student> isPasoPrueba = student -> student.getCalificacion() >= 5.0;
        Student luijo = new Student(4.9);
        System.out.println(isPasoPrueba.test(luijo));

    }

    static class Student {
        private double calificacion;

        public Student(double calificacion) {
            this.calificacion = calificacion;
        }

        public double getCalificacion() {
            return calificacion;
        }
    }

}
