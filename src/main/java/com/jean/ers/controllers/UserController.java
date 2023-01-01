package com.jean.ers.controllers;

import com.jean.ers.dtos.requests.LoginRequest;
import com.jean.ers.dtos.requests.RegisterRequest;
import com.jean.ers.dtos.responses.Principal;
import com.jean.ers.models.User;
import com.jean.ers.services.TokenService;
import com.jean.ers.services.UserService;
import com.jean.ers.utils.custom_exceptions.RegisterException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/users")
public class UserController {
    @Inject
    private UserService userService;

    @Inject
    private TokenService tokenService;

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public User registerNewUser(RegisterRequest req) {
        User user;
        if (userService.isValidUsername(req.getUsername())) {
            if (userService.isUniqueUsername(req.getUsername())) {
                if (userService.isValidEmail(req.getEmail())) {
                    if (userService.isUniqueEmail(req.getEmail())) {
                        if (userService.isValidPassword(req.getPassword1())) {
                            if (userService.isSamePassword(req.getPassword1(), req.getPassword2())) {
                                user = userService.saveUser(req);
                            } else throw new RegisterException("Passwords do not match");
                        } else throw new RegisterException("Password needs to be 8 characters, at least one letter and one number");
                    } else throw new RegisterException("Email is already taken");
                } else throw new RegisterException("Incorrect email");
            } else throw new RegisterException("Username is already taken");
        } else throw new RegisterException("Username needs to be 8-20 characters");
        return user;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Principal loginUser(LoginRequest req) {
        Principal principal = userService.login(req);
        String token = tokenService.generateToken(principal);
        principal.setToken(token);
        return principal;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

}
