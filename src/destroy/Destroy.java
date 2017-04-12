package destroy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import appraisal.SqlFinalAppraisal;
import appraisal.SqlFirstAppraisal;

public class Destroy extends JPanel{
	private JButton inquireJButton= null;
	private JButton finalJButton= null;
	private JPanel upJPanel = null;
	
	private JButton destroyJButton= null;
	private JPanel downJPanel = null;
	
	
	public JTable tableJTabel = null;
	private DefaultTableModel tableModel = null;
	private JScrollPane scrollPane = null;
	
	private String [] columnNames = {"���","���","����","��","���� ","�ܼ�","�ֿ�"};
	private String [] [] columnNames1 = null;
	private String jiandingid = null;
	private String account = null;
	private int Y = 0;
	
	public void Destroycreate(){
		setLayout(new BorderLayout());
		upJPanel = new JPanel();
		
		inquireJButton = new JButton("      ��ѯ����        ");
		inquireJButton.addActionListener(new onInquireJButton());

		finalJButton = new JButton("      ��ѯ����        ");
		finalJButton.addActionListener(new onfinalJButton());
		
		upJPanel.setLayout(new FlowLayout(1, 80, 2));
		upJPanel.add(inquireJButton);
		upJPanel.add(finalJButton);
		///////////////////////////////////////////////////////////////////////////////////
		scrollPane = new JScrollPane();
		tableModel = new DefaultTableModel(columnNames1, columnNames);
		tableJTabel = new JTable(tableModel);
		//�����������
		tableJTabel.setRowSorter(new TableRowSorter<>(tableModel));
		tableJTabel.setSelectionBackground(Color.GREEN);
		tableJTabel.setSelectionForeground(Color.RED);
		tableJTabel.setRowHeight(40);
		tableJTabel.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableJTabel);
		downJPanel = new JPanel();
		
		destroyJButton = new JButton("      ����        ");
		destroyJButton.addActionListener(new onDestroyJButton());
		
		downJPanel.setLayout(new FlowLayout(1, 80, 10));
		downJPanel.add(destroyJButton);
		
		add(upJPanel,BorderLayout.NORTH);
		add(scrollPane,BorderLayout.CENTER);
		add(downJPanel,BorderLayout.SOUTH);	
	}
	
	private class onInquireJButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int n=tableModel.getRowCount();
			while(n>0){
				tableModel.removeRow(n-1);
				n--;
			}
			SqlInquireAppraisal sqlInquireAppraisal = new SqlInquireAppraisal();
			
			String [][] result=sqlInquireAppraisal.Appraisal(account);
			if(Integer.parseInt(result[0][0])!=0){
				for (int i = 1; i <result.length; i++) {
						result[i][0]=i+"";
						tableModel.addRow(result[i]);
				}
				JOptionPane.showMessageDialog(scrollPane,
				 "��ѯ�ɹ��� ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(scrollPane,
						 "��ѯ���Ϊ��  ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		
	}
	
	private class onfinalJButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int n=tableModel.getRowCount();
			while(n>0){
				tableModel.removeRow(n-1);
				n--;
			}
			SqlInquiDestroy sqlInquiDestroy = new SqlInquiDestroy();
			String [][] result=sqlInquiDestroy.Destroy(account);
			if(Integer.parseInt(result[0][0])!=0){
				for (int i = 1; i <result.length; i++) {
						result[i][0]=i+"";
						tableModel.addRow(result[i]);
				}
				JOptionPane.showMessageDialog(scrollPane,
				 "��ѯ�ɹ��� ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(scrollPane,
						 "��ѯ���Ϊ��  ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		
	}
	private class onDestroyJButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// �ж��Ƿ���ڱ�ѡ����
				jiandingid=tableJTabel.getValueAt(selectedRow, 1)+"";
				
				if (tableJTabel.getValueAt(selectedRow, 6)!=null) {
					System.out.println("����");
					SqlInquireAppraisal sqlInquireAppraisal = new SqlInquireAppraisal();
					
					sqlInquireAppraisal.onDestroy(jiandingid, account);
					System.out.println(jiandingid+"||"+account);
					System.out.println(account+"jianding");
					
					JOptionPane.showMessageDialog(scrollPane,
							 "������� ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}else {
					System.out.println("���ò���");
				}	
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
		
	}

}
