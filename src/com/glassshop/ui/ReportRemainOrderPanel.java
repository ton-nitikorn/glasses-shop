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

public class ReportRemainOrderPanel extends JPanel implements ListSelectionListener,
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
	
	private JTextField tfSumQuantity = new JTextField();
	private JLabel lbSumQuantity = new JLabel("จำนวนรวมสินค้าที่สั่งซื้อ");
	private JTextField tfSumSell = new JTextField();
	private JLabel lbSumSell = new JLabel("จำนวนรวมสินค้าที่ขาย");
	private JTextField tfSumStock = new JTextField();
	private JLabel lbSumStock = new JLabel("จำนวนรวมสินค้าคงเหลือ");
	
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	
	private JButton btPrint = new JButton("พิมพ์รายงาน");
	
	// Create columns names
	private String columnNames[] = {"วันที่สั่งซื้อ", "เลขที่ใบเสร็จ", "ชื่อบริษัท" ,"ชนิดสินค้า", "ประเภทสินค้า" ,"ยี่ห้อสินค้า","รุ่นสินค้า","จำนวนที่สั่งซื้อ","",""};
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	

	// Create data table
	private JScrollPane scrollPane;
	private enum Mode {
		EDIT, ADD
	};

	private String selectId = "";
	private DecimalFormat qtyf = new DecimalFormat("##,###.##");
	
	public ReportRemainOrderPanel(){
		
		onload();
		buildScreen();
		
		tfOrderNo.addKeyListener(this);
		tfGoodsSub.addKeyListener(this);
		
		cboVendor.addActionListener(this);
		cboGoodName.addActionListener(this);
		cboGoodBrand.addActionListener(this);
		cboGoodType.addActionListener(this);
		btPrint.addActionListener(this);
		
		cboVendor.setFont(GlassShop.FONT);
		cboGoodName.setFont(GlassShop.FONT);
		cboGoodBrand.setFont(GlassShop.FONT);
		cboGoodType.setFont(GlassShop.FONT);
		
		datePicker1.addActionListener(this);
		datePicker2.addActionListener(this);
		datePicker1.setTextEditable(true);
		datePicker2.setTextEditable(true); 
		datePicker1.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
		datePicker2.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		} });
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
	}
	public void buildScreen() {
		setLayout(new BorderLayout(0, 0));

		tfOrderNo.setPreferredSize(new Dimension(300, 20));
		tfGoodsSub.setPreferredSize(new Dimension(300, 20));
		tfSumQuantity.setPreferredSize(new Dimension(150, 20));
		tfSumSell.setPreferredSize(new Dimension(150, 20));
		tfSumStock.setPreferredSize(new Dimension(150, 20));
		cboVendor.setPreferredSize(new Dimension(300, 20));
		cboGoodName.setPreferredSize(new Dimension(300, 20));
		cboGoodType.setPreferredSize(new Dimension(300, 20));
		cboGoodBrand.setPreferredSize(new Dimension(300, 20));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbOrderNo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfOrderNo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbVendorName, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboVendor, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		topPanel.add(lbGoodName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodType, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodType, new GridBagConstraints(3, 1, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodBrand, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(cboGoodBrand, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodsSub, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodsSub, new GridBagConstraints(3, 2, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbFromDate, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(datePicker1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbToDate, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(datePicker2, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ค้นหาตามข้อมูลการคงเหลือสินค้า",0,0,GlassShop.FONT));
		topPanel.add(lbTableCount, new GridBagConstraints(4, 3, 1, 1, 0.0,
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
		printButtonPanel.add(lbSumSell);
		printButtonPanel.add(tfSumSell);
		printButtonPanel.add(lbSumStock);
		printButtonPanel.add(tfSumStock);
		
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
        String goodsId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
        String goodsTypeId = ((CatchDataModel) cboGoodType.getSelectedItem()).value;
        String goodsBrandId = ((CatchDataModel) cboGoodBrand.getSelectedItem()).value;
        
		OrderBuyDao orderDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
		Vector vc = orderDao.selectRemainReport(orderNo, fromDt, toDt, vendorId, goodsId,
				goodsTypeId, goodsBrandId, goodsSub);
		
		tableModel.setRowCount(0);
		
		for(int i=0; i<vc.size(); i++){
			OrderbuyModel order =(OrderbuyModel) vc.get(i);
			
			if(order != null){
	
				Object[] dataValues = new String[columnNames.length];
				dataValues = dataValuesTable(order);
				tableModel.addRow(dataValues);
				
			}
			
		}
		tfSumQuantity.setText(qtyf.format(Double.parseDouble(sumTotal(7))));
		tfSumSell.setText(qtyf.format(Double.parseDouble(sumTotal(8))));
		tfSumStock.setText(qtyf.format(Double.parseDouble(sumTotal(9))));
		tfSumQuantity.setHorizontalAlignment(JTextField.RIGHT);
		tfSumSell.setHorizontalAlignment(JTextField.RIGHT);
		tfSumStock.setHorizontalAlignment(JTextField.RIGHT);
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+vc.size()+" รายการ");
		table.getColumnModel().getColumn(8).setMinWidth(0);
        table.getColumnModel().getColumn(8).setMaxWidth(0);
        table.getColumnModel().getColumn(9).setMinWidth(0);
        table.getColumnModel().getColumn(9).setMaxWidth(0);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        
        table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		validate();
	}
	private String sumTotal(int col){
		int row = tableModel.getRowCount();
		String returnValue="";
		double total=0;
		for (int i=0; i< row; i++){
			double test = Double.parseDouble((String) table.getValueAt(i,col));
			total = total + test;
		}
		returnValue = total + "";
		return returnValue;
	}
	private Object[] dataValuesTable(OrderbuyModel order){
		
		Object[] dataValues = new String[columnNames.length];
		dataValues = new String[columnNames.length];
		if (order.getOrderDate() != null && !order.getOrderDate().equals("")){
			dataValues[0] = CatchDataFrame.SQLDate2String(order.getOrderDate());
		}else{
			dataValues[0] = "";
		}
		dataValues[1] = order.getOrderNo();
		dataValues[2] = order.getVendorName();
		dataValues[3] = order.getGoodsName();
		dataValues[4] = order.getGoodsType();
		dataValues[5] = order.getGoodsBrand();
		dataValues[6] = order.getGoodsSub();
		dataValues[7] = order.getOrderQuantity();
		dataValues[8] = order.getSellQuantity();
		dataValues[9] = order.getStockQuantity();
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
//				|| (e.getSource() == cboGoodType) ){
//				
//				updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		if (e.getSource() == cboGoodName){
			String goodId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
			CatchDataFrame cd = new CatchDataFrame();
			cboGoodType.removeAllItems();
			cd.GoodTypeNameByGoodsCombobox(goodId,cboGoodType);
			
			updateFillerTable(tfOrderNo.getText(),tfGoodsSub.getText());
		}else if(e.getSource() == btPrint){
			List<OrderReportModel> list = new ArrayList<OrderReportModel>();
			
			String fromDt = CatchDataFrame.PickerDat2String(datePicker1);
			String toDt =  CatchDataFrame.PickerDat2String(datePicker2);
	        String vendorId = ((CatchDataModel) cboVendor.getSelectedItem()).value;
	        String goodsId = ((CatchDataModel) cboGoodName.getSelectedItem()).value;
	        String goodsTypeId = ((CatchDataModel) cboGoodType.getSelectedItem()).value;
	        String goodsBrandId = ((CatchDataModel) cboGoodBrand.getSelectedItem()).value;
	        
			OrderBuyDao orderDao = (OrderBuyDao)GlassShop.CONTEXT.getBean("orderBuyDAO");
			Vector vc = orderDao.selectRemainReport2(tfOrderNo.getText(), fromDt, toDt, vendorId, goodsId,
					goodsTypeId, goodsBrandId, tfGoodsSub.getText());
			
			if(vc.size() > 0){
				Reporter reporter = new Reporter(GlassShop.prop.getProperty(Constanst.REPORT_TEMPLATE_REST_ORDER),vc);		
				try {
					reporter.printAndPreview(GlassShop.prop.getProperty(Constanst.REPORT_NAME_REST_ORDER));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
