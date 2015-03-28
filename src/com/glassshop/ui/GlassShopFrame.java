package com.glassshop.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GlassShopFrame extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Menu
	private JMenu menu = new JMenu("  เข้าสู่ระบบ  ");
	private JMenuItem menu_login = new JMenuItem("  เข้าสู่ระบบ ");
	private JMenu menu_1 = new JMenu("  ข้อมูลลูกค้า  ");
	private JMenuItem menu_1_1 = new JMenuItem("เพิ่มข้อมูลลูกค้า");
	private JMenuItem menu_1_2 = new JMenuItem("ค้นหาข้อมูลลูกค้า");
	private JMenu menu_2 = new JMenu("  ข้อมูลการซื้อ-ขาย  ");
	private JMenu menu_2_1 = new JMenu("ข้อมูลการสั่งซื้อ");
	private JMenuItem menu_2_1_1 = new JMenuItem("เพิ่มข้อมูลการสั่งซื้อ");
	private JMenuItem menu_2_1_2 = new JMenuItem("แก้ไขข้อมูลการสั่งซื้อ");
	private JMenuItem menu_2_2_1 = new JMenuItem("เพิ่มข้อมูลการขาย");
	private JMenuItem menu_2_2_2 = new JMenuItem("ลบข้อมูลการขาย");
	private JMenu menu_3 = new JMenu(" ข้อมูลอื่นๆ ");
	private JMenu menu_2_2 = new JMenu("ข้อมูลการขาย");
	private JMenuItem menu_3_1 = new JMenuItem("การจัดการข้อมูลบริษัท");
	private JMenuItem menu_3_2 = new JMenuItem("การจัดการข้อมูลพนักงานขาย");
	private JMenuItem menu_3_3 = new JMenuItem("การจัดการข้อมูลชนิดสินค้า");
	private JMenuItem menu_3_4 = new JMenuItem("การจัดการข้อมูลประเภทสินค้า");
	private JMenuItem menu_3_5 = new JMenuItem("การจัดการข้อมูลยี่ห้อสินค้า");
	private JMenu menu_4 = new JMenu("  รายงาน  ");
	private JMenuItem menu_4_1 = new JMenuItem("ข้อมูลลูกค้า");
	private JMenuItem menu_4_2 = new JMenuItem("ข้อมูลการสั่งซื้อสินค้า");
	private JMenuItem menu_4_3 = new JMenuItem("ข้อมูลการขายสินค้า");
	private JMenuItem menu_4_4 = new JMenuItem("ข้อมูลสินค้าคงเหลือ");
	private JMenu menu_5 = new JMenu("  เปลี่ยนรหัสผ่าน  ");
	private JMenuItem menu_5_1 = new JMenuItem("  เปลี่ยนรหัสผ่าน  ");
	private JMenu menu_6 = new JMenu(" ออกจากระบบ ");
	private JMenuItem menu_logout = new JMenuItem(" ออกจากระบบ ");
	
	public JPanel activePanel = new JPanel();
	
	public static boolean LoggedIn = false;
	
	public GlassShopFrame(){
		setTitle("Theera Glasses Shop ");
		setResizable(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBackground(GlassShop.bgColor);
		//create menu bar on the top of screen
		buildMenuBar();
		
		LoginPanel loginPanel = new LoginPanel(this);
		activePanel = loginPanel;
		
		add(activePanel, BorderLayout.CENTER);
		
		setVisibleMenu(false);

		menu_login.addActionListener(this);
		menu_logout.addActionListener(this);
		menu_1_1.addActionListener(this);
		menu_1_2.addActionListener(this);
		menu_3_1.addActionListener(this);
		menu_3_2.addActionListener(this);
		menu_3_3.addActionListener(this);	
		menu_3_4.addActionListener(this);	
		menu_3_5.addActionListener(this);
		menu_4_1.addActionListener(this);
		menu_4_2.addActionListener(this);
		menu_4_3.addActionListener(this);
		menu_4_4.addActionListener(this);
		menu_5_1.addActionListener(this);
		menu_2_1_1.addActionListener(this);
		menu_2_1_2.addActionListener(this);
		menu_2_2_1.addActionListener(this);
		menu_2_2_2.addActionListener(this);
		
	}
	
	public void setEnableMenu(boolean flag){
		menu_1.setEnabled(!flag);
		menu_1.setEnabled(flag);
		menu_2.setEnabled(flag);
		menu_3.setEnabled(flag);
		menu_4.setEnabled(flag);
		menu_5.setEnabled(flag);
		menu_6.setEnabled(flag);
	}
	public void setVisibleMenu(boolean flag){
		menu.setVisible(flag);
		menu_1.setVisible(flag);
		menu_2.setVisible(flag);
		menu_3.setVisible(flag);
		menu_4.setVisible(flag);
		menu_5.setVisible(flag);
		menu_6.setVisible(flag);
	}
	
	private void buildMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu.setFont(GlassShop.FONT);
		menu_1.setFont(GlassShop.FONT);
		menu_1_1.setFont(GlassShop.FONT);
		menu_1_2.setFont(GlassShop.FONT);
		menu_2.setFont(GlassShop.FONT);
		menu_2_1.setFont(GlassShop.FONT);
		menu_2_1_1.setFont(GlassShop.FONT);
		menu_2_1_2.setFont(GlassShop.FONT);
		menu_2_2.setFont(GlassShop.FONT);
		menu_2_2_1.setFont(GlassShop.FONT);
		menu_2_2_2.setFont(GlassShop.FONT);
		menu_3.setFont(GlassShop.FONT);
		menu_3_1.setFont(GlassShop.FONT);
		menu_3_2.setFont(GlassShop.FONT);
		menu_3_3.setFont(GlassShop.FONT);
		menu_3_4.setFont(GlassShop.FONT);
		menu_3_5.setFont(GlassShop.FONT);
		menu_4.setFont(GlassShop.FONT);
		menu_4_1.setFont(GlassShop.FONT);
		menu_4_2.setFont(GlassShop.FONT);
		menu_4_3.setFont(GlassShop.FONT);
		menu_4_4.setFont(GlassShop.FONT);
		menu_5.setFont(GlassShop.FONT);
		menu_5_1.setFont(GlassShop.FONT);
		menu_6.setFont(GlassShop.FONT);
		menu_login.setFont(GlassShop.FONT);
		menu_logout.setFont(GlassShop.FONT);
		
		menuBar.add(menu);
		menu.add(menu_login);
		menuBar.add(menu_1);
		menu_1.add(menu_1_1);	
		menu_1.add(menu_1_2);
		menuBar.add(menu_2);
		menu_2.add(menu_2_1);
		menu_2_1.add(menu_2_1_1);
		menu_2_1.add(menu_2_1_2);
		menu_2.add(menu_2_2);
		menu_2_2.add(menu_2_2_1);
		menu_2_2.add(menu_2_2_2);
		menuBar.add(menu_3);
		menu_3.add(menu_3_1);
		menu_3.add(menu_3_2);
		menu_3.add(menu_3_3);
		menu_3.add(menu_3_4);
		menu_3.add(menu_3_5);
		menuBar.add(menu_4);
		menu_4.add(menu_4_1);
		menu_4.add(menu_4_2);
		menu_4.add(menu_4_3);
		menu_4.add(menu_4_4);
		menuBar.add(menu_5);
		menuBar.add(menu_6);
		menu_5.add(menu_5_1);
		menu_6.add(menu_logout);
	}

	public void actionPerformed(ActionEvent e) {
		activePanel.removeAll();
		this.remove(activePanel);
		setTitle("Theera Glasses Shop");
		
		if(LoggedIn){

			if(e.getSource() == menu_1_1){
				setTitle("Theera Glasses Shop [เพิ่มข้อมูลลูกค้า]");
				CustomerPanel panel = new CustomerPanel("1_1",this,null,null);
				activePanel = panel;
	
			}else if(e.getSource() == menu_1_2){
				setTitle("Theera Glasses Shop [ค้นหาข้อมูลลูกค้า]");
				CustomerSearchPanel panel = new CustomerSearchPanel("1_2", this,null,null);
				activePanel = panel;
				
			}else if(e.getSource() == menu_2_1_1){
				setTitle("Theera Glasses Shop [เพิ่มข้อมูลการสั่งซื้อ]");
				OrderBuyAddPanel panel = new OrderBuyAddPanel(this, null, "menu_2_1_1");
				activePanel = panel;
			}else if(e.getSource() == menu_2_1_2){
				setTitle("Theera Glasses Shop [แก้ไขข้อมูลการสั่งซื้อ]");
				OrderBuySearchPanel panel = new OrderBuySearchPanel(this);
				activePanel = panel;
				
			}else if(e.getSource() == menu_2_2_1){
				setTitle("Theera Glasses Shop [เพิ่มข้อมูลการขาย]");
				OrderSaleSearchPanel panel = new OrderSaleSearchPanel(this, "menu_2_2_1");
				activePanel = panel;
				
			}else if(e.getSource() == menu_2_2_2){
				setTitle("Theera Glasses Shop [แก้ไขข้อมูลการสั่งซื้อ]");
				OrderSaleSearchPanel panel = new OrderSaleSearchPanel(this, "menu_2_2_2");
				activePanel = panel;
			}else if(e.getSource() == menu_3_1){
				setTitle("Theera Glasses Shop [การจัดการข้อมูลบริษัท]");
				VendorPanel panel = new VendorPanel();
				activePanel = panel;
	
			}else if(e.getSource() == menu_3_2){
				setTitle("Theera Glasses Shop [การจัดการข้อมูลพนักงานขาย]");
				SalesManPanel panel = new SalesManPanel();
				activePanel = panel;
	
			}else if(e.getSource() == menu_3_3){
				setTitle("Theera Glasses Shop [การจัดการข้อมูลชนิดสินค้า]");
				GoodsPanel panel = new GoodsPanel();
				activePanel = panel;
	
			}else if(e.getSource() == menu_3_4){
				setTitle("Theera Glasses Shop [การจัดการข้อมูลประเภทสินค้า]");
				GoodsTypePanel panel = new GoodsTypePanel();
				activePanel = panel;
				
			}else if(e.getSource() == menu_3_5){
				setTitle("Theera Glasses Shop [การจัดการข้อมูลยี่ห้อสินค้า]");
				GoodsBrandPanel panel = new GoodsBrandPanel();
				activePanel = panel;
				
			}else if(e.getSource() == menu_4_1){
				setTitle("Theera Glasses Shop [รายงานข้อมูลลูกค้า]");
				CustomerReportPanel panel = new CustomerReportPanel();
				activePanel = panel;
			}else if(e.getSource() == menu_4_2){
				setTitle("Theera Glasses Shop [รายงานข้อมูลการสั่งซื้อสินค้า]");
				ReportOrderBuyPanel panel = new ReportOrderBuyPanel(this);
				activePanel = panel;
			}else if(e.getSource() == menu_4_3){
				setTitle("Theera Glasses Shop [รายงานข้อมูลการขายสินค้า]");
				ReportSellPanel panel = new ReportSellPanel();
				activePanel = panel;
			}else if(e.getSource() == menu_4_4){
				setTitle("Theera Glasses Shop [รายงานข้อมูลสินค้าคงเหลือ]");
				ReportRemainOrderPanel panel = new ReportRemainOrderPanel();
				activePanel = panel;
			}else if(e.getSource() == menu_5_1){
				setTitle("Theera Glasses Shop [เปลี่ยนพาสเวิร์ด]");
				ChangePasswordPanel panel = new ChangePasswordPanel();
				activePanel = panel;
			}else if(e.getSource() == menu_logout){
				this.dispose();
			}else if(e.getSource() == menu_login){
				LoggedIn = false;			
				LoginPanel loginPanel = new LoginPanel(this);
				activePanel = loginPanel;
				setVisibleMenu(false);
			}
		
		}else{
			LoginPanel loginPanel = new LoginPanel(this);
			activePanel = loginPanel;
			setVisibleMenu(false);
		}

		this.add(activePanel, BorderLayout.CENTER);
		activePanel.revalidate();
		validate();
		repaint();
	}
	
	public void changePanel(JPanel panel){
		activePanel.removeAll();
		this.remove(activePanel);
		
		activePanel = panel;
		
		this.add(activePanel, BorderLayout.CENTER);
		activePanel.revalidate();
		validate();
		repaint();
	}

	public void clearPanel(){
		activePanel.removeAll();
		this.remove(activePanel);
		validate();
		repaint();
	}

}
