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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongnhl.registration.RegistrationDAO;
import truongnhl.registration.RegistrationDTO;
import truongnhl.utils.MyApplicationConstants;

/**
 *
 * @author 12345
 */
@WebServlet(name = "ProcessRequestServlet", urlPatterns = {"/ProcessRequestServlet"})
public class ProcessRequestServlet extends HttpServlet {
//    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "";
//    private final String SEARCH_PAGE = "search.jsp";
//    private final String SEARCH_PAGE = "searchPage";

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
        
        String url = siteMaps.getProperty(MyApplicationConstants.ProcessRequestFeature.LOGIN_PAGE);

        try {
            // 1. Get cookies from request
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                // 2. Traverse all cookies to check authentication
                for (Cookie cookie : cookies) {
                    // 3. get username and password from name value
                    String username = cookie.getName();
                    String password = cookie.getValue();
                    
                    // 4. call DAO to check authentication
                    RegistrationDAO dao = new RegistrationDAO();
//                    boolean result = dao.checkLogin(username, password);
                    RegistrationDTO result = dao.checkLogin(username, password);
                    
//                    if(result){
                    if(result != null){
                        url = siteMaps.getProperty(MyApplicationConstants.ProcessRequestFeature.SEARCH_PAGE); 
                        break;
                    }
                }// end for travese cookies
            }// end cookies is existed  
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (NamingException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        } finally {
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
