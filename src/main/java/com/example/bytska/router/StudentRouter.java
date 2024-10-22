package com.example.bytska.router;

import com.example.bytska.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class StudentRouter {
    @Bean
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler studentHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/students"), studentHandler::getStudents);
    }
}
