package org.cardservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
@Schema(name = "Response", description = "Schema holds Response details")
public class ResponseDTO {

	@Schema(description = "schema holds response code", example = "200")
	public String responseCode;
	@Schema(description = "schema holds response Message", example=" API response")
	public String responseMessage;

}
