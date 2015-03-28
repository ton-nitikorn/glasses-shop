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
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import com.glassshop.dao.OrderSaleDao;
import com.glassshop.model.OrderSaleModel;
import com.glassshop.model.OrderSaleUpdate;
import com.glassshop.model.Sell;

public class OrderSaleAddPanel extends JPanel implements ListSelectionListener,
ActionListener, KeyListener, MouseListener {

	static Logger log = Logger.getLogger(GoodsPanel.class.getName());
	
	private JTextField tfOrderGoodsId = new JTextField();
	
	private JLabel lbVendorName = new JLabel("ชื่อบริษัท");
	private JTextField tfVendorName = new JTextField();
	private JLabel lbGoodName = new JLabel("ชนิดสินค้า");
	private JTextField tfGoodName = new JTextField();
	private JLabel lbGoodType = new JLabel("ประเภทสินค้า");
	private JTextField tfGoodType = new JTextField();
	private JLabel lbGoodBrand = new JLabel("ยี่ห้อสินค้า");
	private JTextField tfGoodBrand = new JTextField();
	private JLabel lbGoodModel = new JLabel("รุ่นสินค้า");
	private JTextField tfGoodModel = new JTextField();
	
	private JLabel lbGoodStock = new JLabel("จำนวนสินค้าที่มีอยู่");
	private JTextField tfGoodStock = new JTextField();
	private JLabel lbGoodSell = new JLabel("จำนวนสินค้าที่ขาย");
	private JTextField tfGoodSell = new JTextField();
	private JLabel lbGoodRemain = new JLabel("จำนวนสินค้าคงเหลือ");
	private JTextField tfGoodRemain = new JTextField();
	private JLabel lbPrice = new JLabel("ราคาซื้อ/หน่วย");
	private JTextField tfPrice = new JTextField();
	private JLabel lbTagPrice = new JLabel("ราคาป้าย/หน่วย");
	private JTextField tfTagPrice = new JTextField();
	private JLabel lbSalePrice = new JLabel("ราคาขาย");
	private JTextField tfSalePrice = new JTextField();
	private JLabel lbTotal = new JLabel("ราคารวม");
	private JTextField tfTotal = new JTextField();
	
	private JLabel lbRemark = new JLabel("หมายเหตุ");
	private JTextField tfRemark = new JTextField();
	
	private JLabel lbSaleDate = new JLabel("วันที่ขาย");
	final JDatePickerImpl datePicker1 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JButton btSearch = new JButton("ค้นหาข้อมูล");
	
	GlassShopFrame glassShopFrame;
	
	private enum Mode {
		EDIT, ADD
	};

	private String selectId = "";
	
	private String MENU_ID = "";
	
	public OrderSaleAddPanel(GlassShopFrame glassShopFrame, OrderSaleUpdate order, String menuId){
		MENU_ID = menuId;
		this.glassShopFrame = glassShopFrame;
		
		onload();
		buildScreen();
		setTextbox(order);
		if(!MENU_ID.equals("menu_2_2_1")){
			setOrderTextbox(order);
		}
		
	}
	
	private void onload() {
		btAdd.addActionListener(this);
		btDelete.addActionListener(this);
		btSearch.addActionListener(this);
		
		datePicker1.getModel().setSelected(true);
		
		setPanelMode(Mode.ADD);
	}
	private void setPanelMode(Mode mode) {
		if (mode == Mode.ADD) {
			btAdd.setVisible(true);
			btCancel.setVisible(true);
			btDelete.setVisible(true);
		} else if (mode == Mode.EDIT) {
			btAdd.setVisible(false);
			btCancel.setVisible(true);
			btDelete.setVisible(true);
		}
	}
	
	public void buildScreen() {
		setLayout(new BorderLayout(0, 0));
		createTextbox();
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbSaleDate, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(datePicker1, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		
		topPanel.add(lbVendorName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(tfVendorName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodName, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodName, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodType, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodType, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodBrand, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodBrand, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodModel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodModel, new GridBagConstraints(1, 6, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ข้อมูลสินค้า",0,0,GlassShop.FONT));
		
		JPanel buttomPanel = new JPanel();
		buttomPanel.setLayout(new GridBagLayout());
		if(MENU_ID.equals("menu_2_2_1")){
			buttomPanel.add(lbGoodStock, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfGoodStock, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbGoodSell, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfGoodSell, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbGoodRemain, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfGoodRemain, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbPrice, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfPrice, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbTagPrice, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfTagPrice, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbSalePrice, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfSalePrice, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbTotal, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfTotal, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbRemark, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfRemark, new GridBagConstraints(2, 4, 4, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		}else if(MENU_ID.equals("menu_2_2_2")){
			buttomPanel.add(lbGoodSell, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfGoodSell, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbSalePrice, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfSalePrice, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(lbTotal, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			buttomPanel.add(tfTotal, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			
		}
		buttomPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"จำนวน/ราคาสินค้า",0,0,GlassShop.FONT));
		
		JPanel printButtonPanel = new JPanel();
		printButtonPanel.setLayout(new GridBagLayout());
		
		printButtonPanel.add(lbRemark, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		printButtonPanel.add(tfRemark, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

		if(MENU_ID.equals("menu_2_2_1")){
			printButtonPanel.add(btAdd, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			printButtonPanel.add(btCancel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			printButtonPanel.add(btSearch, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		}else if(MENU_ID.equals("menu_2_2_2")){
			printButtonPanel.add(btDelete, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
			printButtonPanel.add(btSearch, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		
		}
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);
		add(printButtonPanel, BorderLayout.SOUTH);
		
		buttomPanel.setBackground(GlassShop.bgColor);
		topPanel.setBackground(GlassShop.bgColor);
		printButtonPanel.setBackground(GlassShop.bgColor);
	}
	
	private void createTextbox(){
		tfGoodModel.setPreferredSize(new Dimension(500, 20));
		tfVendorName.setPreferredSize(new Dimension(300, 20));
		tfGoodName.setPreferredSize(new Dimension(300, 20));
		tfGoodType.setPreferredSize(new Dimension(300, 20));
		tfGoodBrand.setPreferredSize(new Dimension(300, 20));
		tfGoodStock.setPreferredSize(new Dimension(100, 20));
		tfGoodSell.setPreferredSize(new Dimension(100, 20));
		tfGoodRemain.setPreferredSize(new Dimension(100, 20));
		tfPrice.setPreferredSize(new Dimension(100, 20));
		tfTagPrice.setPreferredSize(new Dimension(100, 20));
		tfSalePrice.setPreferredSize(new Dimension(100, 20));
		tfTotal.setPreferredSize(new Dimension(100, 20));
		tfRemark.setPreferredSize(new Dimension(600, 50));
	}
	private void setTextbox(OrderSaleUpdate order){
		if (null!= order){
			
			tfGoodModel.setText(order.getGoodsSub());
			tfVendorName.setText(order.getVendorName());
			tfGoodName.setText(order.getGoodsName());
			tfGoodType.setText(order.getGoodsType());
			tfGoodBrand.setText(order.getGoodsBrand());
			tfGoodStock.setText(order.getGoodsQuantity());
			tfGoodRemain.setText(order.getGoodsQuantity());
			tfPrice.setText(order.getGoodsCost());
			tfTagPrice.setText(order.getGoodsCostLabel());
			tfOrderGoodsId.setText(order.getOrderGoodsId());
		
			
		}
	}	
	private void setOrderTextbox(OrderSaleUpdate order){
		OrderSaleDao orderSaleDao = (OrderSaleDao)GlassShop.CONTEXT.getBean("orderSaleDAO");
		Sell sell = orderSaleDao.selectByOrderId(order.getOrderGoodsId());
		
		if(sell != null){
			tfGoodSell.setText(sell.getSellQuantity());
			tfSalePrice.setText(sell.getSellCost());
			tfTotal.setText(sell.getSellCostSum());
			tfRemark.setText(sell.getSellNote());
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
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
		if(e.getSource() == btSearch){
			
			OrderSaleSearchPanel panel = new OrderSaleSearchPanel(glassShopFrame, MENU_ID);
			glassShopFrame.changePanel(panel);
			
		}  else if (e.getSource() == btAdd) {
			OrderSaleDao orderSaleDao = (OrderSaleDao)GlassShop.CONTEXT.getBean("orderSaleDAO");
			
			OrderSaleModel order = orderValue();
			orderSaleDao.insertSell(order);
		
			OrderSaleSearchPanel panel = new OrderSaleSearchPanel(glassShopFrame, MENU_ID);
			glassShopFrame.changePanel(panel);
			log.info("success");
		}  else if (e.getSource() == btDelete) {
			OrderSaleDao orderSaleDao = (OrderSaleDao)GlassShop.CONTEXT.getBean("orderSaleDAO");
			orderSaleDao.delete(tfOrderGoodsId.getText(),tfGoodSell.getText());
		
			OrderSaleSearchPanel panel = new OrderSaleSearchPanel(glassShopFrame, MENU_ID);
			glassShopFrame.changePanel(panel);
			log.info("success");
		}
	}
	private OrderSaleModel orderValue(){
		OrderSaleModel order = new OrderSaleModel();
		
		order.setOrderGoodsId(tfOrderGoodsId.getText());
		order.setSaleDate(CatchDataFrame.PickerDat2String(datePicker1));
		order.setGoodsQuantity(tfGoodSell.getText());
		order.setGoodsCost(tfPrice.getText());
		order.setGoodsCostSum(tfTotal.getText());
		order.setSaleNote(tfRemark.getText());
		
		return order;
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
