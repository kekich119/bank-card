package com.example.bankcards.controller;

import com.example.bankcards.dto.SignIn;
import com.example.bankcards.dto.SignUp;
import com.example.bankcards.entity.User;
import com.example.bankcards.security.JWT;
import com.example.bankcards.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springdoc.core.service.SecurityService;
import jakarta.servlet.http.Cookie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {


    private final UserService userService;
    private final JWT jwtcore;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public SecurityController(UserService userService, JWT jwtcore, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtcore = jwtcore;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUp signUp) {
        if (userService.existsByEmail(signUp.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User user = new User();
        String hashedPassword = passwordEncoder.encode(signUp.getPassword());
        user.setEmail(signUp.getEmail());
        user.setPassword(hashedPassword);
        user.setRoleId(1);
        user.setName(signUp.getName());
        userService.addUser(user);



        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignIn signIn, HttpServletResponse httpServletResponse) {
        Authentication authentication;

        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword()));


        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtcore.generateToken(authentication);
        Cookie cookie = new Cookie("JWT", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(jwtcore.getLifeTime());
        httpServletResponse.addCookie(cookie);
        System.out.println("Jwt has been generated");
        return ResponseEntity.ok().build();


    }



}
