package com.aprendekomodo.usecase.course;

import com.aprendekomodo.model.course.Course;
import com.aprendekomodo.model.course.gateways.CourseGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CourseUseCase {

    private CourseGateway courseGateway;

    public Mono<Course> createCourse(Course course){
        return courseGateway.createCourseEntity(course);
    }

}
