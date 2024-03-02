package org.example.buysell.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtility {

    public static ResponseEntity<ResponseDTO> generateResponse(boolean success, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(success);
        responseDTO.setMessage(message);

        if (success) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
    }
}
