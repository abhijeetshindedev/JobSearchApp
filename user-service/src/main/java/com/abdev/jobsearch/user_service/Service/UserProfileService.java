package com.abdev.jobsearch.user_service.Service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.abdev.jobsearch.user_service.DTO.CertificationRequest;
import com.abdev.jobsearch.user_service.DTO.EducationRequest;
import com.abdev.jobsearch.user_service.DTO.ExperienceRequest;
import com.abdev.jobsearch.user_service.DTO.UserProfileRequest;
import com.abdev.jobsearch.user_service.DTO.UserProfileResponse;
import com.abdev.jobsearch.user_service.Entity.Certification;
import com.abdev.jobsearch.user_service.Entity.Education;
import com.abdev.jobsearch.user_service.Entity.Experience;
import com.abdev.jobsearch.user_service.Entity.User;
import com.abdev.jobsearch.user_service.Entity.UserProfile;
import com.abdev.jobsearch.user_service.Repo.CertificationRepo;
import com.abdev.jobsearch.user_service.Repo.EducationRepo;
import com.abdev.jobsearch.user_service.Repo.ExperienceRepo;
import com.abdev.jobsearch.user_service.Repo.UserProfileRepo;
import com.abdev.jobsearch.user_service.Repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepo profileRepo;
    private final UserRepo userRepo;
    private final EducationRepo educationRepo;
    private final ExperienceRepo experienceRepo;
    private final CertificationRepo certificationRepo;
    // private final UserContext userContext;

    // Crud User
    // public UserProfileResponse getUserDetailsFromId(Long userId){

    //     UserProfileResponse userDetails = new UserProfileResponse();

    //     User user = userRepo.findById(userId);
    //     UserProfile userProfile = profileRepo.findById(userId).orElseThrow( () -> new RuntimeException("UserProfile Not Found"));
    //     userDetails.setFullName(user.getF);
    // }

    public long getUserIdFromEmail(String email){
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

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

    public Education addEducation(EducationRequest req){

        User user = userRepo.findById(req.getUserId()).
                    orElseThrow(() -> new RuntimeException("User Not Found"));
        Education education = new Education();
        education.setUser(user);
        education.setDegree(req.getDegree());
        education.setCourse(req.getCourse());
        education.setInstitute(req.getInstitute());
        education.setFromDate(req.getFromDate());
        education.setToDate(req.getToDate());
        education.setGrade(req.getGrade());
        education.setDescription(req.getDescription());

        educationRepo.save(education);

        return education;
    }

    public Experience addExperience(ExperienceRequest req) {

        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Experience exp = new Experience();
        exp.setUser(user);
        exp.setCompany(req.getCompany());
        exp.setLocation(req.getLocation());
        exp.setDesignation(req.getDesignation());
        exp.setFromDate(req.getFromDate());
        exp.setToDate(req.getToDate());
        exp.setCurrentlyWorking(req.isCurrentlyWorking());
        exp.setJobDescription(req.getJobDescription());

        LocalDate end = req.getToDate() != null ? req.getToDate() : LocalDate.now();
        Period p = Period.between(req.getFromDate(), end);
        exp.setExperienceInMonths(p.getYears() * 12 + p.getMonths());

        return experienceRepo.save(exp);
    }

    public Certification addCertification(CertificationRequest req){

        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Certification certification = new Certification();
        certification.setUser(user);
        certification.setTitle(req.getTitle());
        certification.setIssuer(req.getIssuer());
        certification.setCredentialUrl(req.getCredentialUrl());

        certificationRepo.save(certification);
        return certification;
    }
}
