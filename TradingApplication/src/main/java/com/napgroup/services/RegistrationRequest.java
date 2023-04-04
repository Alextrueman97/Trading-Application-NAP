package com.napgroup.services;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// when the client sends a request we want to capture:
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest{
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
