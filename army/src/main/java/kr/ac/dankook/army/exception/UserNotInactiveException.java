package kr.ac.dankook.army.exception;

import lombok.Getter;

@Getter
public class UserNotInactiveException extends RuntimeException{
    private final ErrorCode errorCode;

    public UserNotInactiveException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
