package com.example.bytska.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private long id;
    private String title;
    private String description;
    private long courseId; // Пов'язано з одним курсом (1:Б)
    private int maxScore; // Максимальний бал за завдання
    private List<Mark> studentMarks; // Список оцінок студентів (Б:Б)
}




