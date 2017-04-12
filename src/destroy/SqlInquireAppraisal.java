package destroy;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.SqlGetConnection;

public class SqlInquireAppraisal {

	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	private String [][] appraisal = null ; 
	public SqlInquireAppraisal() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
	
	public String [][] Appraisal(String guanliyuan){
		String sql ="select * "
				+ "from  Appraisal "
				+ "where not guanliyuanid1  = '"+ guanliyuan +"'and "+ " not guanliyuanid2 = '"+ guanliyuan+"' ";
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			resultSet.last();
			appraisal = new String[resultSet.getRow()+1][9];
			appraisal[0][0]=Integer.toString(resultSet.getRow());
			System.out.println("结果数："+appraisal[0][0]);
			resultSet.beforeFirst();
			
			if(resultSet==null){
				System.out.println("查询失败！");
			}
			else {
				while(resultSet.next()){
					appraisal[resultSet.getRow()][1]=resultSet.getString("danganid");
					appraisal[resultSet.getRow()][2]=resultSet.getString("danganname");
					appraisal[resultSet.getRow()][3] = resultSet.getString("juanname");
					appraisal[resultSet.getRow()][4] = resultSet.getString("danganleiname");
					appraisal[resultSet.getRow()][5]=resultSet.getString("mijiname");
					appraisal[resultSet.getRow()][6]=resultSet.getString("cangku");
					appraisal[resultSet.getRow()][7]=resultSet.getString("guanliyuanid1");
					appraisal[resultSet.getRow()][8]=resultSet.getString("guanliyuanid2");
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(appraisal[0][0]);
		for(int i = 1;i<=Integer.parseInt(appraisal[0][0]);i++){
			System.out.print(i+"编号："+appraisal[i][1]);
			System.out.print("\t名称："+appraisal[i][2]);
			System.out.print("\t卷："+appraisal[i][3]);
			System.out.print("\t档案类："+appraisal[i][4]);
			System.out.print("\t档案密级："+appraisal[i][5]);
			System.out.print("\t仓库："+appraisal[i][6]);
			System.out.print("\t管理员1："+appraisal[i][7]);
			System.out.print("\t管理员2："+appraisal[i][8]);
			System.out.println();
		}
		return appraisal;
	}
	public void  onDestroy(String danganid,String guanliyuan) {
		
		System.out.println(guanliyuan);
		System.out.println(danganid);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String date = df.format(new Date());
		try{
			
			statement=connection.createStatement();
			String	sql;
			
				sql = "insert into xiaohui "+
						"select  jiandingid,jiandingname,keywords,"+date+" "+
						"from jianding "+
						"where jiandingid = '"+danganid+"'";
				statement.executeUpdate(sql);
				
				sql = "insert into xiaohuiguanliyuan values "
						+ "('"+danganid+"', '"+guanliyuan+"')";
				statement.executeUpdate(sql);
				
				
				sql = "delete from jianding  "
						+ "where jiandingid = '"+danganid+"'";
				statement.executeUpdate(sql);
				
				sql = "delete from jiandingguanliyuan  "
						+ "where jiandingid = '"+danganid+"'";
				
				sql = "delete from jiandingcangku  "
						+ "where jiandingid = '"+danganid+"'";
				statement.executeUpdate(sql);
				
		}catch(Exception e){
			System.out.println("rsetPassword 连接失败！");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlInquireAppraisal sqlInquireAppraisal = new SqlInquireAppraisal();
		sqlInquireAppraisal.Appraisal("1");
		
	}

}
