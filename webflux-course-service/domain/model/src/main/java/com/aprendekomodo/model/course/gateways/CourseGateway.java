package com.aprendekomodo.model.course.gateways;

import com.aprendekomodo.model.course.Course;
import reactor.core.publisher.Mono;

public interface CourseGateway {

    Mono<Course> createCourseEntity(Course course);

}
