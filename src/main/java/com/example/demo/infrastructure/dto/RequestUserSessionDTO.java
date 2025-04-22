package com.example.demo.infrastructure.dto;

import com.example.demo.infrastructure.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUserSessionDTO {
    @Pattern(regexp = Constants.EMAIL_REGEX, message = Constants.ERROR_INPUT_DATA)
    @NotBlank(message = Constants.ERROR_BLANK_DATA)
    @Size(max = 255, message = Constants.ERROR_DATA_LENGTH)
    private String email;
}
