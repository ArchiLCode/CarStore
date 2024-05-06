package ru.solonchev.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solonchev.backend.dto.car.CarAddRequest;
import ru.solonchev.backend.dto.user.request.UserAddRequest;
import ru.solonchev.backend.dto.user.response.UserResponse;
import ru.solonchev.backend.model.Car;
import ru.solonchev.backend.model.User;
import ru.solonchev.backend.service.CarService;
import ru.solonchev.backend.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/car-store")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private User userAddRequestToUser(UserAddRequest userAddRequest) {
        return User.builder()
                .email(userAddRequest.getEmail())
                .password(userAddRequest.getPassword())
                .firstName(userAddRequest.getFirstName())
                .lastName(userAddRequest.getLastName())
                .build();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addUser(
            @RequestBody UserAddRequest user
    ) {
        userService.addUser(userAddRequestToUser(user));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok().body(new UserResponse(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        ));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(
                userService.getAllUsers()
        );
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}
