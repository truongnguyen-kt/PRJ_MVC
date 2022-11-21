package truongnhl.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import truongnhl.utils.DBHelper;

public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
    public RegistrationDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. connect 
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Select username, fullname "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                //2.2 Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 Execute Query --> Result Set
                rs = stm.executeQuery();
                //2.4 Process resul Set
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    result = new RegistrationDTO(username, password, fullname, false);
                }
            }//end connection is available
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

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            //1. connect 
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Select username, password, fullname, isAdmin "
                        + "From Registration "
                        + "Where fullname like ?";
                //2.2 Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //2.3 Execute Query --> Result Set
                rs = stm.executeQuery();
                //2.4 Process resul Set
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);

                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }// end account List has not been existed
                    this.accountList.add(dto);
                }// end traverse Result set
            }//end connection is available
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

    }

    public boolean deleteAccount(String username)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. connect 
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //2.2 Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //2.3 Execute Query --> Result Set
                int effectedRow = stm.executeUpdate();
                //2.4 Process resul Set
                if (effectedRow > 0) {
                    result = true;
                }
            }//end connection is available
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

    public boolean updateAccount(String username, String password, boolean role) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Registration "
                        + "set password = ?, isAdmin = ? "
                        + "where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                
//                System.out.println(username + " " + password + " " + role + "\n");
                
                int effectedRow = stm.executeUpdate();

                if (effectedRow > 0) {
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
    
    public boolean createAccount(RegistrationDTO dto)
           throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            //1. connect 
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Insert Into Registration("
                        + "username, password, fullname, isAdmin"
                        + ") "
                        + "Values("
                        + "?, ?, ?, ?"
                        + ")";
                //2.2 Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //2.3 Execute Query --> Result Set
                int effectedRow = stm.executeUpdate();
                //2.4 Process resul Set
                if (effectedRow > 0) {
                    result = true;
                }
            }//end connection is available
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
