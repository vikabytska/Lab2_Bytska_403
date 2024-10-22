package com.example.bytska.handler;

import com.example.bytska.entity.Mark;
import com.example.bytska.entity.Task;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component // Додано
public class TaskHandler {
    public Mono<ServerResponse> getTasks(ServerRequest request) {
        // Створюємо Flux для завдань
        Flux<Task> taskFlux = Flux.just(
                new Task(1L, "Homework 1", "Solve the problems from chapter 1", 1L, 100, Arrays.asList(new Mark(1L, 1L, null, 80), new Mark(2L, 1L, null,  90))),
                new Task(2L, "Project 1", "Create a project on your favorite topic", 2L, 150, Arrays.asList(new Mark(3L, 1L, null, 70))),
                new Task(3L, "Lab 1", "Complete the lab exercises", 3L, 50, Arrays.asList(new Mark(4L, 3L, null, 85))),
                new Task(4L, "Midterm Exam", "Prepare for the midterm examination", 4L, 200, Arrays.asList(new Mark(5L, 4L, null, 85))),
                new Task(5L, "Project 2", "Create a project on your favorite topic", 2L, 150, Arrays.asList(new Mark(3L, 1L, null, 100))),
                new Task(6L, "Project 3", "Create a project on this topic", 2L, 150, Arrays.asList(new Mark(3L, 1L, null, 84)))
        );

        // Повертаємо task у форматі JSON
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskFlux, Task.class); // Використано taskFlux
    }
}

