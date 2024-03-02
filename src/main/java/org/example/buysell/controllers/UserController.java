package org.example.buysell.controllers;

import lombok.RequiredArgsConstructor;
import org.example.buysell.dto.ResponseDTO;
import org.example.buysell.dto.ResponseUtility;
import org.example.buysell.dto.UserDTO;
import org.example.buysell.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ResponseDTO responseDTO;

    /**
     * {@code Post "/api/user/registration"} <br/>
     * Используется для создания нового пользователя
     * @param userDto вся информация относящиеся к юзеру
     * @return UserDTO - Возвращает статус операции true - если успешно, false - не успешно
     */
    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registration(@RequestBody @Validated UserDTO userDto) {

        boolean created = userService.createUser(userDto);
        if (created) {
            return ResponseEntity.ok().body(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * {@code Get /api/user} <br/>
     * Используется для получения всех пользователей
     * @return UserDTO - Возвращает статус true - если успешно
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * {@code Post /api/user/login} <br/>
     * Используется для аутентификации пользователя
     * @param userDto
     * @return String - Возвращает сообщении "Login successful" - если успешно, "Invalid email or password" -
     * если введена неправильно email или пароль
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
        boolean login = userService.authenticateUser(userDto.getEmail(), userDto.getPassword());
        if (login) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    /**
     * {@code Put /api/user/edit/{id}} <br/>
     * Используется для обновления данных пользователя по ID
     * @param id - ID пользователя
     * @param userDto - вся информация относящиеся юзеру
     * @return UserDTO - Возвращает success - елси успешно
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDTO> editUserById(@PathVariable Long id, @RequestBody UserDTO userDto) {
        userDto.setId(id);
        UserDTO updatedUser = userService.editUserById(userDto);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code Delete /api/user/delete/{id}} <br/>
     * Используется для удаления пользователя по ID
     * @param id - ID пользователя
     * @return HttpStatus - Возвращает статус OK - успешно
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") Long id) {
        boolean status = userService.deleteUser(id);
        String message = status ? "User deleted successfully" : "Failed to delete User";

        return ResponseUtility.generateResponse(status, message);
    }
}
