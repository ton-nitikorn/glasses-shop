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

import com.glassshop.dao.GoodsDao;
import com.glassshop.dao.GoodsTypeDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.GoodsType;

public class GoodsTypePanel extends JPanel implements ListSelectionListener,
		ActionListener, KeyListener, MouseListener {

	static Logger log = Logger.getLogger(GoodsPanel.class.getName());

	private JLabel lbGoodsName = new JLabel("ชนิดสินค้า");
	private JLabel lbGoodsName2 = new JLabel("ชนิดสินค้า");

	private JLabel lbGoodsType = new JLabel("ประเภทสินค้า");
	private JTextField tfGoodsType = new JTextField();
	private JLabel lbGoodsType2 = new JLabel("ประเภทสินค้า");
	private JTextField tfGoodsType2 = new JTextField();
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	private JComboBox cboGoods = new JComboBox();
	private JComboBox cboGoods2 = new JComboBox();

	// Create columns names
	private String columnNames[] = { "เลขที่ประเภทสินค้า", "ชนิดสินค้า",
			"ประเภทสินค้า" };
	private MyTableModel tableModel = new MyTableModel(columnNames, 0);
	// private DefaultTableModel tableModel = new DefaultTableModel(columnNames,
	// 0);
	// Create data table
	private JScrollPane scrollPane;

	private enum Mode {
		EDIT, ADD
	};

	private String selectId = "";

	public GoodsTypePanel() {
		onload();
		buildScreen();

		tfGoodsType2.addKeyListener(this);
		cboGoods.addKeyListener(this);

		// Add Action Listener to Edit button
		btAdd.addActionListener(this);
		// Add Action Listener to Edit button
		btEdit.addActionListener(this);
		// Add Action Listener to Delete button
		btDelete.addActionListener(this);
		// Add Action Listener to Cancel button
		btCancel.addActionListener(this);

		cboGoods2.addKeyListener(this);
		cboGoods2.addActionListener(this);
		// Add List Selection Listener to table
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(this);
		table.setFont(GlassShop.FONT);
		table.addMouseListener(this);
		table.getTableHeader().setFont(GlassShop.FONT);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
	}

	private void onload() {
		updateTable();
		CatchDataFrame cd = new CatchDataFrame();
		cboGoods = cd.GoodsTypeCombobox();
		cboGoods2 = cd.GoodsTypeCombobox();

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

		tfGoodsType.setPreferredSize(new Dimension(300, 20));
		tfGoodsType2.setPreferredSize(new Dimension(300, 20));
		cboGoods.setPreferredSize(new Dimension(300, 20));
		cboGoods2.setPreferredSize(new Dimension(300, 20));

		cboGoods.setFont(GlassShop.FONT);
		cboGoods2.setFont(GlassShop.FONT);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());

		topPanel.add(lbGoodsName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						20, 0, 0, 0), 0, 0));
		topPanel.add(cboGoods, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						20, 0, 0, 0), 0, 0));
		topPanel.add(lbGoodsType, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						20, 0, 0, 0), 0, 0));
		topPanel.add(tfGoodsType, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						20, 0, 0, 0), 0, 0));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(btAdd);
		buttonPanel.add(btEdit);
		buttonPanel.add(btDelete);
		buttonPanel.add(btCancel);
		topPanel.add(buttonPanel, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(
						20, 0, 0, 0), 0, 0));

		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
		"เพิ่ม / แก้ไข / ลบข้อมูลชนิดสินค้า",0,0,GlassShop.FONT));

		JPanel buttomPanel = new JPanel();
		buttomPanel.setLayout(new GridBagLayout());
		buttomPanel.add(lbTableCount, new GridBagConstraints(3, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 130, 5, 0), 0, 0));
		buttomPanel.add(lbGoodsName2, new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 0, 0, 0), 0, 0));
		buttomPanel.add(cboGoods2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						20, 0, 0, 0), 0, 0));
		buttomPanel.add(lbGoodsType2, new GridBagConstraints(2, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 0, 0, 0), 0, 0));
		buttomPanel.add(tfGoodsType2, new GridBagConstraints(3, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(20, 0, 0, 0), 0, 0));

		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		buttomPanel.add(scrollPane, new GridBagConstraints(0, 2, 4, 1, 0.0,	0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,new Insets(0, 0, 0, 0), 0, 0));

		buttomPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
				"ค้นหาข้อมูลชนิดสินค้า",0,0,GlassShop.FONT));

		topPanel.setBackground(GlassShop.bgColor);
		buttonPanel.setBackground(GlassShop.bgColor);
		buttomPanel.setBackground(GlassShop.bgColor);
		add(topPanel, BorderLayout.NORTH);
		add(buttomPanel, BorderLayout.CENTER);

	}

	private void updateTable() {
		GoodsTypeDao goodsTypeDao = (GoodsTypeDao) GlassShop.CONTEXT
				.getBean("goodsTypeDAO");
		List<GoodsType> listGoodsType = goodsTypeDao.selectAll();

		tableModel.setRowCount(0);

		for (int i = 0; i < listGoodsType.size(); i++) {
			GoodsType goodsType = listGoodsType.get(i);

			if (goodsType != null) {
				Object[] dataValues = new String[columnNames.length];
				dataValues[0] = goodsType.getGoodstypeId();
				dataValues[1] = goodsType.getGoodsName();
				dataValues[2] = goodsType.getGoodstypeName();

				tableModel.addRow(dataValues);

			}

		}

		table = new JTable(tableModel);
		lbTableCount.setText("จำนวนรายการที่พบ " + listGoodsType.size() + " รายการ");
		validate();
	}

	private void updateFillterGoodsType(String goodsTypeName) {
		GoodsTypeDao goodsTypeDao = (GoodsTypeDao) GlassShop.CONTEXT.getBean("goodsTypeDAO");
		List<GoodsType> listGoodsType = goodsTypeDao.selectAll();

		tableModel.setRowCount(0);
		String val = ((CatchDataModel) cboGoods2.getSelectedItem()).name;
		log.info("val : " + val);
		for (int i = 0; i < listGoodsType.size(); i++) {
			GoodsType goodsType = listGoodsType.get(i);

			if (goodsType != null) {
				Object[] dataValues = null;
				if ((goodsTypeName != null && goodsTypeName.length() > 0) && (val.length()>0 && val != null)  ) {

					if (goodsType.getGoodstypeName().toUpperCase().contains(goodsTypeName.toUpperCase()) 
							&& (goodsType.getGoodsName().toUpperCase().startsWith(val))) {
						dataValues = new String[columnNames.length];
						dataValues[0] = goodsType.getGoodstypeId();
						dataValues[1] = goodsType.getGoodsName();
						dataValues[2] = goodsType.getGoodstypeName();
					}
					// }
				}else if (val != null && val.length() > 0){
					if (goodsType.getGoodsName().toUpperCase().startsWith(val)) {
						dataValues = new String[columnNames.length];
						dataValues[0] = goodsType.getGoodstypeId();
						dataValues[1] = goodsType.getGoodsName();
						dataValues[2] = goodsType.getGoodstypeName();
					}
				} else  if (goodsTypeName != null && goodsTypeName.length() > 0){
					if (goodsType.getGoodstypeName().toUpperCase().contains(goodsTypeName.toUpperCase())) {
						dataValues = new String[columnNames.length];
						dataValues[0] = goodsType.getGoodstypeId();
						dataValues[1] = goodsType.getGoodsName();
						dataValues[2] = goodsType.getGoodstypeName();
					}
				}
				if (dataValues != null)
					tableModel.addRow(dataValues);
			}

		}

		table = new JTable(tableModel);
		lbTableCount
				.setText("จำนวนรายการที่พบ " + listGoodsType.size() + " รายการ");
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
	
		updateFillterGoodsType(tfGoodsType2.getText().trim());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == cboGoods2){
			
			updateFillterGoodsType(tfGoodsType2.getText().trim());
			
		}else if (e.getSource() == btDelete) {
			if ("".equals(selectId))
				return;
			GoodsDao goodsDao = (GoodsDao) GlassShop.CONTEXT
					.getBean("goodsDAO");
			goodsDao.delete(selectId);
			updateTable();

		} else if (e.getSource() == btEdit) {
			if ("".equals(selectId))
				return;

			GoodsTypeDao goodsTypeDao = (GoodsTypeDao) GlassShop.CONTEXT.getBean("goodsTypeDAO");
			GoodsType goodsType = goodsTypeDao.selectById(selectId);
			goodsType.setGoodsId(((CatchDataModel) cboGoods.getSelectedItem()).value);
			goodsType.setGoodstypeName(tfGoodsType.getText());
			goodsTypeDao.update(goodsType);
			updateTable();

		} else if (e.getSource() == btAdd) {
			log.info(" " + ((CatchDataModel) cboGoods.getSelectedItem()).value);
			if ("".equals(tfGoodsType.getText())) return;
			GoodsTypeDao goodsTypeDao = (GoodsTypeDao) GlassShop.CONTEXT.getBean("goodsTypeDAO");
			GoodsType goodsType = new GoodsType();
			goodsType.setGoodsId(((CatchDataModel) cboGoods.getSelectedItem()).value);
			goodsType.setGoodstypeName(tfGoodsType.getText());
			goodsTypeDao.insert(goodsType);
			updateTable();

		} else if (e.getSource() == btCancel) {
			selectId = "";
		}

		table.clearSelection();
		tfGoodsType.setText("");
		
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
			JTable target = (JTable) e.getSource();
			String goodsId = null;
			String goodsType = null;
			String goodsName = null;
			int row = target.getSelectedRow();

			goodsId = (String) table.getValueAt(row, 0);
			goodsType = (String) table.getValueAt(row, 1);
			goodsName = (String) table.getValueAt(row, 2);
			String t = ((CatchDataModel) cboGoods.getSelectedItem()).name;
			for (int i=0; i<cboGoods.getItemCount();i++){
				if(((CatchDataModel) cboGoods.getItemAt(i)).name.equals(goodsType)){
					cboGoods.setSelectedIndex(i);
				}
				
			}
			tfGoodsType.setText(goodsName);

			setPanelMode(Mode.EDIT);

			selectId = goodsId;
		}

	}
}
