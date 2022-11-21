/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.orderdetail;

import java.io.Serializable;

/**
 *
 * @author 12345
 */
public class OrderDetailDTO implements Serializable{

    private int id;
    private String sku;
    private int orderid;
    private int quantity;
    private float price;
    private float total;

    public OrderDetailDTO(String sku, int orderid, int quantity, float price, float total) {
        this.sku = sku;
        this.orderid = orderid;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    

    public OrderDetailDTO() {
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the orderid
     */
    public int getOrderid() {
        return orderid;
    }

    /**
     * @param orderid the orderid to set
     */
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    
}
