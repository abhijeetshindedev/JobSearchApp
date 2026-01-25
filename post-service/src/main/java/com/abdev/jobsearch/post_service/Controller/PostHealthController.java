package com.abdev.jobsearch.post_service.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostHealthController {

    @GetMapping("/health")
    public String healthcheck(){
        return "post Service is running on Java 21";
    }

}
