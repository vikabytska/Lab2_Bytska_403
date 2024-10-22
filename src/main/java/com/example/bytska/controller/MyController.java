package com.example.bytska.controller;

import com.example.bytska.entity.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@RestController
public class MyController {

    @GetMapping("/users")
    public Flux<User> getUsers() {
        Flux<User> users = Flux.just(
                        new User(1L, "vika.bytska", "11111", "vika@gmail.com"),
                        new User(2L, "jane.smith", "22222", "jane@gmail.com"),
                        new User(3L, "john.jane", "33333", "john@gmail.com"),
                        new User(4L, "alica", "44444", "alica@gmail.com"),
                        new User(5L, "bobbob", "55555", "bob@gmail.com"),
                        new User(6L, "charlie123", "66666", "charlie@gmail.com")
                )
                .skip(0);

        return users;
    }

    @GetMapping("/tutors")
    public Flux<Tutor> getTutors() {
        // Створюємо Flux для користувачів
        Flux<User> userFlux = Flux.just(
                new User(1L, "vika.bytska", "11111", "vika@gmail.com"),
                new User(2L, "jane.smith", "22222", "jane@gmail.com"),
                new User(3L, "john.jane", "33333", "john@gmail.com")
        );

        // Створюємо Flux для викладачів
        Flux<Tutor> tutors = Flux.just(
                new Tutor(1L, "Vika", "Bytska", null, 1L, Arrays.asList(1L, 2L)),
                new Tutor(2L, "Jane", "Smith", null, 2L, Arrays.asList(3L)),
                new Tutor(3L, "John", "Jane", null, 3L, Arrays.asList(4L))
        );

        // Поєднуємо студентів з користувачами
        return tutors.flatMap(tutor ->
                userFlux.filter(user -> user.getId() == tutor.getUserId())
                        .map(user -> {
                            tutor.setUser(user); // Прив'язуємо користувача до викладача
                            return tutor; // Повертаємо викладача з прив'язаним користувачем
                        })
        );
    }




    @GetMapping("/courses")
    public Flux<Course> getCourses() {
        Flux<Course> courses = Flux.just(
                        new Course(1L, "Math 101", "Introduction to Mathematics", 1L, Arrays.asList(4L, 5L), Arrays.asList(1L)),
                        new Course(2L, "History 101", "Introduction to History", 1L, Arrays.asList(4L, 5L), Arrays.asList(2L, 5L, 6L)),
                        new Course(3L, "Biology 101", "Introduction to Biology", 2L, Arrays.asList(5L, 6L), Arrays.asList(3L)),
                        new Course(4L, "Biology 401", "Introduction to Biology 4", 3L, Arrays.asList(4L, 5L, 6L), Arrays.asList(4L))
                )
                .skip(0);

        return courses;
    }

    @GetMapping("/students")
    public Flux<Student> getStudents() {
        // Створюємо Flux для користувачів
        Flux<User> userFlux = Flux.just(
                new User(4L, "alica", "44444", "alica@gmail.com"),
                new User(5L, "bobbob", "55555", "bob@gmail.com"),
                new User(6L, "charlie123", "66666", "charlie@gmail.com")
        );

        // Створюємо Flux для студентів
        Flux<Student> students = Flux.just(
                new Student(1L, "Alica", "One", null, 4L, Arrays.asList(1L, 2L)), // Зв'язок з userId
                new Student(2L, "Bob", "Two", null, 5L, Arrays.asList(3L)),
                new Student(3L, "Charlie", "Three", null, 6L, Arrays.asList(4L))
        );

        // Поєднуємо студентів з користувачами
        return students.flatMap(student ->
                userFlux.filter(user -> user.getId() == student.getUserId())
                        .map(user -> {
                            student.setUser(user); // Прив'язуємо користувача до студента
                            return student; // Повертаємо студента з прив'язаним користувачем
                        })
        );
    }


    @GetMapping("/marks")
    public Flux<Mark> getMarks() {

        // Створюємо Flux для студентів
        Flux<Student> studentFlux = Flux.just(
                new Student(1L, "Alica", "One", null, 4L, Arrays.asList(1L, 2L)),
                new Student(2L, "Bob", "Two", null, 5L, Arrays.asList(3L)),
                new Student(3L, "Charlie", "Three", null, 6L, Arrays.asList(4L))
        );

        // Створюємо Flux для оцінок
        Flux<Mark> markFlux = Flux.just(
                new Mark(1L, 1L, null, 80),
                new Mark(2L, 1L, null,  90),
                new Mark(3L, 1L, null, 70),
                new Mark(4L, 3L, null, 85),
                new Mark(5L, 4L, null, 85)
        );

        // Поєднуємо оцінки з відповідними студентами
        return markFlux.flatMap(mark ->
                studentFlux.filter(student -> student.getId() == mark.getStudentId())
                        .next() // Отримуємо перший знайдений студент
                        .map(student -> {
                            mark.setStudent(student); // Прив'язуємо студента до оцінки
                            return mark; // Повертаємо оцінку з прив'язаним студентом
                        })
        );
    }



    @GetMapping("/tasks")
    public Flux<Task> getTasks() {

        // Створюємо Flux для студентів
        Flux<Student> studentFlux = Flux.just(
                new Student(1L, "Alica", "One", null, 4L, Arrays.asList(1L, 2L)),
                new Student(2L, "Bob", "Two", null, 5L, Arrays.asList(3L)),
                new Student(3L, "Charlie", "Three", null, 6L, Arrays.asList(4L))
        );

        // Створюємо Flux для завдань
        Flux<Task> taskFlux = Flux.just(
                new Task(1L, "Homework 1", "Solve the problems from chapter 1", 1L, 100, Arrays.asList(new Mark(1L, 1L, null, 80), new Mark(2L, 1L, null,  90))), // Додані оцінки
                new Task(2L, "Project 1", "Create a project on your favorite topic", 2L, 150, Arrays.asList(new Mark(3L, 1L, null, 70))), // Додана оцінка
                new Task(3L, "Lab 1", "Complete the lab exercises", 3L, 50, Arrays.asList(new Mark(4L, 3L, null, 85))), // Додана оцінка
                new Task(4L, "Midterm Exam", "Prepare for the midterm examination", 4L, 200, Arrays.asList(new Mark(5L, 4L, null, 85))),
                new Task(5L, "Project 2", "Create a project on your favorite topic", 2L, 150, Arrays.asList(new Mark(3L, 1L, null, 100))),
                new Task(6L, "Project 3", "Create a project on this topic", 2L, 150, Arrays.asList(new Mark(3L, 1L, null, 84)))
        );

        return taskFlux;
    }

}

