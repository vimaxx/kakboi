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
public class Plan {
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
}
