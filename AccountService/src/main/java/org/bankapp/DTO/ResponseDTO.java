package org.bankapp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "Response", description = "Schema holds Response details")
public class ResponseDTO {

    @Schema(description = "schema holds response code", example = "200")
    public String responseCode;
    @Schema(description = "schema holds response Message", example=" API response")
    public String responseMessage;

    public ResponseDTO(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
