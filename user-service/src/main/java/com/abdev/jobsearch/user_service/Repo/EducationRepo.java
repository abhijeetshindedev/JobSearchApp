package com.abdev.jobsearch.user_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdev.jobsearch.user_service.Entity.Education;

@Repository
public interface EducationRepo extends JpaRepository<Education, Long> {

}
