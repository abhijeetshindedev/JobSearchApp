package com.abdev.jobsearch.user_service.DTO;

import java.util.List;

import com.abdev.jobsearch.user_service.Entity.Certification;
import com.abdev.jobsearch.user_service.Entity.Education;
import com.abdev.jobsearch.user_service.Entity.Experience;
import com.abdev.jobsearch.user_service.Entity.SocialLinks;

import lombok.Data;

@Data
public class UserProfileResponse {

    private Long id;
    private String fullName;
    private String headline;
    private String summary;
    private String phoneNumber;
    private String location;
    private String profileImageUrl;
    private SocialLinks socialLinks;

    private List<Education> educations;
    private List<Experience> experiences;
    private List<Certification> certifications;

}
