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
public class Region extends Model{
    private int regionID;
    private String regionCode;
    private String regionName;
    private int regionPopulation;
    
    private ArrayList<Branch> branches;

    public Region() {
    }

    public Region(int regionID, String regionCode, String regionName, int regionPopulation, ArrayList<Branch> branches) {
        this.regionID = regionID;
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.regionPopulation = regionPopulation;
        this.branches = branches;
    }

    @Override
    void setFrom(ResultSet rs) throws SQLException {
        regionID = rs.getInt("region_id");
        regionCode = rs.getString("region_code");
        regionName = rs.getString("region_name");
        regionPopulation = rs.getInt("region_population");
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegionPopulation() {
        return regionPopulation;
    }

    public void setRegionPopulation(int regionPopulation) {
        this.regionPopulation = regionPopulation;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }
    
    
}
