package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	
	private static Connection conn = null;

	public static Connection getConnection() {
		return conn;
	}

	public static void open() throws SQLException, IOException {
		Properties props = new Properties();
//		try (FileInputStream fis = new FileInputStream("db.properties")) {
        try{
//			// Eigenschaften laden
//			props.load(fis);
//
//			String uri = props.getProperty("db.uri");
//			String username = props.getProperty("db.username");
//			String password = props.getProperty("db.password");

            String uri = "jdbc:postgresql://localhost:5433/db01";
            String username = System.getenv("DBS_USERNAME");
            String password = System.getenv("DBS_PASSWORD");

			if (conn != null) {
				conn.close();
				conn = null;
			}

			conn = DriverManager.getConnection(uri, username, password);
			conn.setAutoCommit(false);
			System.out.println("Connect durchgefuehrt ....");
		} catch (Exception e) {
            System.err.println(e.getMessage());
        }
	}
}
