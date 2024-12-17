package com.iric.spring.valid;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ResponseBody
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public CommonResult<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    //     log.error("[handleValidationExceptions]", ex);
    //     StringBuilder sb = new StringBuilder();
    //     ex.getBindingResult().getAllErrors().forEach(error -> {
    //         String fieldName = ((org.springframework.validation.FieldError) error).getField();
    //         String errorMessage = error.getDefaultMessage();
    //         sb.append(fieldName).append(":").append(errorMessage).append(";");
    //     });
    //     return CommonResult.error(sb.toString());
    // }
    //
    // /**
    //  * 处理系统异常，兜底处理所有的一切
    //  */
    // @ExceptionHandler(value = Exception.class)
    // public CommonResult<?> defaultExceptionHandler(Throwable ex) {
    //     log.error("[defaultExceptionHandler]", ex);
    //     // 返回 ERROR CommonResult
    //     return CommonResult.error(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMsg());
    // }

}
