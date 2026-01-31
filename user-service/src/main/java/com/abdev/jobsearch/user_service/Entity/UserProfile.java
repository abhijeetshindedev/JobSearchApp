package com.abdev.jobsearch.user_service.Entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {
    
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String headline;
    private String summary;
    private String location;
    private String phoneNumber;
    private String profileImageString;

    @Embedded
    private SocialLinks socialLinks;
}
