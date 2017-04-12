package returndangan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import main.SqlGetConnection;

public class SqlReturn {
	protected static Connection connection;
	protected static Statement statement;
	protected ResultSet result;
	private boolean retrun = false;
	public SqlReturn(String danganid) {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
		try {
			statement=connection.createStatement();
			String sql ="update dangan set jieyueid = '000000000000'"
					+ "where danganid = '"+danganid+"'";
			statement.executeUpdate(sql);
			
			statement=connection.createStatement();
			 sql ="select jieyueid "
					 +"from dangan "
					+ "where danganid = '"+danganid+"'";
			 result=statement.executeQuery(sql);
			 while(result.next()){
				 if (result.getString("jieyueid").equals("000000000000")) {
					 System.out.println(result.getString("jieyueid"));
					 retrun = true;
				}
			 }

		} catch(Exception e){
			System.out.println("rsetPassword ¡¨Ω” ß∞‹£°");
			e.printStackTrace();
		}
		
	}
	public boolean isReturn() {
		return retrun;
	}
}
