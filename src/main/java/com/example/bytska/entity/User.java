package com.example.bytska.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class User {
    private long id;
    private String username;
    private String pass;
    private String email;

}
