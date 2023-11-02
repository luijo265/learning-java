package com.aprendekomodo.mongo.mapper;

import com.aprendekomodo.model.course.Course;
import com.aprendekomodo.mongo.document.CourseDocument;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseDocumentMapper {

    @Mapping(source = "courseId", target = "id")
    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "courseType", target = "tipoCurso")
    CourseDocument toDocument(Course course);

    Course toCourse(CourseDocument courseDocument);

}
