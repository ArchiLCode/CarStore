package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.exception.ApiError;
import ru.solonchev.backend.exception.car.CarAlreadyExistAtUserException;
import ru.solonchev.backend.exception.car.CarAlreadyExistException;
import ru.solonchev.backend.exception.car.CarNotFoundAtUserException;
import ru.solonchev.backend.exception.car.CarNotFoundException;
import ru.solonchev.backend.exception.user.UserNotFoundException;
import ru.solonchev.backend.model.Car;
import ru.solonchev.backend.model.User;
import ru.solonchev.backend.repository.CarRepository;
import ru.solonchev.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public Car createCar(Car car) throws CarAlreadyExistException {
        if (carRepository.existsByName(car.getName())) {
            throw new CarAlreadyExistException();
        }
        car.setUsers(new ArrayList<>());
        return carRepository.save(car);
    }

    public Car changeCar(Car car) throws CarNotFoundException {
        if (!carRepository.existsById(car.getId())) {
            throw new CarNotFoundException();
        }
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCarById(Long id) throws CarNotFoundException {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException();
        }
        carRepository.deleteById(id);
    }

    public List<Car> getCarsOfUser(Long userId) throws CarNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new CarNotFoundException();
        }
        return user.get().getCars();
    }

    public void addCarToUser(Long userId, Long carId) throws ApiError {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException();
        }
        Car car = optionalCar.get();
        User user = optionalUser.get();
        if (user.getCars().contains(car)) {
            throw new CarAlreadyExistAtUserException();
        }
        car.addUser(userRepository.findById(userId).get());
        carRepository.save(car);
    }

    public void deleteCarAtUser(Long userId, Long carId) throws ApiError {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException();
        }
        Car car = optionalCar.get();
        User user = optionalUser.get();
        if (!user.getCars().contains(car)) {
            throw new CarNotFoundAtUserException();
        }
        car.removeUser(user);
        carRepository.save(car);
    }

    public List<Car> getCarsOrderedByName() {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Car> getCarsOrderedByPrice() {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

}
