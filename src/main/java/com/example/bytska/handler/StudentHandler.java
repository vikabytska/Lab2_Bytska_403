package com.example.bytska.handler;


import com.example.bytska.entity.Student;
import com.example.bytska.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class StudentHandler {

    public Mono<ServerResponse> getStudents(ServerRequest request) {
        // Створюємо Flux для студентів
        Flux<Student> studentFlux = Flux.just(
                new Student(1L, "Alica", "One", null, 4L, Arrays.asList(1L, 2L)),
                new Student(2L, "Bob", "Two", null, 5L, Arrays.asList(3L)),
                new Student(3L, "Charlie", "Three", null, 6L, Arrays.asList(4L))
        );

        // Отримуємо користувачів
        Flux<User> userFlux = Flux.just(
                new User(4L, "alica", "44444", "alica@gmail.com"),
                new User(5L, "bobbob", "55555", "bob@gmail.com"),
                new User(6L, "charlie123", "66666", "charlie@gmail.com")
        );

        // Поєднуємо кожного Student з відповідним User на основі userId
        Flux<Student> studentsWithUsers = studentFlux.flatMap(student ->
                userFlux.filter(user -> user.getId() == student.getUserId())
                        .map(user -> {
                            student.setUser(user);
                            return student;
                        })
        );

        // Повертаємо список студентів з користувачами у форматі JSON
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentsWithUsers, Student.class);
    }
}

