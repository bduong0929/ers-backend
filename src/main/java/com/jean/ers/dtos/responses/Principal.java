package com.jean.ers.dtos.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Principal {
    private String id;
    private String username;
    private String email;
    private String givenName;
    private String surname;
    private String role;

    private String token;

    public Principal(String id, String username, String email, String givenName, String surname, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.role = role;
    }
}
