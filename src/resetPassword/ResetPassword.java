package resetPassword;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import login.Login;


public class ResetPassword extends JFrame{

	private JLabel newPassw = null;
	private JPasswordField newPassword = null;
	private JLabel newRestpassw = null;
	private JPasswordField newRsetPassword = null;
	private JButton set = null;
	private Container container=null;
	private String account = null;
	
	public void ResetPasswordcreate() {
		// TODO Auto-generated constructor stub
		
		container =getContentPane();
		container.setLayout(null);
		
		newPassw = new JLabel("�����룺");
		newPassw.setBounds(150, 80, 70, 30);
		container.add(newPassw);
		
		newPassword = new JPasswordField();
		newPassword.setEchoChar('*');
		newPassword.setBounds(230, 80 ,170, 30);
		container.add(newPassword);
		
		newRestpassw = new JLabel("�ٴ�ȷ�ϣ�");
		newRestpassw.setBounds(150, 160, 70, 30);
		container.add(newRestpassw);
		
		newRsetPassword = new JPasswordField();
		newRsetPassword.setEchoChar('*');
		newRsetPassword.setBounds(230, 160, 170, 30);
		container.add(newRsetPassword);
		
		set = new JButton("ȷ��");
		set.setBounds(260, 250, 90, 30);
		container.add(set);
		set.addActionListener(new RsetPassw());
		
		setTitle("�������Ϲ���__�޸�����");
		setLayout(null);
		setSize(550, 400);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	private class RsetPassw implements ActionListener{
		public void actionPerformed(ActionEvent e)
        {
			System.out.println(String.valueOf(newPassword.getPassword()).length());
			
			if(String.valueOf(newPassword.getPassword()).length()<9&&
					String.valueOf(newPassword.getPassword()).length()!=0&&
					String.valueOf(newRsetPassword.getPassword()).length()!=0&&
					String.valueOf(newRsetPassword.getPassword()).length()<9)
			{
				if(String.valueOf(newPassword.getPassword()).equals(String.valueOf(newRsetPassword.getPassword()))){
					System.out.println("��������ͬ");
					SqlResetPassword sqlResetPassword = new SqlResetPassword();
					if(sqlResetPassword.ResetPassword(account, String.valueOf(newPassword.getPassword()))){
						System.out.println("�޸�����ɹ���");
						newPassword.setText(null);
						newRsetPassword.setText(null);
						JOptionPane.showMessageDialog(getContentPane(),
							 "�����޸ĳɹ�!", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						Login login = new Login();
						login.setAccount(account);
						login.Log();
						
						setVisible(false);
					}
				}else {
					System.out.println("�����޸�ʧ�ܣ�");
					JOptionPane.showMessageDialog(getContentPane(),
						 "�����޸�ʧ��!", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					newPassword.setText(null);
					newRsetPassword.setText(null);
				}	
				
			}else {
				JOptionPane.showMessageDialog(getContentPane(),
						 "���볤��1-8!", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
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
		ResetPassword resetPassword = new ResetPassword();
	}

}
