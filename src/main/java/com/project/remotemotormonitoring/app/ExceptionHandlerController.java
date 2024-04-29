package com.project.remotemotormonitoring.app;

import com.project.remotemotormonitoring.service.exceptions.Error;
import com.project.remotemotormonitoring.service.exceptions.FailedToSendEmailException;
import com.project.remotemotormonitoring.service.exceptions.ResourceAlreadyExists;
import com.project.remotemotormonitoring.service.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations ={ RestController.class})
@Slf4j
public class ExceptionHandlerController {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Error resourceNotFound(ResourceNotFoundException e) {
        log.info("Validation error: {}", e.getMessage());
        return Error.of(4, e.getMessage());
    }
    @ExceptionHandler(ResourceAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Error resourceNotFound(ResourceAlreadyExists e) {
        log.info("Validation error: {}", e.getMessage());
        return Error.of(4, e.getMessage());
    }

    @ExceptionHandler(FailedToSendEmailException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public @ResponseBody
    Error failedToSendEmail(FailedToSendEmailException e) {
        log.info("Validation error: {}", e.getMessage());
        return Error.of(4, e.getMessage());
    }
}
