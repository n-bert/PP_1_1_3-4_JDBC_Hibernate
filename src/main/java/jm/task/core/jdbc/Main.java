package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection con = Util.getConnection();
        UserService us = new UserServiceImpl();

        us.createUsersTable();
        us.saveUser("Name1", "LastName1", (byte) 37);
        us.saveUser("Name2", "LastName2", (byte) 25);
        us.saveUser("Name3", "LastName3", (byte) 42);
        us.saveUser("Name4", "LastName4", (byte) 90);
        us.removeUserById(1);

        for (User user : us.getAllUsers()) {
            System.out.println(user);
        }

        us.cleanUsersTable();
        us.dropUsersTable();

        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
