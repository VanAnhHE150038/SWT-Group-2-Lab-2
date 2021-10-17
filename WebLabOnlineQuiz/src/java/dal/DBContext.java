/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class DBContext {

    String connectionURL;
    String image, username, password;
    String timePerQ;

    public DBContext() {
        try {
            InitialContext initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java://comp/env");
            connectionURL = environmentContext.lookup("myConnectionURL").toString();
            username = environmentContext.lookup("username").toString();
            password = environmentContext.lookup("password").toString();
            image = environmentContext.lookup("image").toString();
            timePerQ = environmentContext.lookup("timePerQ").toString();
        } catch (NamingException ex) {
            System.out.println("Error at initConfig: " + ex);
        }
    }

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionURL, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error at getConnection: " + ex);
        }
        return null;
    }

    public String getImagePath() {
        return image;
    }

    public String getTimePerQ() {
        return timePerQ;
    }

}
