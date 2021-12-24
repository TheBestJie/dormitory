package com.lu.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private Long userId;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private Integer floorId;
    private Role role;
}
