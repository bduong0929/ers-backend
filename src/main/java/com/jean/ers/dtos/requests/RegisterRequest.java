package com.jean.ers.dtos.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password1;
    private String password2;
    private String givenName;
    private String surname;
}
