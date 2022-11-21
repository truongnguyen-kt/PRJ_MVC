/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import truongnhl.order.OrderDAO;
import truongnhl.orderdetail.OrderDetailDAO;
import truongnhl.orderdetail.OrderDetailDTO;
import truongnhl.product.ProductDTO;

/**
 *
 * @author 12345
 */
@WebServlet(name = "ConfirmCheckoutServlet", urlPatterns = {"/ConfirmCheckoutServlet"})
public class ConfirmCheckoutServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        
        String url = siteMaps.getProperty("checkoutSuccess");
        try {
            Map<ProductDTO, Integer> list = (Map<ProductDTO, Integer>) session.getAttribute("CHECKOUT_ITEMS");
            float total = Float.parseFloat(request.getParameter("txtTotal"));
            Date d = new Date();
            OrderDAO orderDAO = new OrderDAO();
            int orderID = orderDAO.createNewOrder(d, total);
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            for (ProductDTO productDTO : list.keySet()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(productDTO.getSku(), orderID, list.get(productDTO), productDTO.getPrice(), productDTO.getPrice() * list.get(productDTO));
                boolean result = orderDetailDAO.addNewOrderDetail(orderDetailDTO);
                if(!result){
                    url = siteMaps.getProperty("outOfStock");
                }
            }
        } catch (NumberFormatException ex) {
            log("ConfirmCheckout NumberFormat " + ex.getMessage());
        } catch (NamingException ex) {
            log("ConfirmCheckout Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("ConfirmCheckout SQL " + ex.getMessage());
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
