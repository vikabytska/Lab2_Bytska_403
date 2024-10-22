package com.example.bytska.router;

import com.example.bytska.handler.CourseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class CourseRouter {
    @Bean
    public RouterFunction<ServerResponse> courseRoutes(CourseHandler courseHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/courses"), courseHandler::getCourseIdsForTutors);
    }
}
