package com.aprendekomodo.mongo.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "courses")
public class CourseDocument {

    @Id
    private String id;

    private String titulo;

    private String descripcion;

    private String tipoCurso;

}
