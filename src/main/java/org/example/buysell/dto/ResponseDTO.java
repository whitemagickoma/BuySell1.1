package org.example.buysell.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDTO {

    private Boolean success;
    private String message;
}
