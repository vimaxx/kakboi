/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Branch;
import Model.DBAdmin;
import Model.Goal;
import Model.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Deni Barasena
 */
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        if( loggedUser == null ) {
            response.sendRedirect("login");
            return;
        }
        
        if("ceo".equalsIgnoreCase(loggedUser.getUserType())) {
            ArrayList<Goal> goals = DBAdmin.getAllGoals();
            ArrayList<Branch> branches = DBAdmin.getAllBranches();
            
            for (int i = 0; i < goals.size(); i++) {
                Goal g = goals.get(i);
                
                g.setStrategies(DBAdmin.getAllStrategies(g.getGoalID()) );
            }
            
            request.setAttribute("goals", goals);
            request.setAttribute("branches", branches);
            
            request.setAttribute("monthlyRevenue", 6283);
            request.setAttribute("weeklyRevenue", 1519);
            request.setAttribute("averageRevenue", 213);
            
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } else if ("manager".equalsIgnoreCase(loggedUser.getUserType())) {
            request.getRequestDispatcher("manager_main.jsp").forward(request, response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
