package org.bankapp.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



@Valid
@Schema(name = "Customer", description = "Schema is hold Customer and Account details" )
public class CustomerDTO {

    public CustomerDTO() {
    }

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }
}
