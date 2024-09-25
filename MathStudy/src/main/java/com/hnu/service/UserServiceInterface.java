package com.hnu.service;

import com.hnu.entity.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAllUsers();

    boolean verifyUser(String name, String pwd);

    boolean isEmailRegistered(String email);

    boolean isNameRegistered(String name);

    void addUser(User newUser);

    boolean changePwd(String userName, String oldPassword, String newPassword);
}
