package org.brg.data;

import org.brg.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO {
    private static final String SQL_SElECT = "SELECT * FROM userlist;";
    private static final String SQL_LOGIN = "SELECT * FROM userlist WHERE user_name = ? AND password = ?;";
    private static final String SQL_INSERT = "INSERT INTO users (id_user, user_name, email, password) VALUES (NULL,?,?,?);";

    public List<User> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user;
        List<User> userList = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SElECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(Objects.requireNonNull(rs));
                DBConnection.close(stmt);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return userList;
    }

    public int insert(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                assert stmt != null;
                DBConnection.close(stmt);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return rows;
    }

    public boolean login(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        boolean isUser = false;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_LOGIN);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            rs = stmt.executeQuery();
            while (rs.next()) {
                isUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(Objects.requireNonNull(rs));
                DBConnection.close(stmt);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return isUser;
    }
}
