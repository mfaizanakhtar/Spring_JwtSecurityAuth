package com.springauthentication.springsecurityjwt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springauthentication.springsecurityjwt.DTO.UserNameDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NamedNativeQuery(name = "User.findUsersOfCourse",
        query = "SELECT u.username FROM user u INNER JOIN users_courses uc ON u.userid=uc.user_id" +
            " INNER JOIN course c ON uc.course_id=c.id WHERE c.course_name=:courseName",
        resultSetMapping = "Mapping.UserNameDTO")
@SqlResultSetMapping(name = "Mapping.UserNameDTO",
        classes = @ConstructorResult(targetClass = UserNameDTO.class,
                columns = {@ColumnResult(name = "username")}
        ))

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private int userid;

    @Column(unique = true)
    private String username;

    private String password;

    @Value("Student")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_courses",
    joinColumns = {@JoinColumn(name="user_id")},
    inverseJoinColumns = {@JoinColumn(name="course_id")})
    private Set<Course> courses = new HashSet<Course>();

    public User() {
    }

    public User(String username, String password,String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getRole()));

        return authorities;
    }

    public int getUserid() {
        return userid;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password){
        this.password=password;
    }

    @JsonIgnore
    public Set<Course> getCourses() {
        return courses;
    }

//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
