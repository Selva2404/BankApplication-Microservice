package org.cardservice.controller;

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
import org.cardservice.DTO.CardDTO;
import org.cardservice.DTO.CardsDtDTO;
import org.cardservice.DTO.ErrorResponseDTO;
import org.cardservice.DTO.ResponseDTO;
import org.cardservice.service.impl.CardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Tag(
        name = "CRUD Operations for card REST API",
        description = "in this controller used for create, update, retrieve and delete for the card details"
)
public class CardController {


    CardServiceImpl cardServiceImpl;
    CardsDtDTO cardsDtDTO;

    @Operation(
            summary = "Create card details",
            description = "REST API used to Create card details from DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = " HTTP status create"
    )
    @PostMapping("/CreateCard")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam @NotEmpty(message="mobile Number is null or empty") @Pattern(regexp="^[6-9]\\d{9,11}$") String mobileNumber) {

        System.out.println("   cardDTO  ="+mobileNumber);
        cardServiceImpl.createCardDetails(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDTO("201","Successfully card Created "));

    }
    @Operation(
            summary = "fetch card details",
            description = "REST API used to fetch card details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status fetch"
    )
    @GetMapping("/findCard")
    public ResponseEntity<CardDTO> findCard(@RequestHeader("Trace-ID") String traceId,@RequestParam @NotEmpty(message="mobile Number is null or empty") @Pattern(regexp="^[6-9]\\d{9,11}$") String mobileNumber) {
        CardDTO cardDTO= cardServiceImpl.findCardDetails(mobileNumber);
        System.out.println("  card controller:{} "+traceId);
        return ResponseEntity
                .status(HttpStatus.OK)
               .body(cardDTO);
    }
    @Operation(
            summary = "Update card details",
            description = "REST API used to Update card details from DB"
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

    })
    @PutMapping("/updateCard")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestBody @Valid CardDTO cardDTO) {
       // log.debug("loanDTO=>"+ cardDTO);
        boolean status= cardServiceImpl.updateCardDetails(cardDTO);

        if(status) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("200", "card details successfully updated"));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("500", "internal server error"));
        }
    }
    @Operation(
            summary = "delete card details",
            description = "REST API used to delete card details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status delete"
    )
    @DeleteMapping("/deleteCard")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestParam @NotEmpty(message="mobile Number is null or empty") @Pattern(regexp="^[6-9]\\d{9,11}$") String mobileNumber) {

        cardServiceImpl.deleteCardDetails(mobileNumber);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("200", "card details successfully deleted"));
    }

    @GetMapping("/config-info")
    public ResponseEntity<CardsDtDTO> getLoanConfig() {

        //cardServiceImpl.deleteCardDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDtDTO);
    }


}
//ab -n 20 -c 10 http://localhost:8200/cards/config-info