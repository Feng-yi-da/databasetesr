package appraisal;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.SqlGetConnection;

public class SqlFirstAppraisal {
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	private String [][] firstAppraisal = null ;
	private int borrowtime = 1; 
	
	public SqlFirstAppraisal() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
	
	public String[][] FirstAppraisal () {
		String sql ="select * "
				+ "from  firstappraisal ";
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			resultSet.last();
			firstAppraisal = new String[resultSet.getRow()+1][8];
			firstAppraisal[0][0]=Integer.toString(resultSet.getRow());
			System.out.println("结果数："+firstAppraisal[0][0]);
			resultSet.beforeFirst();
			
			if(resultSet==null){
				System.out.println("查询失败！");
			}
			else {
				while(resultSet.next()){
					firstAppraisal[resultSet.getRow()][1]=resultSet.getString("danganid");
					firstAppraisal[resultSet.getRow()][2]=resultSet.getString("danganname");
					firstAppraisal[resultSet.getRow()][3] = resultSet.getString("juanname");
					firstAppraisal[resultSet.getRow()][4] = resultSet.getString("danganleiname");
					firstAppraisal[resultSet.getRow()][5]=resultSet.getString("mijiname");
					firstAppraisal[resultSet.getRow()][6]=resultSet.getString("cangku");
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(firstAppraisal[0][0]);
		for(int i = 1;i<=Integer.parseInt(firstAppraisal[0][0]);i++){
			System.out.print(i+"编号："+firstAppraisal[i][1]);
			System.out.print("\t名称："+firstAppraisal[i][2]);
			System.out.print("\t卷："+firstAppraisal[i][3]);
			System.out.print("\t档案类："+firstAppraisal[i][4]);
			System.out.print("\t档案密级："+firstAppraisal[i][5]);
			System.out.print("\t关键字："+firstAppraisal[i][6]);
			System.out.println();
		}
		
		return firstAppraisal;
	}
	
	public void  onAppraisal(String danganid,String guanliyuan) {
		System.out.println(guanliyuan);
		System.out.println(danganid);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String date = df.format(new Date());
		try{
			String sql = "insert into jianding "+
						"select  danganid , danganname , keywords ,"+date+" "+
						"from dangan "+
						"where danganid = '"+danganid+" '";
			statement.executeUpdate(sql);
			
			sql = "insert into jiandingguanliyuan values "
					+ "('"+danganid+"', '"+guanliyuan+"' ,'' )";
			statement.executeUpdate(sql);
		}catch(Exception e){
			System.out.println("rsetPassword 连接失败！");
			e.printStackTrace();
		}
	}
	
	public void  onCancel(String danganid,String guanliyuan) {
		
		System.out.println(guanliyuan);
		System.out.println(danganid);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String date = df.format(new Date());
		
		try{
			
			String sql = "update dangan "+
					"set dangantime = "+ date+" "+
					"where danganid = '"+danganid+"'";
			statement.executeUpdate(sql);
			
			sql = "update danganguanliyuan "+
					"set guanliyuanid = "+ guanliyuan+" "+
					"where danganid = '"+danganid+"'";
			statement.executeUpdate(sql);
			
		}catch(Exception e){
			System.out.println("rsetPassword 连接失败！");
			e.printStackTrace();
		}
	}
}
