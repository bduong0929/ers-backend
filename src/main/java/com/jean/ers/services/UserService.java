package com.jean.ers.services;

import com.jean.ers.daos.RoleDAO;
import com.jean.ers.daos.UserDAO;
import com.jean.ers.dtos.requests.LoginRequest;
import com.jean.ers.dtos.requests.RegisterRequest;
import com.jean.ers.dtos.responses.Principal;
import com.jean.ers.dtos.responses.UserListResponse;
import com.jean.ers.models.Role;
import com.jean.ers.models.User;
import com.jean.ers.utils.custom_exceptions.LoginException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class UserService {
    @Inject
    private UserDAO userDAO;

    @Inject
    private RoleDAO roleDAO;

    public User saveUser(RegisterRequest req) {
        Role role = roleDAO.findAll()
                .stream()
                .filter(r -> r.getRole().equals("DEFAULT"))
                .findAny()
                .orElse(null);

        User createdUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getEmail(), req.getPassword1(), req.getGivenName(), req.getSurname(), role);
        userDAO.save(createdUser);
        return createdUser;
    }

    public Principal login(LoginRequest req) {
        User foundUser = userDAO.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(req.getUsername()) && u.getPassword().equals(req.getPassword()))
                .findAny()
                .orElse(null);

        if (foundUser == null) throw new LoginException("Incorrect username or password");
        return new Principal(foundUser.getId(), foundUser.getUsername(), foundUser.getEmail(), foundUser.getGivenName(), foundUser.getSurname(), foundUser.getRole().getRole());
    }

    public List<UserListResponse> findAllUsers() {
        return userDAO.findAll().stream().map(UserListResponse::new).collect(Collectors.toList());
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isUniqueUsername(String username) {
        return userDAO.findAll().stream().noneMatch(u -> u.getUsername().equals(username));
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public boolean isUniqueEmail(String email) {
        return userDAO.findAll().stream().noneMatch(u -> u.getEmail().equals(email));
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password1, String password2) {
        return password1.equals(password2);
    }
}
