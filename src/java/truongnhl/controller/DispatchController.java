/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongnhl.utils.MyApplicationConstants;

/**
 *
 * @author 12345
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {
//    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "";
//    private final String LOGIN_CONTROLLER ="LoginServlet";
//    private final String LOGIN_CONTROLLER ="loginController";
//    private final String SEARCH_FULLNAME_CONTROLLER = "SearchFullnameServlet";
//    private final String SEARCH_FULLNAME_CONTROLLER = "searchFullnameController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
//    private final String PROCESS_REQUEST_CONTROLLER = "ProcessRequestServlet";
//    private final String PROCESS_REQUEST_CONTROLLER = "processRequestController";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    private final String VIEW_CART_PAGE = "viewCartPage";
//    private final String REMOVE_ITEM_CONTROLLER = "RemoveItemServlet";
//    private final String REMOVE_ITEM_CONTROLLER = "removeItemController";
//    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
//    private final String LOGOUT_CONTTROLLER = "LogoutServlet";
//    private final String LOGOUT_CONTTROLLER = "logout";
//    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
//    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "createNewAccountController";
//    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
//    private final String CHECK_OUT_CONTROLLER = "checkOutServlet";
    
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
        
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
        
        // which button did user click?
        String button = request.getParameter("btAction");
        
        try{
            if(button == null){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.PROCESS_REQUEST_CONTROLLER);
            } else if (button.equals("Login")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.SEARCH_FULLNAME_CONTROLLER);
            } else if(button.equals("Delete")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if(button.equals("Update")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if(button.equals("Add book to Your Cart")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER);
            } else if(button.equals("View Your Cart")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.VIEW_CART_PAGE);
            } else if(button.equals("Remove Selected Item")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.REMOVE_ITEM_CONTROLLER);
            } else if(button.equals("Logout")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGOUT_CONTTROLLER);
            } else if(button.equals("Create New Account")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
            } else if(button.equals("Check Out")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.CHECK_OUT_CONTROLLER);
            }
        } finally{
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
