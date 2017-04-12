package details;

import java.sql.*;

import main.SqlGetConnection;

public class SqlDetails {
	
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	private String details[]=new String [12];
	
	public SqlDetails(String danganid) {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
		String sql ="select * "
				+ "from  details "
				+ "where danganid = '"+danganid+"'";
		System.out.println("1");
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()){
				details[1]=resultSet.getString("danganid");
				details[2]=resultSet.getString("danganname");
				details[3]=resultSet.getString("cangkuname");
				details[4]=resultSet.getString("dangantime");
				details[5]=resultSet.getString("guanliyuanname");
				details[6]=resultSet.getString("storagelife");
				details[7]=resultSet.getString("jieyueid");
				details[8]=resultSet.getString("jieyuename");
				details[9]=resultSet.getString("company");
				details[10]=resultSet.getString("jieyueriqi");
				details[11]=resultSet.getString("keywords");
			}
			for(int i=1;i<=11;i++)
					System.out.println(details[i]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getdetails() {
		return details;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlDetails sqlDetails = new SqlDetails("010004AC0000");
	}

}
