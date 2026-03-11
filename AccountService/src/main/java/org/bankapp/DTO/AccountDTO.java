package org.bankapp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;



@Schema(name = "Account", description = "Schema is hold Account details" )

public class AccountDTO {

    public AccountDTO() {

    }

    //@NotNull(message = "")
    //private Long customerId;
    @Schema(description = "Schema is hold account Number details", example="10987654356789")
    @NotNull(message = "invalid account number")
    private Long accountNumber;
    @Schema(description = "Schema is hold account Type details", example="saving Account")
    @NotNull(message = "invalid account Type")
    private String accountType;
    @Schema(description = "Schema is hold branch Address Type details", example="5/176 ,Namakkal")
    @NotNull(message = "invalid branch Address")
    private String branchAddress;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
