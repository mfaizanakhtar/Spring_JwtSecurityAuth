package com.springauthentication.springsecurityjwt.Controllers;

import com.springauthentication.springsecurityjwt.model.Course;
import com.springauthentication.springsecurityjwt.repository.CourseRepository;
import com.springauthentication.springsecurityjwt.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseService courseService;

    @PostMapping("/addCourse")
    public Course addCourses(@RequestBody Course courses) throws Exception {

        return courseService.AddCourse(courses);
    }

    @GetMapping("/myCourses")
    public List<Course> UserCourses(){
        List<Course> courses = courseService.findCoursesCurrentUser();
        return courses;
    }
}
