package com.aprendekomodo.mongo;

import com.aprendekomodo.model.course.Course;
import com.aprendekomodo.model.course.gateways.CourseGateway;
import com.aprendekomodo.mongo.mapper.CourseDocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseGatewayImpl implements CourseGateway {

    private CourseRepository courseRepository;

    private CourseDocumentMapper mapper;

    @Override
    public Mono<Course> createCourseEntity(Course course) {
        return Mono.just(course)
                .map(mapper::toDocument)
                .flatMap(courseRepository::save)
                .map(mapper::toCourse);
    }
}
