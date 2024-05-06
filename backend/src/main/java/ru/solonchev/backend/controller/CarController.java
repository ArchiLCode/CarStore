package ru.solonchev.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solonchev.backend.dto.car.CarAddRequest;
import ru.solonchev.backend.model.Car;
import ru.solonchev.backend.service.CarService;
import ru.solonchev.backend.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/car-store")
@RequiredArgsConstructor
public class CarController {
    private final UserService userService;
    private final CarService carService;

    private Car carAddRequestToCar(CarAddRequest carAddRequest) {
        return Car.builder()
                .name(carAddRequest.getName())
                .price(carAddRequest.getPrice())
                .description(carAddRequest.getDescription())
                .imgLink(carAddRequest.getImgLink())
                .build();
    }

    @PostMapping("/cars")
    public ResponseEntity<Void> addPizza(
            @RequestBody CarAddRequest carAddRequest
    ) {
        carService.createCar(carAddRequestToCar(carAddRequest));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/car/{carId}")
    public ResponseEntity<Void> addCarToUser(
            @PathVariable("userId") Long userId,
            @PathVariable("carId") Long carId
    ) {
        carService.addCarToUser(userId, carId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(
                carService.getAllCars()
        );
    }

    @GetMapping("/cars/order/name")
    public ResponseEntity<List<Car>> getAllCarsOrderedByName() {
        return ResponseEntity.ok().body(carService.getCarsOrderedByName());
    }

    @GetMapping("/cars/order/price")
    public ResponseEntity<List<Car>> getAllCarsOrderedByPrice() {
        return ResponseEntity.ok().body(carService.getCarsOrderedByPrice());
    }

    @GetMapping("/users/{userId}/cars")
    public ResponseEntity<List<Car>> getCarsOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(carService.getCarsOfUser(userId));
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long carId) {
        carService.deleteCarById(carId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}/car/{carId}")
    public ResponseEntity<Void> deletePizzaOfUser(
            @PathVariable("userId") Long userId,
            @PathVariable("carId") Long carId
    ) {
        carService.deleteCarAtUser(userId, carId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}/car")
    public ResponseEntity<Void> deleteAllPizzasOfUser(@PathVariable("userId") Long userId) {
        userService.deleteAllCarsAtUser(userId);
        return ResponseEntity.ok().build();
    }
}

