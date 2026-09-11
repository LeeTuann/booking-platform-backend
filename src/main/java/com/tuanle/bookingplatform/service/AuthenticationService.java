package com.tuanle.bookingplatform.service;


import com.tuanle.bookingplatform.dto.AuthRequestDTO;
import com.tuanle.bookingplatform.dto.AuthResponseDTO;
import com.tuanle.bookingplatform.dto.RegisterRequestDTO;
import com.tuanle.bookingplatform.entity.User;
import com.tuanle.bookingplatform.repository.UserRepository;
import com.tuanle.bookingplatform.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        //tao user
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .role("USER")
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthResponseDTO.builder().token(jwtToken).build();
    }

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        //tu dong doi chieu mat khau tho va mat khau da ma hoa trong db
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        //Cap phat token
        String jwtToken = jwtService.generateToken(user);
        return AuthResponseDTO.builder().token(jwtToken).build();
    }
}
