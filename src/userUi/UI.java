package userUi;

import java.awt.*;
import javax.swing.*;
import javax.swing.Box.Filler;

import appraisal.Appraisal;
import destroy.Destroy;
import filing.Filing;
import inquire.Inquire;

public class UI extends JFrame{

	private Container container=null;
	private JTabbedPane chaXunJTabbedPane ;
	private String account = null;
	public void UIcreate() {
		// TODO Auto-generated constructor stub
		
		Inquire inquire = new Inquire();
		inquire.Inquirecreate();
		inquire.setAccount(account);
		Appraisal appraisal = new Appraisal();
		appraisal.Appraisalcreate();
		appraisal.setAccount(account);
		Destroy destroy = new Destroy();
		destroy.Destroycreate();
		destroy.setAccount(account);
		System.out.println("UI"+account);
		Filing filing = new Filing();
		filing.setAccount(account);
		filing.Filingcreate();

		
		chaXunJTabbedPane = new JTabbedPane();
		chaXunJTabbedPane.addTab("             查询              ",inquire); 
		chaXunJTabbedPane.addTab("        立档              ",filing);
		chaXunJTabbedPane.addTab("        鉴定              ",appraisal);
		chaXunJTabbedPane.addTab("        销毁              ",destroy);
		
		container =getContentPane();
		container.setLayout(new BorderLayout());
		container.add(chaXunJTabbedPane,BorderLayout.CENTER);
		
		setTitle("档案资料管理");
		setSize(1000, 700);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		ui.UIcreate();
		ui.setAccount("1");
	}

}
