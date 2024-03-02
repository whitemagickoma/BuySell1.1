package org.example.buysell.dao;

import org.example.buysell.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Добавления нового пользователя в базу данных
     * @param userDto вся информация о пользователе
     */
    public void createUser(UserDTO userDto) {
        String sql = "INSERT INTO users (" +
                "name," +
                "email," +
                "phone_number," +
                "password," +
                "active)" +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, userDto.getName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getPassword(), userDto.isActive());
    }

    /**
     * Получение пользователя по Email с базы данных
     * @param email email пользователя
     * @return возвращает userDto объект
     */
    public UserDTO getUserByEmail(String email) {
        try {
            String sql = "SELECT *" +
                    "FROM users" +
                    "WHERE email = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(UserDTO.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Получение всех пользователей с базы данных
     * @return возвращает лист userDto объектов
     */
    public List<UserDTO> getAllUsers() {
        String sql = "SELECT * " +
                "FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserDTO.class));
    }

    /**
     * Редактирование данных пользователя по ID в базе данных
     * @param userDto вся информация отнасящиеся пользователю
     */
    public UserDTO editUserById(UserDTO userDto) {
        String sql = "UPDATE users " +
                "SET " +
                "name = ?," +
                "email = ?," +
                "phone_number = ?," +
                "password = ?," +
                "active = ?" +
                "WHERE id = ?";
        int changed = jdbcTemplate.update(sql, userDto.getName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getPassword(), userDto.isActive(), userDto.getId());
        if (changed > 0) {
            return userDto;
        } else {
            return null;
        }
    }

    /**
     * Удаление пользователя с базы данных
     * @param id ID пользователя
     */
    public int deleteUser(Long id) {
        String sql = "DELETE\n" +
                "FROM users\n" +
                "WHERE id = ?";
        Object[] args = {id};
        return jdbcTemplate.update(sql, args);
    }
}