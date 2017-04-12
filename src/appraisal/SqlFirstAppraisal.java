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
			System.out.println("�������"+firstAppraisal[0][0]);
			resultSet.beforeFirst();
			
			if(resultSet==null){
				System.out.println("��ѯʧ�ܣ�");
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
			System.out.print(i+"��ţ�"+firstAppraisal[i][1]);
			System.out.print("\t���ƣ�"+firstAppraisal[i][2]);
			System.out.print("\t��"+firstAppraisal[i][3]);
			System.out.print("\t�����ࣺ"+firstAppraisal[i][4]);
			System.out.print("\t�����ܼ���"+firstAppraisal[i][5]);
			System.out.print("\t�ؼ��֣�"+firstAppraisal[i][6]);
			System.out.println();
		}
		
		return firstAppraisal;
	}
	
	public void  onAppraisal(String danganid,String guanliyuan) {
		System.out.println(guanliyuan);
		System.out.println(danganid);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
			System.out.println("rsetPassword ����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	public void  onCancel(String danganid,String guanliyuan) {
		
		System.out.println(guanliyuan);
		System.out.println(danganid);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
			System.out.println("rsetPassword ����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
}
