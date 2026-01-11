package com.gateway.bankgateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Order(1)
@Component
public class requestfilter implements GlobalFilter {





    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = exchange.getRequest().getHeaders().getFirst(FillterUtility.id);

        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString();
        }

        ServerHttpRequest mutatedRequest = exchange.getRequest()
                .mutate()
                .header(FillterUtility.id, traceId)
                .build();

        exchange.getAttributes().put(FillterUtility.id, traceId);

        String finalTraceId = traceId;

        return chain.filter(exchange.mutate().request(mutatedRequest).build())
                .contextWrite(ctx -> ctx.put(FillterUtility.id, finalTraceId))  // Reactor context
                .then(Mono.fromRunnable(() ->{
                    exchange.getResponse().getHeaders().add(FillterUtility.id, finalTraceId);
    }));
    }
}
