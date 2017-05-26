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
public class Region extends Model {
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

    public String getRegionName() {
        return regionName;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public int getRegionID() {
        return regionID;
    }

    public int getRegionPopulation() {
        return regionPopulation;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setRegionPopulation(int regionPopulation) {
        this.regionPopulation = regionPopulation;
    }

    @Override
    void setFrom(ResultSet rs) throws SQLException {
        this.regionID = rs.getInt("region_id");
        this.regionCode = rs.getString("region_code");
        this.regionName = rs.getString("region_name");
        this.regionPopulation = rs.getInt("region_population");
    }
}
