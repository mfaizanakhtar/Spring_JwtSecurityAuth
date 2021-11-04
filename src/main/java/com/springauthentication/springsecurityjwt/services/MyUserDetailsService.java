package com.springauthentication.springsecurityjwt.services;

import com.springauthentication.springsecurityjwt.DTO.UserNameDTO;
import com.springauthentication.springsecurityjwt.model.Course;
import com.springauthentication.springsecurityjwt.model.User;
import com.springauthentication.springsecurityjwt.repository.CourseRepository;
import com.springauthentication.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CourseService courseService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return getUserfromdb(userName);
    }

    public User getUserfromdb(String username){
        Optional<User> findUser = Optional.ofNullable(userRepository.findUserByusername(username));
        if(findUser.get()==null){
            throw new UsernameNotFoundException("No user found");
        }
        return findUser.get();
    }

    public User AddUser(User user) throws Exception {
        Optional<User> findEmployeeObj = Optional.ofNullable(userRepository.findUserByusername(user.getUsername()));
        if(findEmployeeObj.isPresent()){
            throw new Exception("User already exists");
        }
        String encodedPassword=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User updateUserCourses(UserDetails userDetails,String courseName) throws Exception {
        User user = (User) userDetails;
        Course findCourse = courseService.findCourseByName(courseName);
        user.getCourses().add(findCourse);
        userRepository.save(user);
        return user;
    }

    public List<UserNameDTO> UsersOfCourse(String CourseName){
        List<UserNameDTO> users = userRepository.findUsersOfCourse(CourseName);
        return users;
    }


}
