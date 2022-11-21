/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import truongnhl.cart.CartObject;
import truongnhl.product.ProductDTO;
import truongnhl.utils.MyApplicationConstants;

/**
 *
 * @author 12345
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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

        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.VIEW_CART_PAGE);

        try {
            HttpSession session = request.getSession(false); 
            if (session != null) {
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        String[] checked = request.getParameterValues("chkItem");
                        if (checked != null) {
                            Map<ProductDTO, Integer> list = cart.getCheckedItems(checked);
                            session.setAttribute("CHECKOUT_ITEMS", list);
//                            System.out.println(list);
                            url = siteMaps.getProperty(MyApplicationConstants.CheckoutFeature.CHECK_OUT_PAGE);
                        }
                    }
                }
            }
        }finally {
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
