package borrow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.SqlGetConnection;


public class SqlBorrow {
	protected static Connection connection;
	protected static Statement statement;
	protected ResultSet resultSet;
	private boolean jieyue = false;
	 private String time = null;
	 public SqlBorrow(String jieyueid,String jieyueidname,String company,String danganid) {
		// TODO Auto-generated constructor stub
		 SqlGetConnection getConnection=new SqlGetConnection();
			connection=getConnection.getconnection();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
			time=df.format(new Date());
			System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			try{
				String sql ="insert into jieyue (jieyueid,jieyuename,company,jieyueriqi) values ('"
						+ jieyueid+"','"+jieyueidname+"','"+company+"',"+time
						+ ");";
				statement=connection.createStatement();
				statement.executeUpdate(sql);
				
				sql ="insert into jieyuedangan (jieyueid,danganid) values ('"
						+ jieyueid+"','"+danganid
						+ "');";
				statement=connection.createStatement();
				statement.executeUpdate(sql);
				
				sql ="update dangan set jieyueid ='"
						+ jieyueid
						+ "' where danganid = '"+danganid+"';";
				statement=connection.createStatement();
				statement.executeUpdate(sql);
				jieyue = true;
			} catch (Exception e) {
			// TODO: handle exception
				System.out.println("���� ʧ�ܣ���login��");
				e.printStackTrace();
			}
	}
	 public boolean isBorrow(){
			return jieyue;
		}
}
