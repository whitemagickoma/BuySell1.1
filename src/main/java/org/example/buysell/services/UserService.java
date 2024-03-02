package org.example.buysell.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.buysell.dao.UserDAO;
import org.example.buysell.dto.ResponseDTO;
import org.example.buysell.dto.UserDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDao;

    /**
     * Создание нового пользователя
     * @param userDto вся информация относящиеся к пользователю
     * @return возвращает статус операции
     */
    public boolean createUser(UserDTO userDto) {
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

    /**
     * Получение всех пользователей
     * @return возвращает лист пользователей
     */
    public List<UserDTO> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Аутентификация пользователя
     * @param email Email пользователя для логина
     * @param password Пароль кабинета
     * @return возвращает статус операции
     */
    public boolean authenticateUser(String email, String password) {
        UserDTO userDto = userDao.getUserByEmail(email);
        return userDto != null && userDto.getPassword().equals(password);
    }

    /**
     * Редактирование данных пользователя по ID
     * @param userDto вся информация относящиеся к пользователю
     * @return возвращает обновленную информацию
     */
    public UserDTO editUserById(UserDTO userDto) {
        return userDao.editUserById(userDto);
    }

    /**
     * Удаление пользователя
     * @param id ID пользователя
     */
    public boolean deleteUser(Long id) {
        try {
            return userDao.deleteUser(id) == 1 ? true : false;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
