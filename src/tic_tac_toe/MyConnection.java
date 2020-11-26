package tic_tac_toe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection 
{
    public static Connection getConnection()
    {     
        Connection con = null;
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/userdatabase", "root", "");
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            System.out.println(ex.getMessage());
        } 
        return con;
    }
}