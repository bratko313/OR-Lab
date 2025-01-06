package fer.or.api.exceptions;

import fer.or.api.util.HeaderUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(405).headers(HeaderUtil.createHeaders("Not implemented", "Method not implemented for requested resource")).build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
        return ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "No request handler found")).build();
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.status(500).headers(HeaderUtil.createHeaders("Internal server error", "An unexpected error occurred, try again later")).build();
    }
}
