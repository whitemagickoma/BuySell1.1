package org.example.buysell.dao;

import lombok.RequiredArgsConstructor;
import org.example.buysell.dto.ImageDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageDao {

    private final JdbcTemplate jdbcTemplate;

    // Создание картинки
    public void saveImage(ImageDto imageDto) {
        String sql = "INSERT INTO images (product_id, name, size, is_preview_image, bytes)";
        jdbcTemplate.update(sql, imageDto.getProductDto().getId(), imageDto.getName(), imageDto.getSize(), imageDto.isPreviewImage(), imageDto.getBytes());
    }

    // Получение картинки по ID
    public List<ImageDto> getImageById(Long id) {
        String sql = "SELECT * FROM  images WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ImageDto.class));
    }

    // Удаление картинки
    public void deleteImage(Long id) {
        String sql = "DELETE FROM images WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
