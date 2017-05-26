/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Deni Barasena
 */
public class Branch {
    private int branchID;
    private Region region;
    private String branchName;
    private float latitude;
    private float longitude;
    private Employee branchManager;

    private final ArrayList<Employee> employees = new ArrayList<Employee>();
    private final ArrayList<Applicants> applicants = new ArrayList<Applicants>();

    public Branch() {
    }
    
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Applicants> getApplicants() {
        return applicants;
    }

    public int getBranchID() {
        return branchID;
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
    
    
}
