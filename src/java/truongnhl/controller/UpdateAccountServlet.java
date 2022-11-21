/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongnhl.registration.RegistrationDAO;
import truongnhl.utils.MyApplicationConstants;

/**
 *
 * @author 12345
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
//    private final String ERROR_PAGE = "error.html";
//    private final String ERROR_PAGE = "errorPage";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        
        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeature.ERROR_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String searchValue = request.getParameter("lastSearchValue");
        String isAdmin = request.getParameter("chkAdmin");
        boolean role = false;
        if (isAdmin != null) {
            role = true;
        }
        try {
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateAccount(username, password, role);
            if(result){
                url = "search"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (NamingException ex){
            ex.printStackTrace();
        } finally{
            response.sendRedirect(url);
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
