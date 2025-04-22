package com.example.demo.infrastructure.dto;

import com.example.demo.infrastructure.utils.Constants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSubscriptionDTO {

    @NotNull(message = Constants.ERROR_BLANK_DATA)
    @DecimalMin(value = "0.0", inclusive = false, message = Constants.ERROR_INPUT_DATA)
    @Digits(integer = 10, fraction = 2, message = Constants.ERROR_INPUT_DATA)
    private Float price;

    @Pattern(regexp = Constants.ALPHANUMERIC_SPACE_REGEX, message = Constants.ERROR_INPUT_DATA)
    @NotBlank(message = Constants.ERROR_BLANK_DATA)
    @Size(max = 255, message = Constants.ERROR_DATA_LENGTH)
    private String partner;
}

