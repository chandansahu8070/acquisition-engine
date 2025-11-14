package com.metronet.ae.AEHandler;

import com.metronet.ae.model.AcquisitionRequest;
import com.metronet.ae.service.AEService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component

public class AEHandler {
    private final AEService aeService;

    public AEHandler(AEService aeService) {
        this.aeService = aeService;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(AcquisitionRequest.class)
                .flatMap(req -> Mono.fromCallable(() -> aeService.create(req)))
                .flatMap(resp -> ServerResponse.status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return Mono.fromCallable(() -> aeService.getById(id))
                .flatMap(resp -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp))
                .onErrorResume(e -> ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return Mono.fromCallable(aeService::getAll)
                .flatMap(resp -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(AcquisitionRequest.class)
                .flatMap(req -> Mono.fromCallable(() -> aeService.update(id, req)))
                .flatMap(resp -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return Mono.fromRunnable(() -> aeService.delete(id))
                .then(ServerResponse.noContent().build());
    }
}
