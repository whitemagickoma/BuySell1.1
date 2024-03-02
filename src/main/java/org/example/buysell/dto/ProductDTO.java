package org.example.buysell.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(min = 5, max = 64)
    private String title;

    @NotNull
    @Size(min = 5, max = 128)
    private String description;

    @NotNull
    @PositiveOrZero
    private int price;

    @NotNull
    private String author;

    @DateTimeFormat
    private LocalDateTime dateOfCreated;

    private List<ImageDTO> imageDTOList = new ArrayList<>();
    private Long previewImageId;
    private Long userId;

    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct (ImageDTO imageDto) {
        imageDto.setProductDto(this);
        imageDTOList.add(imageDto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO productDto = (ProductDTO) o;
        return Objects.equals(id, productDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
