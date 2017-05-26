/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Deni Barasena
 */
public class Employee extends Model{
    private int employeeID;
    private String employeeName;
    private String positionName;
    private String email;
    private String contacts;
    private Timestamp employedSince;
    private Branch branch;    

    public Employee() {
    }

    public Employee(int employeeID, String employeeName, String positionName, String email, String contacts, Timestamp employedSince, Branch branch) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.positionName = positionName;
        this.email = email;
        this.contacts = contacts;
        this.employedSince = employedSince;
        this.branch = branch;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Timestamp getEmployedSince() {
        return employedSince;
    }

    public void setEmployedSince(Timestamp employedSince) {
        this.employedSince = employedSince;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    void setFrom(ResultSet rs) throws SQLException {
        this.employeeID = rs.getInt("employee_id");
        this.employeeName = rs.getString("employee_name");
        this.positionName = rs.getString("position_name");
        this.email = rs.getString("email");
        this.employedSince = rs.getTimestamp("employed_since");
//        this.branch = rs.ge
    }
}
