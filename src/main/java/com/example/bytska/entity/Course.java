package com.example.bytska.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Course {
    private Long id;
    private String title; // Назва курсу
    private String description; // Опис курсу
    private Long tutorId; // Посилання на викладача
    private List<Long> studentsIds;
    private List<Long> tasksIds;
}
