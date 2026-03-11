package org.cardservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter @Getter
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema holds Error details")
public class ErrorResponseDTO {


    @Schema(description = "schema holds api Path")
    public String apiPath;
    @Schema(description = "schema holds error Code")
    public HttpStatus errorCode;
    @Schema(description = "schema holds error Message")
    public String errorMessage;
    @Schema(description = "schema holds error Time")
    public LocalDateTime errorTime;
}
