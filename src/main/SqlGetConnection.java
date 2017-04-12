package main;
import java.sql.*;
public class SqlGetConnection {
	protected Connection connection = null;
	private String  address = "jdbc:mysql://localhost:3306/archivesmanagement?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private String name="root";
	private String password="7777777";
	 public Connection getconnection(){
		 try {
		      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
		     System.out.println("成功加载sql驱动");
		    }
		    catch (Exception e) {
		      System.out.print("sql驱动加载失败");
		      e.printStackTrace();
		    }
		    try {
		      connection = DriverManager.getConnection(
		         address,name,password);
		           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		      System.out.println("成功连接到数据库");
		      System.out.println();
		    }
		    catch (Exception e) {
		      System.out.print("数据库连接失败！");
		      System.out.println();
		      e.printStackTrace();
		    }
		return connection;
	 }

}
