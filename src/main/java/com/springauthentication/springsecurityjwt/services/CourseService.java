package com.springauthentication.springsecurityjwt.services;

import com.springauthentication.springsecurityjwt.model.Course;
import com.springauthentication.springsecurityjwt.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course AddCourse(Course course) throws Exception {
        Optional<Course> findCourse = Optional.ofNullable(courseRepository.findCourseBycourseName(course.getCourseName()));
        if(findCourse.isPresent()){
            throw new Exception("Course Exists");
        }
        Course savedCourse = courseRepository.save(course);

        return savedCourse;
    }

    public Course findCourseByName(String courseName) throws Exception {
        Optional<Course> findCourse = Optional.ofNullable(courseRepository.findCourseBycourseName(courseName));
        if(findCourse.get()==null){
            throw new Exception("Course not found");
        }
        return findCourse.get();
    }

    public List<Course> findCoursesCurrentUser(){
        List<Course> courses = courseRepository.findCoursesCurrentUser();
        return courses;
    }
}
