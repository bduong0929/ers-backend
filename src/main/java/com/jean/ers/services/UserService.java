package com.jean.ers.services;

import com.jean.ers.daos.UserDAO;
import com.jean.ers.dtos.requests.RegisterRequest;
import com.jean.ers.models.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class UserService {
    @Inject
    private UserDAO userDAO;

    public User saveUser(RegisterRequest req) {
        User createdUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getEmail(), req.getPassword1(), req.getGivenName(), req.getSurname());
        userDAO.save(createdUser);
        return createdUser;
    }

    public boolean isValidUsername(String username) {

    }
}
