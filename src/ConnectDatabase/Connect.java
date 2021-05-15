package ConnectDatabase;

import java.sql.*;

public class Connect {
	private Connection connection;
	String className="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/khoa";
	String username="root";
	String password ="khoa12345";
	String table="pointuser";
	public void getConnect() {
		try {
			Class.forName(className);
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("Connect suscess!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet getData() {
		ResultSet rs=null;
		String sqlCommand="select * from "+table;
		try {
			Statement st=connection.createStatement();
			rs= st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	public void UpdateData(Account account) {
		String sqlCommand="update "+table+" set maxpoint=? where username=? and password=? ";
		try {
			PreparedStatement ps=connection.prepareStatement(sqlCommand);
			ps.setFloat(1,account.getMaxpoint());
			ps.setString(2, account.getUsername());
			ps.setString(3,account.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteAccount(Account account) {
		String sqlCommand="delete from "+table+" where username=? and password=? ";
		try {
			PreparedStatement ps=connection.prepareStatement(sqlCommand);
			ps.setString(1,account.getUsername() );
			ps.setString(2, account.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void changeInfo(Account account) {
		String sqlCommand="update "+table+" set username=? and password=? where username=? and password=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sqlCommand) ;
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getUsername());
			ps.setString(4, account.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
