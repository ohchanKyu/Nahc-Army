package kr.ac.dankook.army.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_ACCEPTABLE, "User Not Exists"),
    USER_NOT_INACTIVE(HttpStatus.UNAUTHORIZED,"User Not Inactive"),
    API_JSON_PARSING_ERROR(HttpStatus.NOT_ACCEPTABLE,"API JSON Parsing Error"),
    NoEntityException(HttpStatus.NOT_ACCEPTABLE,"No Entity Found");

    private final HttpStatus httpStatus;
    private final String message;
}
