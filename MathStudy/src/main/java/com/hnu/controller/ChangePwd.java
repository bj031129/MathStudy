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
public class ChangePwd {

    @Autowired
    private UserServiceInterface userService;

    @PostMapping("/change-password")
    public void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从请求中获取参数
        String userName = request.getParameter("userName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        // 调用服务层处理密码修改逻辑
        boolean isChanged = userService.changePwd(userName, oldPassword, newPassword);

        // 返回响应
        if (isChanged) {
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
            response.getWriter().write("{\"message\": \"Password changed successfully.\", \"status\": \"1\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
            response.getWriter().write("{\"message\": \"Old password is incorrect.\", \"status\": \"0\"}");
        }
    }
}
