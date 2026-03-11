package com.gateway.bankgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

@SpringBootApplication
public class BankgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankgatewayApplication.class, args);
	}

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Customer Service route
                .route("account",r -> r.path("/account/**")
                        .filters(f -> f.stripPrefix(1)
                                .circuitBreaker(c-> c.setName("AccountCircuitBeaker")
                                        .setFallbackUri("forward:/account-fallback")
                                ))
                        .uri("lb://ACCOUNT"))
                .route("loans", r -> r.path("/loans/**")
                        .filters(f -> f.stripPrefix(1)
                                        .retry(re->re.setRetries(3)
                                                .setMethods(HttpMethod.GET)
                                                .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)
                                //.setFallbackUri("forward:/loans-fallback")
                                ))
                         .uri("lb://LOANS"))
                .route("cards", r -> r.path("/cards/**")
                        .filters(f -> f.stripPrefix(1)
                                        //.requestRateLimiter(c->c.setRateLimiter(rateLimiter()).setKeyResolver(userKeyResolver()))
                                )
                        .uri("lb://CARDS"))
                .build();
    }

    @Bean
    public RedisRateLimiter rateLimiter() {
        return new RedisRateLimiter (50, 50,50) ;
    }

    @Bean
    KeyResolver userKeyResolver () {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("anu"))).defaultIfEmpty("selva");
    }
}
