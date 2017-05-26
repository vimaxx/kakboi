/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Deni Barasena
 */
public class Goal extends Model{
    private int goalID;
    private String goalType;
    private String goalName;
    private String goalDetails;
    private int goalPriority;
    private String goalStatus;
    private Timestamp time;
    
    private ArrayList<Strategy> strategies;

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getGoalDetails() {
        return goalDetails;
    }

    public void setGoalDetails(String goalDetails) {
        this.goalDetails = goalDetails;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public void setGoalPriority(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public String getGoalStatus() {
        return goalStatus;
    }

    public void setGoalStatus(String goalStatus) {
        this.goalStatus = goalStatus;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public ArrayList<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(ArrayList<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    void setFrom(ResultSet rs) throws SQLException {
        goalID = rs.getInt("goal_id");
        goalDetails = rs.getString("goal_details");
        goalName = rs.getString("goal_name");
        goalPriority = rs.getInt("goal_priority");
        goalType = rs.getString("goal_type");
        goalStatus = rs.getString("goal_status");
        time = rs.getTimestamp("time");
    }
    
    public boolean isOngoing() {
        return "Ongoing".equalsIgnoreCase(goalStatus);
    }
}
