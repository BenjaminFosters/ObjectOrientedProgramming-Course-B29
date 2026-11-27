/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author chevrianszweinevi
 */

import models.Book;
import models.User;
import java.util.ArrayList;
import java.util.List;

public class UserManagement { 
    private List<User> userList;
    private int incrementID = 0;
    
    public UserManagement() {
        this.userList = new ArrayList<>();
    }

    public void addUser(String name) {
        String tempID = String.format("USR-%04d", ++incrementID);
        User newUser = new User(tempID, name);
        userList.add(newUser);
        System.out.println("Berhasil menambahkan User!");
    }

    public void listAllUsers() {
        if (userList.isEmpty()) {
            System.out.println("Tidak ada User :(");
        } else {
            
            for (User user : userList) {
                System.out.println(user.getId() + " : " + user.getName());
            }
        }
    }
            
    public void editUser(String id, String newName) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                user.setName(newName);
                System.out.println("Nama user dengan ID " + id + " telah diganti menjadi \"" + newName + "\"");
                return;
            }
        }
        System.out.println("User tidak ditemukan.");
    }

    public void deleteUser(String id) {
        boolean isRemoved = userList.removeIf(user -> user.getId().equals(id));
        if (isRemoved) {
            System.out.println("User telah dihapus.");
        } else {
            System.out.println("User tidak ditemukan.");
        }
    }

    public User findUserById(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}