package com.bank.app.accounts.exception.handler;

import com.bank.app.accounts.dto.ErrorResponseDto;
import com.bank.app.accounts.exception.ResourceAlreadyExistsException;
import com.bank.app.accounts.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public final ErrorResponseDto handleResourceNotFoundException(Exception ex, WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.NOT_FOUND, ex.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public final ErrorResponseDto handleResourceAlreadyExistsException(Exception ex, WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(),
                LocalDateTime.now());
    }

    /**
     * Handles all the run time exceptions
     * Called only if no any other specific exception class has been found in this class
     *
     * @param ex
     * @param webRequest
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ErrorResponseDto handleGlobalException(Exception ex, WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(), LocalDateTime.now());
    }

    /**
     * Handles the exceptions thrown from the validations in DTO classes
     *
     * @param ex      the exception to handle
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorResponseDto errorResponseDto =
                new ErrorResponseDto(request.getDescription(false), HttpStatus.BAD_REQUEST,
                        ex.getFieldErrors().stream()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .collect(Collectors.joining(", ")), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
