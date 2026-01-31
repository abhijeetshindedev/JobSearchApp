package com.abdev.jobsearch.user_service.Entity;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class SocialLinks {
    private String linkedin;
    private String github;
    private String portfolio;
    private String twitter;
}
