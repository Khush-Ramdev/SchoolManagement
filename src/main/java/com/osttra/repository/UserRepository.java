package com.osttra.repository;

import com.mysql.cj.protocol.Resultset;
import com.osttra.to.UserTO;
import com.osttra.utils.DBUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    Connection connection = DBUtils.getConnection();

    public int add(UserTO user) {
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("insert into user values (?,?,?,?,?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setBoolean(5, user.isApproved());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("here");
                res = 1;
            }
            if (e instanceof NullPointerException) {
                res = 2;
            }
        }
        return res;
    }

    public UserTO getUser(UserTO user) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from user where email = ? and password = ?");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());


            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setName(rs.getString(1));
                user.setRole(rs.getString(4));
                user.setApproved(rs.getBoolean(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("SQL Constraint Violation");
            }
            if (e instanceof NullPointerException) {
                System.out.println("NULL pointer Exception in checking user!");
            }
        }
        return user;
    }

    public UserTO getUser(String email) {
        UserTO newUser = new UserTO();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from user where email = ?");
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                newUser.setName(rs.getString(1));
                newUser.setEmail(rs.getString(2));
                newUser.setPassword(rs.getString(3));
                newUser.setRole(rs.getString(4));
                newUser.setApproved(rs.getBoolean(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("SQL Constraint Violation");
            }
            if (e instanceof NullPointerException) {
                System.out.println("NULL pointer Exception in checking user!");
            }
        }
        return newUser;
    }

    public UserTO updateUser(UserTO user) {
        try {
            PreparedStatement statement = connection.prepareStatement("update user set name = ? where email = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());

           statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("SQL Constraint Violation");
            }
            if (e instanceof NullPointerException) {
                System.out.println("NULL pointer Exception in checking user!");
            }
        }
        return user;
    }

    public int deleteUser(UserTO user) {
        int res= -1;
        try {
            PreparedStatement statement = connection.prepareStatement("delete from user where email = ? and role = ?");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getRole());
            statement.executeUpdate();
            res= 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("SQL Constraint Violation");
            }
            if (e instanceof NullPointerException) {
                System.out.println("NULL pointer Exception in checking user!");
            }
        }
        return res;
    }

    public ArrayList<UserTO> getAllUsers(){
        ArrayList<UserTO> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from user");

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name  =  rs.getString(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String role = rs.getString(4);
                boolean approved= rs.getBoolean(5);

                UserTO user =  new UserTO(name,email,password,role,approved);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public int ApproveUser(UserTO user) {
        int res= -1;
        try {
            PreparedStatement statement = connection.prepareStatement("update user set approved = NOT approved where email = ?");
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
            res= 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("SQL Constraint Violation");
            }
            if (e instanceof NullPointerException) {
                System.out.println("NULL pointer Exception in checking user!");
            }
        }
        return res;
    }
}
