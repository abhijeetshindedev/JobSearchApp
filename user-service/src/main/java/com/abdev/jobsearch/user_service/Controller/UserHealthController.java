package com.abdev.jobsearch.user_service.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserHealthController {

    @GetMapping("/health")
    public String healthcheck(){
        System.out.println("User Service is running on Java 21");
        return "User Service is running on Java 21";
    }
}
