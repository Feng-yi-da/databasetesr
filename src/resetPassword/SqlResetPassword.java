package resetPassword;

import java.sql.*;
import main.SqlGetConnection;


public class SqlResetPassword {
	public SqlResetPassword() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	private boolean result = false;
	public boolean ResetPassword(String account,String rsetPassword) {
		try {
			statement=connection.createStatement();
			System.out.println();
			statement.executeUpdate("update guanliyuan set password ="+rsetPassword
			+" where guanliyuanid ="+account+";");         
			System.out.println("�޸����� ִ��");
			resultSet=statement.executeQuery("SELECT password FROM guanliyuan where guanliyuanid = "+account+";");
			System.out.println("�޸����� ��ѯ");
			while(resultSet.next()){
				if(resultSet.getString("password").equals(rsetPassword)){
					System.out.println("�޸����� �ɹ�");
					result=true;
				}
				else{
					System.out.println("�޸����� ʧ��");
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("�޸����� ����ʧ�ܣ�");
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlResetPassword sqlResetPassword = new SqlResetPassword();
		sqlResetPassword.ResetPassword("1", "2");
	}

}
