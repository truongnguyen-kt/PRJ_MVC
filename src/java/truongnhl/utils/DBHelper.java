package truongnhl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class DBHelper {

    public static Connection makeConnection() throws SQLException, NamingException {

        //1. Find Server Context - JNDI java naming directory in
        Context serverContext = new InitialContext();
        //2. Fine Container Context
        Context tomcatContext = (Context) serverContext.lookup("java:comp/env");
        //3. Get DS
        DataSource ds = (DataSource) tomcatContext.lookup("MVC");
        //4. Open connection
        Connection con = ds.getConnection();
        return con;

//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Make Connection URL String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Register_PRJ301";
//        //3. Open Connect
//        Connection con = DriverManager.getConnection(url, "sa", "12345");
//        return con;
    }

    public static Properties getSiteMap(String siteMapFile, ServletContext context) 
            throws IOException {
        if (siteMapFile == null) {
            return null;
        }
        if (siteMapFile.trim().isEmpty()) {
            return null;
        }
        Properties result = new Properties();
        InputStream is = null;

        try {
            is = context.getResourceAsStream(siteMapFile);
            result.load(is);
            
            return result;
        } finally{
            if(is != null){
                is.close();
            }
        }
    }

}
