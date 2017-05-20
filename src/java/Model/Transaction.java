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
public class Transaction {
    private String transactionID;
    private Customer subject;
    private Employee handler;
    private Branch branch;
    private String transactionType; //Complaints, Purchases, Returns
    private String transactionTitle;
    private String transactionDetails;
    private Timestamp time;
    private Timestamp timeResponded;    //For complaints, NULL if open, 
    
    //Transient Data
    private ArrayList<Product> relatedProduct;  //products complained, purchased, or returned;
    private ArrayList<Employee> relatedEmployee; //employee complained
}
