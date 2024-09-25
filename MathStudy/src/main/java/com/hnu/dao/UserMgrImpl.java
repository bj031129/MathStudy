package com.hnu.dao;

import com.hnu.entity.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMgrImpl implements UserMgrInterface {
    private static final String FILE_PATH = "src/main/java/com/hnu/db/Users/Users.txt";
    private List<User> users;

    //构造函数从文件中读取所有用户
    public UserMgrImpl() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(" ");
                if (userInfo.length == 3) {
                    User user = new User(userInfo[0], userInfo[1], userInfo[2]);
                    users.add(user);
                }
            }
            this.users = users;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean isNameRegistered(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmailRegistered(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verifyUser(String name, String pwd) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPwd().equals(pwd)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addUser(User newUser) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(newUser.getEmail() + " " + newUser.getName() + " " + newUser.getPwd());
            bw.newLine();
            this.users.add(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.getEmail() + " " + user.getName() + " " + user.getPwd());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean changePwd(String userName, String oldPassword, String newPassword) {
        for (User user : users) {
            if (user.getName().equals(userName)) {
                if (user.getPwd().equals(oldPassword)) {
                    user.setPwd(newPassword);
                    updateUsers();
                    return true;
                }
            }
        }
        return false;
    }


}
