package com.hnu.controller;

import com.hnu.service.EmailServiceInterface;
import com.hnu.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自该地址的请求
public class Register {

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private EmailServiceInterface emailService;

    @PostMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");

        if (userService.isEmailRegistered(email)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\":\"Email is already registered.\",\"status\":\"0\"}");
            return;
        }

        Random ran = new Random();
        String registerCode = String.valueOf(ran.nextInt(900000) + 100000);
        emailService.sendEmail(email, "Registration Code", "Your registration code is: " + registerCode);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"registerCode\":\"" + registerCode + "\",\"message\":\"Registration code sent successfully.\",\"status\":\"1\"}");
    }
}
