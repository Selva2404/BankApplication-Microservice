package org.bankapp.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.ServletRequest;
import lombok.AllArgsConstructor;
import org.bankapp.service.iCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.bankapp.DTO.CustomerDtDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/api" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

     iCustomerService iCustomerService;

    @Operation(
            summary = "fetch customer details",
            description = "REST API used to fetch Customer, Account, Cards and Loans details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status fetch"
    )
    @GetMapping("/findCustomers")
    public ResponseEntity<CustomerDtDTO> findCustomer(@RequestHeader("Trace-ID") String traceId, @RequestParam String mobileNumber){
        CustomerDtDTO customerDtDTO=iCustomerService.findCustomer(traceId,mobileNumber);
            System.out.println("trace the application flow:{} "+traceId);
        return  ResponseEntity.status(HttpStatus.OK).body(customerDtDTO);
    }
}
