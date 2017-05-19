package Model;

import java.util.ArrayList;

public enum Page {
    MAIN_PAGE("Dashboard", "main")                  //Income Graphs, overall performances/productivity, current strategies, market condition overview, customer satisfactions
    ,LOGIN_PAGE("Login", "login")                   //Log in
    ,BRANCH_PAGE("Branches", "branches")            //Aus/Sea Map, branch tables, branch performances, worker productivities, market overview, supply and demand
    ,STRATEGY_PAGE("Strategy", "strategy")          //Goals, Planning, Strategies, 
    ,PERFORMANCE_PAGE("Performance", "performance") //Detail performance according to metrics: productivity, customer satisfactions, product/warehouse buffers, 
    
    ,BM_MAIN_PAGE("Dashboard", "bm_main")           //Branch Income graphs, Branch overall performances/productivity, Current Objectives, customer satisfactions
    ,BM_PRODUCT_PAGE("Product", "bm_product")       //Product performances, product placements, store maps, product margin, product cost/prices, product stocks/buffer
    ,BM_EMPLOYEE_PAGE("Employee", "bm_employee")    //Worker performances, Applicants, Hire/fire workers, 
    ,BM_TRANSACTIONS_PAGE("Transactions", "bm_transactions")    //Transactions details: purchases, returns, complaints
    ;
    
    
    private final String title;
    private final String jspLink;
    
    private final ArrayList<String> extraCSS = new ArrayList<>();
    private final ArrayList<String> extraJS = new ArrayList<>();
    private String extraHead = "";
    
    private final ArrayList<Page> breadCrumbs = new ArrayList<>();

    private Page(String title, String jspLink) {
        this.title = title;
        this.jspLink = jspLink;
        
        this.initPage();
    }
    
    private Page(String title, String jspLink, Page parent) {
        this.title = title;
        this.jspLink = jspLink;
        
        this.breadCrumbs.add(parent);
        for (int i = 0; i < parent.getBreadCrumbs().size(); i++) {
            Page pageNode = parent.getBreadCrumbs().get(i);
            this.breadCrumbs.add(pageNode);
        }
    }

    public ArrayList<Page> getBreadCrumbs() {
        return breadCrumbs;
    }

    public String getJspLink() {
        return jspLink;
    }

    public String getTitle() {
        return title;
    }
    
    public String printExtraJS() {
        String res = "";
        
        for (int i = 0; i < extraJS.size(); i++) {
            String js = extraJS.get(i);
            res += "<script type='text/javascript' src='" + js + "'></script>\n"; 
        }
        
        return res;
    }
    
    public String printExtraCSS() {
        String res = "";
        
        for (int i = 0; i < extraCSS.size(); i++) {
            String css = extraCSS.get(i);
            res += "<link href='" + css + "' rel='stylesheet' type='text/css'>\n"; 
        }
        
        return res;
    }
    
    public String printExtraHead() {
        return extraHead;
    }
    
    private void initPage() {
        extraHead = "";
        
        switch(toString()) {
            case "MAIN_PAGE":
                break;
        }
        
    }
    
    public static Page fromString(String string) {
        Page p = valueOf(string);
        if( p != null ) {
            return p;
        }
        
        for (Page value : values()) {
            p = value;
            if( p.getTitle().equals(string) ) {
                return p;
            }
        }
        
        return null;
    }
}
