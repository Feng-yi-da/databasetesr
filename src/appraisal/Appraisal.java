package appraisal;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import details.Details;
import inquire.SqlInquire;
import userUi.UI;

public class Appraisal extends JPanel{

	private JButton firstJButton= null;
	private JButton finalJButton= null;
	private JPanel upJPanel = null;
	
	private JButton appraisalJButton= null;
	private JButton cancelJButton= null;
	private JPanel downJPanel = null;
	
	
	private JTable tableJTabel = null;
	private DefaultTableModel tableModel = null;
	private JScrollPane scrollPane = null;
	
	private String [] columnNames = {"序号","编号","名称","卷","分类 ","密级","仓库","管理员"};
	private String [] [] columnNames1 = null;
	private String jiandingid = null;
	private String account = null;
	
	public void Appraisalcreate() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		upJPanel = new JPanel();
		
		firstJButton = new JButton("      初次        ");
		firstJButton.addActionListener(new onFristJButton());

		finalJButton = new JButton("      最终        ");
		finalJButton.addActionListener(new onFinalJButton());
		
		upJPanel.setLayout(new FlowLayout(1, 80, 2));
		upJPanel.add(firstJButton);
		upJPanel.add(finalJButton);
		///////////////////////////////////////////////////////////////////////////////////
		scrollPane = new JScrollPane();
		tableModel = new DefaultTableModel(columnNames1, columnNames);
		tableJTabel = new JTable(tableModel);
		//表格属性设置
		tableJTabel.setRowSorter(new TableRowSorter<>(tableModel));
		tableJTabel.setSelectionBackground(Color.GREEN);
		tableJTabel.setSelectionForeground(Color.RED);
		tableJTabel.setRowHeight(40);
		tableJTabel.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableJTabel);
		downJPanel = new JPanel();
		
		appraisalJButton = new JButton("      鉴定        ");
		appraisalJButton.addActionListener(new onAppraisal());
		
		cancelJButton = new JButton("     取消        ");
		cancelJButton.addActionListener(new onCancel());
		
		downJPanel.setLayout(new FlowLayout(1, 80, 10));
		downJPanel.add(appraisalJButton);
		downJPanel.add(cancelJButton);
		
		add(upJPanel,BorderLayout.NORTH);
		add(scrollPane,BorderLayout.CENTER);
		add(downJPanel,BorderLayout.SOUTH);
	}
	
	private class onFristJButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int n=tableModel.getRowCount();
			while(n>0){
				tableModel.removeRow(n-1);
				n--;
			}
			SqlFirstAppraisal sqlFirstAppraisal = new SqlFirstAppraisal();
			String [][] result=sqlFirstAppraisal.FirstAppraisal();
			if(Integer.parseInt(result[0][0])!=0){
				for (int i = 1; i <result.length; i++) {
						result[i][0]=i+"";
						tableModel.addRow(result[i]);
				}
				JOptionPane.showMessageDialog(scrollPane,
				 "查询成功  ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(scrollPane,
						 "查询结果为空  ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
	} 

	private class onFinalJButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int n=tableModel.getRowCount();
			while(n>0){
				tableModel.removeRow(n-1);
				n--;
			}
			SqlFinalAppraisal sqlFinalAppraisal = new SqlFinalAppraisal();
			String [][] result=sqlFinalAppraisal.FinalAppraisal(account);
			if(Integer.parseInt(result[0][0])!=0){
				for (int i = 1; i <result.length; i++) {
						result[i][0]=i+"";
						tableModel.addRow(result[i]);
				}
				JOptionPane.showMessageDialog(scrollPane,
				 "查询成功， ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(scrollPane,
						 "查询结果为空  ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		
	}

	private class onAppraisal implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub\
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// 判断是否存在被选中行
				jiandingid=tableJTabel.getValueAt(selectedRow, 1)+"";
				
				if (tableJTabel.getValueAt(selectedRow, 7)==null) {
					System.out.println("初次鉴定");
					SqlFirstAppraisal sqlFirstAppraisal = new SqlFirstAppraisal();
					System.out.println(jiandingid+"||"+account);
					sqlFirstAppraisal.onAppraisal(jiandingid, account);
					System.out.println(account+"jianding");
					JOptionPane.showMessageDialog(scrollPane,
							 "鉴定完毕 ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}else {
					System.out.println("最终鉴定");
					SqlFinalAppraisal sqlFinalAppraisal = new SqlFinalAppraisal();
					System.out.println(jiandingid+"||"+account);
					sqlFinalAppraisal.onAppraisal(jiandingid, account);
					System.out.println(account+"jianding");
					JOptionPane.showMessageDialog(scrollPane,
							 "鉴定完毕  ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}	
			}	
		}
	}
	
	public class onCancel implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// 判断是否存在被选中行
				jiandingid=tableJTabel.getValueAt(selectedRow, 1)+"";
				/////////////////////////////////////////////////////////
				if (tableJTabel.getValueAt(selectedRow, 7)==null) {
					System.out.println("初次鉴定");
					SqlFirstAppraisal sqlFirstAppraisal = new SqlFirstAppraisal();
					System.out.println(jiandingid+"||"+account);
					sqlFirstAppraisal.onCancel(jiandingid, account);
					System.out.println(account+"jianding");
					JOptionPane.showMessageDialog(scrollPane,
							 "取消鉴定，  ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}else {
					System.out.println("最终鉴定");
					SqlFinalAppraisal sqlFinalAppraisal = new SqlFinalAppraisal();
					System.out.println(jiandingid+"||"+account);
					sqlFinalAppraisal.onCancel(jiandingid, account);
					System.out.println(account+"jianding");
					JOptionPane.showMessageDialog(scrollPane,
							 "取消鉴定，  ", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}
				///////////////////////////////////////////////////////////////
				
			}
			
		}
	}
	private class onXiangQing implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// 判断是否存在被选中行
					jiandingid=tableJTabel.getValueAt(selectedRow, 1)+"";
					System.out.println(jiandingid);
					//JiangDingXiangXi jiangDingxiangXi = new JiangDingXiangXi(jiandingid);
			}	
			jiandingid = null;
		}
	}
	
	private class onChaXun implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int n=tableModel.getRowCount();
			while(n>0){
				tableModel.removeRow(n-1);
				n--;
			}
			/*
			SqlJianDingChaxun sqlJianDingChaxun = new SqlJianDingChaxun();
			
				String [][] result=sqlJianDingChaxun.ChaXunDangAn();
				if(Integer.parseInt(result[0][0])!=0){
					for (int i = 1; i <result.length; i++) {
						//biaoGeJTabel.
						tableModel.addRow(result[i]);
					}
				}
				*/
		}
		
	}
	
	private class onXiaoHui implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// 判断是否存在被选中行
				jiandingid=tableJTabel.getValueAt(selectedRow, 1)+"";
					//XiaoHui xiaoHui = new XiaoHui(jiandingid);
			}
			jiandingid = null;
		}
	}
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getDanganId(){
		return account ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI ui = new UI();
	}

}
