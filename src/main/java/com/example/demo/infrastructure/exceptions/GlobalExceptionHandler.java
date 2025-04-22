package com.example.demo.infrastructure.exceptions;

import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SubscriptionTypeValidationException.class)
    public ResponseEntity<ResponseDTO> handlerSubscriptionTypeValidationException(SubscriptionTypeValidationException ex) {
        ResponseDTO responseDto = new ResponseDTO(Constants.CODE_ERROR_BAD_REQUEST, Constants.TYPE_ERROR);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ResponseDTO errorResponse = new ResponseDTO(Constants.CODE_ERROR_SAVE_DATABASE, Constants.INTEGRITY_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponseDTO errorResponse = new ResponseDTO(Constants.CODE_ERROR_NOT_FOUND, Constants.NOT_FOUND_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La solicitud contiene datos inválidos: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseDTO> handleIllegalStateException(IllegalStateException ex) {
        ResponseDTO errorResponse = new ResponseDTO(Constants.CODE_ERROR_UNAUTHORIZED, Constants.ERROR_UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public final ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ResponseDTO responseDto = new ResponseDTO(Constants.CODE_ERROR_BAD_REQUEST, Constants.TYPE_ERROR);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ResponseDTO> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        ResponseDTO errorResponse = new ResponseDTO(Constants.CODE_ERROR_INVALID_STATE, Constants.ERROR_UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder("");
        for (ObjectError error : errors) {
            FieldError fieldError = (FieldError) error;
            errorMessage.append(error.getDefaultMessage()).append(" ").append(fieldError.getField());
        }
        String message = String.join(", ", errorMessage);
        ResponseDTO responseDto = new ResponseDTO(Constants.CODE_ERROR_BAD_REQUEST, Constants.TYPE_ERROR);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}

