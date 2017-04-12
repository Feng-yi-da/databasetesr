package filing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.SqlGetConnection;
import userUi.UI;

public class Filing extends JPanel{
	
	private JLabel JuanJLabel = null;
	private JLabel leiJLabel = null;
	private JLabel mijiJLabel = null;
	private JLabel cangKuJLable = null;
	private JLabel danganmingJLabel = null;
	private JTextField danganmingJText = null;
	
	private JLabel keywordsJLabel = null ;
	private JTextArea keywordsTextArea = null;
	private JPanel jPanel;
	private JScrollPane jScrollPane;
	
	private JButton jButton = null;
	
	protected static Connection connection;
	protected static Statement statement;
	protected ResultSet resultSet;
	
	private String[]juan1 = null;
	private String[]juan2 = null;
	private JComboBox<String> juanxuanze;
	private String[]lei1 = null;
	private String[]lei2 = null;
	private JComboBox<String> leixuanze ;
	private String[]miji1 = null;
	private String[]miji2 = null;
	private JComboBox<String> mijixuanze;
	private String[]cangku1 = null;
	private String[]cangku2 = null;
	private JComboBox<String> cangkuxuanze;
	private String account = null;
	public Filing() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public void Filingcreate() {
		String selecteditem = null;
		setLayout(null);

		String sql ="SELECT * FROM juan ";
		SqlGetConnection getConnection=new SqlGetConnection();
		connection=getConnection.getconnection();
				
		try {
			statement=connection.createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.last();
			juan1 = new String[resultSet.getRow()];
			juan2 = new String[resultSet.getRow()];
			resultSet.beforeFirst();
			while (resultSet.next()) {
				juan1[resultSet.getRow()-1]=resultSet.getString("juanname");
				juan2[resultSet.getRow()-1]=resultSet.getString("juanid");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		juanxuanze = new JComboBox<String>(juan1);
		JuanJLabel = new JLabel("卷：");
		JuanJLabel.setBounds(240, 60, 70, 30);
		add(JuanJLabel);
		juanxuanze.setBounds(280, 60, 120, 30);
		add(juanxuanze);
		
		
		sql="SELECT * FROM lei where not parentid  ='0'";
		try {
			statement=connection.createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.last();
	
			
			lei1 = new String[resultSet.getRow()];
			lei2 = new String[resultSet.getRow()];
			resultSet.beforeFirst();
			while (resultSet.next()) {
				if(resultSet.getString("danganleiid").length()==2){
					lei1[resultSet.getRow()-1]=resultSet.getString("danganleiname");
					lei2[resultSet.getRow()-1]=resultSet.getString("danganleiid");
				}
			
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leixuanze = new JComboBox<String>(lei1);
		leiJLabel = new JLabel("类：");
		leiJLabel.setBounds(550, 60, 70, 30);
		add(leiJLabel);
		leixuanze.setBounds(590,60, 120, 30);
		add(leixuanze);
		
		
		sql="SELECT * FROM miji";
		try {
			statement=connection.createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.last();
			miji1 = new String[resultSet.getRow()];
			miji2 = new String[resultSet.getRow()];
			resultSet.beforeFirst();
			while (resultSet.next()) {
				miji1[resultSet.getRow()-1]=resultSet.getString("mijiname");
				miji2[resultSet.getRow()-1]=resultSet.getString("mijiid");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mijixuanze = new JComboBox<String>(miji1);
		mijiJLabel = new JLabel("密级：");
		mijiJLabel.setBounds(240, 160, 70, 30);
		add(mijiJLabel);
		mijixuanze.setBounds(280, 160, 120, 30);
		add(mijixuanze);
		
		
		sql="SELECT * FROM cangku";
		try {
			statement=connection.createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.last();
			cangku1 = new String[resultSet.getRow()];
			cangku2 = new String[resultSet.getRow()];
			resultSet.beforeFirst();
			while (resultSet.next()) {
				cangku1[resultSet.getRow()-1]=resultSet.getString("cangkuname");
				cangku2[resultSet.getRow()-1]=resultSet.getString("cangkuid");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cangkuxuanze = new JComboBox<String>(cangku1);
		cangKuJLable = new JLabel("仓库：");
		cangKuJLable.setBounds(550,160,70,30);
		add(cangKuJLable);
		cangkuxuanze.setBounds(590, 160, 120, 30);
		add(cangkuxuanze);
	
		danganmingJLabel = new JLabel("档案名：");
		danganmingJLabel.setBounds(380, 240, 70, 30);
		add(danganmingJLabel);
		danganmingJText = new JTextField();
		danganmingJText.setBounds(430, 240, 140, 30);
		add(danganmingJText);
		/*
		keywordsJLabel = new JLabel("关键字：");
		keywordsJLabel.setBounds(380, 360, 70, 30);
		add(keywordsJLabel);
		*/
		keywordsJLabel = new JLabel("关键字: ");
		keywordsJLabel.setBounds(240, 410, 70, 30);
		add(keywordsJLabel);
		keywordsTextArea = new JTextArea();
		keywordsTextArea.setLineWrap(true);
		jPanel = new JPanel();
		jScrollPane = new JScrollPane(keywordsTextArea);
		jScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		jScrollPane.setBounds(0, 0,400, 230);
		jPanel.add(jScrollPane);
		jPanel.setBounds(290, 320, 400, 230);
		jPanel.setBackground(Color.blue);
		jPanel.setLayout(null);
		add(jPanel);
		/*
		keyworsJText = new JTextField();
		keyworsJText.setBounds(430, 360, 200, 30);
		add(keyworsJText);
		*/
		jButton = new JButton("录入");
		jButton.setBounds(430, 580, 100, 30);
		jButton.addActionListener(new onLiDang());
		add(jButton);
	}
	
	private class onLiDang implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String[] result=new String[7];
			if(danganmingJText.getText().length()==0){
				JOptionPane.showMessageDialog(getRootPane(),
						 "档案名不能为空!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}else if (danganmingJText.getText().length()>=15) {
				JOptionPane.showMessageDialog(getRootPane(),
						 "档案名不能超过15字!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}else if (keywordsTextArea.getText().length()>300) {
				JOptionPane.showMessageDialog(getRootPane(),
						 "档案关键字不能超过300字!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}else {
				result[0]=danganmingJText.getText();
				result[1]=keywordsTextArea.getText();
				result[2]=juan2[juanxuanze.getSelectedIndex()];
				result[3]=lei2[leixuanze.getSelectedIndex()];
				result[4]=miji2[mijixuanze.getSelectedIndex()];
				result[5]=cangku2[cangkuxuanze.getSelectedIndex()];
				result[6]=account;
				for(int i=0;i<6;i++)
				System.out.println(result[i]);
				System.out.println("1asfkjhasjdh：  "+account);
				SqlFiling sqlFiling = new SqlFiling(result);
				JOptionPane.showMessageDialog(getRootPane(),
						 "立档成功", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				danganmingJText.setText(null);
				keywordsTextArea.setText(null);
		}
	}
	
	
	}
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getDanganId(){
		return account;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI ui = new UI();
		ui.UIcreate();
	}

}
