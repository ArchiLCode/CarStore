package ru.solonchev.backend.exception.car;

import ru.solonchev.backend.exception.ApiError;

public class CarAlreadyExistAtUserException extends ApiError {
    private static final int code = 400;
    private static final String message = "Pizza already exist at user";
    private static final String description = "Pizza already exist at user";

    public CarAlreadyExistAtUserException() {
        super(code, message, description);
    }
}
