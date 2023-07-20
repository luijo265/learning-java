package com.platzi.functional._15_streams_intro;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        List<String> courseList = NombresUtils.getList(
                "Java",
                "Webflux",
                "GOF"
        );

        for (String course: courseList){
            System.out.println("Platzi: "+course);
        }

        Stream<String> courseStream = Stream.of("Java",
                "Webflux",
                "GOF"
        );

//        Stream<Integer> courseSizes = courseStream.map(course -> course.length());

//        Optional<Integer> longest = courseSizes.max((x, y) -> y - x);

        // Los stream solo se pueden consumir una vez, luego del primer uso queda nulo o limpio
        Stream<String> emphasisCourses = courseStream.map(course -> course.concat("!!"));
        Stream<String> justJavaCourse = emphasisCourses.filter(course -> course.contains("Java"));
        justJavaCourse.forEach(System.out::println);

        Stream<String> coursesString2 = courseList.stream();

//        coursesString2.map(course -> course.concat("!!!"))
//                .filter(course -> course.contains("Java"))
//                .forEach(System.out::println);
        addOperator(
                coursesString2
                        .map(course -> course.concat("!!!"))
                    .filter(course -> course.contains("Java"))
        ).forEach(System.out::println);
    }

    static <T> Stream<T> addOperator(Stream<T> stream){
        return stream.peek(data -> System.out.println("Dato: "+data));
    }

}
