package com.zoologico.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoologico.dtos.LoginResponseDto;
import com.zoologico.dtos.LoginUserDto;
import com.zoologico.dtos.NewUserDto;
import com.zoologico.entities.User;
import com.zoologico.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        return ResponseEntity.badRequest().body("Revise sus credenciales");
    }
    try {
        String jwt = authService.authenticate(loginUserDto.getUserName(), loginUserDto.getPassword());
        User user = authService.getUserByUserName(loginUserDto.getUserName());
        LoginResponseDto response = new LoginResponseDto(
            user.getUserName(),
            user.getRole().getName().name(),
            jwt
        );
        return ResponseEntity.ok(response);
    } catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Revise los campos");
        }
        try {
            authService.registerUser(newUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registrado");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth(){
            return ResponseEntity.ok().body("Autenticado");
    }
}
