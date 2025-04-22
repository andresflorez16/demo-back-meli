package com.example.demo.infrastructure.utils;

public class Constants {

    public static final String NUMERIC_REGEX = "^[0-9]+$";
    public static final String DECIMAL_REGEX = "^[0-9]+(\\.[0-9]+)?$";
    public static final String ALPHANUMERIC_SPACE_REGEX = "^[\\p{L}A-Za-z0-9? _:]+$";
    public static final String NAMES_REGEX = "^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ\\s]+$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String ERROR_INPUT_DATA = "Error input data";
    public static final String ERROR_BLANK_DATA = "Not null or blank data";
    public static final String ERROR_DATA_LENGTH = "Error data length";
    public static final String ERROR_UNAUTHORIZED = "User unauthorized";
    public static final String INTEGRITY_ERROR = "Integrity error";
    public static final String TYPE_ERROR = "Some attributes are not valid";
    public static final String ERROR_UNPROCESSABLE_ENTITY = "Error unprocessable entity";
    public static final String NOT_FOUND_ERROR = "Resource not found";
    public static final String CODE_OK = "01";
    public static final String CODE_ERROR_BAD_REQUEST = "02";
    public static final String CODE_ERROR_SAVE_DATABASE = "03";
    public static final String CODE_ERROR_INVALID_STATE = "04";
    public static final String CODE_ERROR_NOT_FOUND = "05";
    public static final String CODE_INTERNAL_ERROR = "06";
    public static final String CODE_ERROR_UNAUTHORIZED = "07";

}