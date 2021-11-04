package com.springauthentication.springsecurityjwt.Controllers;

import com.springauthentication.springsecurityjwt.DTO.UserNameDTO;
import com.springauthentication.springsecurityjwt.model.Course;
import com.springauthentication.springsecurityjwt.model.User;
import com.springauthentication.springsecurityjwt.repository.CourseRepository;
import com.springauthentication.springsecurityjwt.repository.UserRepository;
import com.springauthentication.springsecurityjwt.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MyUserDetailsService userService;


    @PostMapping("/addUser")
    public User addEmployee(@RequestBody User user) throws Exception {

        return userService.AddUser(user);
    }

    @GetMapping("/getCurrentUser")
    public User getEmployee(@AuthenticationPrincipal UserDetails userDetails){
        return (User) userDetails;
    }

    @PutMapping("/addUserCourses/{CourseName}")
    public User addUserCourses(@AuthenticationPrincipal UserDetails userDetails,@PathVariable String CourseName) throws Exception {
        return userService.updateUserCourses(userDetails,CourseName);

    }

    @GetMapping("/getUsersOfCourse/{CourseName}")
    public List<UserNameDTO> getUsersofCourse(@PathVariable String CourseName){
        return userService.UsersOfCourse(CourseName);
    }
}
