package com.springauthentication.springsecurityjwt.Controllers;

import com.springauthentication.springsecurityjwt.model.AuthRequest;
import com.springauthentication.springsecurityjwt.model.AuthResponse;
import com.springauthentication.springsecurityjwt.services.MyUserDetailsService;
import com.springauthentication.springsecurityjwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authenticate {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password",e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));


    }
}
