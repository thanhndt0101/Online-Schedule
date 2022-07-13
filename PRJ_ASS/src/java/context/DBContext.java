/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DBContext {
    public Connection getConnection() throws Exception{
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber+";databaseName="+DBName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url,userID , password);
    }
    private final String serverName = "ADMIN" ;
    private final String DBName = "Assignment" ;
    private final String portNumber = "1433" ;
    private final String userID = "sa" ;
    private final String password = "tho3152003" ;
    public static void main(String[] args) {
        try{
            DBContext dbContext = new DBContext();
            if (dbContext.getConnection()!=null){
                System.out.println("ket noi thanh cong");
            }else{
                System.out.println("ket noi that bai");
            }
        }catch(Exception ex){
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
