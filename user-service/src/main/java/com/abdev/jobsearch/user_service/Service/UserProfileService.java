package com.abdev.jobsearch.user_service.Service;

import org.springframework.stereotype.Service;

import com.abdev.jobsearch.user_service.DTO.UserProfileRequest;
import com.abdev.jobsearch.user_service.Entity.User;
import com.abdev.jobsearch.user_service.Entity.UserProfile;
import com.abdev.jobsearch.user_service.Repo.UserProfileRepo;
import com.abdev.jobsearch.user_service.Repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepo profileRepo;
    private final UserRepo userRepo;
    // private final UserContext userContext;

    public UserProfile saveOrUpdateProfile(UserProfileRequest request) {

        Long userId = request.getUserId();
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile profile = profileRepo.findById(userId)
                .orElse(new UserProfile());

        profile.setUser(user);
        profile.setHeadline(request.getHeadline());
        profile.setSummary(request.getSummary());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setLocation(request.getLocation());
        profile.setProfileImageString(request.getProfileImageUrl());
        profile.setSocialLinks(request.getSocialLinks());

        return profileRepo.save(profile);
    }

}
