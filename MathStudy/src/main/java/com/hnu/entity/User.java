package com.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final String email;
    private final String name;
    private String pwd;
}
