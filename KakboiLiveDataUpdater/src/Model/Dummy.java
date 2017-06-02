/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Deni Barasena
 */
public class Dummy {
    private final ArrayList<Branch> branches = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Goal> goals = new ArrayList<>();
    private final ArrayList<Region> regions = new ArrayList<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();
//    private ArrayList<Product> products = new ArrayList<>();
            
    public Dummy() {
        
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    public void reDB() {
        
    }
}
