package inquire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.Activator;

import javax.swing.*;
import javax.swing.table.*;
import javax.xml.soap.Detail;

import borrow.Borrow;
import details.Details;
import returndangan.SqlReturn;

public class Inquire extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel danganidJLabel = null;
	private JTextField danganidJTextField = null;
	
	private JLabel dangannameJLabel = null;
	private JTextField dangannameJTextField = null;
	
	private JLabel juannameJLabel = null;
	private JTextField juannameJTextField = null;
	
	private JLabel keywordsJLabel = null;
	private JTextField keywordsJTextField = null;
	
	private JButton inquireJButton = null;
	private JPanel upJPanel = null;
	
	private JTable tableJTabel = null;
	private DefaultTableModel tableModel = null;
	private JScrollPane scrollPane = null;
	
	private JButton detailsJButton= null;
	private JButton returnJButton= null;
	private JButton borrowJButton= null;
	private JPanel downJPanel = null;
	
	private String [] columnNames = {"���","���","����","��","���� ","�ܼ�","�ؼ��� ","�Ƿ���� ","�黹ʱ�� "};
	private String [] [] columnNames1 = null;
	private String danganid = null;
	private String account = null;
	public  void Inquirecreate() {
		// TODO Auto-generated method stub
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		upJPanel = new JPanel();
		
		danganidJLabel = new JLabel("��ţ�",danganidJLabel.RIGHT);
		danganidJTextField = new JTextField(null,15);
		
		dangannameJLabel = new JLabel("���ƣ�",dangannameJLabel.RIGHT);
		dangannameJTextField = new JTextField(null,15);
		
		juannameJLabel = new JLabel("������",juannameJLabel.RIGHT);
		juannameJTextField = new JTextField(null,15);
		
		keywordsJLabel = new JLabel("�ؼ��֣�",keywordsJLabel.RIGHT);
		keywordsJTextField = new JTextField(null,15);
		
		inquireJButton = new JButton("    ��ѯ    ");
		inquireJButton.addActionListener(new onInquire());
		
		upJPanel.add(danganidJLabel);
		upJPanel.add(danganidJTextField);
		
		upJPanel.add(dangannameJLabel);
		upJPanel.add(dangannameJTextField);
		
		upJPanel.add(juannameJLabel);
		upJPanel.add(juannameJTextField);
		
		upJPanel.add(keywordsJLabel);
		upJPanel.add(keywordsJTextField);
		
		upJPanel.add(inquireJButton);
		
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
		detailsJButton = new JButton("        ��ϸ              ");
		detailsJButton.addActionListener(new onDetails());
		borrowJButton = new JButton("         ����           ");
		borrowJButton.addActionListener(new onBorrow());
		returnJButton = new JButton("         �黹              ");
		returnJButton.addActionListener(new onReturn());
		
		downJPanel.setLayout(new FlowLayout(1, 80, 10));
		downJPanel.add(detailsJButton);
		downJPanel.add(borrowJButton);
		downJPanel.add(returnJButton);
		
		add(upJPanel,BorderLayout.NORTH);
		add(scrollPane,BorderLayout.CENTER);
		add(downJPanel,BorderLayout.SOUTH);
	}
	
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getDanganId(){
		return account;
	}
	
	private class onInquire implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int n=tableModel.getRowCount();
			while(n>0){
				tableModel.removeRow(n-1);
				n--;
			}
				if(danganidJTextField.getText().length()<=12){
					if (dangannameJTextField.getText().length()<=15) {
						if(juannameJTextField.getText().length()<=15){
							if(keywordsJTextField.getText().length()<=30){
									SqlInquire sqlInquire = new SqlInquire();
								String [][] result=sqlInquire.Inquire
										(danganidJTextField.getText(), dangannameJTextField.getText(),
												juannameJTextField.getText(), keywordsJTextField.getText());
								if(Integer.parseInt(result[0][0])!=0){
									for (int i = 1; i <result.length; i++) {
										result[i][0]=i+"";
										tableModel.addRow(result[i]);
									}
									JOptionPane.showMessageDialog(scrollPane,
											 "��ѯ�ɹ���  ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(scrollPane,
											 "��ѯ���Ϊ�գ�  ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
									}
							}else {
								JOptionPane.showMessageDialog(scrollPane,
									 "�ؼ��ֳ���30��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
								keywordsJTextField.setText(null);
							}
						}else {
							JOptionPane.showMessageDialog(scrollPane,
									"��������15��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
							juannameJTextField.setText(null);
						}						
					} else {
						JOptionPane.showMessageDialog(scrollPane,
								"������ ����15��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						dangannameJTextField.setText(null);
					}
				}else {
						JOptionPane.showMessageDialog(scrollPane,
								"��ų���12λ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						danganidJTextField.setText(null);
				}	
		} 
	}
	
	private class onDetails implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// �ж��Ƿ���ڱ�ѡ����
					danganid=tableJTabel.getValueAt(selectedRow, 1)+"";
					System.out.println(danganid);
					Details details = new Details(danganid);
			}	
			danganid = null;
		}
	}	
	
	private class onBorrow implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// �ж��Ƿ���ڱ�ѡ����
				if(!tableJTabel.getValueAt(selectedRow, 7).equals("���")){
					danganid=tableJTabel.getValueAt(selectedRow, 1)+"";
					System.out.println(danganid);
					Borrow borrow = new Borrow(danganid);
				}
				else {
					JOptionPane.showMessageDialog(scrollPane,
							 "�����ѽ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
			danganid = null;
		}
		
	}
	
	private class onReturn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = tableJTabel.getSelectedRow();
			if (selectedRow != -1) {// �ж��Ƿ���ڱ�ѡ����
				danganid=tableJTabel.getValueAt(selectedRow, 1)+"";
				if(!tableModel.getValueAt(selectedRow, 7).toString().equals("δ���")){
					System.out.println(tableModel.getValueAt(selectedRow, 7).toString()+"||");
					
					SqlReturn sqlReturn = new SqlReturn(danganid);
					if (sqlReturn.isReturn()) {
						JOptionPane.showMessageDialog(scrollPane,
								 "�����黹�ɹ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(scrollPane,
								 "�����黹ʧ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(scrollPane,
							 "����û�н��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
					
			}
			danganid = null;
		}
	}
}
