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
import truongnhl.registration.RegistrationCreateError;
import truongnhl.registration.RegistrationDAO;
import truongnhl.registration.RegistrationDTO;
import truongnhl.utils.MyApplicationConstants;

/**
 *
 * @author 12345
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "";
//    private final String CREATE_ERROR = "createNewAccount.jsp";
//    private final String CREATE_ERROR = "createError";

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
        
        String url = siteMaps.getProperty(MyApplicationConstants.CreateFeature.ERROR_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean errorFound = false;
        RegistrationCreateError errors = new RegistrationCreateError();

        try {
            //1. check user's errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errorFound = true;
                errors.setUsernameLengthError("Username is required input from 6 to 20 charracters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errorFound = true;
                errors.setPasswordLengthError("Password is required input from 6 to 30 charracters");
            } else if (!confirm.trim().equals(password.trim())) {
                errorFound = true;
                errors.setConfirmNotMatch("Confirm must be matched password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                errorFound = true;
                errors.setFullnameLengthError("Fullname is required input from 2 to 50 charracters");
            }

            if (errorFound) {
                //1.1 cache store
                request.setAttribute("CREATE_ERRORS", errors);
                //1.2 show error to user
            } else {
                //2. insert to DB --> call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = siteMaps.getProperty(MyApplicationConstants.CreateFeature.LOGIN_PAGE);
                }//create is successfully;
                //3. check system's errors
            }

        } catch (NamingException ex) {
            String errMsg = ex.getMessage();
            log("Create Account Naming: " + errMsg);
            if(errMsg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (SQLException ex) {
            log("Create Account Naming: " + ex.getCause());

        } finally {
            //4. login page
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
