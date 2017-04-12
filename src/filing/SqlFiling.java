package filing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.SqlGetConnection;


public class SqlFiling {
	protected static Connection connection;
	protected static Statement statement;
	protected ResultSet resultSet;
	private String danganid;
	
	public SqlFiling(String[] result) {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			String date = df.format(new Date());
			//阿萨德 0 档案名
		    //阿萨德 1关键字
			//010001 2 卷号
			//AA 3 类号
			//00 4密级号
			//01 5仓库号
			//2014081502 6
			     
			String sql ="select danganid from  dangan where danganid like '"+
							result[2]+""+result[3]+"%' order by danganid desc";
			System.out.println(sql);
			statement=connection.createStatement();
			resultSet = statement.executeQuery(sql);
			String num = "0000";
			if(resultSet.next()){
				System.out.print(resultSet.getString(1)+" 11 ");
				num = resultSet.getString(1).substring(resultSet.getString(1).length()-4,
						resultSet.getString(1).length());
			}
				 int n = Integer.parseInt(num)+1;
				 DecimalFormat df1=new DecimalFormat("0000");
			     danganid=df1.format(n);
			     danganid=result[2]+result[3]+danganid;
			     System.out.println(danganid);
			     
			     sql="insert into dangan values ('"+danganid+"','"+result[0]+"','000000000000','"+result[1]+"', "+date+")";
			     //System.out.println(sql);
			     statement.executeUpdate(sql);

			     sql ="insert into dangancangku values ('"+danganid+"','"+result[5]+"')";
			     statement.executeUpdate(sql);
			     
			     sql ="insert into danganguanliyuan values ('"+danganid+"','"+result[6]+"')";
			     statement.executeUpdate(sql);
			     
			     sql ="insert into danganjuan values ('"+danganid+"','"+result[2]+"')";
			     statement.executeUpdate(sql);
			     sql ="insert into danganlei values ('"+danganid+"','"+result[3]+"')";
			     statement.executeUpdate(sql);
			     sql ="insert into danganmiji values ('"+danganid+"','"+result[4]+"')";
			     statement.executeUpdate(sql);
			     statement.close();
			
			     connection.close();
		}
		catch(Exception e){
			System.out.println("查询 log信息 失败！（login）");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
