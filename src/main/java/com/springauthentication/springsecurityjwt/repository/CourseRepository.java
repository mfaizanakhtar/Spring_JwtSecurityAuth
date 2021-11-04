package com.springauthentication.springsecurityjwt.repository;

import com.springauthentication.springsecurityjwt.model.Course;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,String> {
    public Course findCourseBycourseName(String courseName);

    @Query(value = "SELECT * FROM course c INNER JOIN users_courses uc ON c.id=uc.course_id",nativeQuery = true)
    public List<Course> findCoursesCurrentUser();


}
