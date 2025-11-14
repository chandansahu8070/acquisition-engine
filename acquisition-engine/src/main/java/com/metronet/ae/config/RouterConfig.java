package com.metronet.ae.config;


import com.metronet.ae.AEHandler.AEHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(AEHandler handler) {
        return route(POST("/api/v1/acquisitions").and(accept(APPLICATION_JSON)), handler::create)
                .andRoute(GET("/api/v1/acquisitions/{id}"), handler::getById)
                .andRoute(GET("/api/v1/acquisitions"), handler::getAll)
                .andRoute(PUT("/api/v1/acquisitions/{id}").and(accept(APPLICATION_JSON)), handler::update)
                .andRoute(DELETE("/api/v1/acquisitions/{id}"), handler::delete);
    }
}
