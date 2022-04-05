package ru.vyacheslavkozlov.firstrunday.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequestDTO {
    private String email;
    private String password;
}
