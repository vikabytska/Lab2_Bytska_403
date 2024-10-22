package com.example.bytska.handler;


import com.example.bytska.entity.Tutor;
import com.example.bytska.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class TutorHandler {

    public Mono<ServerResponse> getTutors(ServerRequest request) {
        // Створюємо Flux для викладачів
        Flux<Tutor> tutorFlux = Flux.just(
                new Tutor(1L, "Vika", "Bytska", null, 1L, Arrays.asList(1L, 2L)),
                new Tutor(2L, "Jane", "Smith", null, 2L, Arrays.asList(3L)),
                new Tutor(3L, "John", "Jane", null, 3L, Arrays.asList(4L))
        );

        // Отримуємо користувачів
        Flux<User> userFlux = Flux.just(
                new User(1L, "vika.bytska", "11111", "vika@gmail.com"),
                new User(2L, "jane.smith", "22222", "jane@gmail.com"),
                new User(3L, "john.jane", "33333", "john@gmail.com")
        );

        // Поєднуємо кожного Tutor з відповідним User на основі userId
        Flux<Tutor> tutorsWithUsers = tutorFlux.flatMap(tutor ->
                userFlux.filter(user -> user.getId() == tutor.getUserId())
                        .next() // Отримуємо перший відповідний User або Mono.empty()
                        .map(user -> {
                            tutor.setUser(user); // Прив'язуємо користувача до викладача
                            return tutor;
                        })
                        .defaultIfEmpty(tutor) // Повертаємо Tutor без змін, якщо User не знайдено
        );

        // Повертаємо список викладачів з користувачами у форматі JSON
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(tutorsWithUsers, Tutor.class);
    }
}








