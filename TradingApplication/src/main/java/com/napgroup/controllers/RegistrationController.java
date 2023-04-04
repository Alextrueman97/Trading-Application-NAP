package com.napgroup.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.napgroup.services.RegistrationServiceImpl;
import com.napgroup.services.RegistrationRequest;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationServiceImpl registrationServiceImpl;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationServiceImpl.register(request);
    }

    @GetMapping(path= "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationServiceImpl.confirmToken(token);
    }
}
