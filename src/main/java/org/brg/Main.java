package org.brg;

import org.brg.data.UserDAO;
import org.brg.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User userInsert = new User("testing", "i.test@gmail.com", "testing220");
        if (userDAO.insert(userInsert) > 0) {
            System.out.println("Usuario registrado con éxito!!");
        }
        List<User> userList = userDAO.select();
        userList.forEach(System.out::println);
    }
}