package com.example.bytska.handler;

import com.example.bytska.entity.Mark;
import com.example.bytska.entity.Student;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class MarkHandler {

    public Mono<ServerResponse> getMarks(ServerRequest request) {
        // Отримати параметр 'start', якщо не задано - починаємо з 0
        String startParam = request.queryParam("start").orElse("0");
        long start;

        try {
            start = Long.parseLong(startParam);
        } catch (NumberFormatException e) {
            start = 0; // якщо формат неправильний, починаємо з 0
        }

        // Список студентів
        Flux<Student> studentFlux= Flux.just(
                new Student(1L, "Alica", "One", null, 4L, Arrays.asList(1L, 2L)),
                new Student(2L, "Bob", "Two", null, 5L, Arrays.asList(3L)),
                new Student(3L, "Charlie", "Three", null, 6L, Arrays.asList(4L))
        );

        // Створюємо Flux для оцінок
        Flux<Mark> markFlux = Flux.just(
                new Mark(1L, 1L, null, 80),
                new Mark(2L, 1L, null, 95),
                new Mark(3L, 2L, null, 65),
                new Mark(4L, 3L, null, 70)
        );

        // Поєднуємо кожного Student з відповідною оцінкою на основі studentId
        Flux<Mark> markWithStudent = markFlux.flatMap(mark ->
                studentFlux.filter(student -> student.getId() == mark.getStudentId())
                        .map(student -> {
                            mark.setStudent(student);
                            return mark;
                        })
        );

        // Повертаємо список оцінок з відповідними студентами у форматі JSON
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(markWithStudent.skip(start), Mark.class); // Використовуємо markWithStudent
    }

}


