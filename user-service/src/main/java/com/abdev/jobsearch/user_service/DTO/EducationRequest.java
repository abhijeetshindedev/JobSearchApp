package com.abdev.jobsearch.user_service.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EducationRequest {
    private long userId;
    private String degree;
    private String course;
    private String institute;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String grade;
    private String description;

}
