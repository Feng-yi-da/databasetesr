package main;
import java.sql.*;
public class SqlGetConnection {
	protected Connection connection = null;
	private String  address = "jdbc:mysql://localhost:3306/archivesmanagement?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private String name="root";
	private String password="7777777";
	 public Connection getconnection(){
		 try {
		      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		     System.out.println("�ɹ�����sql����");
		    }
		    catch (Exception e) {
		      System.out.print("sql��������ʧ��");
		      e.printStackTrace();
		    }
		    try {
		      connection = DriverManager.getConnection(
		         address,name,password);
		           //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������
		      System.out.println("�ɹ����ӵ����ݿ�");
		      System.out.println();
		    }
		    catch (Exception e) {
		      System.out.print("���ݿ�����ʧ�ܣ�");
		      System.out.println();
		      e.printStackTrace();
		    }
		return connection;
	 }

}
