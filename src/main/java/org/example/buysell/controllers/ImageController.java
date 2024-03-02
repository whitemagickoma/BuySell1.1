package org.example.buysell.controllers;

import lombok.RequiredArgsConstructor;
import org.example.buysell.dao.ImageDAO;
import org.example.buysell.dto.ImageDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/image")
public class ImageController {

    private final ImageDAO imageDao;

    /**
     * {@code Get /api/image/} <br/>
     * Используется для получения изображения по ID
     * @param id ID изображения
     * @param imageDto вся информация о изображении
     * @return возвращает статус операции
     */
    public ResponseEntity<?> getImageById(@PathVariable Long id, ImageDTO imageDto) {
        imageDao.getImageById(id);
        return ResponseEntity.ok().contentLength(imageDto.getSize()).body(new ByteArrayResource(imageDto.getBytes()));
    }
}
