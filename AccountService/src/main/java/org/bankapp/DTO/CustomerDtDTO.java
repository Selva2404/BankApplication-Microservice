package org.bankapp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CustomerDtDTO {

    @Schema(description = "Schema holds customer name", example = "selva")
    @NotEmpty(message = "enter email")
    @Size(min = 3,max = 25)
    private String customerName;
    @Schema(description = "Schema holds Email ID", example = "selva@gmail.com")
    @NotEmpty(message = "enter email")
    @Email(message = "invalid email id")
    private String email;
    @Schema(description = "Schema holds mobile number", example = "9597726473")
    @NotEmpty(message = "enter mobile number")
    // @Pattern(regexp ="")
    private String mobileNumber;
    @Schema(description = "Schema holds account details")
    //  @NotEmpty(message = "enter account details")
    private AccountDTO accountDTO;

    @Schema(description = "Schema holds Card details")
    private CardDTO cardDTO;

    @Schema(description = "Schema holds Loan details")
    private LoanDTO loanDTO;
}
