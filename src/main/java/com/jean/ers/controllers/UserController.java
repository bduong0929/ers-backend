package com.jean.ers.controllers;

import com.jean.ers.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/users")
public class UserController {
    @Inject
    private UserService userService;
}
