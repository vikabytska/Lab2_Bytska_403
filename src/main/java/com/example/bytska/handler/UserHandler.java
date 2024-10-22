package com.example.bytska.handler;

import com.example.bytska.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class UserHandler {

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        // Отримати параметр 'start', якщо не задано - починаємо з 0
        String startParam = request.queryParam("start").orElse("0");
        long start;

        try {
            start = Long.parseLong(startParam);
        } catch (NumberFormatException e) {
            start = 0; // якщо формат неправильний, починаємо з 0
        }

        // Створюємо Flux для користувачів
        Flux<User> userFlux = Flux.just(
                new User(1L, "vika.bytska", "11111", "vika@gmail.com"),
                new User(2L, "jane.smith", "22222", "jane@gmail.com"),
                new User(3L, "john.jane", "33333", "john@gmail.com"),
                new User(4L, "alica", "44444", "alica@gmail.com"),
                new User(5L, "bobbob", "55555", "bob@gmail.com"),
                new User(6L, "charlie123", "66666", "charlie@gmail.com")
        );

        // Повертаємо список користувачів у форматі JSON
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userFlux.skip(start), User.class);
    }
}



