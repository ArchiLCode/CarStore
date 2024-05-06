package ru.solonchev.backend.exception.car;

import ru.solonchev.backend.exception.ApiError;

public class CarNotFoundAtUserException extends ApiError {
    private static final int code = 400;
    private static final String message = "Pizza not found at User";
    private static final String description = "Pizza not found at User";

    public CarNotFoundAtUserException() {
        super(code, message, description);
    }
}
