package details;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Details extends JFrame{
	
	private Container container=null;
	
	private JLabel danganidjLabel = null;
	private JLabel dangannamejLabel = null;
	private JLabel cangkuweizhijLabel = null;	
	private JLabel guanliyuannamelidangjLabel = null;
	private JLabel baocuntimenjLabel = null;
	private JLabel dangantimejLabel = null;
	
	private JLabel jiuyueidjLabel = null;
	private JLabel jieyuenamejLabel = null;
	private JLabel companyjLabel = null;	
	private JLabel jieuyuetimenjLabel = null;

	private JLabel keywordsJLabel = null;
	private JTextArea keywordsTextArea = null;
	private JPanel jPanel;
	private JScrollPane jScrollPane;
	private JButton jButton = null;
	private String [] details;
	
	public Details(String danganid) {
		// TODO Auto-generated constructor stub
		SqlDetails sqlDetails = new SqlDetails(danganid);
		details = sqlDetails.getdetails();
		if (details[7]==null) {
			NoDetails();
		} else {
			 YesDetails();
		}
	}
	
	public void NoDetails() {
		
		container = getContentPane();
		container.setLayout(null);

		danganidjLabel = new JLabel("档案编号: "+details[1]);
		danganidjLabel.setBounds(50, 30, 180, 30);
		container.add(danganidjLabel);
		dangannamejLabel = new JLabel("档案名称: "+details[2]);
		dangannamejLabel.setBounds(300, 30, 180, 30);
		container.add(dangannamejLabel);
		
		guanliyuannamelidangjLabel = new JLabel("立档人: "+details[5]);
		guanliyuannamelidangjLabel.setBounds(50, 100, 180, 30);
		container.add(guanliyuannamelidangjLabel);
		baocuntimenjLabel = new JLabel("立档时间: "+details[4]);
		baocuntimenjLabel.setBounds(300, 100, 180, 30);
		container.add(baocuntimenjLabel);
		
		cangkuweizhijLabel = new JLabel("存放位置: "+details[3]);
		cangkuweizhijLabel.setBounds(50, 170, 180, 30);
		container.add(cangkuweizhijLabel);
		dangantimejLabel = new JLabel("保存时间: "+details[6]);
		dangantimejLabel.setBounds(300, 170, 180, 30);
		container.add(dangantimejLabel);
		
		keywordsJLabel = new JLabel("关键字: ");
		keywordsJLabel.setBounds(20, 350, 50, 30);
		container.add(keywordsJLabel);
		keywordsTextArea = new JTextArea();
		//keywordsTextArea.setBounds(90, 250, 400, 230);
		keywordsTextArea.setLineWrap(true);
		keywordsTextArea.setText("1548485484");
		keywordsTextArea.setText(details[11]);
		jPanel = new JPanel();
		jScrollPane = new JScrollPane(keywordsTextArea);
		jScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		jScrollPane.setBounds(0, 0,400, 230);
		jPanel.add(jScrollPane);
		jPanel.setBounds(90, 250, 400, 230);
		jPanel.setBackground(Color.blue);
		jPanel.setLayout(null);
		container.add(jPanel);

		jButton = new JButton("确定");
		jButton.setBounds(230, 500, 80, 30);
		container.add(jButton);
		jButton.addActionListener(new onQueDing());
		
		setTitle("档案资料管理__详细");
		setSize(550, 600);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void  YesDetails() {
		
		container = getContentPane();
		container.setLayout(null);
		
		danganidjLabel = new JLabel("档案编号: "+details[1]);
		danganidjLabel.setBounds(50, 30, 180, 30);
		container.add(danganidjLabel);
		dangannamejLabel = new JLabel("档案名称: "+details[2]);
		dangannamejLabel.setBounds(300, 30, 180, 30);
		container.add(dangannamejLabel);
		
		guanliyuannamelidangjLabel = new JLabel("立档人: "+details[5]);
		guanliyuannamelidangjLabel.setBounds(50, 100, 180, 30);
		container.add(guanliyuannamelidangjLabel);
		baocuntimenjLabel = new JLabel("立档时间: "+details[4]);
		baocuntimenjLabel.setBounds(300, 100, 180, 30);
		container.add(baocuntimenjLabel);
		
		cangkuweizhijLabel = new JLabel("存放位置: "+details[3]);
		cangkuweizhijLabel.setBounds(50, 170, 180, 30);
		container.add(cangkuweizhijLabel);
		dangantimejLabel = new JLabel("保存时间: "+details[6]);
		dangantimejLabel.setBounds(300, 170, 180, 30);
		container.add(dangantimejLabel);
		
		
		jiuyueidjLabel = new JLabel("借阅编号: "+details[7]);
		jiuyueidjLabel.setBounds(50, 240, 180, 30);
		container.add(jiuyueidjLabel); 
		jieyuenamejLabel = new JLabel("借阅人姓名: "+details[8]);
		jieyuenamejLabel.setBounds(300, 240, 180, 30);
		container.add(jieyuenamejLabel); 
		
		companyjLabel = new JLabel("借阅单位: "+details[9]);
		companyjLabel.setBounds(50, 310, 180, 30);
		container.add(companyjLabel); 
		jieuyuetimenjLabel = new JLabel("借阅日期: "+details[10]);
		jieuyuetimenjLabel.setBounds(300, 310, 180, 30);
		container.add(jieuyuetimenjLabel);
	
		keywordsJLabel = new JLabel("关键字: ");
		keywordsJLabel.setBounds(20, 440, 50, 30);
		container.add(keywordsJLabel);
		keywordsTextArea = new JTextArea();
		keywordsTextArea.setText(details[11]);
		
		jPanel = new JPanel();
		jScrollPane = new JScrollPane(keywordsTextArea);
		jScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		jScrollPane.setBounds(0, 0,400, 180);
		jPanel.add(jScrollPane);
		jPanel.setBounds(90, 370, 400, 180);
		jPanel.setBackground(Color.blue);
		jPanel.setLayout(null);
		container.add(jPanel);
		
		jButton = new JButton("确定");
		jButton.setBounds(230, 580, 80, 30);
		container.add(jButton);
		jButton.addActionListener(new onQueDing());
		
		setTitle("档案资料管理__详细");
		setSize(550, 670);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private class onQueDing implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			setVisible(false);
		}
	}
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //Details details = new Details("010001AA0002");
		 Details details = new Details("010001AC0000");
	}

}
