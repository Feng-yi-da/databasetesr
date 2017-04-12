package registerAccount;

import java.sql.*;

import main.SqlGetConnection;

public class SqlRegisterAccount {
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	
	public SqlRegisterAccount() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
	public int RegisterAccount(String account ,String name, String password) {
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery("select guanliyuanid from guanliyuan "
					+ "where guanliyuanid = '"+account+"'");
			if (!resultSet.next()) {
				resultSet.close();
				statement.executeUpdate("insert into guanliyuan (guanliyuanid,guanliyuanname,password) values ( '"
						+account+"' , '"+name+"' , '"+password+"')");
				resultSet.close();
				statement.close();
				connection.close();
			} else {
				System.out.println("帐号已存在！");
				resultSet.close();
				statement.close();
				connection.close();
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlRegisterAccount sqlRegisterAccount = new SqlRegisterAccount();
		sqlRegisterAccount.RegisterAccount("2014081503", "李明", "1");
	}

}
