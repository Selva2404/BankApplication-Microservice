package org.bankapp.service.client;

import org.bankapp.DTO.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", fallback =  LoansfallBack.class)
public interface LoanFeingClient {

    @GetMapping(value = "/api/findLoan", consumes = "application/json")
    ResponseEntity<LoanDTO> findLoan(@RequestHeader("Trace-ID") String traceId, @RequestParam String mobileNumber);

}
