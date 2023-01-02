package com.jean.ers.controllers;

import com.jean.ers.dtos.requests.LoginRequest;
import com.jean.ers.dtos.responses.Principal;
import com.jean.ers.services.TokenService;
import com.jean.ers.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class AuthController {
    @Inject
    private TokenService tokenService;
    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Principal loginUser(LoginRequest req) {
        Principal principal = userService.login(req);
        String token = tokenService.generateToken(principal);
        principal.setToken(token);
        return principal;
    }
}
