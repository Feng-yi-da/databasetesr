package registerAccount;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import login.Login;

public class RegisterAccount extends JFrame{

	private JLabel accountJLabel = null;
	private JTextField inputAccount = null;
	private JLabel nameJLabel = null;
	private JTextField inputName = null;
	private JLabel passwordJLabel = null;
	private JPasswordField inputPassword = null;
	private JLabel repasswprdJLabel = null;
	private JPasswordField inputRepassword = null;
	private JButton registerJButton = null;
	private Container container = null;
	private String account = null;

	public RegisterAccount() {
		// TODO Auto-generated constructor stub
		container =getContentPane();
		container.setLayout(null);
		
		accountJLabel = new JLabel("�ʺţ�");
		accountJLabel.setBounds(150, 60, 70, 30);
		container.add(accountJLabel);
	
		inputAccount = new JTextField();
		inputAccount.setBounds(230, 60 ,170, 30);
		container.add(inputAccount);
		
		nameJLabel = new JLabel("������");
		nameJLabel.setBounds(150, 120, 70, 30);
		container.add(nameJLabel);
	
		inputName = new JTextField();
		inputName.setBounds(230, 120 ,170, 30);
		container.add(inputName);
		
		passwordJLabel = new JLabel("���룺");
		passwordJLabel.setBounds(150, 180, 70, 30);
		container.add(passwordJLabel);
		
		inputPassword = new JPasswordField();
		inputPassword.setEchoChar('*');
		inputPassword.setBounds(230, 180 ,170, 30);
		container.add(inputPassword);
		
		repasswprdJLabel = new JLabel("ȷ�����룺");
		repasswprdJLabel.setBounds(150, 240, 70, 30);
		container.add(repasswprdJLabel);
		
		inputRepassword = new JPasswordField();
		inputRepassword.setEchoChar('*');
		inputRepassword.setBounds(230, 240, 170, 30);
		container.add(inputRepassword);
		
		registerJButton = new JButton("ע��");
		registerJButton.setBounds(250, 300, 90, 30);
		registerJButton.addActionListener(new onRegister());
		container.add(registerJButton);
		
		setTitle("�������Ϲ���__ע��");
		setLayout(null);
		setSize(550, 400);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private class onRegister implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				System.out.println("���ȷ�ϰ�ť");
				System.out.println("�ʺţ�"+inputAccount.getText()+"  ���룺"+String.valueOf(inputPassword.getPassword())+"  �������룺"+String.valueOf(inputRepassword.getPassword()));
				if (!inputAccount.getText().equals("")) {
					if (inputAccount.getText().length()<=10) {
						if (!inputName.getText().equals("")) {
							if (inputName.getText().length()<=8) {
								if (!String.valueOf(inputPassword.getPassword()).equals("")) {
									if (String.valueOf(inputPassword.getPassword()).length()<=8) {
										if (String.valueOf(inputPassword.getPassword()).equals
												(String.valueOf(inputRepassword.getPassword()))) {
													SqlRegisterAccount sqlRegisterAccount = new SqlRegisterAccount();
													int result = sqlRegisterAccount.RegisterAccount(inputAccount.getText(), inputName.getText(), String.valueOf(inputPassword.getPassword()));
													if (result==1) {
														JOptionPane.showMessageDialog(getContentPane(),
																 "ע��ɹ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
														Login login =  new Login();
														login.setAccount(inputAccount.getText());
														login.Log();
														inputAccount.setText(null);
														inputName.setText(null);
														inputPassword.setText(null);
														inputRepassword.setText(null);
														setVisible(false);
													} else if (result==-1) {
														JOptionPane.showMessageDialog(getContentPane(),
																 "�ʺ��Ѵ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
														inputAccount.setText(null);
														inputName.setText(null);
														inputPassword.setText(null);
														inputRepassword.setText(null);
													} else {
														JOptionPane.showMessageDialog(getContentPane(),
																 "ע��ʧ�ܣ�������", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
														inputAccount.setText(null);
														inputName.setText(null);
														inputPassword.setText(null);
														inputRepassword.setText(null);
													}

						
									
									
									
										} else {
											JOptionPane.showMessageDialog(getContentPane(),
													"�������벻һ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
											inputPassword.setText(null);
											inputRepassword.setText(null);
										}	
									} else {
										JOptionPane.showMessageDialog(getContentPane(),
												"���벻�ܳ���8λ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
										inputPassword.setText(null);
										inputRepassword.setText(null);
									}
									
								} else {
									JOptionPane.showMessageDialog(getContentPane(),
											"���벻��Ϊ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
								}	
							} else {
								JOptionPane.showMessageDialog(getContentPane(),
										"�������ܳ���6λ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(getContentPane(),
									"��������Ϊ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(),
								"�ʺŲ��ܳ���10λ", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						inputAccount.setText(null);
						inputPassword.setText(null);
						inputRepassword.setText(null);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"�ʺŲ���Ϊ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					inputPassword.setText(null);
					inputRepassword.setText(null);
				}
				//inputPassword.setText(null);
				//inputRepassword.setText(null);
				//Login login = new Login();
				//login.setId(inputAccount.getText());
				//inputAccount.setText(null);
				//setVisible(false);
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
		RegisterAccount register = new RegisterAccount();
	}

}
