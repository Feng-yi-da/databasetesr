package login;

import java.sql.*;
import main.SqlGetConnection;

public class SqlLogin {
	
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	
	public SqlLogin() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
			
	public int Login(String account , String password){
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery("select guanliyuanid from guanliyuan "
					+ "where guanliyuanid = '"+account+"'");
			if(resultSet.next()){
				System.out.println("�ʺŴ���");
				resultSet.close();
				resultSet=statement.executeQuery("select guanliyuanid,password from guanliyuan "
						+ "where guanliyuanid = '"+account+"'");
				resultSet.next();
				if (resultSet.getString("guanliyuanid").equals(account)&&
						resultSet.getString("password").equals(password)) {
					System.out.println("������ȷ");
					resultSet.close();
				} else {
					System.out.println("�������");
					resultSet.close();
					return 0;
				}	
			}else {
				System.out.println("�ʺŲ�����");
				resultSet.close();
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlLogin sqlLogin = new SqlLogin();
		sqlLogin.Login("201408150", "123456");
	}

}
