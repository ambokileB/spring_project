package com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private String firstName;
    private Integer phoneNumber;
    private String lastName;
    private String email;
    private String token;


}
