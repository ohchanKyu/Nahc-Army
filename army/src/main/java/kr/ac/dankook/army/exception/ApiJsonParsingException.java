package kr.ac.dankook.army.exception;

import lombok.Getter;

@Getter
public class ApiJsonParsingException extends RuntimeException{
    private final ErrorCode errorCode;
    public ApiJsonParsingException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
