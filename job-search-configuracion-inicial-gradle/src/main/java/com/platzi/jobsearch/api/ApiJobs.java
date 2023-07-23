package com.platzi.jobsearch.api;

import com.platzi.jobsearch.JobPosition;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

@Headers("Accept: application/json")
public interface ApiJobs {

    @RequestLine("GET /jobs?description_like={keyword}")
    List<JobPosition> jobs(@Param(value = "keyword") String keyword);

}
