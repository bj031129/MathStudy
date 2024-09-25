package com.hnu.dao;

import com.hnu.entity.User;

import java.util.List;

public interface UserMgrInterface {

    List<User> getAllUsers();

    boolean isNameRegistered(String name);

    boolean isEmailRegistered(String email);

    boolean verifyUser(String name, String pwd);

    void addUser(User newUser);

    void updateUsers();

    boolean changePwd(String userName, String oldPassword, String newPassword);
}
