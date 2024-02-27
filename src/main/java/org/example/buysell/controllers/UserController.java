package org.example.buysell.controllers;

import org.example.buysell.dto.UserDto;
import org.example.buysell.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Регистрация нового пользователя
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody @Validated UserDto userDto) {

        boolean created = userService.createUser(userDto);
        if (created) {
            return ResponseEntity.ok().body(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Получение пользователей
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Логин
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        boolean login = userService.authenticateUser(userDto.getEmail(), userDto.getPassword());
        if (login) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    // Изменение данных пользователя по ID
    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> editUserById(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        UserDto updatedUser = userService.editUserById(userDto);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление пользователя по ID
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return HttpStatus.OK;
    }
}
