package com.example.api.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseError {
    private boolean success = false;
    private String message;
    private Object validations;
}