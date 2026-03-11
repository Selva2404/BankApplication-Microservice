package org.cardservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Schema(name = "Card", description = "Schema is hold Card details" )
public class CardDTO {

	@Schema(description = "Schema is hold Card Number details", example="10987654356789")
	@NotNull(message="card Number is null or empty")
	private Long cardNumber;

	@Schema(description = "Schema is hold mobile number details", example="9597726473")
	@NotEmpty(message="mobile Number is null or empty")
	@Pattern(regexp="^[6-9]\\d{9,11}$")
	private String mobileNumber;

	@Schema(description = "Schema is hold  card Type details", example="business credit card")
	@NotEmpty(message="card Type is null or empty")
	private String cardType;

	@Schema(description = "Schema is hold  card limit details", example="100000")
	@NotEmpty(message="card mount is null or empty")
	private String cardLimit;

	@Schema(description = "Schema is hold bill amount details", example="2000")
	@NotEmpty(message="bill amount is null or empty")
	private String billAmount;

	@Schema(description = "Schema is hold outstanding Amount details", example="3000")
	@NotEmpty(message="outstanding Amount is null or empty")
	private String outstandingAmount;

}
