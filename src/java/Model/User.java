/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Deni Barasena
 */
public class User extends Model{
    private int userID;
    private String fullname;
    private String email;
    private String userType;
    private String password;

    public User() {
    }

    public User(int userID, String fullname, String email, String userType, String password) {
        this.userID = userID;
        this.fullname = fullname;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    void setFrom(ResultSet rs) throws SQLException {
        userID = rs.getInt("userID");
        fullname = rs.getString("username");
        userType = rs.getString("userType");
    }
}
