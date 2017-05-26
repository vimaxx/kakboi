/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Deni Barasena
 */
public class Branch extends Model{
    private int branchID;
    private Region region;
    private String branchName;
    private float latitude;
    private float longitude;
    private Employee branchManager;
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    public Branch() {
    }

    public Branch(int branchID, Region region, String branchName, float latitude, float longitude, Employee branchManager) {
        this.branchID = branchID;
        this.region = region;
        this.branchName = branchName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.branchManager = branchManager;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Employee getBranchManager() {
        return branchManager;
    }

    public void setBranchManager(Employee branchManager) {
        this.branchManager = branchManager;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    
    @Override
    void setFrom(ResultSet rs) throws SQLException {
        branchID = rs.getInt("branch_id");
        branchName = rs.getString("branch_name");
        latitude = rs.getFloat("latitude");
        longitude = rs.getFloat("longitude");
        
    }
}
