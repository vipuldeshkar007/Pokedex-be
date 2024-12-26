package com.pokedex.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ResponseDto<T> implements Serializable {
    private Integer httpStatus;
    private String message;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private T data;
    public ResponseDto(int httpStatusCode, T response, String message){
        this.httpStatus = httpStatusCode;
        this.data = response;
        this.message = message;
    }
}
