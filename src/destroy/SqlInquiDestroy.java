package destroy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.SqlGetConnection;

public class SqlInquiDestroy {
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	
	private String [][] destroy = null ; 
	
	public SqlInquiDestroy() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
	public String [][] Destroy(String guanliyuan){
		String sql ="select * "
				+ "from  Destroy ;";
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			resultSet.last();
			destroy = new String[resultSet.getRow()+1][9];
			destroy[0][0]=Integer.toString(resultSet.getRow());
			System.out.println("结果数："+destroy[0][0]);
			resultSet.beforeFirst();
			
			if(resultSet==null){
				System.out.println("查询失败！");
			}
			else {
				while(resultSet.next()){
					destroy[resultSet.getRow()][1]=resultSet.getString("danganid");
					destroy[resultSet.getRow()][2]=resultSet.getString("danganname");
					destroy[resultSet.getRow()][3] = resultSet.getString("juanname");
					destroy[resultSet.getRow()][4] = resultSet.getString("danganleiname");
					destroy[resultSet.getRow()][5]=resultSet.getString("mijiname");
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(destroy[0][0]);
		for(int i = 1;i<=Integer.parseInt(destroy[0][0]);i++){
			System.out.print(i+"编号："+destroy[i][1]);
			System.out.print("\t名称："+destroy[i][2]);
			System.out.print("\t卷："+destroy[i][3]);
			System.out.print("\t档案类："+destroy[i][4]);
			System.out.print("\t档案密级："+destroy[i][5]);
			System.out.print("\t仓库："+destroy[i][6]);
			System.out.print("\t管理员1："+destroy[i][7]);
			System.out.print("\t管理员2："+destroy[i][8]);
			System.out.println();
		}
		return destroy;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlInquiDestroy sqlInquiDestroy = new SqlInquiDestroy();
		sqlInquiDestroy.Destroy(null);
	}

}
