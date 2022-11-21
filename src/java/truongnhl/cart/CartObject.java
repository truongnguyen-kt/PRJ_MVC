/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import truongnhl.product.ProductDAO;
import truongnhl.product.ProductDTO;

public class CartObject implements Serializable {

    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return this.items;
    }

    public void addItemToCart(String sku)
            throws SQLException, NamingException {

        //1. Checking items has existed
        if (sku == null) {
            return;
        }

        if (sku.trim().isEmpty()) {
            return;
        }

        if (this.items == null) {
            this.items = new HashMap<>();
        }
//        System.out.println(this.items);
        //2. Checking item exited in items
        int quantity = 1;
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductBySku(sku);

//        System.out.println(dto);
//        System.out.println("item " + this.items);
        for (ProductDTO productDTO : this.items.keySet()) {
            if (productDTO.getSku().equals(sku)) {
                quantity = this.items.get(productDTO) + 1;
                this.items.replace(productDTO, quantity);
                return;
            }
        }
        this.items.put(dto, quantity);
//        if (this.items.containsKey(dto)) {
//            
//            
//        }

        //3. Update items
    }

    public void removeItemFromCart(String sku) throws SQLException, NamingException {
        if (this.items == null) {
            return;
        }
//        //2. checking item existed in cart
//        ProductDAO dao = new ProductDAO();
//        ProductDTO dto = dao.getProductBySku(sku);
        for (ProductDTO productDTO : this.items.keySet()) {
            if (productDTO.getSku().equals(sku)) {
                this.items.remove(productDTO);
                return;
            }
        }
    }

    public Map<ProductDTO, Integer> getCheckedItems(String[] checked) {
        if (items == null) {
            return null;
        }
//        System.out.println(items);
        Map<ProductDTO, Integer> list = new HashMap<>();
//        ProductDAO dao = new ProductDAO();
//        ProductDTO dto = new ProductDTO();
        for (String sku : checked) {
            for (ProductDTO productDTO : items.keySet()) {
                if (productDTO.getSku().equals(sku)) {
//                    items.remove(productDTO);
                    list.put(productDTO, items.get(productDTO));
                }
            }
        }
        return list;
    }

}
