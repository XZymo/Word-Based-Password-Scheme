package NovelPasswordScheme;

import java.sql.*;

public class SQLiteJDBC {
	public static void main(String args[]){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:passwordSchemeData.db");
			stmt = c.createStatement();
			String sql = "CREATE TABLE USERS " +
					"(ID INT PRIMARY KEY     NOT NULL," +
					"FNAME           TEXT    NOT NULL, " + 
					"LNAME           INT     NOT NULL, " + 
					"PASSWORD        CHAR(25), " +
					"T1ATTEMPTCOUNT  INT, " + 
					"T2ATTEMPTCOUNT  INT, " + 
					"T3ATTEMPTCOUNT  INT, " + 
					"T4ATTEMPTCOUNT  INT)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Database created successfully");
	}
}