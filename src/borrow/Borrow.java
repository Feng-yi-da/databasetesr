package borrow;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import main.SqlGetConnection;


public class Borrow extends JFrame{
	protected static Connection connection;
	protected static Statement statement;
	protected ResultSet resultSet;
	private SimpleDateFormat df ;
	private String date = null;
	
	private Container container=null;
	private JLabel jieyueidJLabel = null;
	private JLabel jieyuenameJLabel = null;
	public JTextField jieyuenameJTextField = null;
	private JLabel companyJLabel = null;
	public JTextField companyJTextField = null;
	private JLabel danganidJLabel = null;
	private JLabel dangannameJLabel = null;
	private JLabel dangancangkuJLabel = null;
	private JButton jButton = null;
	private String jieyueid = null;
	private String danganid = null;
	private boolean isBorrow = false;
	public Borrow(String danganid) {
		// TODO Auto-generated constructor stub
		this.danganid=danganid;
		container =getContentPane();
		container.setLayout(null);
		
		df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		date = df.format(new Date());
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
		try {
			String sql ="select jieyueid from  jieyuedangan where jieyueid like '"+date+"%' order by jieyueid desc";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(sql);
			String num = "0000";
			if(resultSet.next()){
				System.out.print(resultSet.getString(1)+"  ");
				num = resultSet.getString(1).substring(resultSet.getString(1).length()-4,
						resultSet.getString(1).length());
				System.out.println("今天有借书记录");
			}else{
				System.out.println("今天无借书记录");
			}
				 int n = Integer.parseInt(num)+1;
				 DecimalFormat df=new DecimalFormat("0000");
			     jieyueid=df.format(n);
			     resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println(jieyueid);
		jieyueid=  date+jieyueid;
		jieyueidJLabel = new JLabel("借阅编号:            "+jieyueid,jieyueidJLabel.RIGHT);
		jieyueidJLabel.setBounds(150, 40, 180, 30);
		container.add(jieyueidJLabel);
		
		jieyuenameJLabel = new JLabel("姓名：",jieyuenameJLabel.RIGHT);
		jieyuenameJLabel.setBounds(150, 100, 70, 30);
		container.add(jieyuenameJLabel);
		jieyuenameJTextField = new JTextField();
		jieyuenameJTextField.setBounds(230, 100, 170, 30);
		container.add(jieyuenameJTextField);
		
		companyJLabel = new JLabel("单位：",companyJLabel.RIGHT);
		companyJLabel.setBounds(150, 160, 70, 30);
		container.add(companyJLabel);
		companyJTextField = new JTextField();
		companyJTextField.setBounds(230, 160,170, 30);
		container.add(companyJTextField);
		
		
		danganidJLabel = new JLabel("档案编号:             "+danganid);
		danganidJLabel.setBounds(150, 220, 180, 30);
		container.add(danganidJLabel);
		
		String weizhi = null;
		try {
			String sql ="select * from  dangan natural join dangancangku natural join cangku  where danganid ='"+danganid
					+ "'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
			weizhi = resultSet.getString("cangkuname");
			}
			     resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dangannameJLabel = new JLabel("档案名称:             "+danganid);
		dangannameJLabel.setBounds(150, 280, 180, 30);
		container.add(dangannameJLabel);
		
		dangancangkuJLabel = new JLabel("档案位置:             "+weizhi);
		dangancangkuJLabel.setBounds(150, 340, 180, 30);
		container.add(dangancangkuJLabel);
		
		jButton = new JButton("借阅");
		jButton.setBounds(180, 400, 210, 30);
		container.add(jButton);
		jButton.addActionListener(new onBorrow());
	
		setTitle("档案资料管理__借阅");
		setSize(550, 500);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private class onBorrow implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(jieyuenameJTextField.getText().length()!=0){
				if(jieyuenameJTextField.getText().length()<=6){
					if(companyJTextField.getText().length()!=0){
						if (companyJTextField.getText().length()<=15) {
							
							SqlBorrow sqlBorrow = new SqlBorrow(jieyueid,jieyuenameJTextField.getText(),
									companyJTextField.getText(),danganid);
							System.out.println("点击借阅");
								 isBorrow = sqlBorrow.isBorrow();
								 System.out.println("isBorrow1"+isBorrow);
								if (isBorrow) {
									JOptionPane.showMessageDialog(getContentPane(),
										 "借出成功", "系统信息", JOptionPane.INFORMATION_MESSAGE);
									setVisible(false);
								}else {
									JOptionPane.showMessageDialog(getContentPane(),
											 "归还失败", "系统信息", JOptionPane.INFORMATION_MESSAGE);
								}
								
						}else {
							JOptionPane.showMessageDialog(getContentPane(),
									 "单位名称过长", "系统信息", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}else {
						JOptionPane.showMessageDialog(getContentPane(),
							 "单位不能为空", "系统信息", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(),
							 "姓名过长", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(getContentPane(),
						 "姓名不能为空", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
					
			
		}
	}
	
	public boolean isBorrow() {
		return isBorrow;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Borrow borrow = new Borrow("010001AA0001");
	}

}
