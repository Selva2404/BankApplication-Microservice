package org.bankapp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Schema(name = "Loan", description = "Schema is hold Card details" )
public class LoanDTO {

	@Schema(description = "Schema is hold loan Number details", example="10987654356789")
	@NotNull(message="loan Id is null or empty")
	private Long loanId;


	@Schema(description = "Schema is hold mobile number details", example="9597726473")
	@NotEmpty(message="mobile Number is null or empty")
	@Pattern(regexp="^[6-9]\\d{9,11}$")
	private String mobileNumber;

	@Schema(description = "Schema is hold  loan Type details", example="business loan")
	@NotEmpty(message="loan Type is null or empty")
	private String loanType;

	@Schema(description = "Schema is hold  loan amount details", example="100000")
	@NotEmpty(message="Loan mount is null or empty")
	private String LoanAmount;

	@Schema(description = "Schema is hold  amount paid details", example="10000")
	@NotEmpty(message="amount paid is null or empty")
	private String amountPaid;

	@Schema(description = "Schema is hold outstanding Amount details", example="90000")
	@NotEmpty(message="outstanding Amount is null or empty")
	private String outstandingAmount;

}
