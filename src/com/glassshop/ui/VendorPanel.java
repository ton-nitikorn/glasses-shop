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

import javax.swing.JButton;
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

import com.glassshop.dao.VendorDao;
import com.glassshop.model.Vendor;

public class VendorPanel extends JPanel implements ListSelectionListener, ActionListener, KeyListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbCompanyName1 = new JLabel("ชื่อบริษัท");
	private JTextField tfCompanyName1 = new JTextField();
	private JLabel lbCompanyTel1 = new JLabel("เบอร์โทรบริษัท");
	private JTextField tfCompanyTel1 = new JTextField();
	private JLabel lbCompanyName2 = new JLabel("ชื่อบริษัท");
	private JTextField tfCompanyName2 = new JTextField();
	private JLabel lbCompanyTel2 = new JLabel("เบอร์โทรบริษัท");
	private JTextField tfCompanyTel2 = new JTextField();
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private	JTable	table;
	// Create columns names
	private String columnNames[] = { "เลขที่บริษัท", "ชื่อบริษัท", "เบอร์โทรบริษัท" };
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	
	// Create data table
	private	JScrollPane scrollPane;
	
	private enum Mode {EDIT,ADD};
	
	private String selectId = "";
	
	public VendorPanel(){
		onload();
		buildScreen();
		setBackground(GlassShop.bgColor);
		
		//Add Action Listener to Edit button
		btAdd.addActionListener(this);
		//Add Action Listener to Edit button
		btEdit.addActionListener(this);
		//Add Action Listener to Delete button
		btDelete.addActionListener(this);
		//Add Action Listener to Cancel button
		btCancel.addActionListener(this);
		
		tfCompanyName2.addKeyListener(this);
		tfCompanyTel2.addKeyListener(this);
		
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
	
	
	public void buildScreen(){
		setLayout(new BorderLayout(0, 0));
		
		tfCompanyName1.setPreferredSize(new Dimension(300,20));
		tfCompanyTel1.setPreferredSize(new Dimension(300,20));
		tfCompanyName2.setPreferredSize(new Dimension(300,20));
		tfCompanyTel2.setPreferredSize(new Dimension(300,20));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbCompanyName1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		topPanel.add(tfCompanyName1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 100), 0, 0));
		topPanel.add(lbCompanyTel1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		topPanel.add(tfCompanyTel1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(btAdd);
		buttonPanel.add(btEdit);
		buttonPanel.add(btDelete);
		buttonPanel.add(btCancel);
		topPanel.add(buttonPanel, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ข้อมูลบริษัท",0,0,GlassShop.FONT));
		
		JPanel buttomPanel = new JPanel();
		buttomPanel.setLayout(new GridBagLayout());
		buttomPanel.add(lbCompanyName2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		buttomPanel.add(tfCompanyName2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 100), 0, 0));
		buttomPanel.add(lbCompanyTel2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		buttomPanel.add(tfCompanyTel2, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		buttomPanel.add(lbTableCount, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 130, 5, 0), 0, 0));
		
		scrollPane = new JScrollPane( table );
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		buttomPanel.add(scrollPane, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
//		buttomPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ค้นหาข้อมูลบริษัท",0,0,GlassShop.FONT));
		topPanel.setBackground(GlassShop.bgColor);
		buttonPanel.setBackground(GlassShop.bgColor);
		buttomPanel.setBackground(GlassShop.bgColor);
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);
	
	}
	
	private void onload(){
		updateTable();
		
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
	
	private void updateTable(){
		VendorDao vendorDao = (VendorDao)GlassShop.CONTEXT.getBean("vendorDAO");
		List<Vendor> listVendor = vendorDao.selectAll();
		
		tableModel.setRowCount(0);
				
		for(int i=0; i<listVendor.size(); i++){
			Vendor vendor = listVendor.get(i);
			
			if(vendor != null){
				Object[] dataValues = new String[columnNames.length];
				
				dataValues[0] = vendor.getVendorId();
				dataValues[1] = vendor.getVendorName();
				dataValues[2] = vendor.getVendorTel();
				
				tableModel.addRow(dataValues);
			}
			
		}
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+listVendor.size()+" รายการ");
		validate();
	}
	
//	private void updateFillterVendor(String vendorName, String vendorTel){
//		VendorDao vendorDao = (VendorDao)GlassShop.CONTEXT.getBean("vendorDAO");
//		List<Vendor> listVendor = vendorDao.selectFillter(vendorName, vendorTel);
//		
//		tableModel.setRowCount(0);
//		
//		if(listVendor != null){
//			for(int i=0; i<listVendor.size(); i++){
//				Vendor vendor = listVendor.get(i);
//				
//				if(vendor != null){
//					Object[] dataValues = new String[columnNames.length];
//					
//					dataValues[0] = vendor.getVendorId();
//					dataValues[1] = vendor.getVendorName();
//					dataValues[2] = vendor.getVendorTel();
//					
//					tableModel.addRow(dataValues);
//				}
//				
//			}
//		}
//		
//		table = new JTable(tableModel);
//		lbTableCount.setText("จำนวนรายการที่พบ "+listVendor.size()+" รายการ");
//		validate();
//	}
	
	private void updateFillterVendor(String vendorName, String vendorTel){
		VendorDao vendorDao = (VendorDao)GlassShop.CONTEXT.getBean("vendorDAO");
		List<Vendor> listVendor = vendorDao.selectAll();
		
		tableModel.setRowCount(0);
				
		for(int i=0; i<listVendor.size(); i++){
			Vendor vendor = listVendor.get(i);
			
			if(vendor != null){
				Object[] dataValues = null;
				if((vendorName != null && !vendorName.equals("")) && 
					(vendorTel != null && !vendorTel.equals("")) ){
					
					if(vendor.getVendorName().contains(vendorName) && vendor.getVendorTel().contains(vendorTel)){
						dataValues = new String[columnNames.length];
						dataValues[0] = vendor.getVendorId();
						dataValues[1] = vendor.getVendorName();
						dataValues[2] = vendor.getVendorTel();
					}
				}else if(vendorTel != null && !vendorTel.equals("")){
					if(vendor.getVendorTel().contains(vendorTel)){
						dataValues = new String[columnNames.length];
						dataValues[0] = vendor.getVendorId();
						dataValues[1] = vendor.getVendorName();
						dataValues[2] = vendor.getVendorTel();
					}
				}else if(vendor.getVendorName().contains(vendorName)){
					dataValues = new String[columnNames.length];
					dataValues[0] = vendor.getVendorId();
					dataValues[1] = vendor.getVendorName();
					dataValues[2] = vendor.getVendorTel();
				}
				
				if(dataValues != null)
					tableModel.addRow(dataValues);
			}
			
		}
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+listVendor.size()+" รายการ");
		validate();
	}


	public void valueChanged(ListSelectionEvent e) {
//		String vendorId = null;
//		String vendorName = null;
//		String vendorTel = null;
//
//        int[] selectedRow = table.getSelectedRows();
//
//        for (int i = 0; i < selectedRow.length; i++) {
//        	vendorId = (String) table.getValueAt(selectedRow[i],0);
//        	vendorName = (String) table.getValueAt(selectedRow[i],1);
//        	vendorTel = (String) table.getValueAt(selectedRow[i],2);
//        }
//        tfCompanyName1.setText(vendorName);
//		tfCompanyTel1.setText(vendorTel);
//		
//		setPanelMode(Mode.EDIT);
//		
//		selectId = vendorId;
	}


	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btDelete){
			if("".equals(selectId)) return;
			VendorDao vendorDao = (VendorDao)GlassShop.CONTEXT.getBean("vendorDAO");
			vendorDao.delete(selectId);
			updateTable();
		
		}else if(e.getSource() == btEdit){
			if("".equals(selectId)) return;
			VendorDao vendorDao = (VendorDao)GlassShop.CONTEXT.getBean("vendorDAO");
			Vendor vendor = vendorDao.selectById(selectId);
			vendor.setVendorName(tfCompanyName1.getText());
			vendor.setVendorTel(tfCompanyTel1.getText());
			vendorDao.update(vendor);
			updateTable();
		
		}else if(e.getSource() == btAdd){
			if("".equals(tfCompanyName1.getText()) || "".equals(tfCompanyTel1.getText())) return;
			VendorDao vendorDao = (VendorDao)GlassShop.CONTEXT.getBean("vendorDAO");
			Vendor vendor = new Vendor();
			vendor.setVendorName(tfCompanyName1.getText());
			vendor.setVendorTel(tfCompanyTel1.getText());
			vendorDao.insert(vendor);
			updateTable();
		
		}else if(e.getSource() == btCancel){
			selectId = "";		
		}
		
		table.clearSelection();
		tfCompanyName1.setText("");
		tfCompanyTel1.setText("");
		setPanelMode(Mode.ADD);
		revalidate();
	}


	public void keyTyped(KeyEvent e) {}


	public void keyPressed(KeyEvent e) {}


	public void keyReleased(KeyEvent e) {
		updateFillterVendor(tfCompanyName2.getText().trim(), tfCompanyTel2.getText().trim());
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable)e.getSource();         
			int row = target.getSelectedRow();

	        String vendorId = (String) table.getValueAt(row,0);
	        String vendorName = (String) table.getValueAt(row,1);
	        String vendorTel = (String) table.getValueAt(row,2);

	        tfCompanyName1.setText(vendorName);
			tfCompanyTel1.setText(vendorTel);
			
			setPanelMode(Mode.EDIT);
			
			selectId = vendorId;
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
