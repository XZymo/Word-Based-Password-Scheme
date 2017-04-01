package NovelPasswordScheme;

import java.sql.*;

public class DBControl {
	
	private static String className = "org.sqlite.JDBC";
	private static String connectionPath = "jdbc:sqlite:passwordSchemeData.db";
	
	public static int insertNewUser(String fName, String lName, String pswrd, int type){
		Connection c = null;
		Statement stmt = null;
		int id = -1;
		try {
			Class.forName(className);
			c = DriverManager.getConnection(connectionPath);
			stmt = c.createStatement();
			String sql = "INSERT INTO USERS (FNAME,LNAME,PASSWORD,TYPE,T1FAILCOUNT,T1TIME,T2FAILCOUNT,T3FAILCOUNT,T4FAILCOUNT) " +
                   "VALUES ('"+ fName +"', '"+ lName +"', '"+ pswrd +"', "+ type +", 0, 0.0, 0, 0, 0);"; 
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
	public static int updateTest1(int id, int failCount, double time){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(className);
			c = DriverManager.getConnection(connectionPath);
			stmt = c.createStatement();
			String sql = "UPDATE USERS SET T1FAILCOUNT="+ failCount +", T1TIME="+ time +" WHERE ID="+id+";";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Update successful");
		return id;
	}
	public static int updateTest2(int id, int failCount){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(className);
			c = DriverManager.getConnection(connectionPath);
			stmt = c.createStatement();
			String sql = "UPDATE USERS SET T2FAILCOUNT="+ failCount +" WHERE ID="+id+";";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Update successful");
		return id;
	}
	public static int updateTest3(int id, int failCount){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(className);
			c = DriverManager.getConnection(connectionPath);
			stmt = c.createStatement();
			String sql = "UPDATE USERS SET T3FAILCOUNT="+ failCount +" WHERE ID="+id+";";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Update successful");
		return id;
	}
	public static int updateTest4(int id, int failCount){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(className);
			c = DriverManager.getConnection(connectionPath);
			stmt = c.createStatement();
			String sql = "UPDATE USERS SET T4FAILCOUNT="+ failCount +" WHERE ID="+id+";";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Update successful");
		return id;
	}
	public static String[] getPasswords(String fName, String lName){
		Connection c = null;
		Statement stmt = null;
		String[] results = new String[3];
		try {
			Class.forName(className);
			c = DriverManager.getConnection(connectionPath);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM USERS WHERE FNAME='"+fName+"' AND LNAME='"+lName+"'");
			for (int i = 0; rs.next(); ++i){
				results[i] = rs.getString(1);
			}
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Passwords retreived successfully");
		return results;
	}
	public static void addTestResults(String fName, String lName, String p1, String p2, String p3, int t1, double time1, int t2, double time2, int t3, double time3){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(className);
			c = DriverManager.getConnection(connectionPath);
			stmt = c.createStatement();
			String sql = "INSERT INTO RESULTS (FNAME,LNAME,PASSWORD1,PASSWORD2,PASSWORD3,P1ATTEMPTS,P1TIME,P2ATTEMPTS,P2TIME,P3ATTEMPTS, P3TIME) " +
                   "VALUES ('"+fName+"','"+lName+"','"+p1+"','"+p2+"','"+p3+"',"+t1+","+time1+","+t2+","+time2+","+t3+","+time3+");"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Test result added successfully");
	}
}