package com.glassshop.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.glassshop.dao.UserDao;

public class LoginPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GlassShopFrame glassShopFrame;
	
	JLabel lbUser = new JLabel("User Name:");
	JLabel lbPassword = new JLabel("รหัสผ่าน");
	JLabel lbAuth = new JLabel("โปรแกรมระบบการจัดการร้าน ธีระการแว่น");
	
	JTextField tfUser = new JTextField();
	JPasswordField pfPassword = new JPasswordField();
	
	JButton btLogin = new JButton("เข้าสู่ระบบ");
	JButton btLogout = new JButton("ออกจากระบบ");
	
	public LoginPanel(GlassShopFrame glassShopFrame){
		this.glassShopFrame = glassShopFrame;
		setBackground(GlassShop.bgColor);
		buildScreen();
		
		btLogin.addActionListener(this);
		btLogout.addActionListener(this);
	}
	
	public void buildScreen(){
		
		
		setLayout(new BorderLayout());
		
//		lbUser.setPreferredSize(new Dimension(100,20));
//		lbPassword.setPreferredSize(new Dimension(100,20));
//		lbAuth.setPreferredSize(new Dimension(600,20));
		tfUser.setPreferredSize(new Dimension(100,20));
		pfPassword.setPreferredSize(new Dimension(100,20));
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());
		southPanel.add(lbPassword ,new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(pfPassword, new GridBagConstraints(1, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(btLogin, new GridBagConstraints(2, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(btLogout, new GridBagConstraints(3, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 10, 0, 0), 0, 0));
		
		southPanel.add(lbAuth, new GridBagConstraints(1, 1, 3, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 10, 0, 0), 0, 0));
		
		ImageIcon image = new ImageIcon("C:/GlassesShopApp/screen.jpg");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		
		panel.setBackground(GlassShop.bgColor);
		southPanel.setBackground(GlassShop.bgColor);
		bottomPanel.setBackground(GlassShop.bgColor);
		this.add( label, BorderLayout.NORTH );
		this.add(southPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btLogin){
			String userName = tfUser.getText().trim();
			String password = pfPassword.getText();
			
			if ( password != null){
				UserDao userDao = (UserDao) GlassShop.CONTEXT.getBean("userDAO");
				boolean isPass = userDao.selectPassword(password);
				
				if (isPass){
					glassShopFrame.clearPanel();
					glassShopFrame.setVisibleMenu(true);
					GlassShopFrame.LoggedIn = true;
				}else{
					JOptionPane.showMessageDialog(this, "Access Denine", "Error", JOptionPane.ERROR_MESSAGE);
					pfPassword.setText("");
					GlassShopFrame.LoggedIn = false;
					pfPassword.requestFocus();
					glassShopFrame.setVisibleMenu(false);
				}
			}
		}else if(e.getSource() == btLogout){
			
			glassShopFrame.dispose();
		}
		
	}

}
