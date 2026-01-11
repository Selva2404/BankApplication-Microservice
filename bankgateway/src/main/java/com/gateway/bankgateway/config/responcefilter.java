package com.gateway.bankgateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class responcefilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            String traceId=(String)exchange.getAttribute(FillterUtility.id);
            if(traceId != null){
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add(FillterUtility.id, traceId);
            }
        }));
    }
}
