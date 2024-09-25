package com.hnu.controller;

import com.hnu.entity.User;
import com.hnu.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自该地址的请求
public class AddUser {

    @Autowired
    private UserServiceInterface userService;

    @PostMapping("/add-user")
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (userService.isNameRegistered(userName)) {
            // 用户名已存在，返回错误响应
            response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
            response.getWriter().write("{\"message\":\"userName is already registered.\",\"status\":\"0\"}");
        } else {
            // 用户名未被占用，创建新用户
            User newUser = new User(email, userName, password);

            userService.addUser(newUser);

            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
            response.getWriter().write("{\"message\":\"Password set successfully.\",\"status\":\"1\"}");
        }
    }
}
