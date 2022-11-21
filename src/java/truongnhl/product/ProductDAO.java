/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import truongnhl.registration.RegistrationDTO;
import truongnhl.utils.DBHelper;

/**
 *
 * @author 12345
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

//    public float getPriceBySku(String sku) {
//        if (productList != null) {
//            for (ProductDTO productDTO : productList) {
//                if(productDTO.getSku().equals(sku)){
//                    return productDTO.getPrice();
//                }
//            }
//        }
//        return 0;
//    }
    
    public ProductDTO getProductBySku(String sku) 
        throws SQLException, NamingException{
        
        getProduct();
        if(this.productList != null){
            for (ProductDTO productDTO : this.productList) {
                if(productDTO.getSku().equals(sku)){
                    return productDTO;
                }
            }
        }
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO result = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select name, description, quantity, price, status "
                        + "From Product "
                        + "Where sku = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, sku);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    result = new ProductDTO(sku, name, description, quantity, price, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public ProductDTO getProductByName(String name) 
        throws SQLException, NamingException{
        
        getProduct();
        if(this.productList != null){
            for (ProductDTO productDTO : this.productList) {
                if(productDTO.getName().equals(name)){
                    return productDTO;
                }
            }
        }
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO result = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select sku, description, quantity, price, status"
                        + "From Product "
                        + "Where name = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String sku = rs.getString("sku");
                    float price = rs.getFloat("price");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
//                    System.out.println(sku + name + description + quantity + price);
                    
                    result = new ProductDTO(sku, name, description, quantity, price, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public void getProduct() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Select sku, name, description, quantity, price, status "
                        + "From Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    if(this.productList == null){
                        this.productList = new ArrayList<>();
                    }
                    ProductDTO dto = new ProductDTO(rs.getString("sku"), rs.getString("name"), rs.getString("description"), rs.getInt("quantity"), rs.getFloat("price"), rs.getBoolean("status"));
                    
//                    System.out.println(dto.getName() + " " + dto.getPrice());
                    
                    this.productList.add(dto);
                }
            }
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    } 
    
}
