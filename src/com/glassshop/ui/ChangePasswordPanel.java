package com.glassshop.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.glassshop.dao.UserDao;
import com.glassshop.model.UserModel;

public class ChangePasswordPanel extends JPanel implements ListSelectionListener, ActionListener, KeyListener, MouseListener{
	
	private JLabel lbOldPassword = new JLabel("รหัสผ่านเดิม");
	private JPasswordField tfOldPassword = new JPasswordField();
	private JLabel lbNewPassword = new JLabel("รหัสผ่านใหม่");
	private JPasswordField tfNewPassword = new JPasswordField();
	private JLabel lbConfirmPassword = new JLabel("ยืนยันรหัสผ่านใหม่");
	private JPasswordField tfConfirmPassword = new JPasswordField();
	private JButton btChange = new JButton("เปลี่ยนรหัสผ่าน");
	private JButton btCancel = new JButton("ยกเลิก");
	
	public ChangePasswordPanel(){
		buildScreen();
		setBackground(GlassShop.bgColor);
		
		//Add Action Listener to Edit button
		btChange.addActionListener(this);
		//Add Action Listener to Edit button
		btCancel.addActionListener(this);
	}
	public void buildScreen(){
		
		setLayout(new BorderLayout(0, 0));
		tfOldPassword.setPreferredSize(new Dimension(150,20));
		tfNewPassword.setPreferredSize(new Dimension(150,20));
		tfConfirmPassword.setPreferredSize(new Dimension(150,20));
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());
		southPanel.add(lbOldPassword ,new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(tfOldPassword, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(lbNewPassword, new GridBagConstraints(0, 1, 1, 1, 0.0,	0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(tfNewPassword, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(lbConfirmPassword, new GridBagConstraints(0, 2, 1, 1, 0.0,	0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(tfConfirmPassword, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(btChange, new GridBagConstraints(0, 3, 1, 1, 0.0,	0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		southPanel.add(btCancel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		
		
		southPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"เปลี่ยนรหัสผ่าน",0,0,GlassShop.FONT));
		
		ImageIcon image = new ImageIcon("C:/GlassesShopApp/screen.jpg");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		
		panel.setBackground(GlassShop.bgColor);
		southPanel.setBackground(GlassShop.bgColor);
		bottomPanel.setBackground(GlassShop.bgColor);
		this.add(label, BorderLayout.NORTH );
		this.add(southPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == btCancel) {
			tfOldPassword.setText("");
			tfNewPassword.setText("");
			tfConfirmPassword.setText("");
		 }else if (e.getSource() == btChange) {
			 String oldPassword = tfOldPassword.getText();
			 String newPassword = tfNewPassword.getText();
			 String conPassword = tfConfirmPassword.getText();
			 
			 UserDao userDao = (UserDao)GlassShop.CONTEXT.getBean("userDAO");
			 UserModel um = userDao.selectById("");
			 if ((null!=oldPassword && !oldPassword.equals("")) && (null!=newPassword && !newPassword.equals(""))
					 && (null!=conPassword && !conPassword.equals(""))){
				 
				 if (!um.getPasswordName().equals(tfOldPassword.getText())){
					 JOptionPane.showMessageDialog(this, "รหัสเดิมไม่ถูกต้อง", "Error", JOptionPane.ERROR_MESSAGE);
				 
			 	 }else if (!newPassword.equals(conPassword)){
					 JOptionPane.showMessageDialog(this, "รหัสใหม่ไม่ตรงกัน", "Error", JOptionPane.ERROR_MESSAGE);
			 	 }else if (newPassword.length()<5){
			 		JOptionPane.showMessageDialog(this, "รหัสผ่านสั้นเกินไป", "Error", JOptionPane.ERROR_MESSAGE);
				 }else {
					 userDao.changePassword(tfConfirmPassword.getText());
					 JOptionPane.showMessageDialog(this, "รหัสผ่านถูกกำหนดใหม่เรียบร้อยแล้ว กรุณาเข้าสู่ระบบใหม่อีกครั้ง", "Success", JOptionPane.INFORMATION_MESSAGE);
					 tfOldPassword.setText("");
					 tfNewPassword.setText("");
					 tfConfirmPassword.setText("");
				 }
			 }else{
				 JOptionPane.showMessageDialog(this, "กรุณาระบุรหัสผ่านให้ครบทุกช่อง", "Error", JOptionPane.ERROR_MESSAGE);
			 }
			 
				
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
