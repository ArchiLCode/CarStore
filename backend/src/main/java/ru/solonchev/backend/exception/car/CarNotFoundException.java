package ru.solonchev.backend.exception.car;

import ru.solonchev.backend.exception.ApiError;

public class CarNotFoundException extends ApiError {
    private static final int code = 404;
    private static final String message = "Pizza Not Found";
    private static final String description = "Pizza with such id was not found";

    public CarNotFoundException() {
        super(code, message, description);
    }
}
