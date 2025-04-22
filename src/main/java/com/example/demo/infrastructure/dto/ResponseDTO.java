package com.example.demo.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String code;
    private String message;
    private Object data;

    public ResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDTO(String codeErrorNotFound) {
    }
}