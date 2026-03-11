package org.bankapp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;



@Schema(name = "ErrorResponse", description = "Schema holds Error details")
public class ErrorResponseDTO
{
    @Schema(description = "schema holds api Path")
    public String apiPath;
    @Schema(description = "schema holds error Code")
    public HttpStatus errorCode;
    @Schema(description = "schema holds error Message")
    public String errorMessage;
    @Schema(description = "schema holds error Time")
    public LocalDateTime errorTime;
    public ErrorResponseDTO(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }
}

