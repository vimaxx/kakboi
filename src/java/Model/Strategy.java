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
public class Strategy extends Model{
    private int strategyID;
    private String strategyType;
    private String strategyName;
    private String strategyDetails;
    private int strategyPriority;
    private float strategyWeight;
    private String strategyStatus;
    private Timestamp time;
    
    //Transient Data
    private Goal goal;
    private ArrayList<Plan> plans;
    private ArrayList<String> metricFormulas;

    public Strategy() {
    }

    public Strategy(int strategyID, String strategyType, String strategyName, String strategyDetails, int strategyPriority, float strategyWeight, String strategyStatus, Timestamp time, Goal goal, ArrayList<Plan> plans, ArrayList<String> metricFormulas) {
        this.strategyID = strategyID;
        this.strategyType = strategyType;
        this.strategyName = strategyName;
        this.strategyDetails = strategyDetails;
        this.strategyPriority = strategyPriority;
        this.strategyWeight = strategyWeight;
        this.strategyStatus = strategyStatus;
        this.time = time;
        this.goal = goal;
        this.plans = plans;
        this.metricFormulas = metricFormulas;
    }

    public int getStrategyID() {
        return strategyID;
    }

    public void setStrategyID(int strategyID) {
        this.strategyID = strategyID;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyDetails() {
        return strategyDetails;
    }

    public void setStrategyDetails(String strategyDetails) {
        this.strategyDetails = strategyDetails;
    }

    public int getStrategyPriority() {
        return strategyPriority;
    }

    public void setStrategyPriority(int strategyPriority) {
        this.strategyPriority = strategyPriority;
    }

    public float getStrategyWeight() {
        return strategyWeight;
    }

    public void setStrategyWeight(float strategyWeight) {
        this.strategyWeight = strategyWeight;
    }

    public String getStrategyStatus() {
        return strategyStatus;
    }

    public void setStrategyStatus(String strategyStatus) {
        this.strategyStatus = strategyStatus;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    public ArrayList<String> getMetricFormulas() {
        return metricFormulas;
    }

    public void setMetricFormulas(ArrayList<String> metricFormulas) {
        this.metricFormulas = metricFormulas;
    } 
    
    @Override
    void setFrom(ResultSet rs) throws SQLException {
        strategyID = rs.getInt("strategy_id");
        strategyDetails = rs.getString("strategy_details");
        strategyName = rs.getString("strategy_name");
        strategyPriority = rs.getInt("strategy_priority");
        strategyWeight = rs.getFloat("strategy_weight");
        strategyStatus = rs.getString("strategy_status");
        strategyType = rs.getString("strategy_type");
        time = rs.getTimestamp("time");
    }
}
