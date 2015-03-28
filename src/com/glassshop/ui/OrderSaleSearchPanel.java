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
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import com.glassshop.dao.OrderSaleDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.OrderSaleModel;
import com.glassshop.model.OrderSaleUpdate;

public class OrderSaleSearchPanel extends JPanel implements ListSelectionListener,
		ActionListener, KeyListener, MouseListener {

	static Logger log = Logger.getLogger(GoodsPanel.class.getName());

	private JLabel lbOrderNo = new JLabel("เลขที่ใบเสร็จ");
	private JTextField tfOrderNo = new JTextField();
	
	private JLabel lbVendorName = new JLabel("ชื่อบริษัท");
	private JComboBox cboVendor = new JComboBox();
	private JLabel lbGoodsSub = new JLabel("รุ่นสินค้า");
	private JTextField tfGoodsSub = new JTextField();
	private JLabel lbGoodName = new JLabel("ชนิดสินค้า");
	private JComboBox cboGoodName = new JComboBox();
	private JLabel lbGoodType = new JLabel("ประเภทสินค้า");
	private JComboBox cboGoodType = new JComboBox();
	private JLabel lbGoodBrand = new JLabel("ยี่ห้อสินค้า");
	private JComboBox cboGoodBrand = new JComboBox();
	
	private JLabel lbFromDate = new JLabel("ตั้งแต่วันที่");
	final JDatePickerImpl datePicker1 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JLabel lbToDate = new JLabel("จนถึงวันที่");
	final JDatePickerImpl datePicker2 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	
	// Create columns names
	private String columnNames[] = {"", "เลขที่ใบเสร็จ", "ชื่อบริษัท" ,"ชนิดสินค้า", "ประเภทสินค้า" ,"ยี่ห้อสินค้า","รุ่นสินค้า","ราคาซื้อ","ราคาป้าย","จำนวนคงเหลือ" };
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	

	// Create data table
	private JScrollPane scrollPane;
	private enum Mode {
		EDIT, ADD
	};

	private String selectId = "";
	
	private String MENU_ID = "";
	
	GlassShopFrame glassShopFrame;
	
	public OrderSaleSearchPanel(GlassShopFrame glassShopFrame, String menuId){
		
		this.glassShopFrame = glassShopFrame;
		MENU_ID = menuId;
		
		onload();
		buildScreen();
		
		tfOrderNo.addKeyListener(this);
		tfGoodsSub.addKeyListener(this);
		datePicker1.addKeyListener(this);
		datePicker2.addActionListener(this);
		datePicker1.setTextEditable(true);
		datePicker2.setTextEditable(true); 
		
		datePicker1.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
		datePicker2.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
		cboVendor.addActionListener(this);
		cboGoodName.addActionListener(this);
		cboGoodBrand.addActionListener(this);
		cboGoodType.addActionListener(this);
		
		tfOrderNo.setFont(GlassShop.FONT);
		tfGoodsSub.setFont(GlassShop.FONT);
		datePicker1.setFont(GlassShop.FONT);
		datePicker2.setFont(GlassShop.FONT);
		cboVendor.setFont(GlassShop.FONT);
		cboGoodName.setFont(GlassShop.FONT);
		cboGoodBrand.setFont(GlassShop.FONT);
		cboGoodType.setFont(GlassShop.FONT);
		
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(this);
	    table.setFont(GlassShop.FONT);
	    table.addMouseListener(this);
	    table.getTableHeader().setFont(GlassShop.FONT);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
	}
	private void onload() {
		
		
		CatchDataFrame cd = new CatchDataFrame();
		cboVendor = cd.VendorCombobox();
		cboGoodName = cd.GoodsTypeCombobox();
		cboGoodType = cd.GoodTypeNameCombobox();
		cboGoodBrand = cd.GoodBrandNameCombobox();
		datePicker1.getModel().setSelected(true);
		datePicker2.getModel().setSelected(true);
		updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		setPanelMode(Mode.ADD);
	}
	private void setPanelMode(Mode mode) {
//		if (mode == Mode.ADD) {
//			btAdd.setVisible(true);
//			btCancel.setVisible(true);
//			btEdit.setVisible(false);
//			btDelete.setVisible(false);
//		} else if (mode == Mode.EDIT) {
//			btAdd.setVisible(false);
//			btCancel.setVisible(true);
//			btEdit.setVisible(true);
//			btDelete.setVisible(true);
//		}
	}
	
	public void buildScreen() {
		setLayout(new BorderLayout(0, 0));

		tfOrderNo.setPreferredSize(new Dimension(300, 20));
		tfGoodsSub.setPreferredSize(new Dimension(300, 20));
		cboVendor.setPreferredSize(new Dimension(300, 20));
		cboGoodName.setPreferredSize(new Dimension(300, 20));
		cboGoodType.setPreferredSize(new Dimension(300, 20));
		cboGoodBrand.setPreferredSize(new Dimension(300, 20));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		if (MENU_ID.equals("menu_2_2_1")){
			topPanel.add(lbOrderNo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfOrderNo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbVendorName, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(cboVendor, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		}else if (MENU_ID.equals("menu_2_2_2")){
			topPanel.add(lbVendorName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(cboVendor, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbFromDate, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(datePicker1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbToDate, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(datePicker2, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		}
		
		topPanel.add(lbGoodName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodType, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodType, new GridBagConstraints(3, 1, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodBrand, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodBrand, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodsSub, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodsSub, new GridBagConstraints(3, 2, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));

		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ค้นหาตามข้อมูลสินค้า",0,0,GlassShop.FONT));
		topPanel.add(lbTableCount, new GridBagConstraints(4, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 130, 5, 0), 0, 0));
		JPanel buttomPanel = new JPanel();
		buttomPanel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		buttomPanel.add(scrollPane,BorderLayout.CENTER);
		

		topPanel.setBackground(GlassShop.bgColor);
//		buttonPanel.setBackground(GlassShop.bgColor);
		buttomPanel.setBackground(GlassShop.bgColor);
		
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);

	}
	
	private void updateFillerTable(String orderNo, String goodsSub ){
		
        String sType = "";
        if(MENU_ID.equals("menu_2_2_1")){
        	sType = "ADD";//Add sell
        }else  if(MENU_ID.equals("menu_2_2_2")){
        	sType = "DEL";//Del sell
        }
        String fromDt = CatchDataFrame.PickerDat2String(datePicker1);
		String toDt =  CatchDataFrame.PickerDat2String(datePicker2);
        String vendorId = ((CatchDataModel) cboVendor.getSelectedItem()).value;
        String goodsId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
        String goodsTypeId = ((CatchDataModel) cboGoodType.getSelectedItem()).value;
        String goodsBrandId = ((CatchDataModel) cboGoodBrand.getSelectedItem()).value;
        
		OrderSaleDao orderDao = (OrderSaleDao)GlassShop.CONTEXT.getBean("orderSaleDAO");
		List<OrderSaleModel> listOrder = orderDao.selectFillterOrder(orderNo, vendorId, goodsId,
				goodsTypeId, goodsBrandId, goodsSub, sType, fromDt, toDt);
		
		tableModel.setRowCount(0);
		
		for(int i=0; i<listOrder.size(); i++){
			OrderSaleModel order = listOrder.get(i);
			
			if(order != null){
	
				Object[] dataValues = new String[columnNames.length];
				dataValues = dataValuesTable(order);
				tableModel.addRow(dataValues);
				
			}
			
		}
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+listOrder.size()+" รายการ");
		table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
		validate();
	}
	private Object[] dataValuesTable(OrderSaleModel order){
		
		Object[] dataValues = new String[columnNames.length];
		dataValues = new String[columnNames.length];
		dataValues[0] = order.getOrderGoodsId();
		dataValues[1] = order.getOrderNo();
		dataValues[2] = order.getVendorName();
		dataValues[3] = order.getGoodsName();
		dataValues[4] = order.getGoodsType();
		dataValues[5] = order.getGoodsBrand();
		dataValues[6] = order.getGoodsSub();
		dataValues[7] = order.getGoodsCost();
		dataValues[8] = order.getGoodsCostLabel();
		dataValues[9] = order.getGoodsQuantity();
//		dataValues[10] = order.getSellCost();
//		dataValues[11] = order.getSellCostSum();
//		dataValues[12] = order.getSellQuantity();
//		dataValues[13] = order.getSellDate();
//		dataValues[14] = order.getSaleNote();
		return dataValues;
	}
	private OrderSaleUpdate tableValue(JTable target){
		int row = target.getSelectedRow();
		String orderGoodId = (String)table.getValueAt(row,0);
		String orderNo = (String)table.getValueAt(row,1);
		String vendorName = (String)table.getValueAt(row,2);
		String goodsName = (String)table.getValueAt(row,3);
		String goodsType = (String)table.getValueAt(row,4);
		String goodsBrand = (String)table.getValueAt(row,5);
		String goodsSub = (String)table.getValueAt(row,6);
		String goodsCost = (String)table.getValueAt(row,7);
		String goodsCostLabel = (String)table.getValueAt(row,8);
		String goodsQuantity = (String)table.getValueAt(row,9);
//		String sellCost = (String)table.getValueAt(row,10);
//		String sellCostSum = (String)table.getValueAt(row,11);
//		String sellQuantity = (String)table.getValueAt(row,12);
//		String sellDate = (String)table.getValueAt(row,13);
//		String saleNote = (String)table.getValueAt(row,14);
		
		OrderSaleUpdate order = new OrderSaleUpdate(orderGoodId, orderNo, vendorName, goodsName, goodsType, 
				goodsBrand, goodsSub, goodsCost, goodsCostLabel, goodsQuantity);
		
		return order;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			//Disable for Edit mode by veena 2013-03-17
			JTable target = (JTable)e.getSource();   
			int row = target.getSelectedRow();
			
			OrderSaleUpdate order = tableValue(target);
			setPanelMode(Mode.EDIT);
			
			OrderSaleAddPanel panel = new OrderSaleAddPanel(glassShopFrame, order, MENU_ID);
			glassShopFrame.changePanel(panel);
			
		}
		
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
		updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == cboGoodName){
			String goodId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
			CatchDataFrame cd = new CatchDataFrame();
			cboGoodType.removeAllItems();
			cd.GoodTypeNameByGoodsCombobox(goodId,cboGoodType);
			
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}else if((e.getSource() == cboVendor) || (e.getSource() == cboGoodBrand) || (e.getSource() == cboGoodType)){
			
//			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
