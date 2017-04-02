package NovelPasswordScheme;

import java.sql.*;

public class SQLiteJDBC {
	public static void main(String args[]){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:data/passwordSchemeData.db");
			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS USERS;";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE USERS" +
					"(ID INTEGER PRIMARY KEY  AUTOINCREMENT," +
					"FNAME        TEXT    NOT NULL, " + 
					"LNAME        TEXT    NOT NULL, " + 
					"PASSWORD     CHAR(25), " +
					"TYPE         INT, " +
					"T1FAILCOUNT  INT, " + 
					"T1TIME       REAL, " +
					"T2FAILCOUNT  INT, " + 
					"T3FAILCOUNT  INT, " + 
					"T4FAILCOUNT  INT)"; 
			stmt.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS RESULTS;";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE RESULTS " +
					"(FNAME       TEXT    NOT NULL, " + 
					"LNAME        TEXT    NOT NULL, " + 
					"PASSWORD1    CHAR(25), " +
					"PASSWORD2    CHAR(25), " +
					"PASSWORD3    CHAR(25), " +
					"P1ATTEMPTS   INT, " +
					"P1TIME       REAL, " +
					"P2ATTEMPTS   INT, " +
					"P2TIME       REAL, " +
					"P3ATTEMPTS   INT, " +
					"P3TIME       REAL)"; 
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