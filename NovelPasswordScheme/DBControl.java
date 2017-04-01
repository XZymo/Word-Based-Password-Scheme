package NovelPasswordScheme;

import java.sql.*;

public class DBControl {
	public static int insertNewUser(String fName, String lName, String pswrd){
		Connection c = null;
		Statement stmt = null;
		int id = -1;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:passwordSchemeData.db");
			stmt = c.createStatement();
			String sql = "INSERT INTO USERS (FNAME,LNAME,PASSWORD,T1FAILCOUNT,T1TIME,T2FAILCOUNT,T3FAILCOUNT,T4FAILCOUNT) " +
                   "VALUES ('"+ fName +"', '"+ lName +"', '"+ pswrd +"', 0, 0.0, 0, 0, 0);"; 
			stmt.executeUpdate(sql);
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID) FROM USERS");
			while (rs.next()){
				id = Integer.parseInt(rs.getString(1));
			}
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Insert successful");
		return id;
	}
}