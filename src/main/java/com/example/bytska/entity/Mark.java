package com.example.bytska.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mark {
    private long id;
    private long studentId; // Студент, який виконав завдання
    private Student student;
    private int mark; // Оцінка студента за це завдання
}
