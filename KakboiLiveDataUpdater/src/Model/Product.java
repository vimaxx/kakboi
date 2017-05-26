/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Map;

/**
 *
 * @author Deni Barasena
 */
public class Product {
    private int productID;
    private String productName;
    private String productDetails;
    private float productCost;      //Supply Cost
    
    //Transient Data;
    private int totalStock;              
    private Map<Branch, Integer> stockInBranches;   
    private Map<Branch, Float> costInBranches;   
}
