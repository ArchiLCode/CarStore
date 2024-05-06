package ru.solonchev.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.solonchev.backend.dto.exception.ApiErrorResponse;
import ru.solonchev.backend.exception.car.CarAlreadyExistAtUserException;
import ru.solonchev.backend.exception.car.CarAlreadyExistException;
import ru.solonchev.backend.exception.car.CarNotFoundAtUserException;
import ru.solonchev.backend.exception.car.CarNotFoundException;
import ru.solonchev.backend.exception.user.UserIsAlreadyExistException;
import ru.solonchev.backend.exception.user.UserNotFoundException;

@RestControllerAdvice
public class BackendApiExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> userAlreadyExist(UserIsAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> userNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.toApiErrorResponse());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> pizzaAlreadyExist(CarAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> pizzaNotFound(CarNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> pizzaAlreadyExistsAtUser(CarAlreadyExistAtUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> pizzaNotFoundAtUser(CarNotFoundAtUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }
}
