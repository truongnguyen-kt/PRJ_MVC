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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import truongnhl.registration.RegistrationDAO;
import truongnhl.registration.RegistrationDTO;
import truongnhl.utils.MyApplicationConstants;

/**
 *
 * @author 12345
 */
public class LoginServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.jsp";
//    private final String SEARCH_PAGE = "searchPage";
//    private final String INVALID_PAGE = "invalid.html";
//    private final String INVALID_PAGE = "invalidPage";

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

        ServletContext context = getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        
        PrintWriter out = response.getWriter();
        String url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.INVALID_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            // 1. call Model/DAO
            // 1.1 new Object
            RegistrationDAO dao = new RegistrationDAO();
            // 1.2 call mathod of that Obj
//                boolean result = dao.checkLogin(username, password);
            RegistrationDTO result = dao.checkLogin(username, password);
            // 2. Process result
//                if(result){
            if (result != null) {
                url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.SEARCH_PAGE);
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                // add cookie to client using reqObj
//                    Cookie cookie  = new Cookie(username, password);
//                    cookie.setMaxAge(60 * 3);
//                    response.addCookie(cookie);
            }//end user is existed

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            //response.sendRedirect(url); => hien thi url
            RequestDispatcher rd = request.getRequestDispatcher(url);   // => an url, tat ca phai qua dispatcher
            rd.forward(request, response);
            out.close();
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
