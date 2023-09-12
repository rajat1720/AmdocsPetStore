package com.amdocs.petsearch.doa;

import java.sql.*;
import java.util.*;
import java.util.Scanner;

import com.amdocs.petsearch.model.PetClass;
import com.amdocs.petsearch.exception.*;


public class DatabaseManager {
    private static final String jdbcUrl = "Jdbc:Oracle:thin:@localhost:1521:orcl";
    private static final String username = "scott";
    private static final String password = "tiger";
    
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
    
    
}