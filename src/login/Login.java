package login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import registerAccount.RegisterAccount;
import resetPassword.ResetPassword;
import userUi.UI;

public class Login extends JFrame{
	
	private JButton login = null;
	private JButton change = null;
	private JButton regsiter = null;
	private JLabel accountJLabel = null;
	private JLabel passwordJLabel = null;
	private JTextField inputaccount= null;
	private JPasswordField inputPassword= null;
	private Container container=null;
	private String account = null;
	
	public void Log() {
		// TODO Auto-generated constructor stub
		container =getContentPane();
		container.setLayout(null);
		
		accountJLabel = new JLabel("�˻���");
		accountJLabel.setBounds(120, 80, 70, 30);
		container.add(accountJLabel);
		
		inputaccount = new JTextField();
		inputaccount.setBounds(190, 80, 170, 30);
		inputaccount.setText(account);
		container.add(inputaccount);
		
		passwordJLabel = new JLabel("���룺");
		passwordJLabel.setBounds(120, 160, 70, 30);
		container.add(passwordJLabel);
		
		inputPassword = new JPasswordField();
		inputPassword.setEchoChar('*');
		inputPassword.setBounds(190, 160, 170, 30);
		container.add(inputPassword);
		
		login = new JButton("��¼");
		login.setBounds(220, 250, 120, 30);
		login.addActionListener(new onLogin());
		container.add(login);

		change = new JButton("�޸�����");
		change.setBounds(400, 160, 90, 30);
		change.addActionListener(new onChange());
		container.add(change);

		regsiter  = new JButton("ע��");
		regsiter.setBounds(400, 80, 90, 30);
		regsiter.addActionListener(new onRegsiter());
		container.add(regsiter);

		setTitle("�������Ϲ���__��¼");
		setLayout(null);
		setSize(550, 400);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class onLogin implements ActionListener {
		 public void actionPerformed(ActionEvent e)
         {
			System.out.println("�����¼��ť��ť");
			System.out.println();
			if(!inputaccount.getText().equals("")){
				SqlLogin sqlLogin = new SqlLogin();
				int result = sqlLogin.Login(inputaccount.getText(),String.valueOf(inputPassword.getPassword()));
				if ( result==1 ) {
					
					inputPassword.setText(null);
					JOptionPane.showMessageDialog(getContentPane(),
							"��¼�ɹ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					UI ui = new UI();
					ui.setAccount(inputaccount.getText());
					ui.UIcreate();
					System.out.println(inputaccount.getText());
					inputaccount.setText(null);
					setVisible(false);
				
				} else if(result==0){
					inputPassword.setText(null);
					JOptionPane.showMessageDialog(getContentPane(),
							"�������", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					inputPassword.setText(null);
					inputaccount.setText(null);
					JOptionPane.showMessageDialog(getContentPane(),
							"�ʺŲ�����", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				} 
			}else {
				JOptionPane.showMessageDialog(getContentPane(),
						"�ʺŲ���Ϊ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			}
			 
		}	
	}
	
	private class onRegsiter implements ActionListener{ 
		public void actionPerformed(ActionEvent e)
        {
			System.out.println("���ע�ᰴť");
			inputaccount.setText(null);
			inputPassword.setText(null);
			setVisible(false);
			RegisterAccount registerAccount = new RegisterAccount();
        }
	}
	
	private class onChange implements ActionListener{ 
		public void actionPerformed(ActionEvent e)
        {
			System.out.println("����޸����밴ť");
			System.out.println();

			if(!inputaccount.getText().equals("")){
				SqlLogin sqlLogin = new SqlLogin();
				int result = sqlLogin.Login(inputaccount.getText(),String.valueOf(inputPassword.getPassword()));
				if ( result==1 ) {
					
					inputPassword.setText(null);
					JOptionPane.showMessageDialog(getContentPane(),
							"��¼�ɹ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					ResetPassword resetPassword = new ResetPassword();
					resetPassword.ResetPasswordcreate();
					resetPassword.setAccount(inputaccount.getText());
					System.out.println("�ʺţ�"+inputaccount.getText()+"  ���룺"
							+String.valueOf(inputPassword.getPassword()));
					inputaccount.setText(null);
					setVisible(false);
				
				} else if(result==0){
					inputPassword.setText(null);
					JOptionPane.showMessageDialog(getContentPane(),
							"�������", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					inputPassword.setText(null);
					inputaccount.setText(null);
					JOptionPane.showMessageDialog(getContentPane(),
							"�ʺŲ�����", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				} 
			}else {
				JOptionPane.showMessageDialog(getContentPane(),
						"�ʺŲ���Ϊ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
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
		Login login = new Login();
		login.Log();
	}

}
