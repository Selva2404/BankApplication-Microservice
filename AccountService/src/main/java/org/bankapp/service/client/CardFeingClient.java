package org.bankapp.service.client;

import org.bankapp.DTO.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="Cards", fallback = Cardsfallback.class)
public interface CardFeingClient {

    @GetMapping(value = "/api/findCard",consumes = "application/json")
    ResponseEntity<CardDTO> findCard(@RequestHeader("Trace-ID") String traceId, @RequestParam String mobileNumber);

}
