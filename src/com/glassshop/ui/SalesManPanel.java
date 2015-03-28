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

import com.glassshop.dao.CustomerDao;
import com.glassshop.dao.GoodsBrandDao;
import com.glassshop.dao.GoodsDao;
import com.glassshop.dao.SalesManDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.Customer;
import com.glassshop.model.GoodsBrand;
import com.glassshop.model.SalesMan;

public class SalesManPanel  extends JPanel implements ListSelectionListener, ActionListener, KeyListener, MouseListener{
	
	static Logger log = Logger.getLogger(GoodsPanel.class.getName());
	
	private JLabel lbVendorName = new JLabel("ชื่อบริษัท");
	private JLabel lbVendorName2 = new JLabel("ชื่อบริษัท");
	private JLabel lbSalesName = new JLabel("ชื่อผู้ขาย");
	private JTextField tfSalesName = new JTextField();
	private JLabel lbSalesName2 = new JLabel("ชื่อผู้ขาย");
	private JTextField tfSalesName2 = new JTextField();
	private JLabel lbSalesTel = new JLabel("เบอร์โทรผู้ขาย");
	private JTextField tfSalesTel = new JTextField();
	private JLabel lbSalesTel2 = new JLabel("เบอร์โทรผู้ขาย");
	private JTextField tfSalesTel2 = new JTextField();
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private	JTable	table;
	private JComboBox cbVendor = new JComboBox();
	private JComboBox cbVendor2 = new JComboBox();
	// Create columns names
	private String columnNames[] = { "เลขที่ผู้ขาย", "ชื่อผู้ขาย", "เบอร์โทรผู้ขาย" ,"ชื่อบริษัท","เบอร์โทรบริษัท"};
	
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	
	// Create data table
	private	JScrollPane scrollPane;
	
	private enum Mode {EDIT,ADD};
//	private Mode mode;
	
	private String selectId = "";
	

	public SalesManPanel(){
		onload();
		buildScreen();
		
		tfSalesName2.addKeyListener(this);
		tfSalesTel2.addKeyListener(this);
		cbVendor2.addActionListener(this);
		
		//Add Action Listener to Edit button
		btAdd.addActionListener(this);
		//Add Action Listener to Edit button
		btEdit.addActionListener(this);
		//Add Action Listener to Delete button
		btDelete.addActionListener(this);
		//Add Action Listener to Cancel button
		btCancel.addActionListener(this);
		//Add List Selection Listener to table
		
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(this);
	    table.setFont(GlassShop.FONT);
	    table.addMouseListener(this);
	    table.getTableHeader().setFont(GlassShop.FONT);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
	}
	private void onload(){
		updateTable();
		CatchDataFrame cd = new CatchDataFrame();
		cbVendor = cd.VendorCombobox();
		cbVendor2 = cd.VendorCombobox();
		setPanelMode(Mode.ADD);
	}
	
	private void setPanelMode(Mode mode){
		if(mode == Mode.ADD){
			btAdd.setVisible(true);
			btCancel.setVisible(true);
			btEdit.setVisible(false);
			btDelete.setVisible(false);
		}else if(mode == Mode.EDIT){
			btAdd.setVisible(false);
			btCancel.setVisible(true);
			btEdit.setVisible(true);
			btDelete.setVisible(true);
		}
	}
	
	
	public void buildScreen(){
		setLayout(new BorderLayout(0, 0));
		
		tfSalesName.setPreferredSize(new Dimension(300,20));
		tfSalesName2.setPreferredSize(new Dimension(300,20));
		cbVendor.setPreferredSize(new Dimension(300,20));
		tfSalesTel.setPreferredSize(new Dimension(300,20));
		tfSalesTel2.setPreferredSize(new Dimension(300,20));
		cbVendor2.setPreferredSize(new Dimension(300,20));
		cbVendor.setFont(GlassShop.FONT);
		cbVendor2.setFont(GlassShop.FONT);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbVendorName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(cbVendor, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 100), 0, 0));
		topPanel.add(lbSalesName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(tfSalesName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(lbSalesTel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		topPanel.add(tfSalesTel, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(btAdd);
		buttonPanel.add(btEdit);
		buttonPanel.add(btDelete);
		buttonPanel.add(btCancel);
		topPanel.add(buttonPanel, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ข้อมูลพนักงานขาย",0,0,GlassShop.FONT));
		
		JPanel buttomPanel = new JPanel();
		buttomPanel.setLayout(new GridBagLayout());
		buttomPanel.add(lbVendorName2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		buttomPanel.add(cbVendor2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 100), 0, 0));
		buttomPanel.add(lbSalesName2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		buttomPanel.add(tfSalesName2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		buttomPanel.add(lbSalesTel2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
		buttomPanel.add(tfSalesTel2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
//		buttomPanel.add(lbTableCount, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 130, 5, 0), 0, 0));
		
		scrollPane = new JScrollPane( table );
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		buttomPanel.add(scrollPane, new GridBagConstraints(0, 3, 4, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
//		buttomPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ค้นหาข้อมูลพนักงานขาย",0,0,GlassShop.FONT));
		topPanel.setBackground(GlassShop.bgColor);
		buttonPanel.setBackground(GlassShop.bgColor);
		buttomPanel.setBackground(GlassShop.bgColor);
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);
	
	}
	private void updateTable(){
		SalesManDao salesManDao = (SalesManDao)GlassShop.CONTEXT.getBean("salesManDAO");
		List<SalesMan> listSalesMan = salesManDao.selectAll();
		
		tableModel.setRowCount(0);
				
		for(int i=0; i<listSalesMan.size(); i++){
			SalesMan salesMan = listSalesMan.get(i);
			
			if(salesMan != null){
				Object[] dataValues = new String[columnNames.length];
				dataValues[0] = salesMan.getSalesmanId();
				dataValues[1] = salesMan.getSalesmanName();
				dataValues[2] = salesMan.getSalesmanTel();
				dataValues[3] = salesMan.getVendorName();
				dataValues[4] = salesMan.getVendorTel();
				tableModel.addRow(dataValues);
				
			}
			
		}
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ " + listSalesMan.size() + " รายการ");
		validate();
	}
	
	private void updateFillterSalesMan(String vendorId, String salesName, String salesTel){
		log.info(vendorId);
		SalesManDao salesManDao = (SalesManDao)GlassShop.CONTEXT.getBean("salesManDAO");
		Vector vc = salesManDao.selectFillter(vendorId, salesName, salesTel);
		
		tableModel.setRowCount(0);
		for (int i=0; i<vc.size();i++){
			SalesMan salesMan = (SalesMan)vc.get(i);
			if (salesMan != null) {
				Object[] dataValues = null;
				
				dataValues = dataValuesTable(salesMan);
				tableModel.addRow(dataValues);
			}
		}
		
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+vc.size()+" รายการ");
		validate();
	}
	
	private Object[] dataValuesTable(SalesMan salesMan){
		
		Object[] dataValues = new String[columnNames.length];
		dataValues = new String[columnNames.length];
		dataValues[0] = salesMan.getSalesmanId();
		dataValues[1] = salesMan.getSalesmanName();
		dataValues[2] = salesMan.getSalesmanTel();
		dataValues[3] = salesMan.getVendorName();
		dataValues[4] = salesMan.getVendorTel();
		return dataValues;
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
		updateFillterSalesMan(((CatchDataModel) cbVendor2.getSelectedItem()).value,tfSalesName2.getText(),tfSalesTel2.getText());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cbVendor2){
			String vendorVal = "";
			vendorVal = ((CatchDataModel) cbVendor2.getSelectedItem()).value;
			updateFillterSalesMan(vendorVal,tfSalesName2.getText(),tfSalesTel2.getText());
			
		}else if (e.getSource() == btDelete) {
			if ("".equals(tfSalesName.getText()) && "".equals(cbVendor.getSelectedItem())) return;
			SalesManDao salesManDao = (SalesManDao) GlassShop.CONTEXT.getBean("salesManDAO");
			salesManDao.delete(selectId);
			updateTable();

		} else if (e.getSource() == btEdit) {
			if ("".equals(tfSalesName.getText()) && "".equals(cbVendor.getSelectedItem())) return;
			
			SalesManDao salesManDao = (SalesManDao) GlassShop.CONTEXT.getBean("salesManDAO");
			SalesMan salesMan = salesManDao.selectById(selectId);
			salesMan.setVendorId(((CatchDataModel) cbVendor.getSelectedItem()).value);
			salesMan.setSalesmanName(tfSalesName.getText());
			salesMan.setSalesmanTel(tfSalesTel.getText());
			salesManDao.update(salesMan);
			updateTable();

		}else if (e.getSource() == btAdd) {
			
			if ("".equals(tfSalesName.getText()) && "".equals(cbVendor.getSelectedItem())) return;
			SalesManDao salesManDao = (SalesManDao) GlassShop.CONTEXT.getBean("salesManDAO");
			SalesMan salesMan = new SalesMan();
			salesMan.setVendorId(((CatchDataModel) cbVendor.getSelectedItem()).value);
			salesMan.setSalesmanName(tfSalesName.getText());
			salesMan.setSalesmanTel(tfSalesTel.getText());
			salesManDao.insert(salesMan);
			updateTable();

		} else if (e.getSource() == btCancel) {
			selectId = "";
		}
		table.clearSelection();
		tfSalesName.setText("");
		tfSalesTel.setText("");
		cbVendor.setSelectedIndex(0);
		setPanelMode(Mode.ADD);
		revalidate();
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable)e.getSource();         
			int row = target.getSelectedRow();

	        String salesManId = (String) table.getValueAt(row,0);
	        String salesManName = (String) table.getValueAt(row,1);
	        String salesManTel = (String) table.getValueAt(row,2);
	        String vendorName = (String) table.getValueAt(row,3);
	        String vendorTel = (String) table.getValueAt(row,4);
	        
	        tfSalesName.setText(salesManName);
	        tfSalesTel.setText(salesManTel);
	        
	        for (int i=0; i<cbVendor.getItemCount();i++){
	        	
				if(((CatchDataModel) cbVendor.getItemAt(i)).name.equals(vendorName)){
					cbVendor.setSelectedIndex(i);
				}
				
			}
			setPanelMode(Mode.EDIT);
			
			selectId = salesManId;
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
	
}
