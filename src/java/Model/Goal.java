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
public class Goal {
    private int goalID;
    private String goalType;
    private String goalName;
    private String goalDetails;
    private int goalPriority;
    private String goalStatus;
    private Timestamp time;
    
    private ArrayList<Strategy> strategies;
}
