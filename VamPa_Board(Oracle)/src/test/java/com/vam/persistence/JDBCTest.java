package com.vam.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCTest {
	
	@Test
	public void testConnection1() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "ora_user";
		String password = "1234";
		
		String query = "select * from dual";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        System.out.println("드라이버 찾기 성공");
	        
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("연결 성공");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			System.out.println("쿼리 실행 성공");
			
			while(rs.next()) {
//				rs.getString(0);
			}		
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
    static { 
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 찾기 성공");
            
        } catch(Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
    @Test 
    public void testConnection() {
    	String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "ora_user";
		String password = "1234";
		
        try(Connection con = DriverManager.getConnection( 
                url, 
                user, 
                password)){ 
            System.out.println(con); 
        } catch (Exception e) { 
            fail(e.getMessage()); 
        }    
    }    
}