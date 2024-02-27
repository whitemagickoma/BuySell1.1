package org.example.buysell.controllers;

import lombok.RequiredArgsConstructor;
import org.example.buysell.dao.ImageDao;
import org.example.buysell.dto.ImageDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/image")
public class ImageController {

    private final ImageDao imageDao;

    // Получение картинки по ID
    public ResponseEntity<?> getImageById(@PathVariable Long id, ImageDto imageDto) {
        imageDao.getImageById(id);
        return ResponseEntity.ok().contentLength(imageDto.getSize()).body(new ByteArrayResource(imageDto.getBytes()));
    }
}
