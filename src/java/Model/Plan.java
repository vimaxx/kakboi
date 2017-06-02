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
public class Plan extends Model{
    private int planID;
    private String planType;
    private String planName;
    private String planDetails;
    private int planPriority;
    private float planWeight;
    private String planStatus;
    private Timestamp time;
    
    //Transient Data
    private Strategy strategy;
    private Goal goal;
    private ArrayList<Branch> relatedBranches;

    public Plan(int planID, String planType, String planName, String planDetails, int planPriority, float planWeight, String planStatus, Timestamp time, Strategy strategy, Goal goal, ArrayList<Branch> relatedBranches) {
        this.planID = planID;
        this.planType = planType;
        this.planName = planName;
        this.planDetails = planDetails;
        this.planPriority = planPriority;
        this.planWeight = planWeight;
        this.planStatus = planStatus;
        this.time = time;
        this.strategy = strategy;
        this.goal = goal;
        this.relatedBranches = relatedBranches;
    }

    @Override
    void setFrom(ResultSet rs) throws SQLException {
        planDetails = rs.getString("plan_details");
        planPriority = rs.getInt("plan_priority");
        planWeight = rs.getFloat("plan_weight");
        planStatus = rs.getString("plan_status");
        time = rs.getTimestamp("time");
        planID = rs.getInt("plan_id");
        planName = rs.getString("plan_name");
        planType = rs.getString("plan_type");
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(String planDetails) {
        this.planDetails = planDetails;
    }

    public int getPlanPriority() {
        return planPriority;
    }

    public void setPlanPriority(int planPriority) {
        this.planPriority = planPriority;
    }

    public float getPlanWeight() {
        return planWeight;
    }

    public void setPlanWeight(float planWeight) {
        this.planWeight = planWeight;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ArrayList<Branch> getRelatedBranches() {
        return relatedBranches;
    }

    public void setRelatedBranches(ArrayList<Branch> relatedBranches) {
        this.relatedBranches = relatedBranches;
    }
    
    
}
