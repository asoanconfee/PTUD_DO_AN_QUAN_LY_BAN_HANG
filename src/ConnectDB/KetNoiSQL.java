package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class KetNoiSQL {
    private static Connection conn = null;
    private static KetNoiSQL instance = new KetNoiSQL();
    
    public static KetNoiSQL getInstance(){
        return instance;
    }
    
    public void connect()  {
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QuanLyCuaHangThoiTrang3";
		String user = "sa";
		String password = "123";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void disconnect() {
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return conn;
	}
}


     



