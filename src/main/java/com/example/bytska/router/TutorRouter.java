package com.example.bytska.router;

import com.example.bytska.handler.TutorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class TutorRouter {
    @Bean
    public RouterFunction<ServerResponse> tutorRoutes(TutorHandler tutorHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/tutors"), tutorHandler::getTutors);
    }
}
