package com.abdev.jobsearch.user_service.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExperienceRequest {
    private long userId;
    private String company;
    private String location;
    private String designation;
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean currentlyWorking;
    private String jobDescription;
}
