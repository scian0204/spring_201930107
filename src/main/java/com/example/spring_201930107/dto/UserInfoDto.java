package com.example.spring_201930107.dto;

import com.example.spring_201930107.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private long id;
    private String uid;
    private String name;
    private String email;

    public UserInfoDto(User user) {
        this.id = user.getId();
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
