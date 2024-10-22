package com.example.bytska.handler;

import com.example.bytska.entity.Course;
import com.example.bytska.entity.Tutor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class CourseHandler {

    public Mono<ServerResponse> getCourseIdsForTutors(ServerRequest request) {
        // Створюємо Flux для викладачів
        Flux<Tutor> tutorFlux = Flux.just(
                new Tutor(1L, "Vika", "Bytska", null, 1L, null),
                new Tutor(2L, "Jane", "Smith", null, 2L, null),
                new Tutor(3L, "John", "Jane", null, 3L, null)
        );

        // Створюємо Flux для курсів
        Flux<Course> courseFlux = Flux.just(
                new Course(1L, "Math 101", "Introduction to Mathematics", 1L, Arrays.asList(4L, 5L), Arrays.asList(1L)),
                new Course(2L, "History 101", "Introduction to History", 1L, Arrays.asList(4L, 5L), Arrays.asList(2L, 5L, 6L)),
                new Course(3L, "Biology 101", "Introduction to Biology", 2L, Arrays.asList(5L, 6L), Arrays.asList(3L)),
                new Course(4L, "Biology 401", "Introduction to Biology 4", 3L, Arrays.asList(4L, 5L, 6L), Arrays.asList(4L))
        );

        // Поєднуємо викладачів з їх курсами
        Flux<Tutor> tutorsWithCourseIds = tutorFlux.flatMap(tutor ->
                courseFlux.filter(course -> course.getTutorId() == tutor.getId()) // Фільтруємо курси за tutorId
                        .map(Course::getId) // Отримуємо тільки ідентифікатори курсів
                        .collectList() // Збираємо список ідентифікаторів
                        .map(courseIds -> {
                            tutor.setCourseIds(courseIds); // Прив'язуємо ідентифікатори курсів до викладача
                            return tutor;
                        })
        );

        // Повертаємо список викладачів з прив'язаними ідентифікаторами курсів у форматі JSON
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(tutorsWithCourseIds, Tutor.class);
    }
}

