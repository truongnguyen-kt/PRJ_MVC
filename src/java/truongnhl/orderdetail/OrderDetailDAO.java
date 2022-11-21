/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import truongnhl.utils.DBHelper;

/**
 *
 * @author 12345
 */
public class OrderDetailDAO implements Serializable {

    public boolean addNewOrderDetail(OrderDetailDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into orderDetail("
                        + "sku, orderid, quantity, price, total"
                        + ") "
                        + "Values("
                        + "?, ?, ?, ?, ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSku());
                stm.setInt(2, dto.getOrderid());
                stm.setInt(3, dto.getQuantity());
                stm.setFloat(4, dto.getPrice());
                stm.setFloat(5, dto.getTotal());

                int rs = stm.executeUpdate();
                if (rs > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
