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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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
import javax.swing.table.DefaultTableCellRenderer;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import com.glassshop.common.Constanst;
import com.glassshop.dao.OrderBuyDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.OrderbuyModel;
import com.glassshop.report.common.Reporter;
import com.glassshop.report.model.OrderReportModel;

public class ReportOrderBuyPanel extends JPanel implements ListSelectionListener,
ActionListener, KeyListener, MouseListener, FocusListener {

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
	
	private JTextField tfSumQuantity = new JTextField();
	private JLabel lbSumQuantity = new JLabel("จำนวนรวมสินค้า");
	private JTextField tfSumCost = new JTextField();
	private JLabel lbSumCost = new JLabel("ราคารวม");
	
	private JButton btPrint = new JButton("พิมพ์รายงาน");
	
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	
	// Create columns names
	private String columnNames[] = { "","เลขที่ใบเสร็จ","วันที่สั่งซื้อ", "ชื่อบริษัท" , "ชื่อผู้ขาย" , "จำนวนรวม" ,"ราคารวม" ,"หมายเหตุ" };
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	

	// Create data table
	private JScrollPane scrollPane;
	private enum Mode {
		EDIT, ADD
	};

	private String selectId = "";
	
	private DecimalFormat sumf = new DecimalFormat("#,###.00");
	private DecimalFormat qtyf = new DecimalFormat("##,###.##");
	    
	public ReportOrderBuyPanel(GlassShopFrame topFrame){
		onload();
		buildScreen();
		
		datePicker1.addActionListener(this);
		datePicker2.addActionListener(this);
		datePicker1.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
		datePicker2.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
		
		datePicker1.setTextEditable(true);
		datePicker2.setTextEditable(true);
		
		tfOrderNo.addKeyListener(this);
		tfGoodsSub.addKeyListener(this);
		cboVendor.addActionListener(this);
		cboGoodName.addActionListener(this);
		cboGoodBrand.addActionListener(this);
		cboGoodType.addActionListener(this);
		cboSalesName.addActionListener(this);
		btPrint.addActionListener(this);
		
		cboVendor.setFont(GlassShop.FONT);
		cboGoodName.setFont(GlassShop.FONT);
		cboGoodBrand.setFont(GlassShop.FONT);
		cboGoodType.setFont(GlassShop.FONT);
		cboSalesName.setFont(GlassShop.FONT);
		
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
	}
	public void buildScreen() {
		setLayout(new BorderLayout(0, 0));

		tfOrderNo.setPreferredSize(new Dimension(300, 20));
		tfGoodsSub.setPreferredSize(new Dimension(300, 20));
		tfSumCost.setPreferredSize(new Dimension(150, 20));
		tfSumQuantity.setPreferredSize(new Dimension(150, 20));
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
		

		JPanel printButtonPanel = new JPanel();
		printButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		printButtonPanel.add(btPrint);
		printButtonPanel.add(lbSumQuantity);
		printButtonPanel.add(tfSumQuantity);
		printButtonPanel.add(lbSumCost);
		printButtonPanel.add(tfSumCost);

		
		topPanel.setBackground(GlassShop.bgColor);
		printButtonPanel.setBackground(GlassShop.bgColor);
		buttomPanel.setBackground(GlassShop.bgColor);
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);
		add(printButtonPanel, BorderLayout.SOUTH);

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
		Vector vc = orderDao.selectOrderReport(orderNo, fromDt, toDt, vendorId, salesManId, goodsId,
				goodsTypeId, goodsBrandId, goodsSub);
		
		tableModel.setRowCount(0);
		
		for(int i=0; i<vc.size(); i++){
			OrderbuyModel order = (OrderbuyModel)vc.get(i);
			
			if(order != null){
	
				Object[] dataValues = new String[columnNames.length];
				dataValues = dataValuesTable(order);
				tableModel.addRow(dataValues);
				
			}
			
		}
		
		tfSumQuantity.setText(qtyf.format(Double.parseDouble(sumTotal(5))));
		tfSumCost.setText(sumf.format(Double.parseDouble(sumTotal(6))));
		tfSumQuantity.setHorizontalAlignment(JTextField.RIGHT);
		tfSumCost.setHorizontalAlignment(JTextField.RIGHT);
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+vc.size()+" รายการ");
		table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		validate();
	}
	
	private String sumTotal(int col){
		int row = tableModel.getRowCount();
		String returnValue="";
		double total=0;
		double test = 0;
		for (int i=0; i< row; i++){
//			double test = Double.parseDouble((String) table.getValueAt(i,col));
//			total = total + test;
			test = Double.parseDouble(((String) table.getValueAt(i,col)).replaceAll(",", ""));
			total = total + test;
		}
		returnValue = total + "";
		return returnValue;
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
		dataValues[5] = qtyf.format(Double.parseDouble(order.getOrderQuantity()));
		dataValues[6] = sumf.format(Double.parseDouble(order.getGoodsCostSum()));
		return dataValues;
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
		updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if((e.getSource() == cboVendor) || (e.getSource() == cboGoodBrand) || (e.getSource() == cboGoodName)
//				|| (e.getSource() == cboGoodType) || (e.getSource() == cboSalesName)){
//				
//				updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		if(e.getSource() == cboVendor){
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
		}else if(e.getSource() == btPrint){
			List<OrderReportModel> list = new ArrayList<OrderReportModel>();
			
			String orderNo = tfOrderNo.getText();
			String goodsSub = tfGoodsSub.getText();
			String fromDt = CatchDataFrame.PickerDat2String(datePicker1);
			String toDt =  CatchDataFrame.PickerDat2String(datePicker2);
	        String vendorId = ((CatchDataModel) cboVendor.getSelectedItem()).value;
	        String salesManId = ((CatchDataModel) cboSalesName.getSelectedItem()).value;
	        String goodsId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
	        String goodsTypeId = ((CatchDataModel) cboGoodType.getSelectedItem()).value;
	        String goodsBrandId = ((CatchDataModel) cboGoodBrand.getSelectedItem()).value;
	        
			OrderBuyDao orderDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
			Vector vc = orderDao.selectOrderReport2(orderNo, fromDt, toDt, vendorId, salesManId, goodsId,goodsTypeId, goodsBrandId, goodsSub);
			
			if(vc.size() > 0){
				Reporter reporter = new Reporter(GlassShop.prop.getProperty(Constanst.REPORT_TEMPLATE_ORDER),vc);		
				try {
					reporter.printAndPreview(GlassShop.prop.getProperty(Constanst.REPORT_NAME_ORDER));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if(e.getSource() == datePicker1 || e.getSource() == datePicker1){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == datePicker1 || e.getSource() == datePicker1){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == datePicker1 || e.getSource() == datePicker1){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == datePicker1 || e.getSource() == datePicker1){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}
		
	}

}
