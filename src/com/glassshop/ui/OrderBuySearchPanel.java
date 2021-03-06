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

import javax.swing.JButton;
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

import com.glassshop.dao.OrderBuyDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.OrderBuyUpdate;
import com.glassshop.model.OrderSaleUpdate;
import com.glassshop.model.OrderbuyModel;

public class OrderBuySearchPanel extends JPanel implements ListSelectionListener,
		ActionListener, KeyListener, MouseListener {

	static Logger log = Logger.getLogger(GoodsPanel.class.getName());

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
	private JLabel lbFromDate = new JLabel("ตั้งแต่วันที่");
	final JDatePickerImpl datePicker1 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JLabel lbToDate = new JLabel("จนถึงวันที่");
	final JDatePickerImpl datePicker2 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	
	// Create columns names
	private String columnNames[] = { "","เลขที่ใบเสร็จ","วันที่สั่งซื้อ", "ชื่อบริษัท" , "ชื่อผู้ขาย" , "ชนิดสินค้า", "ประเภทสินค้า" ,"ยี่ห้อสินค้า","รุ่นสินค้า","ราคาซื้อ","ราคาป้าย","จำนวนชิ้น" ,"ราคารวม" ,"หมายเหตุ" };
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	

	// Create data table
	private JScrollPane scrollPane;
	private enum Mode {
		EDIT, ADD
	};

	private String selectId = "";
	
	GlassShopFrame glassShopFrame;
	
	public OrderBuySearchPanel(GlassShopFrame glassShopFrame){
		this.glassShopFrame = glassShopFrame;
		
		onload();
		buildScreen();
		
		datePicker1.addActionListener(this);
		datePicker2.addActionListener(this);
		
		cboVendor.addActionListener(this);
		cboGoodName.addActionListener(this);
		cboGoodBrand.addActionListener(this);
		cboGoodType.addActionListener(this);
		cboSalesName.addActionListener(this);
		
		tfOrderNo.addKeyListener(this);
		tfGoodsSub.addKeyListener(this);
		
		datePicker1.setFont(GlassShop.FONT);
		datePicker2.setFont(GlassShop.FONT);
		datePicker1.setTextEditable(true);
		datePicker2.setTextEditable(true);  
		datePicker1.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
		datePicker2.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
		cboVendor.setFont(GlassShop.FONT);
		cboGoodName.setFont(GlassShop.FONT);
		cboGoodBrand.setFont(GlassShop.FONT);
		cboGoodType.setFont(GlassShop.FONT);
		cboSalesName.setFont(GlassShop.FONT);
		
		tfOrderNo.setFont(GlassShop.FONT);
		tfGoodsSub.setFont(GlassShop.FONT);
		
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
		cboSalesName = cd.SalesNameCombobox();
		cboGoodName = cd.GoodsTypeCombobox();
		cboGoodType = cd.GoodTypeNameCombobox();
		cboGoodBrand = cd.GoodBrandNameCombobox();
		
		datePicker1.getModel().setSelected(true);
		datePicker2.getModel().setSelected(true);
		updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		setPanelMode(Mode.ADD);
	}
	private void setPanelMode(Mode mode) {
		if (mode == Mode.ADD) {
			btAdd.setVisible(true);
			btCancel.setVisible(true);
			btEdit.setVisible(false);
			btDelete.setVisible(false);
		} else if (mode == Mode.EDIT) {
			btAdd.setVisible(false);
			btCancel.setVisible(true);
			btEdit.setVisible(true);
			btDelete.setVisible(true);
		}
	}
	
	public void buildScreen() {
		setLayout(new BorderLayout(0, 0));

		tfOrderNo.setPreferredSize(new Dimension(300, 20));
		tfGoodsSub.setPreferredSize(new Dimension(300, 20));
		cboVendor.setPreferredSize(new Dimension(300, 20));
		cboSalesName.setPreferredSize(new Dimension(300, 20));
		cboGoodName.setPreferredSize(new Dimension(300, 20));
		cboGoodType.setPreferredSize(new Dimension(300, 20));
		cboGoodBrand.setPreferredSize(new Dimension(300, 20));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbOrderNo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfOrderNo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbFromDate, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(datePicker1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbToDate, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(datePicker2, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbVendorName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboVendor, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbSalesName, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboSalesName, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodType, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodType, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodBrand, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodBrand, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodsSub, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodsSub, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));

		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ค้นหาตามรายการสั่งซื้อ",0,0,GlassShop.FONT));
		topPanel.add(lbTableCount, new GridBagConstraints(3, 4, 1, 1, 0.0,
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
		
		String fromDt = CatchDataFrame.PickerDat2String(datePicker1);
		String toDt =  CatchDataFrame.PickerDat2String(datePicker2);
        
        String vendorId = ((CatchDataModel) cboVendor.getSelectedItem()).value;
        String salesManId = ((CatchDataModel) cboSalesName.getSelectedItem()).value;
        String goodsId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
        String goodsTypeId = ((CatchDataModel) cboGoodType.getSelectedItem()).value;
        String goodsBrandId = ((CatchDataModel) cboGoodBrand.getSelectedItem()).value;
        
		OrderBuyDao orderDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
		List<OrderbuyModel> listOrder = orderDao.selectFillterOrder(orderNo, fromDt, toDt, vendorId, salesManId, goodsId,
				goodsTypeId, goodsBrandId, goodsSub);
		
		tableModel.setRowCount(0);
		
		for(int i=0; i<listOrder.size(); i++){
			OrderbuyModel order = listOrder.get(i);
			
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
	private Object[] dataValuesTable(OrderbuyModel order){
		
		Object[] dataValues = new String[columnNames.length];
		dataValues = new String[columnNames.length];
		dataValues[0] = order.getOrderId();
		dataValues[1] = order.getOrderNo();
		if (order.getOrderDate() != null && !order.getOrderDate().equals("")){
			dataValues[2] = CatchDataFrame.SQLDate2String(order.getOrderDate());
		}else{
			dataValues[2] = "";
		}
		
		dataValues[3] = order.getVendorName();
		dataValues[4] = order.getSalesmanName();
		dataValues[5] = order.getGoodsName();
		dataValues[6] = order.getGoodsType();
		dataValues[7] = order.getGoodsBrand();
		dataValues[8] = order.getGoodsSub();
		dataValues[9] = order.getGoodsCost();
		dataValues[10] = order.getGoodsCostLabel();
		dataValues[11] = order.getOrderQuantity();
		dataValues[12] = order.getGoodsCostSum();
		dataValues[13] = order.getOrderNote();
		return dataValues;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			//Disable for Edit mode by veena 2013-03-17
			JTable target = (JTable)e.getSource();   
			int row = target.getSelectedRow();
			
			OrderBuyUpdate order = tableValue(target);
			setPanelMode(Mode.EDIT);
			
			OrderBuyAddPanel panel = new OrderBuyAddPanel(glassShopFrame, order,"menu_2_1_2");
			glassShopFrame.changePanel(panel);
			
		}
		
	}
	private OrderBuyUpdate tableValue(JTable target){
		int row = target.getSelectedRow();
		
		String orderId = (String)table.getValueAt(row,0);
		String orderGoodId = (String)table.getValueAt(row,0);
		String orderNo = (String)table.getValueAt(row,1);
		String orderDate = (String)table.getValueAt(row,2);
		String vendorId = (String)table.getValueAt(row,3);
		String salesmanId = (String)table.getValueAt(row,4);
		
		OrderBuyUpdate order = new OrderBuyUpdate(orderId, orderNo,orderDate, vendorId, salesmanId);
		
		return order;
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
		
		if (e.getSource() == datePicker1){
			
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
			
		}else if (e.getSource() == datePicker2){
			
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
			
		
			
		}else if(e.getSource() == cboVendor){
			String vendorVal = ((CatchDataModel) cboVendor.getSelectedItem()).value;
			CatchDataFrame cd = new CatchDataFrame();
			cboSalesName.removeAllItems();
			cd.SalesNameByVendorCombobox(vendorVal,cboSalesName);
			
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
			
		}else if (e.getSource() == cboGoodName){
			String goodId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
			CatchDataFrame cd = new CatchDataFrame();
			cboGoodType.removeAllItems();
			cd.GoodTypeNameByGoodsCombobox(goodId,cboGoodType);
			
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
			
		}else if((e.getSource() == cboGoodBrand) || (e.getSource() == cboGoodType) || (e.getSource() == cboSalesName)){
			
//			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
