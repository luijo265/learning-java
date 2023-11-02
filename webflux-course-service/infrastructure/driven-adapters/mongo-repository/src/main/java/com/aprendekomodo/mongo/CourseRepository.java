package com.aprendekomodo.mongo;

import com.aprendekomodo.mongo.document.CourseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface CourseRepository extends ReactiveMongoRepository<CourseDocument, String>, ReactiveQueryByExampleExecutor<CourseDocument> {
}
