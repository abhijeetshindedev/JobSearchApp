package com.abdev.jobsearch.user_service.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String location;
    private String designation;

    private LocalDate fromDate;
    private LocalDate toDate; // null = current

    private boolean currentlyWorking;

    @Column(length = 2000)
    private String jobDescription;

    private Integer experienceInMonths; //Will be calculated automatically.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
