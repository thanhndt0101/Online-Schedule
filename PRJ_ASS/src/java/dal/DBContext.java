/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HaiCao
 * @param <T>
 */
public abstract class DBContext<T> {
    protected Connection conection;

    public DBContext() {
        try {
            String user = "sa";
            String pass = "tho3152003";
            String url= "jdbc:sqlserver://ADMIN:1433;databaseName=Assignment";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            conection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract ArrayList<T> list();
    public abstract ArrayList<T> list(String identity);
    public abstract T get(T entity);
    public abstract void insert(T entity);
    public abstract void delete(T entity);
    public abstract void update(T entity);
}
