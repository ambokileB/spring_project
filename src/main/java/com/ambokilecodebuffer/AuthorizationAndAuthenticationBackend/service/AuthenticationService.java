package com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.service;

import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.config.JwtService;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.controller.AuthenticationRequest;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.controller.AuthenticationResponse;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.controller.LoginResponse;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.controller.RegisterRequest;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.entity.Response;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.entity.ResponseData;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.entity.Role;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.entity.User;
import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Response<?> register(RegisterRequest request) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
            if (optionalUser.isPresent()) {
                return new Response<>(true, 9001, "Email exist");
            } else {
                var user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .phoneNumber(request.getPhoneNumber())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .build();
                userRepository.save(user);

                var jwtToken = jwtService.generateToken(user);
                var userData = AuthenticationResponse.builder()
                        .token(jwtToken)
                        .email(request.getEmail())
                        .firstName(request.getFirstName())
                        .phoneNumber(request.getPhoneNumber())
                        .lastName(request.getLastName())

                        .build();
                return new Response<>(false, 9000, userData, "User registered successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(true, 9001, e.getMessage());
        }
    }

    public Response<?> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
//                            request.getFirstName()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

            var jwtToken = jwtService.generateToken(user);
//            var userLoginData = AuthenticationResponse.builder()
            var userLoginData = LoginResponse.builder()

                    .token(jwtToken)
                    .email(request.getEmail())
                    .build();
            return new Response<>(false, ResponseData.SUCCESS, userLoginData, "User login successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(true, 9001, e.getMessage());
        }
    }
}
