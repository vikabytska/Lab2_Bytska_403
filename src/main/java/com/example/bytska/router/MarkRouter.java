package com.example.bytska.router;

import com.example.bytska.handler.MarkHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class MarkRouter {
    @Bean
    public RouterFunction<ServerResponse> markRoutes(MarkHandler markHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/marks"), markHandler::getMarks);
    }
}
