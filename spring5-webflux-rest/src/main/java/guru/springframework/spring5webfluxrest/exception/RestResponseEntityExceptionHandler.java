package guru.springframework.spring5webfluxrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundException e) {
        ResourceErrorResponse error=new ResourceErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<ResourceErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

}
