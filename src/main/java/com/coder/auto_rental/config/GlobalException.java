package com.coder.auto_rental.config;

import com.coder.auto_rental.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalException{
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        log.error("异常捕获:{}", e.getMessage());
       e.printStackTrace();
        return Result.fail().setMessage(e.getMessage());
    }

}
