/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakboilivedataupdater;

import Model.DBAdmin;
import Model.Dummy;
import Model.User;
import java.util.logging.Level;
import java.util.logging.Logger;
        
/**
 *
 * @author Deni Barasena
 */
public class KakboiLiveDataUpdater {
    
    public static Dummy dummy;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        init();
        
        while(true) {
            
            
            update();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(KakboiLiveDataUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void init() {
        dummy = new Dummy();
        
        dummy.getUsers().add(new User(0, "CEO", "deni.barasena@gmail.com", "ceo", "asdf1234"));
        dummy.getUsers().add(new User(0, "Manager", "deni.barasena@gmail.com", "manager", "asdf1234"));
        
        dummy.reDB();
        
    }
    
    public static void update() {
        
    }
}
