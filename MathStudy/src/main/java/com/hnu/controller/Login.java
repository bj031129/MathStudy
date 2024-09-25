package com.hnu.controller;

import com.hnu.service.UserServiceInterface;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自该地址的请求
public class Login {

    @Autowired
    private UserServiceInterface userService;

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (userService.verifyUser(userName, password)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Login successful.\",\"status\":\"1\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\":\"Invalid userName or password.\",\"status\":\"0\"}");
        }
    }
}
