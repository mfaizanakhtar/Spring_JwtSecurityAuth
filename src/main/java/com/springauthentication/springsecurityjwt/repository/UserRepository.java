package com.springauthentication.springsecurityjwt.repository;

import com.springauthentication.springsecurityjwt.DTO.UserNameDTO;
import com.springauthentication.springsecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findUserByusername(String username);

    List<UserNameDTO> findUsersOfCourse(String courseName);
}
