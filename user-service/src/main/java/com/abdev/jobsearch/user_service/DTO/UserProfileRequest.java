package com.abdev.jobsearch.user_service.DTO;

import com.abdev.jobsearch.user_service.Entity.SocialLinks;

import lombok.Data;

@Data
public class UserProfileRequest {
    private long userId;
    private String headline;
    private String summary;
    private String phoneNumber;
    private String location;
    private String profileImageUrl;
    private SocialLinks socialLinks;
}
