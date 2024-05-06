package ru.solonchev.backend.exception.car;

import ru.solonchev.backend.exception.ApiError;

public class CarAlreadyExistException extends ApiError {
    private static final int code = 400;
    private static final String message = "Car already exists";
    private static final String description = "You are trying to add Car with already existing name";

    public CarAlreadyExistException() {
        super(code, message, description);
    }
}
