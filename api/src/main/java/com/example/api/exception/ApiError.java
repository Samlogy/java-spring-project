package com.example.api.exception;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiError {
    private int status;
    private String message;
    private Map<String, Object> details;

    public Map<String, Object> getDetails() {
        return details;
    }

    public void addDetail(String key, Object value) {
        this.details.put(key, value);
    }
}
