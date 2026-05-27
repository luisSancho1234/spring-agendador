package com.project.scheduler.models.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public record ResponseDto<T>(
        HttpStatus httpStatus,
        T response
) {
    public static <T> ResponseEntity<ResponseDto<T>> ok(T data) {
        return ResponseEntity.ok(
                new ResponseDto<>(HttpStatus.resolve(200), data)
        );
    }
    public static <T> ResponseEntity<ResponseDto<T>> created(Long id,T data) {
        return ResponseEntity.created(URI.create("/usuarios/" + id)).body(
                new ResponseDto<>(HttpStatus.resolve(201), data)
        );
    }

    public static <T> ResponseEntity<ResponseDto<T>> error(HttpStatus status) {
        return ResponseEntity.status(status)
                .body(new ResponseDto<>(status, null));
    }
}