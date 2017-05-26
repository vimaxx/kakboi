/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Deni Barasena
 */
public class Strategy {
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

    public ArrayList<Plan> getPlans() {
        return plans;
    }
}
