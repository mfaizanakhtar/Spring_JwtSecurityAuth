package com.springauthentication.springsecurityjwt.Controllers;

import com.springauthentication.springsecurityjwt.services.MyUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello(@AuthenticationPrincipal UserDetails userDetails){

        return userDetails.getUsername();
    }
}
