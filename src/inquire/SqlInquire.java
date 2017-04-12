package inquire;

import java.sql.*;
import main.SqlGetConnection;

public class SqlInquire {

	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	private String [][] inquire = null ;
	private int borrowtime = 1; 
	public SqlInquire() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
	
	public String [][] Inquire(String danganid ,String danganname,String jaunname,String keywords) {
		
		String sql ="select * "
				+ "from  inquire "
				+ "where danganid like "+"\'%"+danganid+"%\' "
						+ "and danganname like "+"\'%"+danganname+"%\' "
						+ "and juanname like "+"\'%"+jaunname+"%\' "
						+ "and keywords like "+"\'%"+keywords+"%\'";
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			resultSet.last();
			inquire = new String[resultSet.getRow()+1][9];
			inquire[0][0]=Integer.toString(resultSet.getRow());
			System.out.println("�������"+inquire[0][0]);
			resultSet.beforeFirst();
			
			if(resultSet==null){
				System.out.println("��ѯʧ�ܣ�");
			}
			else {
				while(resultSet.next()){
					inquire[resultSet.getRow()][1]=resultSet.getString("danganid");
					inquire[resultSet.getRow()][2]=resultSet.getString("danganname");
					inquire[resultSet.getRow()][3] = resultSet.getString("juanname");
					inquire[resultSet.getRow()][4] = resultSet.getString("danganleiname");
					inquire[resultSet.getRow()][5]=resultSet.getString("mijiname");
					inquire[resultSet.getRow()][6]=resultSet.getString("keywords");
					inquire[resultSet.getRow()][7]=resultSet.getString("jieyueid");
				}
			}
			resultSet.close();
			
			//�黹ʱ��
			sql ="select *,date_add(jieyue.jieyueriqi,interval "+borrowtime+" month)  from";
			sql = sql + " dangan natural join jieyue";
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()){
				for(int i = 1;i<=Integer.parseInt(inquire[0][0]);i++){
					if(inquire[i][7].equals(resultSet.getString("jieyueid"))){
						inquire[i][7]="���";
						inquire[i][8] = resultSet.getString("date_add(jieyue.jieyueriqi,interval "+borrowtime+" month)");
						break;
					}
				}
			}
			for(int i = 1;i<=Integer.parseInt(inquire[0][0]);i++){
				if (inquire[i][8]==null) {
					inquire[i][7]="δ���";
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
			/*
			System.out.println(inquire[0][0]);
			for(int i = 1;i<=Integer.parseInt(inquire[0][0]);i++){
				System.out.print(i+"��ţ�"+inquire[i][1]);
				System.out.print("\t���ƣ�"+inquire[i][2]);
				System.out.print("\t��"+inquire[i][3]);
				System.out.print("\t�����ࣺ"+inquire[i][4]);
				System.out.print("\t�����ܼ���"+inquire[i][5]);
				System.out.print("\t�ؼ��֣�"+inquire[i][6]);
				System.out.print("\t�Ƿ���ģ�"+inquire[i][7]);
				System.out.print("\t����ʱ�䣺"+inquire[i][8]);
				System.out.println();
			}
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inquire;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlInquire sqlInquire = new SqlInquire();
		sqlInquire.Inquire("", "", "","");
	}

}
