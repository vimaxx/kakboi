/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author Deni Barasena
 */
public class Employee {
    private int employeeID;
    private String employeeName;
    private String positionName;
    private String email;
    private String contacts;
    private Timestamp employedSince;
    private Branch branch;    
}
