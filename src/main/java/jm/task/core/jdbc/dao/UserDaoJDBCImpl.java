package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection con = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
            "id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            " name VARCHAR(20) NOT NULL," +
            " lastName VARCHAR(20) NOT NULL," +
            " age TINYINT NOT NULL);";
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(createUsersTable);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS users";
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(dropUsersTable);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(saveUser)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(removeUserById)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(getAllUsers);
            con.commit();

            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                user.setId(rs.getLong("id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE TABLE users";
        try (Statement statement = con.createStatement()) {
            statement.execute(cleanUsersTable);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
