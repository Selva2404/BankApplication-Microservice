package org.bankapp.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bankapp.DTO.AccountDtDTO;
import org.bankapp.DTO.CustomerDTO;
import org.bankapp.DTO.ErrorResponseDTO;
import org.bankapp.DTO.ResponseDTO;
import org.bankapp.constant.Constant;
import org.bankapp.service.iAccountService;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api",produces = MediaType.APPLICATION_JSON_VALUE)

@Tag(
        name = "CRUD Operations for Account REST API",
        description = "in this controller used for create, update, retrieve and delete for the account details"
)
@RefreshScope
public class AccountController {

    iAccountService iaccountService;
    AccountDtDTO accountDtDTO;

    public AccountController(iAccountService iaccountService,AccountDtDTO accountDtDTO) {
        this.iaccountService = iaccountService;
        this.accountDtDTO = accountDtDTO;
    }

    //@Autowired
    //public AccountController(AccountDtDTO accountDtDTO) {
    //    this.accountDtDTO = accountDtDTO;
    //}
    @Operation(
            summary = "Create Account details",
            description = "REST API used to Create customer and Account details from DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = " HTTP status create"
    )
    @PostMapping("/Create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody @Valid CustomerDTO customerDTO){
        iaccountService.CreateAccount(customerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(Constant.STATUS_201,Constant.message_201));
    }
    @Operation(
            summary = "fetch Account details",
            description = "REST API used to fetch Account details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status fetch"
    )

    @GetMapping("/fetchAccount")
    public ResponseEntity<CustomerDTO> fetchAccount(@RequestParam String mobileNumber){
        CustomerDTO customerDTO=iaccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }
    @Operation(
            summary = "Update Account details",
            description = "REST API used to Update Account details from DB"
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
    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDTO> updateAccount(@RequestBody @Valid CustomerDTO customerDTO){
        boolean status=iaccountService.updateAccountDetails(customerDTO);
        if(status) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("200", "account details updated successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("500", "internal server error"));
        }
    }
    @Operation(
            summary = "delete Account details",
            description = "REST API used to delete Account details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status delete"
    )
    @DeleteMapping("/RemoveAccount")
    public ResponseEntity<ResponseDTO> removeAccount(@RequestParam String mobileNumber){

        iaccountService.removeAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("200", Constant.message_201));
    }

    @Operation(
            summary = "delete Account details",
            description = "REST API used to delete Account details from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status delete"
    )
    @GetMapping("/config-info")
    public ResponseEntity<AccountDtDTO> getDBDetails(){
        System.out.println(accountDtDTO);
        return ResponseEntity.status(HttpStatus.OK).body(accountDtDTO);
    }



}
