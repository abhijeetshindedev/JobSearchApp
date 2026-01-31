package com.abdev.jobsearch.user_service.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CertificationRequest {
    private long userId;
    private String title;
    private String issuer;
    private LocalDate issueDate;
    private String credentialUrl;
}
