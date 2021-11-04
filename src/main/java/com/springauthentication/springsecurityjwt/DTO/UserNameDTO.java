package com.springauthentication.springsecurityjwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class UserNameDTO {

    private String username;

    public UserNameDTO(String username) {
        this.username = username;
    }
}
