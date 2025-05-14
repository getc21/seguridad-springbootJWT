package com.zoologico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zoologico.dtos.NewUserDto;
import com.zoologico.entities.Role;
import com.zoologico.entities.User;
import com.zoologico.enums.RoleList;
import com.zoologico.jwt.JwtUtil;
import com.zoologico.repositories.RoleRepository;

@Service
public class AuthService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public AuthService(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public String authenticate(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authResult = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        return jwtUtil.generateToken(authResult);
    }

    public void registerUser(NewUserDto newUserDto){
    if (userService.existsByUserName(newUserDto.getUserName())){
        throw new IllegalArgumentException("El nombre de usuario ya existe");
    }

    RoleList roleToAssign;
    if (userService.countUsers() == 0) {
        roleToAssign = RoleList.ROLE_ADMIN;
    } else {
        roleToAssign = RoleList.ROLE_USER;
    }

    Role role = roleRepository.findByName(roleToAssign)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    User user = new User(newUserDto.getUserName(), passwordEncoder.encode(newUserDto.getPassword()), role);
    userService.save(user);
}

    public User getUserByUserName(String username) {
        return userService.findByUserName(username);
    }
}
