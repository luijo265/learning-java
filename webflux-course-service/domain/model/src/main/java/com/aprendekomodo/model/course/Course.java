package com.aprendekomodo.model.course;
import com.aprendekomodo.model.exception.BusinessException;
import lombok.*;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Course {

    public static final String COURSE_TYPE_BACKEND = "BACKEND";
    public static final String COURSE_TYPE_FRONTEND = "FRONTEND";
    public static final String COURSE_TYPE_DEVOPS = "DEVOPS";

    private String courseId;
    private String title;
    private String description;
    private String courseType;

    public void setCourseType(String courseType) throws BusinessException {
        if (!Arrays.asList(COURSE_TYPE_BACKEND, COURSE_TYPE_DEVOPS, COURSE_TYPE_FRONTEND).contains(courseType)){
            throw new BusinessException("Tipo de curso no existe");
        }
        this.courseType = courseType;
    }
}
