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
import com.glassshop.dao.GoodsDao;
import com.glassshop.dao.VendorDao;
import com.glassshop.model.Goods;
import com.glassshop.model.GoodsBrand;
import com.glassshop.model.Vendor;

public class GoodsPanel extends JPanel implements ListSelectionListener,
		ActionListener, KeyListener , MouseListener{

	static Logger log = Logger.getLogger(GoodsPanel.class.getName());

	private JLabel lbGoodsName = new JLabel("ชนิดสินค้า");
	private JTextField tfGoodsName = new JTextField();
	private JLabel lbGoodsName2 = new JLabel("ชนิดสินค้า");
	private JTextField tfGoodsName2 = new JTextField();
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	
	// Create columns names
	private String columnNames[] = { "เลขที่ชนิดสินค้า", "ชนิดสินค้า" };
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);	
//	private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	// Create data table
	private JScrollPane scrollPane;
	private enum Mode {
		EDIT, ADD
	};


	private String selectId = "";

	public GoodsPanel() {
		onload();
		buildScreen();

		tfGoodsName2.addKeyListener(this);

		// Add Action Listener to Edit button
		btAdd.addActionListener(this);
		// Add Action Listener to Edit button
		btEdit.addActionListener(this);
		// Add Action Listener to Delete button
		btDelete.addActionListener(this);
		// Add Action Listener to Cancel button
		btCancel.addActionListener(this);

		// Add List Selection Listener to table
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
		updateTable();
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

		tfGoodsName.setPreferredSize(new Dimension(300, 20));
		tfGoodsName2.setPreferredSize(new Dimension(300, 20));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbGoodsName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodsName, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(btAdd);
		buttonPanel.add(btEdit);
		buttonPanel.add(btDelete);
		buttonPanel.add(btCancel);
		topPanel.add(buttonPanel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));

		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"เพิ่ม / แก้ไข / ลบข้อมูลชนิดสินค้า",0,0,GlassShop.FONT));

		JPanel buttomPanel = new JPanel();
		buttomPanel.setLayout(new GridBagLayout());
		buttomPanel.add(lbTableCount, new GridBagConstraints(3, 1, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(20, 130, 5, 0), 0, 0));
		buttomPanel.add(lbGoodsName2, new GridBagConstraints(0, 0, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(20, 0, 0, 0), 0, 0));
		buttomPanel.add(tfGoodsName2, new GridBagConstraints(1, 0, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(20, 0, 0, 0), 0, 0));

		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		buttomPanel.add(scrollPane, new GridBagConstraints(0, 2, 4, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,new Insets(0, 0, 0, 0), 0, 0));

		buttomPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),"ค้นหาข้อมูลชนิดสินค้า",0,0,GlassShop.FONT));
		topPanel.setBackground(GlassShop.bgColor);
		buttonPanel.setBackground(GlassShop.bgColor);
		buttomPanel.setBackground(GlassShop.bgColor);
		
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);

	}

	private void updateTable() {
		GoodsDao goodsDao = (GoodsDao) GlassShop.CONTEXT.getBean("goodsDAO");
		List<Goods> listGoods = goodsDao.selectAll();

		tableModel.setRowCount(0);

		for (int i = 0; i < listGoods.size(); i++) {
			Goods goods = listGoods.get(i);

			if (goods != null) {
				Object[] dataValues = new String[columnNames.length];
				dataValues[0] = goods.getGoodsId().toString();
				dataValues[1] = goods.getGoodsName();

				tableModel.addRow(dataValues);

			}

		}

		table = new JTable(tableModel);
		lbTableCount
				.setText("จำนวนรายการที่พบ " + listGoods.size() + " รายการ");
		validate();
	}

	private void updateFillterGoodsBrand(String goodsName) {
		GoodsDao goodsDao = (GoodsDao) GlassShop.CONTEXT.getBean("goodsDAO");
		List<Goods> listGoods = goodsDao.selectAll();

		tableModel.setRowCount(0);

		for (int i = 0; i < listGoods.size(); i++) {
			Goods goods = listGoods.get(i);

			if (goods != null) {
				Object[] dataValues = null;
				if ((goodsName != null && goodsName.length() > 0)) {

					if (goods.getGoodsName().toUpperCase().contains(goodsName.toUpperCase())) {
						dataValues = new String[columnNames.length];
						dataValues[0] = goods.getGoodsId();
						dataValues[1] = goods.getGoodsName();
					}
					// }
				} else {
					dataValues = new String[columnNames.length];
					dataValues[0] = goods.getGoodsId();
					dataValues[1] = goods.getGoodsName();
				}
				if (dataValues != null)
					tableModel.addRow(dataValues);
			}

		}

		table = new JTable(tableModel);
		lbTableCount
				.setText("จำนวนรายการที่พบ " + listGoods.size() + " รายการ");
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
		updateFillterGoodsBrand(tfGoodsName2.getText().trim());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btDelete) {
			if ("".equals(selectId))
				return;
			GoodsDao goodsDao = (GoodsDao) GlassShop.CONTEXT
					.getBean("goodsDAO");
			goodsDao.delete(selectId);
			updateTable();

		} else if (e.getSource() == btEdit) {
			if ("".equals(selectId))return;
			
			GoodsDao goodsDao = (GoodsDao) GlassShop.CONTEXT.getBean("goodsDAO");
			Goods goods = goodsDao.selectById(selectId);
			log.info("goods name = " + tfGoodsName.getText());
			goods.setGoodsName(tfGoodsName.getText());
			goodsDao.update(goods);
			updateTable();

		} else if (e.getSource() == btAdd) {
			if ("".equals(tfGoodsName.getText()))
				return;
			GoodsDao goodsDao = (GoodsDao) GlassShop.CONTEXT
					.getBean("goodsDAO");
			Goods goods = new Goods();
			goods.setGoodsName(tfGoodsName.getText());
			goodsDao.insert(goods);
			updateTable();

		} else if (e.getSource() == btCancel) {
			selectId = "";
		}

		table.clearSelection();
		tfGoodsName.setText("");
		setPanelMode(Mode.ADD);
		revalidate();

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		

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
	public void mouseClicked(MouseEvent e) {
		
			if (e.getClickCount() == 2) {
				JTable target = (JTable)e.getSource();  
				String goodsId = null;
				String goodsName = null;
				int row = target.getSelectedRow();

				goodsId =  (String) table.getValueAt(row,0);
	        	goodsName = (String) table.getValueAt(row,1);

	        	tfGoodsName.setText(goodsName);
				
				setPanelMode(Mode.EDIT);
				
				selectId = goodsId;
			}
			
		
	}
}
