package com.glassshop.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;

import com.glassshop.dao.GoodsBrandDao;
import com.glassshop.model.GoodsBrand;

public class GoodsBrandPanel extends JPanel implements ListSelectionListener, ActionListener, KeyListener, MouseListener{
	static Logger log = Logger.getLogger(GoodsBrandPanel.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbGoodsBrandId = new JLabel("รหัสสินค้า");
	private JTextField tfGoodsBrandId = new JTextField();
	private JLabel lbGoodsBrandName = new JLabel("ยี่ห้อสินค้า");
	private JTextField tfGoodsBrandName = new JTextField();
	private JLabel lbGoodsBrandName2 = new JLabel("ยี่ห้อสินค้า");
	private JTextField tfGoodsBrandName2 = new JTextField();
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private	JTable	table;
	// Create columns names
	private String columnNames[] = { "เลขที่ยี่ห้อสินค้า", "ยี่ห้อสินค้า" };
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);		
	// Create data table
	private	JScrollPane scrollPane;
	
	private enum Mode {EDIT,ADD};
//	private Mode mode;
	
	private String selectId = "";
	
	public GoodsBrandPanel(){
		onload();
		buildScreen();
		
		//Add Action Listener to Edit button
		btAdd.addActionListener(this);
		//Add Action Listener to Edit button
		btEdit.addActionListener(this);
		//Add Action Listener to Delete button
		btDelete.addActionListener(this);
		//Add Action Listener to Cancel button
		btCancel.addActionListener(this);
		
		tfGoodsBrandName2.addKeyListener(this);
		
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
		
		tfGoodsBrandName.setPreferredSize(new Dimension(300,20));
		tfGoodsBrandName2.setPreferredSize(new Dimension(300,20));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbGoodsBrandName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodsBrandName, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(btAdd);
		buttonPanel.add(btEdit);
		buttonPanel.add(btDelete);
		buttonPanel.add(btCancel);
		topPanel.add(buttonPanel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		
		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
		"เพิ่ม / แก้ไข / ลบข้อมูลยี่ห้อสินค้า",0,0,GlassShop.FONT));
		
		JPanel buttomPanel = new JPanel();
		buttomPanel.setLayout(new GridBagLayout());
		buttomPanel.add(lbTableCount, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 130, 5, 0), 0, 0));
		buttomPanel.add(lbGoodsBrandName2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		buttomPanel.add(tfGoodsBrandName2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		
		scrollPane = new JScrollPane( table );
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		buttomPanel.add(scrollPane, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		buttomPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ค้นหาข้อมูลยี่ห้อสินค้า",0,0,GlassShop.FONT));
		topPanel.setBackground(GlassShop.bgColor);
		buttonPanel.setBackground(GlassShop.bgColor);
		buttomPanel.setBackground(GlassShop.bgColor);
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);
	
	}
	private void updateTable(){
		GoodsBrandDao goodsBrandDao = (GoodsBrandDao)GlassShop.CONTEXT.getBean("goodsBrandDAO");
		List<GoodsBrand> listGoodsBrand = goodsBrandDao.selectAll();
		
		tableModel.setRowCount(0);
		
		for(int i=0; i<listGoodsBrand.size(); i++){
			GoodsBrand goodsBrand = listGoodsBrand.get(i);
			
			if(goodsBrand != null){
	
				Object[] dataValues = new String[columnNames.length];
				dataValues[0] = goodsBrand.getGoodsbrandId().toString();
				dataValues[1] = goodsBrand.getGoodsbrandName();
				
				tableModel.addRow(dataValues);
				
			}
			
		}
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+listGoodsBrand.size()+" รายการ");
		validate();
	}
	private void updateFillterGoodsBrand(String goodsBrandName){
		GoodsBrandDao goodsBrandDao = (GoodsBrandDao)GlassShop.CONTEXT.getBean("goodsBrandDAO");
		List<GoodsBrand> listGoodsBrand = goodsBrandDao.selectAll();
		
		tableModel.setRowCount(0);
				
		for(int i=0; i<listGoodsBrand.size(); i++){
			GoodsBrand goodsBrand = listGoodsBrand.get(i);
			
			if(goodsBrand != null){
				Object[] dataValues = null;
				if((goodsBrandName != null && goodsBrandName.length()>0)){
					
					if(goodsBrand.getGoodsbrandName().toUpperCase().contains(goodsBrandName.toUpperCase())){
						dataValues = new String[columnNames.length];
						dataValues[0] = goodsBrand.getGoodsbrandId();
						dataValues[1] = goodsBrand.getGoodsbrandName();
					}
				}else{
					dataValues = new String[columnNames.length];
					dataValues[0] = goodsBrand.getGoodsbrandId().toString();
					dataValues[1] = goodsBrand.getGoodsbrandName();
				}
				if(dataValues != null)
					tableModel.addRow(dataValues);
			}
			
		}
		
		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ "+listGoodsBrand.size()+" รายการ");
		validate();
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
		updateFillterGoodsBrand(tfGoodsBrandName2.getText().trim());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btDelete){
			if("".equals(selectId)) return;
			GoodsBrandDao goodsBrandDao = (GoodsBrandDao)GlassShop.CONTEXT.getBean("goodsBrandDAO");
			goodsBrandDao.delete(selectId);
			updateTable();
		
		}else if(e.getSource() == btEdit){
			if("".equals(selectId)) return;
			GoodsBrandDao goodsBrandDao = (GoodsBrandDao)GlassShop.CONTEXT.getBean("goodsBrandDAO");
			GoodsBrand gb = goodsBrandDao.selectById(selectId);
			gb.setGoodsbrandName(tfGoodsBrandName.getText());
			goodsBrandDao.update(gb);
			updateTable();
		
		}else if(e.getSource() == btAdd){
			if("".equals(tfGoodsBrandName.getText())) return;
			GoodsBrandDao goodsBrandDao = (GoodsBrandDao)GlassShop.CONTEXT.getBean("goodsBrandDAO");
			GoodsBrand gb = new GoodsBrand();
			gb.setGoodsbrandName(tfGoodsBrandName.getText());
			goodsBrandDao.insert(gb);
			updateTable();
		
		}else if(e.getSource() == btCancel){
			selectId = "";		
		}
		
		table.clearSelection();
		tfGoodsBrandName.setText("");
		setPanelMode(Mode.ADD);
		revalidate();
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//TODO
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable)e.getSource();         
			int row = target.getSelectedRow();

	        String goodsBrandId = (String) table.getValueAt(row,0);
	        String goodsBrandName = (String) table.getValueAt(row,1);

	        tfGoodsBrandName.setText(goodsBrandName);
			
			setPanelMode(Mode.EDIT);
			
			selectId = goodsBrandId;
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
