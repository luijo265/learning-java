package com.aprendekomodo.mongo;

import com.aprendekomodo.model.course.Course;
import com.aprendekomodo.mongo.document.CourseDocument;
import com.aprendekomodo.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepositoryAdapter extends AdapterOperations<Course, CourseDocument, String, CourseRepository>
// implements ModelRepository from domain
{

    public CourseRepositoryAdapter(CourseRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Course.class));
    }
}
