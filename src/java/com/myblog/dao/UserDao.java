package com.myblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.myblog.entities.User;
import com.myblog.helper.ConnectionProvider;
import com.myblog.helper.DoesNotExistException;
import com.myblog.helper.InfoIncompleteException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author Devender
 */
public class UserDao {
    private final static Connection con = ConnectionProvider.getConnection();

    public static boolean insertUser(User user) throws InfoIncompleteException, SQLIntegrityConstraintViolationException {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new InfoIncompleteException(
                    "Information to not sufficient to insert the user because there are not-null column in database");
        } else if (user.getGender() == null) {
            return insertUser(user.getName(), user.getEmail(), user.getPassword());
        } else {
            return insertUser(user.getName(), user.getEmail(), user.getPassword(), user.getGender());
        }
    }

    public static boolean insertUser(String name, String email, String password) {
        boolean flag = false;
        String query = "insert into user (user_name, user_email,user_password) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = UserDao.con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public static boolean insertUser(String name, String email, String password, String gender) throws SQLIntegrityConstraintViolationException {
        boolean flag = false;
        String query = "insert into user (user_name, user_email,user_password, user_gender) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = UserDao.con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, gender);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        return flag;
    }

    public static User getUser(String email) throws DoesNotExistException {
        User user = null;
        String query = "select * from user where user_email=" + "'" + email + "'";
        try {
            PreparedStatement preparedStatement = UserDao.con.prepareStatement(query);
            ResultSet rSet = preparedStatement.executeQuery();
            if (rSet.next()) {
                user = new User();
                user.setUser_id(rSet.getInt("user_id"));
                user.setName(rSet.getString("user_name"));
                user.setEmail(rSet.getString("user_email"));
                user.setPassword(rSet.getString("user_password"));
                user.setGender(rSet.getString("user_gender"));
            } else {
                throw new DoesNotExistException("User does not exist");
            }
        } catch (SQLException e) {
            System.out.println("there is sql excetpion in getUser method");
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static boolean validateUser(String email, String password) throws DoesNotExistException {
        boolean valid = false;
        try {
            valid = getUser(email).getPassword().equals(password);
        } catch (DoesNotExistException e) {
            throw e;
        }
        return valid;
    }

}
