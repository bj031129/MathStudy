package com.hnu.service;

import com.hnu.dao.UserMgrInterface;
import com.hnu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserMgrInterface userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean verifyUser(String name, String pwd) {
        return userDao.verifyUser(name, pwd);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userDao.isEmailRegistered(email);
    }

    @Override
    public boolean isNameRegistered(String name) {
        return userDao.isNameRegistered(name);
    }

    @Override
    public void addUser(User newUser) {
        userDao.addUser(newUser);
    }

    @Override
    public boolean changePwd(String userName, String oldPassword, String newPassword) {
        return userDao.changePwd(userName, oldPassword, newPassword);
    }

}
