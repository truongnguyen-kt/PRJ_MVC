/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import truongnhl.orderdetail.OrderDetailDTO;
import truongnhl.utils.DBHelper;

/**
 *
 * @author 12345
 */
public class OrderDAO implements Serializable {

    public int createNewOrder(Date dateBuy, float total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int id = -1;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Order("
                        + "dateBuy, total"
                        + ") "
                        + "Output inserted.id "
                        + "Values("
                        + "?, ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setDate(1, new java.sql.Date( dateBuy.getTime()));
                stm.setFloat(2, total);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return id;
    }
}
