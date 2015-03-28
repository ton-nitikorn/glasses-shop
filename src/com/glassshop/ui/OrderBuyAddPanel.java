package com.glassshop.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import com.glassshop.dao.OrderBuyDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.OrderBuyUpdate;
import com.glassshop.model.OrderGoods;
import com.glassshop.model.OrderbuyModel;

public class OrderBuyAddPanel extends JPanel implements ListSelectionListener,
														ActionListener, KeyListener, MouseListener {

	private JTextField tfOrderId = new JTextField();
	private JTextField tfOrderGoodsId = new JTextField();
	
	private JLabel lbOrderNo = new JLabel("เลขที่ใบเสร็จ");
	private JTextField tfOrderNo = new JTextField();
	
	private JLabel lbVendorName = new JLabel("ชื่อบริษัท");
	private JComboBox cboVendor = new JComboBox();
	private JLabel lbGoodsSub = new JLabel("รุ่นสินค้า");
	private JTextField tfGoodsSub = new JTextField();
	private JLabel lbSalesName = new JLabel("ชื่อพนักงานขาย");
	private JComboBox cboSalesName = new JComboBox();
	private JLabel lbGoodName = new JLabel("ชนิดสินค้า");
	private JComboBox cboGoodName = new JComboBox();
	private JLabel lbGoodType = new JLabel("ประเภทสินค้า");
	private JComboBox cboGoodType = new JComboBox();
	private JLabel lbGoodBrand = new JLabel("ยี่ห้อสินค้า");
	private JComboBox cboGoodBrand = new JComboBox();
	private JLabel lbOrderDate = new JLabel("วันที่สั่งซื้อ");
	final JDatePickerImpl datePicker1 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	
	private JLabel lbPrice = new JLabel("ราคาซื้อ/หน่วย");
	private JTextField tfPrice = new JTextField();
	private JLabel lbTagPrice = new JLabel("ราคาป้าย/หน่วย");
	private JTextField tfTagPrice = new JTextField();
	private JLabel lbQuantity = new JLabel("จำนวนชิ้น");
	private JTextField tfQuantity = new JTextField();
	private JLabel lbTotal = new JLabel("ราคารวม");
	private JTextField tfTotal = new JTextField();
	
	private JLabel lbRemark = new JLabel("หมายเหตุ");
	private JTextField tfRemark = new JTextField();
	
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JButton btSearch = new JButton("ค้นหาข้อมูล");
	private JButton btOrder = new JButton("เพิ่มรายการสั่งซื้อ");
	
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	
	// Create columns names
	private String columnNames[] = { "","ชนิดสินค้า","","ประเภทสินค้า", "","ยี่ห้อสินค้า" , "รุ่นสินค้า" , "ราคาซื้อ", "ราคาป้าย" ,"จำนวนชิ้น","ราคารวม",""};
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	

	// Create data table
	private JScrollPane scrollPane;
	private enum Mode {
		EDIT, ADD
	};

	private String selectId = "";
	private boolean flag = false;
	private int _row = 0;
	
	GlassShopFrame glassShopFrame;
	
	private String MENU_ID = "";
	
	public OrderBuyAddPanel(GlassShopFrame glassShopFrame, OrderBuyUpdate order, String menuId){
		
		this.glassShopFrame = glassShopFrame;
		
		MENU_ID = menuId;
		
		onload(order);
		buildScreen();
		
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(this);
	    datePicker1.setFont(GlassShop.FONT);
	    cboVendor.setFont(GlassShop.FONT);
	    cboSalesName.setFont(GlassShop.FONT);
		cboGoodName.setFont(GlassShop.FONT);
		cboGoodType.setFont(GlassShop.FONT);
		cboGoodBrand.setFont(GlassShop.FONT);
		cboVendor.addActionListener(this);
		cboSalesName.addActionListener(this);
		cboGoodName.addActionListener(this);
		cboGoodType.addActionListener(this);
		cboGoodBrand.addActionListener(this);
	    table.setFont(GlassShop.FONT);
	    table.addMouseListener(this);
	    table.getTableHeader().setFont(GlassShop.FONT);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
	}
	
	private void onload(OrderBuyUpdate order) {
		
		
		CatchDataFrame cd = new CatchDataFrame();
		cboVendor = cd.VendorCombobox();
		cboSalesName = cd.SalesNameCombobox();
		cboGoodName = cd.GoodsTypeCombobox();
		cboGoodType = cd.GoodTypeNameCombobox();
		cboGoodBrand = cd.GoodBrandNameCombobox();
		
		btOrder.addActionListener(this);
		btAdd.addActionListener(this);
		btEdit.addActionListener(this);
		btSearch.addActionListener(this);
		btDelete.addActionListener(this);
		
		datePicker1.getModel().setSelected(true);
		datePicker1.setTextEditable(true);
		if(order !=null){
			setTextbox(order);
			updateTable(order);
		}else{
			setUpdateTable();
		}
		if (MENU_ID.equals("menu_2_1_1")){
			setPanelMode(Mode.ADD);
		}else{
			setPanelMode(Mode.EDIT);
		}
		
	}
	private void setPanelMode(Mode mode) {
		if (mode == Mode.ADD) {
			btAdd.setVisible(true);
			btCancel.setVisible(true);
			btEdit.setVisible(false);
			btDelete.setVisible(false);
			tfOrderNo.setEnabled(true);
		} else if (mode == Mode.EDIT) {
			btAdd.setVisible(false);
			btCancel.setVisible(true);
			btEdit.setVisible(true);
			btDelete.setVisible(true);
			tfOrderNo.setEnabled(false);
		}
	}
	
	public void buildScreen() {
		setLayout(new BorderLayout(0, 0));
		setTextBox();
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridBagLayout());
		tempPanel.add(lbOrderNo, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		tempPanel.add(tfOrderNo, new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		tempPanel.add(lbOrderDate, new GridBagConstraints(4, 0, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		tempPanel.add(datePicker1, new GridBagConstraints(6, 0, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		tempPanel.add(lbVendorName, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		tempPanel.add(cboVendor, new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		tempPanel.add(lbSalesName, new GridBagConstraints(4, 1, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		tempPanel.add(cboSalesName, new GridBagConstraints(6, 1, 2, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		tempPanel.add(topPanel, new GridBagConstraints(0, 3, 20, 8, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		topPanel.add(lbGoodName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodName, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodType, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodType, new GridBagConstraints(6, 2, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodBrand, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodBrand, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodsSub, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodsSub, new GridBagConstraints(6, 3, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbPrice, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfPrice, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbTagPrice, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfTagPrice, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbQuantity, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfQuantity, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbTotal, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfTotal, new GridBagConstraints(7, 4, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbRemark, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfRemark, new GridBagConstraints(1, 5, 7, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(btOrder, new GridBagConstraints(1, 6, 7, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ข้อมูลการสั่งซื้อ",0,0,GlassShop.FONT));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
//		tablePanel.setLayout(new GridBagLayout());O
//		buildEyesightLensScreen(tablePanel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		tablePanel.add(scrollPane,new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		tablePanel.add(scrollPane,BorderLayout.CENTER);
		topPanel.add(lbTableCount, new GridBagConstraints(5, 6, 2, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 130, 5, 0), 0, 0));
		
		JPanel printButtonPanel = new JPanel();
		printButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		printButtonPanel.add(btAdd);
		printButtonPanel.add(btEdit);
		printButtonPanel.add(btDelete);
		printButtonPanel.add(btSearch);
//		printButtonPanel.add(btCancel);
		
		add(tempPanel, BorderLayout.NORTH);
//		add(topPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		add(printButtonPanel, BorderLayout.SOUTH);
		
		tempPanel.setBackground(GlassShop.bgColor);
		tablePanel.setBackground(GlassShop.bgColor);
		topPanel.setBackground(GlassShop.bgColor);
		printButtonPanel.setBackground(GlassShop.bgColor);
	}
	
	private void setTextBox(){
		tfOrderNo.setPreferredSize(new Dimension(300, 20));
		tfGoodsSub.setPreferredSize(new Dimension(300, 20));
		cboVendor.setPreferredSize(new Dimension(300, 20));
		cboSalesName.setPreferredSize(new Dimension(300, 20));
		cboGoodName.setPreferredSize(new Dimension(300, 20));
		cboGoodType.setPreferredSize(new Dimension(300, 20));
		cboGoodBrand.setPreferredSize(new Dimension(300, 20));
		tfPrice.setPreferredSize(new Dimension(100, 20));
		tfTagPrice.setPreferredSize(new Dimension(100, 20));
		tfQuantity.setPreferredSize(new Dimension(100, 20));
		tfTotal.setPreferredSize(new Dimension(100, 20));
		tfRemark.setPreferredSize(new Dimension(600, 50));
	}
	private void clearTextbox(){
		tfGoodsSub.setText("");
		CatchDataFrame.selectValues(cboGoodName," ");
		CatchDataFrame.selectValues(cboGoodType," ");
		CatchDataFrame.selectValues(cboGoodBrand," ");
		tfPrice.setText("");
		tfTagPrice.setText("");
		tfQuantity.setText("");
		tfTotal.setText("");
		tfRemark.setText("");
	}
	private void setTextbox(OrderBuyUpdate order){
		
		if (null!= order){
			tfOrderId.setText(order.getOrderId());
			tfOrderNo.setText(order.getOrderNo());
			datePicker1.getModel().setDate(12, 10, 2013);
			CatchDataFrame.selectValues(cboVendor, order.getVendorName());
			CatchDataFrame.selectValues(cboSalesName, order.getSalesmanName());
		}
	}	
	private void setUpdateTable(){
		
		if (!flag){
			tableModel.setRowCount(tableModel.getRowCount());
			Object[] dataValues = new String[columnNames.length];
			dataValues = updateValuesTable();
			tableModel.addRow(dataValues);
			table = new JTable(tableModel);
			validate();
		}else{
			setTableValue(_row);
			tableModel.fireTableDataChanged();
			revalidate();
		}
			
		hideColumnTable();
		flag = false;
		_row=0;
	}
	private Object[] updateValuesTable(){
		
		Object[] dataValues = new String[columnNames.length];
		dataValues = new String[columnNames.length];
		dataValues[0] = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
		dataValues[1] = ((CatchDataModel) cboGoodName.getSelectedItem()).name;
		dataValues[2] = ((CatchDataModel) cboGoodType.getSelectedItem()).value;
		dataValues[3] = ((CatchDataModel) cboGoodType.getSelectedItem()).name;
		dataValues[4] = ((CatchDataModel) cboGoodBrand.getSelectedItem()).value;
		dataValues[5] = ((CatchDataModel) cboGoodBrand.getSelectedItem()).name;
		dataValues[6] = tfGoodsSub.getText();
		dataValues[7] = tfPrice.getText();
		dataValues[8] = tfTagPrice.getText();
		dataValues[9] = tfQuantity.getText();
		dataValues[10] = tfTotal.getText();
		dataValues[11] = tfOrderGoodsId.getText();
		return dataValues;
	}
	private void updateTable(OrderBuyUpdate order){
		
		OrderBuyDao orderBuyDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
		Vector vc = orderBuyDao.selectByOrderId(order.getOrderId());
		
		tableModel.setRowCount(0);
		
		for (int i = 0; i < vc.size(); i++){
			
			OrderGoods good = (OrderGoods)vc.get(i);
			Object[] dataValues = new String[columnNames.length];
			dataValues = dataValuesTable(good);
			tableModel.addRow(dataValues);
		}
		      
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+vc.size()+" รายการ");
		hideColumnTable();
		validate();
	}
	private void hideColumnTable(){
		table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(2).setMinWidth(0);
        table.getColumnModel().getColumn(2).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(11).setMinWidth(0);
        table.getColumnModel().getColumn(11).setMaxWidth(0);
	}
	private void updateTable(String orderId){
		
		OrderBuyDao orderBuyDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
		Vector vc = orderBuyDao.selectByOrderId(orderId);
		
		tableModel.setRowCount(0);
		
		for (int i = 0; i < vc.size(); i++){
			
			OrderGoods good = (OrderGoods)vc.get(i);
			Object[] dataValues = new String[columnNames.length];
			dataValues = dataValuesTable(good);
			tableModel.addRow(dataValues);
		}
		      
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+vc.size()+" รายการ");
		hideColumnTable();
		validate();
	}
	private Object[] dataValuesTable(OrderGoods goods){
		
		Object[] dataValues = new String[columnNames.length];
		dataValues = new String[columnNames.length];
		dataValues[0] = goods.getGoodsId();
		dataValues[1] = goods.getGoodsName();
		dataValues[2] = goods.getGoodstypeId();
		dataValues[3] = goods.getGoodsTypeName();
		dataValues[4] = goods.getGoodsbrandId();
		dataValues[5] = goods.getGoodsBrand();
		dataValues[6] = goods.getGoodssub();
		dataValues[7] = goods.getOrdergoodsCost();
		dataValues[8] = goods.getOrdergoodsCostLabel();
		dataValues[9] = goods.getOrdergoodsQuantity();
		dataValues[10] = goods.getOrdergoodsCostSum();
		dataValues[11] = goods.getOrdergoodsId();
		return dataValues;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			//Disable for Edit mode by veena 2013-03-17
			JTable target = (JTable)e.getSource();   
			
			tableValue(target);
			setPanelMode(Mode.EDIT);
			flag = true;
			
		}
		
	}
	
	public Vector insertValues(){
		Vector vc = new Vector();
		int rowCount = table.getRowCount();
		for (int row=0; row< rowCount;row++){
			OrderGoods goods = new OrderGoods();
			if (table.getValueAt(row, 0).toString().length()>0){
				
				goods.setGoodsId((String)table.getValueAt(row, 0));
				goods.setGoodstypeId((String)table.getValueAt(row, 2));
				goods.setGoodsbrandId((String)table.getValueAt(row, 4));
				goods.setGoodssub((String)table.getValueAt(row, 6));
				goods.setOrdergoodsCost((String)table.getValueAt(row, 7));
				goods.setOrdergoodsCostLabel((String)table.getValueAt(row, 8));
				goods.setOrdergoodsQuantity((String)table.getValueAt(row, 9));
				goods.setOrdergoodsCostSum((String)table.getValueAt(row, 10));
				vc.add(goods);
			}
			
		}
		
		return vc;
		
	}
	private void tableValue(JTable target){
		
		int row = target.getSelectedRow();
		_row = row;
		CatchDataFrame.selectValues(cboGoodName, (String)table.getValueAt(row,1));
		CatchDataFrame.selectValues(cboGoodType, (String)table.getValueAt(row,3));
		CatchDataFrame.selectValues(cboGoodBrand, (String)table.getValueAt(row,5));
		tfGoodsSub.setText((String)table.getValueAt(row,6));
		tfPrice.setText((String)table.getValueAt(row,7));
		tfTagPrice.setText((String)table.getValueAt(row,8));
		tfQuantity.setText((String)table.getValueAt(row,9));
		tfTotal.setText((String)table.getValueAt(row,10));
		tfOrderGoodsId.setText((String)table.getValueAt(row,11));
	}
	
	private void setTableValue(int row){
		
		_row = row;
		Object[] dataValues = new String[columnNames.length];

		table.setValueAt(((CatchDataModel) cboGoodName.getSelectedItem()).value, row, 0);
		table.setValueAt(((CatchDataModel) cboGoodName.getSelectedItem()).name, row, 1);
		table.setValueAt(((CatchDataModel) cboGoodType.getSelectedItem()).value,row,2);
		table.setValueAt(((CatchDataModel) cboGoodType.getSelectedItem()).name,row,3);
		table.setValueAt(((CatchDataModel) cboGoodBrand.getSelectedItem()).value,row,4);
		table.setValueAt(((CatchDataModel) cboGoodBrand.getSelectedItem()).name,row,5);
		table.setValueAt(tfGoodsSub.getText(),row,6);
		table.setValueAt(tfPrice.getText(),row,7);
		table.setValueAt(tfTagPrice.getText(),row,8);
		table.setValueAt(tfQuantity.getText(),row,9);
		table.setValueAt(tfTotal.getText(),row,10);
	}
	private OrderbuyModel insertModelValue(){
		
		OrderbuyModel orderModel = new OrderbuyModel();
		orderModel.setOrderId(tfOrderId.getText());
		orderModel.setOrderNo(tfOrderNo.getText());
		orderModel.setOrderDate(CatchDataFrame.PickerDat2String(datePicker1));
		orderModel.setVendorId(((CatchDataModel) cboVendor.getSelectedItem()).value);
		orderModel.setSalesmanId(((CatchDataModel) cboSalesName.getSelectedItem()).value);
		orderModel.setOrderNote(tfRemark.getText());
		return orderModel;
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
		if (e.getSource() == btOrder){
			
			setUpdateTable();
			clearTextbox();
		}else if(e.getSource() == btAdd){
			
			OrderBuyDao orderBuyDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
					
			Vector vc = insertValues();
			
			OrderbuyModel orderModel = insertModelValue();
			
			orderBuyDao.insert(orderModel, vc);
			
			OrderBuySearchPanel panel = new OrderBuySearchPanel(glassShopFrame);
			glassShopFrame.changePanel(panel);
			
		}else if(e.getSource() == btEdit){
			
			OrderBuyDao orderBuyDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
					
			Vector vc = insertValues();
			OrderbuyModel orderModel = insertModelValue();
			
			orderBuyDao.update(orderModel, vc);	
			
			OrderBuySearchPanel panel = new OrderBuySearchPanel(glassShopFrame);
			glassShopFrame.changePanel(panel);
		}else if(e.getSource() == btSearch){
			
			OrderBuySearchPanel panel = new OrderBuySearchPanel(glassShopFrame);
			glassShopFrame.changePanel(panel);
		}else if(e.getSource() == btDelete){
			
			OrderBuyDao orderBuyDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
			if (!orderBuyDao.delete(tfOrderGoodsId.getText())){
				JOptionPane.showMessageDialog(this, "ไม่สามารถลบข้อมูลได้ ที่มีรายการขายไปแล้ว", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				updateTable(tfOrderId.getText());
			}
		}else if(e.getSource() == cboVendor){
			String vendorVal = ((CatchDataModel) cboVendor.getSelectedItem()).value;
			CatchDataFrame cd = new CatchDataFrame();
			cboSalesName.removeAllItems();
			cd.SalesNameByVendorCombobox(vendorVal,cboSalesName);
		}else if (e.getSource() == cboGoodName){
			String goodId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
			CatchDataFrame cd = new CatchDataFrame();
			cboGoodType.removeAllItems();
			cd.GoodTypeNameByGoodsCombobox(goodId,cboGoodType);
		}
			
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
