package com.loanService.Controller;

import com.loanService.DTO.ErrorResponseDTO;
import com.loanService.DTO.LoanDTO;
import com.loanService.DTO.LoanDtDTO;
import com.loanService.DTO.ResponseDTO;

import com.loanService.service.impl.LoanServiceImpl;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Tag(
        name = "CRUD Operations for loan REST API",
        description = "in this controller used for create, update, retrieve and delete for the loan details"
)
public class LoanController {


    private final LoanDtDTO loanDtDTO;

    LoanServiceImpl loanServiceImpl;


    @Operation(
            summary = "Create loan details",
            description = "REST API used to Create Loan details from DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = " HTTP status create"
    )
    @PostMapping("/CreateLoan")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam @NotEmpty(message="mobile Number is null or empty") @Pattern(regexp="^[6-9]\\d{9,11}$") String mobileNumber) {

        loanServiceImpl.createLoan(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDTO("201", "Successfully Created Loan"));

    }
    @Operation(
            summary = "fetch loan details",
            description = "REST API used to fetch loan details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status fetch"
    )
    @GetMapping("/findLoan")
    public ResponseEntity<LoanDTO> findLoan(@RequestHeader("Trace-ID") String tarceId,@RequestParam @NotEmpty(message="mobile Number is null or empty") @Pattern(regexp="^[6-9]\\d{9,11}$") String mobileNumber) {
       LoanDTO loanDTO=loanServiceImpl.findLoan(mobileNumber);
        System.out.println(" loan controller {}"+tarceId);
        return ResponseEntity
                .status(HttpStatus.OK)
               .body(loanDTO);
    }
    @Operation(
            summary = "Update loan details",
            description = "REST API used to Update loan details from DB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status update"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )

            )
    }
    )
    @PutMapping("/updateLoan")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestBody @Valid LoanDTO loanDTO) {
        //log.debug("loanDTO=>"+loanDTO);
        boolean status=loanServiceImpl.updateLoan(loanDTO);

        if(status) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("200", "details successfully updated"));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("500", "internal server error"));
        }
    }
    @Operation(
            summary = "delete loan details",
            description = "REST API used to delete loan details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status delete"
    )
    @DeleteMapping("/deleteLoan")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestParam @NotEmpty(message="mobile Number is null or empty") @Pattern(regexp="^[6-9]\\d{9,11}$") String mobileNumber) {

        loanServiceImpl.deleteLoan(mobileNumber);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("200", "details successfully deleted"));
    }

    @Retry(name ="getDBDetails", fallbackMethod = "getDBDetailsfall")
    @GetMapping("/config-info")
    public ResponseEntity<LoanDtDTO> getDBDetails(){
        System.out.println(loanDtDTO);
        throw new RuntimeException();
       // return ResponseEntity.status(HttpStatus.OK).body(loanDtDTO);
    }


    public ResponseEntity<String> getDBDetailsfall(Throwable t){
        System.out.println("retry working");
        return ResponseEntity.status(HttpStatus.OK).body("selva");
    }
}
