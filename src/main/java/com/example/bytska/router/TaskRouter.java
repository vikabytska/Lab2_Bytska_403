package com.example.bytska.router;


import com.example.bytska.handler.TaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class TaskRouter {
    @Bean
    public RouterFunction<ServerResponse> taskRoutes(TaskHandler taskHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/tasks"), taskHandler::getTasks);
    }
}
