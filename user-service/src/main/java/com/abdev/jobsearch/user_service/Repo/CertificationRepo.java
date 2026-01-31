package com.abdev.jobsearch.user_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdev.jobsearch.user_service.Entity.Certification;

@Repository
public interface CertificationRepo extends JpaRepository<Certification, Long> {

}
 