package org.example.buysell.dao;

import org.example.buysell.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Создание пользователя
    public void createUser(UserDto userDto) {
        String sql = "INSERT INTO users (name, email, phone_number, password, active) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, userDto.getName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getPassword(), userDto.isActive());
    }

    // Получение пользователя по Email
    public UserDto getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(UserDto.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Получение пользователей
    public List<UserDto> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserDto.class));
    }

    // Изменение данных пользователя по ID
    public UserDto editUserById(UserDto userDto) {
        String sql = "UPDATE users SET name = ?, email = ?, phone_number = ?, password = ?, active = ? WHERE id = ?";
        int changed = jdbcTemplate.update(sql, userDto.getName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getPassword(), userDto.isActive(), userDto.getId());
        if (changed > 0) {
            return userDto;
        } else {
            return null;
        }
    }

    // Удаление пользователя по ID
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}