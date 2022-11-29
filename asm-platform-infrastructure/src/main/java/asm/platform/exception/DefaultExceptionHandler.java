package asm.platform.exception;

import asm.platform.bean.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author lvyz
 * 全局异常处理
 * @RestControllerAdvice（控制器中使用的注解是 @RestController）
 * @ControllerAdvice（控制器中使用的注解是 @RController）
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity handleException(HttpRequestMethodNotSupportedException ex) {
        log.error("不支持:{} 请求 {}", ex.getMethod(), ex.getMessage());
        return ResponseEntity.error("不支持' " + ex.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity notFount(RuntimeException ex) {
        log.error("运行时异常:{}", ex.getMessage());
        return ResponseEntity.error(com.platform.bean.ResultCode.RUNTIM_ERROR, "运行时异常:" + ex.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity businessException(BusinessException ex) {
        log.error("业务异常{}", ex.getMessage());
        return ResponseEntity.error(com.platform.bean.ResultCode.ERROR, ex.getMessage());
    }

    /**
     * 系统异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exceptionHandler(Exception ex) {
        log.error("系统异常{}", ex.getMessage());
        return ResponseEntity.error(com.platform.bean.ResultCode.SYS_ERROR, ex.getMessage());
    }

}
