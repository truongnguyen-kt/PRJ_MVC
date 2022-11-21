/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.order;

import java.util.Date;

/**
 *
 * @author 12345
 */
public class OrderDTO {
    private int id;
    private Date dateBuy;
    private float total;

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
     * @return the dateBuy
     */
    public Date getDateBuy() {
        return dateBuy;
    }

    /**
     * @param dateBuy the dateBuy to set
     */
    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
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

    
}
