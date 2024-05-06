package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.exception.user.UserIsAlreadyExistException;
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
public class UserService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public User addUser(User user) throws UserIsAlreadyExistException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserIsAlreadyExistException();
        }
        user.setCars(new ArrayList<>());
        return userRepository.save(user);
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        for (Car car : user.get().getCars()) {
            car.removeUser(user.get());
            if (car.getUsers().isEmpty()) {
                carRepository.delete(car);
            }
        }
        userRepository.deleteById(id);
    }

    public void deleteAllCarsAtUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        for (Car car : user.getCars()) {
            car.getUsers().remove(user);
        }
        user.setCars(new ArrayList<>());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
