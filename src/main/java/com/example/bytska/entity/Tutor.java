package com.example.bytska.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tutor {
    private long id;
    private String firstName;
    private String lastName;
    private User user;
    @JsonIgnore
    private long userId;
    private List<Long> courseIds;
}
