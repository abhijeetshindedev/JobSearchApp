package com.abdev.jobsearch.user_service.Service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.abdev.jobsearch.user_service.DTO.ExperienceRequest;
import com.abdev.jobsearch.user_service.Entity.Experience;
import com.abdev.jobsearch.user_service.Entity.User;
import com.abdev.jobsearch.user_service.Repo.ExperienceRepo;
import com.abdev.jobsearch.user_service.Repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExperienceService {


    private final ExperienceRepo repo;
    private final UserRepo userRepo;
    // private final UserContext userContext;

    public Experience addExperience(ExperienceRequest req) {

        User user = userRepo.findById(req.getUserId())
                .orElseThrow();

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

        return repo.save(exp);
    }
}
