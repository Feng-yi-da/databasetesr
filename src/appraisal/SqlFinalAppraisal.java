package appraisal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.SqlGetConnection;

public class SqlFinalAppraisal {
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	private String [][] finalAppraisal = null ;
	public SqlFinalAppraisal() {
		// TODO Auto-generated constructor stub
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
	}
	
	public String [][] FinalAppraisal(String guanliyuan){
		String sql ="select * "
				+ "from  Finalappraisal "
				+ "where guanliyuanid != "+ guanliyuan+" ";
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			resultSet.last();
			finalAppraisal = new String[resultSet.getRow()+1][8];
			finalAppraisal[0][0]=Integer.toString(resultSet.getRow());
			System.out.println("�������"+finalAppraisal[0][0]);
			resultSet.beforeFirst();
			
			if(resultSet==null){
				System.out.println("��ѯʧ�ܣ�");
			}
			else {
				while(resultSet.next()){
					finalAppraisal[resultSet.getRow()][1]=resultSet.getString("danganid");
					finalAppraisal[resultSet.getRow()][2]=resultSet.getString("danganname");
					finalAppraisal[resultSet.getRow()][3] = resultSet.getString("juanname");
					finalAppraisal[resultSet.getRow()][4] = resultSet.getString("danganleiname");
					finalAppraisal[resultSet.getRow()][5]=resultSet.getString("mijiname");
					finalAppraisal[resultSet.getRow()][6]=resultSet.getString("cangku");
					finalAppraisal[resultSet.getRow()][7]=resultSet.getString("guanliyuanid");
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(finalAppraisal[0][0]);
		for(int i = 1;i<=Integer.parseInt(finalAppraisal[0][0]);i++){
			System.out.print(i+"��ţ�"+finalAppraisal[i][1]);
			System.out.print("\t���ƣ�"+finalAppraisal[i][2]);
			System.out.print("\t��"+finalAppraisal[i][3]);
			System.out.print("\t�����ࣺ"+finalAppraisal[i][4]);
			System.out.print("\t�����ܼ���"+finalAppraisal[i][5]);
			System.out.print("\t�ֿ⣺"+finalAppraisal[i][6]);
			System.out.print("\t����Ա��"+finalAppraisal[i][7]);
			System.out.println();
		}
		return finalAppraisal;
	}
	
	public void  onAppraisal(String danganid,String guanliyuan) {
		
		System.out.println(guanliyuan);
		System.out.println(danganid);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		String date = df.format(new Date());
		try{
			//���¼���ʱ��
			String sql = "update jianding "+
					"set jiandingtime = "+ date+" "+
					"where jiandingid = '"+danganid+"'";
			statement.executeUpdate(sql);
			//��ӹ���Ա2
			sql = "update jiandingguanliyuan "+
					"set guanliyuanid2 = "+ guanliyuan+" "+
					"where jiandingid = '"+danganid+"'";
			statement.executeUpdate(sql);
			//�ֿ�
			sql = "insert into jiandingcangku values "
					+ "('"+danganid+"', '10')";
			statement.executeUpdate(sql);
			//�ӵ�����ɾ��
			sql = "delete from dangan  "
					+ "where danganid = '"+danganid+"'";
			statement.executeUpdate(sql);
			//�ӵ����ֿ�ɾ��
			sql = "delete from dangancangku  "
					+ "where danganid = '"+danganid+"'";
			statement.executeUpdate(sql);
			//�ӵ�������Աɾ��
			sql = "delete from danganguanliyuan  "
					+ "where danganid = '"+danganid+"'";
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
			//��������ʱ��
			String sql = "update dangan "+
					"set dangantime = "+ date+" "+
					"where danganid = '"+danganid+"'";
			statement.executeUpdate(sql);
			//����������
			sql = "update danganguanliyuan "+
					"set guanliyuanid = "+ guanliyuan+" "+
					"where danganid = '"+danganid+"'";
			statement.executeUpdate(sql);
			//ɾ��jianding����
			sql = "delete from jianding  "
					+ "where jiandingid = '"+danganid+"'";
			statement.executeUpdate(sql);
			//ɾ��jiandingguanliyuan
			sql = "delete from jianding  "
					+ "where jiandingid = '"+danganid+"'";
			statement.executeUpdate(sql);
		}catch(Exception e){
			System.out.println("rsetPassword ����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlFinalAppraisal sqlFinalAppraisal = new SqlFinalAppraisal();
		sqlFinalAppraisal.FinalAppraisal("1");
	
	}

}
