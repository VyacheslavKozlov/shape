package ru.vyacheslavkozlov.firstrunday.dto;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
