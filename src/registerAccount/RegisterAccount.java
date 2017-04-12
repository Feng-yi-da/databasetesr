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
		
		accountJLabel = new JLabel("帐号：");
		accountJLabel.setBounds(150, 60, 70, 30);
		container.add(accountJLabel);
	
		inputAccount = new JTextField();
		inputAccount.setBounds(230, 60 ,170, 30);
		container.add(inputAccount);
		
		nameJLabel = new JLabel("姓名：");
		nameJLabel.setBounds(150, 120, 70, 30);
		container.add(nameJLabel);
	
		inputName = new JTextField();
		inputName.setBounds(230, 120 ,170, 30);
		container.add(inputName);
		
		passwordJLabel = new JLabel("密码：");
		passwordJLabel.setBounds(150, 180, 70, 30);
		container.add(passwordJLabel);
		
		inputPassword = new JPasswordField();
		inputPassword.setEchoChar('*');
		inputPassword.setBounds(230, 180 ,170, 30);
		container.add(inputPassword);
		
		repasswprdJLabel = new JLabel("确认密码：");
		repasswprdJLabel.setBounds(150, 240, 70, 30);
		container.add(repasswprdJLabel);
		
		inputRepassword = new JPasswordField();
		inputRepassword.setEchoChar('*');
		inputRepassword.setBounds(230, 240, 170, 30);
		container.add(inputRepassword);
		
		registerJButton = new JButton("注册");
		registerJButton.setBounds(250, 300, 90, 30);
		registerJButton.addActionListener(new onRegister());
		container.add(registerJButton);
		
		setTitle("档案资料管理__注册");
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
				System.out.println("点击确认按钮");
				System.out.println("帐号："+inputAccount.getText()+"  密码："+String.valueOf(inputPassword.getPassword())+"  重新输入："+String.valueOf(inputRepassword.getPassword()));
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
																 "注册成功", "系统信息", JOptionPane.INFORMATION_MESSAGE);
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
																 "帐号已存在", "系统信息", JOptionPane.INFORMATION_MESSAGE);
														inputAccount.setText(null);
														inputName.setText(null);
														inputPassword.setText(null);
														inputRepassword.setText(null);
													} else {
														JOptionPane.showMessageDialog(getContentPane(),
																 "注册失败，请重试", "系统信息", JOptionPane.INFORMATION_MESSAGE);
														inputAccount.setText(null);
														inputName.setText(null);
														inputPassword.setText(null);
														inputRepassword.setText(null);
													}

						
									
									
									
										} else {
											JOptionPane.showMessageDialog(getContentPane(),
													"两次密码不一致", "系统信息", JOptionPane.INFORMATION_MESSAGE);
											inputPassword.setText(null);
											inputRepassword.setText(null);
										}	
									} else {
										JOptionPane.showMessageDialog(getContentPane(),
												"密码不能超过8位", "系统信息", JOptionPane.INFORMATION_MESSAGE);
										inputPassword.setText(null);
										inputRepassword.setText(null);
									}
									
								} else {
									JOptionPane.showMessageDialog(getContentPane(),
											"密码不能为空", "系统信息", JOptionPane.INFORMATION_MESSAGE);
								}	
							} else {
								JOptionPane.showMessageDialog(getContentPane(),
										"姓名不能超过6位", "系统信息", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(getContentPane(),
									"姓名不能为空", "系统信息", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(),
								"帐号不能超过10位", "系统信息", JOptionPane.INFORMATION_MESSAGE);
						inputAccount.setText(null);
						inputPassword.setText(null);
						inputRepassword.setText(null);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"帐号不能为空", "系统信息", JOptionPane.INFORMATION_MESSAGE);
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
