package com.jean.ers.dtos.responses;

import com.jean.ers.models.Role;
import com.jean.ers.models.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString()
public class UserListResponse {
    private String id;
    private String username;
    private String email;
    private String givenName;
    private String surname;
    private String role;

    public UserListResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.givenName = user.getGivenName();
        this.surname = user.getSurname();
        this.role = user.getRole().getRole();
    }
}
