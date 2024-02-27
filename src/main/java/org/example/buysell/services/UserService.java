package org.example.buysell.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.buysell.dao.UserDao;
import org.example.buysell.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    // Создание пользователя
    public boolean createUser(UserDto userDto) {
        String email = userDto.getEmail();

        if (userDao.getUserByEmail(email) == null) {
            userDto.setActive(true);
            log.info("Saving new UserDto with email: {}", email);
            userDao.createUser(userDto);
            return true;
        } else {
            log.warn("User with email {} already exists", email);
            return false;
        }
    }

    // Получение пользователей
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    // Логин
    public boolean authenticateUser(String email, String password) {
        UserDto userDto = userDao.getUserByEmail(email);
        return userDto != null && userDto.getPassword().equals(password);
    }

    // Изменение данных пользователя по ID
    public UserDto editUserById(UserDto userDto) {
        return userDao.editUserById(userDto);
    }

    // Удаление пользователя по ID
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
