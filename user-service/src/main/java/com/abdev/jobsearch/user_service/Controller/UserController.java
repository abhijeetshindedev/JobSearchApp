package com.abdev.jobsearch.user_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdev.jobsearch.user_service.DTO.UserProfileRequest;
import com.abdev.jobsearch.user_service.Service.UserProfileService;

import lombok.RequiredArgsConstructor;

import java.net.Authenticator;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;

    // CRUD - User
    // @GetMapping("/me")
    // public String getUserDetailsFromId(@RequestBody Map<String,Object> request ) {
    //     Long userId = (Long) request.get("userId");


    // }
    

    // CRUD - User Profile 
    @PostMapping("/userprofile")
    public ResponseEntity<?> addUserDetails( @RequestBody UserProfileRequest request, 
                                                Authentication authentiocation){

        UserDetails userDetails = (UserDetails) authentiocation.getPrincipal();
        System.out.println("User Details from Token : "+userDetails);
        long userId = userProfileService.getUserIdFromEmail(userDetails.getUsername());
        request.setUserId(userId);
        return ResponseEntity.ok(userProfileService.saveOrUpdateProfile(request));
    }
}
