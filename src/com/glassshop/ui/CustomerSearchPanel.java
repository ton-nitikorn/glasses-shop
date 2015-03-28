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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import com.glassshop.common.Constanst;
import com.glassshop.dao.CustomerDao;
import com.glassshop.dao.CustomerSightDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.Customer;
import com.glassshop.model.CustomerSight;
import com.glassshop.model.CustomerUpdate;
import com.glassshop.report.common.Reporter;
import com.glassshop.report.model.CustmerAddressReportModel;

public class CustomerSearchPanel extends JPanel implements 
		ListSelectionListener, ActionListener, KeyListener, MouseListener {
	static Logger log = Logger.getLogger(CustomerSearchPanel.class.getName());
	public String MENU_ID = "1_2";
	
	GlassShopFrame glassShopFrame;
	
	private JLabel lbCustNo = new JLabel("รหัสลูกค้า");
	private JTextField tfCustNo = new JTextField();
	private JLabel lbCustName = new JLabel("ชื่อ");
	private JTextField tfCustName = new JTextField();
	private JLabel lbCustLastName = new JLabel("นามสกุล");
	private JTextField tfCustLastName = new JTextField();
	private JLabel lbCustPlace = new JLabel("สถานที่");
	private JTextField tfCustPlace = new JTextField();
	private JLabel lbCustAddress = new JLabel("เลขที่");
	private JTextField tfCustAddress = new JTextField();
	private JLabel lbCustSoi = new JLabel("ตรอก/ซอย");
	private JTextField tfCustSoi = new JTextField();
	private JLabel lbCustRoad = new JLabel("ถนน");
	private JTextField tfCustRoad = new JTextField();
	private JLabel lbCustDistrict = new JLabel("ตำบล/แขวง");
	private JTextField tfCustDistrict = new JTextField();
	private JLabel lbCustCity = new JLabel("อำเภอ/เขต");
	private JTextField tfCustCity = new JTextField();
	private JLabel lbCustProvince = new JLabel("จังหวัด");
	private JTextField tfCustProvince = new JTextField();
	private JLabel lbCustZipcode = new JLabel("รหัสไปรษณีย์");
	private JTextField tfCustZipcode = new JTextField();
	private JLabel lbCustTel = new JLabel("โทรศัพท์บ้าน");
	private JTextField tfCustTel = new JTextField();
	private JLabel lbCustMobile = new JLabel("โทรศัพท์มือถือ");
	private JTextField tfCustMobile = new JTextField();
	private JLabel lbCustTarget = new JLabel("กลุ่มเป้าหมาย");
	private JTextField tfCustTarget = new JTextField();
	private JButton btShowData = new JButton("แสดงข้อมูล");
	private JButton btPrint = new JButton("พิมพ์ชื่อ - ที่อยู่ลูกค้า");
	private JButton btPrintBackup = new JButton("พิมพ์สำรองข้อมูลลูกค้า");
	private JLabel lbcboSearch = new JLabel("ค้นหาตาม");
	private JLabel lbcboBirthday = new JLabel("เดือนเกิด");
	private JLabel lbLensType = new JLabel("ชนิด");
	private JTextField tfLensType = new JTextField();
	
	//setting eyesight and lens
	private JLabel lbSightDate = new JLabel("วันที่ล่าสุด");
	final JDatePickerImpl sightDatePicker = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JLabel lbSightAppoint = new JLabel("นัดครั้งต่อไป");
	final JDatePickerImpl sightAppointPicker = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JLabel lbR = new JLabel("R");
	private JLabel lbL = new JLabel("L");
	private JLabel lbSph = new JLabel("SPH.");
	private JLabel lbCyl = new JLabel("CYL.");
	private JLabel lbAxis = new JLabel("AXIS.");
	private JLabel lbAdd = new JLabel("Add.");
	private JLabel lbVa = new JLabel("V.A.");
	private JLabel lbPrism = new JLabel("PRISM");
	private JLabel lbBase = new JLabel("BASE");
	private JTextField tfRSph = new JTextField();
	private JTextField tfRCyl = new JTextField();
	private JTextField tfRAxis = new JTextField();
	private JTextField tfRAdd = new JTextField();
	private JTextField tfRVa = new JTextField();
	private JTextField tfRPrism = new JTextField();
	private JTextField tfRBase  = new JTextField();
	private JTextField tfLSph = new JTextField();
	private JTextField tfLCyl = new JTextField();
	private JTextField tfLAxis = new JTextField();
	private JTextField tfLAdd = new JTextField();
	private JTextField tfLVa = new JTextField();
	private JTextField tfLPrism = new JTextField();
	private JTextField tfLBase  = new JTextField();
	private JLabel lbPd = new JLabel("P.D.");
	private JTextField tfPd = new JTextField();
	private JLabel lbSegh = new JLabel("Seg.H.");
	private JTextField tfSegh = new JTextField();
	private JLabel lbSingle = new JLabel("Single");
	private JTextField tfSingle = new JTextField();
	private JLabel lbBifocal = new JLabel("Bifocal");
	private JTextField tfBifocal = new JTextField();
	private JLabel lbProgress = new JLabel("Progress");
	private JTextField tfProgress = new JTextField();
	private JLabel lbLens = new JLabel("เลนส์");
	private JTextField tfLens = new JTextField();
	private JLabel lbColor = new JLabel("สี");
	private JTextField tfColor = new JTextField();
	private JLabel lbBorder = new JLabel("กรอบ");
	private JTextField tfBorder = new JTextField();
	private JLabel lbType = new JLabel("ชนิด");
	private JTextField tfType = new JTextField();
	private JLabel lbRemark = new JLabel("หมายเหตุ");
	private JTextField tfRemark = new JTextField();
	
	private JCheckBox chkCustNews = new JCheckBox("เฉพาะลูกค้าต้องการรับข่าวสาร");
	private JCheckBox chkTitleName = new JCheckBox("พิมพ์คำนำหน้านาม");
	
	private JComboBox cboSearch = new JComboBox();
	private JComboBox cboBirthday = new JComboBox();
	private JLabel lbFromDate = new JLabel("ตั้งแต่วันที่");
	final JDatePickerImpl datePicker1 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JLabel lbToDate = new JLabel("จนถึงวันที่");
	final JDatePickerImpl datePicker2 = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JLabel lbRecordDate = new JLabel("วันที่สมัคร");
	final JDatePickerImpl recDatePicker = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JLabel lbBirthDate = new JLabel("วันเกิด");
	final JDatePickerImpl birthDatePicker = (JDatePickerImpl) JDateComponentFactory.createJDatePicker();
	private JCheckBox chkBrithDate = new JCheckBox("บันทึกวันเกิด");
	private JButton btAdd = new JButton("เพิ่มข้อมูล");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขข้อมูล");
	private JButton btDelete = new JButton("ลบข้อมูล");
	private JButton btSearch = new JButton("ค้นหาข้อมูล");
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	private JTable tableEyeSight;
	private JCheckBox cbSelectAll = new JCheckBox("เลือกทั้งหมด");
	
	// Create columns names
	private String columnNames[] = { "","รหัสลูกค้า", "วันที่สมัคร", "ชื่อ", "นามสกุล",
			"สถานที่", "เลขที่", "ตรอก/ซอย", "ถนน", "ตำบล/แขวง", "อำเภอ/เขต",
			"จังหวัด", "รหัสไปรษณีย์", "โทรศัพท์บ้าน", "โทรศัพท์มือถือ",
			"วันเกิด", "กลุ่มเป้าหมาย", "Lens Type","News" ,""};
	
	
	private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	// Create data table
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private enum Mode {
		EDIT, ADD
	};

	// private Mode mode;

	private String selectId = "";

	public CustomerSearchPanel(String menuId, GlassShopFrame glassShopFrame, CustomerUpdate cm, Customer custVal) {
		this.glassShopFrame = glassShopFrame;
		MENU_ID = menuId;
		onload(custVal);
		buildScreen();
		
	}

	private void onload(Customer custVal) {
		
		CatchDataFrame cd = new CatchDataFrame();
		cboSearch = cd.SearchTypeCombobox();
		cboBirthday = cd.MonthsCombobox();
		
		tfCustNo.addKeyListener(this);
		tfCustName.addKeyListener(this);
		tfCustLastName.addKeyListener(this);
		tfCustPlace.addKeyListener(this);
		tfCustAddress.addKeyListener(this);
		tfCustSoi.addKeyListener(this);
		tfCustRoad.addKeyListener(this);
		tfCustDistrict.addKeyListener(this);
		tfCustCity.addKeyListener(this);
		tfCustProvince.addKeyListener(this);
		tfCustZipcode.addKeyListener(this);
		tfCustTel.addKeyListener(this);
		tfCustMobile.addKeyListener(this);
		tfCustTarget.addKeyListener(this);
		cboSearch.addKeyListener(this);
		tfLensType.addKeyListener(this);
		
		datePicker1.addKeyListener(this);
		datePicker2.addKeyListener(this);
		
		cboSearch.addKeyListener(this);
		cboBirthday.addKeyListener(this);
		
		datePicker1.setFont(GlassShop.FONT);
		datePicker2.setFont(GlassShop.FONT);
		chkCustNews.setFont(GlassShop.FONT);
		chkBrithDate.setFont(GlassShop.FONT);
		chkTitleName.setFont(GlassShop.FONT);
		cbSelectAll.setFont(GlassShop.FONT);
		cboSearch.setFont(GlassShop.FONT);
		cboBirthday.setFont(GlassShop.FONT);
		datePicker1.getModel().setSelected(true);
		datePicker2.getModel().setSelected(true);
		recDatePicker.getModel().setSelected(true);
		birthDatePicker.getModel().setSelected(true);
		sightDatePicker.getModel().setSelected(true);
		sightAppointPicker.getModel().setSelected(true);
		
		chkCustNews.setBackground(GlassShop.bgColor);
		chkBrithDate.setBackground(GlassShop.bgColor);
		chkTitleName.setBackground(GlassShop.bgColor);
		cbSelectAll.setBackground(GlassShop.bgColor);
		// Add List Selection Listener to table
		btShowData.addActionListener(this);
		btPrint.addActionListener(this);
		btPrintBackup.addActionListener(this);
		
		cbSelectAll.addActionListener(this);
		btAdd.addActionListener(this);
		
//		if (null != custVal){
//			setTextbox(custVal);
//		}else{
		updateTable();
//		}
		ListSelectionModel cellSelectionModel = null;
		cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(this);
		table.setFont(GlassShop.FONT);
		table.addMouseListener(this);
		table.getTableHeader().setFont(GlassShop.FONT);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		
	}
	private void createTextbox(){
		tfCustNo.setPreferredSize(new Dimension(150,20));
		tfCustName.setPreferredSize(new Dimension(300, 20));
		tfCustLastName.setPreferredSize(new Dimension(300,20));
		tfCustPlace.setPreferredSize(new Dimension(300,20));
		tfCustAddress.setPreferredSize(new Dimension(150,20));
		tfCustSoi.setPreferredSize(new Dimension(150,20));
		tfCustRoad.setPreferredSize(new Dimension(300,20));
		tfCustDistrict.setPreferredSize(new Dimension(300,20));
		tfCustCity.setPreferredSize(new Dimension(300,20));
		tfCustProvince.setPreferredSize(new Dimension(150,20));
		tfCustZipcode.setPreferredSize(new Dimension(150,20));
		tfCustTel.setPreferredSize(new Dimension(300,20));
		tfCustMobile.setPreferredSize(new Dimension(300,20));
		tfCustTarget.setPreferredSize(new Dimension(300,20));
		cboSearch.setPreferredSize(new Dimension(300,20));
		tfLensType.setPreferredSize(new Dimension(100,20));
	}
	public void buildScreen() {
		
		setLayout(new BorderLayout(0, 0));
		createTextbox();
		
			JPanel topPanel = new JPanel();
			
//			tempPanel.add(topPanel, new GridBagConstraints(0, 1, 8, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.setLayout(new GridBagLayout());
			
			topPanel.add(lbCustNo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustNo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			
			topPanel.add(lbCustName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustLastName, new GridBagConstraints(2, 1, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustLastName, new GridBagConstraints(3, 1, 3, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustPlace, new GridBagConstraints(0, 2, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustPlace, new GridBagConstraints(1, 2, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustAddress, new GridBagConstraints(2, 2, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustAddress, new GridBagConstraints(3, 2, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustSoi, new GridBagConstraints(4, 2, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustSoi, new GridBagConstraints(5, 2, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustRoad, new GridBagConstraints(0, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustRoad, new GridBagConstraints(1, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustDistrict, new GridBagConstraints(2, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustDistrict, new GridBagConstraints(3, 3, 3, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustCity, new GridBagConstraints(0, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustCity, new GridBagConstraints(1, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustProvince, new GridBagConstraints(2, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustProvince, new GridBagConstraints(3, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustZipcode, new GridBagConstraints(4, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustZipcode, new GridBagConstraints(5, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustTel, new GridBagConstraints(0, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustTel, new GridBagConstraints(1, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustMobile, new GridBagConstraints(2, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustMobile, new GridBagConstraints(3, 5, 3, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(lbCustTarget, new GridBagConstraints(0, 6, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
			topPanel.add(tfCustTarget, new GridBagConstraints(1, 6, 5, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 5, 0), 0, 0));
			
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		if(!"1_1".equalsIgnoreCase(MENU_ID)){
			buttonPanel.add(lbTableCount);
			topPanel.add(buttonPanel, new GridBagConstraints(0, 7, 6, 1, 0.0, 0.0,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
			topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
					"ค้นหาตามข้อมูลส่วนตัว",0,0,GlassShop.FONT));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablePanel.add(scrollPane,BorderLayout.CENTER);
		
		
		add(topPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		topPanel.setBackground(GlassShop.bgColor);
		buttonPanel.setBackground(GlassShop.bgColor);
		tablePanel.setBackground(GlassShop.bgColor);
	}

	private void updateTable() {
		CustomerDao customerDAO = (CustomerDao) GlassShop.CONTEXT
				.getBean("customerDAO");
		List<Customer> listCust = customerDAO.selectAll();
		
		tableModel.setRowCount(0);
		for (int i = 0; i < listCust.size(); i++) {
			Customer customer = listCust.get(i);
			
			if (customer != null) {
				Object[] dataValues = dataValuesTable(customer);
				tableModel.addRow(dataValues);
			}

		}
		
		table = new JTable(tableModel) {

            private static final long serialVersionUID = 1L;

            private boolean editable;  
            public void setEditable(boolean editable) { this.editable = editable; }            
            @Override  
            public boolean isCellEditable(int row, int col) { return editable; } 
        };
		lbTableCount.setText("จำนวนรายการที่พบ " + listCust.size() + " รายการ");
		hideColumnTable();
		validate();
	}
	private void hideColumnTable(){
		table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(2).setMinWidth(80); //Record date
        table.getColumnModel().getColumn(2).setMaxWidth(80);
        table.getColumnModel().getColumn(3).setMinWidth(120); //name
        table.getColumnModel().getColumn(3).setMaxWidth(120);
        table.getColumnModel().getColumn(4).setMinWidth(150); //Last name
        table.getColumnModel().getColumn(4).setMaxWidth(150);
        table.getColumnModel().getColumn(17).setMinWidth(0);
        table.getColumnModel().getColumn(17).setMaxWidth(0);
        table.getColumnModel().getColumn(18).setMinWidth(0);
        table.getColumnModel().getColumn(18).setMaxWidth(0);
        table.getColumnModel().getColumn(19).setMinWidth(0);
        table.getColumnModel().getColumn(19).setMaxWidth(0);
        table.setDragEnabled(false);
	}
	private void setPanelMode(Mode mode){
		if(mode == Mode.ADD){
			btAdd.setVisible(true);
			btCancel.setVisible(true);
			btEdit.setVisible(false);
			btDelete.setVisible(false);
			createTextbox();
		}else if(mode == Mode.EDIT){
			
			btAdd.setVisible(false);
			btCancel.setVisible(true);
			btEdit.setVisible(true);
			btDelete.setVisible(true);
		}
	}
	private void updateFillterCustomer(String custNo, String custName, String custSurname, String custPlace
			, String custAddress, String custSoi, String custRoad, String custDistric, String custCity
			, String custProvince, String custZipCode, String custTel, String custMobile, String lensType, String custTarget ) {
		
		String fromDt = "";
        String toDt = "";
        String titleName = "";
        String custNews = "";
        String monthBirthday ="";
        String searchType = "";
        	
//		if (MENU_ID.equals("4_1")){
//			fromDt = CatchDataFrame.PickerDat2String(datePicker1);
//	        toDt =  CatchDataFrame.PickerDat2String(datePicker2);
//	        titleName = "False";
//	        custNews = "False";
//	        monthBirthday = ((CatchDataModel)cboBirthday.getSelectedItem()).value;
//	        searchType = ((CatchDataModel)cboSearch.getSelectedItem()).value;
//	        if (chkTitleName.isSelected()){
//	       	 	titleName = "true";
//	        }
//	        
//	        if (chkCustNews.isSelected()){
//	        	custNews = "true";
//	        }
//		}
		
        
		CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
		Vector vcCustomer = customerDao.selectFillterCustomer(custNo, custName, custSurname, custPlace
				, custAddress, custSoi, custRoad, custDistric, custCity	, custProvince, custZipCode
				, custTel, custMobile, custTarget, custNews, lensType,  monthBirthday,searchType, fromDt,toDt);
		tableModel.setRowCount(0);
		for (int i=0; i<vcCustomer.size();i++){
			Customer customer = (Customer)vcCustomer.get(i);
			if (customer != null) {
				Object[] dataValues = null;
				
				dataValues = dataValuesTable(customer);
				tableModel.addRow(dataValues);
			}
		}
		table = new JTable(tableModel);
		table.setSelectionBackground(GlassShop.bgColor);
		lbTableCount.setText("จำนวนรายการที่พบ " + vcCustomer.size() + " รายการ");
		hideColumnTable();
		validate();
		
	}
	
	private Object[] dataValuesTable(Customer customer){
		
		Object[] dataValues = new String[columnNames.length];
		dataValues = new String[columnNames.length];
		dataValues[0] = null;
		dataValues[1] = customer.getCustNo();
		if (null!=customer.getCustRecordDate() && !customer.getCustRecordDate().equals("")){
			dataValues[2] = CatchDataFrame.SQLDate2String(customer.getCustRecordDate().toString());
		}else{
			dataValues[2] = "";
		}
		
		dataValues[3] = customer.getCustName();
		dataValues[4] = customer.getCustSurname();
		dataValues[5] = customer.getCustPlace();
		dataValues[6] = customer.getCustAddress();
		dataValues[7] = customer.getCustSoi();
		dataValues[8] = customer.getCustRoad();
		dataValues[9] = customer.getCustDistrict();
		dataValues[10] = customer.getCustCity();
		dataValues[11] = customer.getCustProvince();
		dataValues[12] = customer.getCustZipcode();
		dataValues[13] = customer.getCustTel();
		dataValues[14] = customer.getCustMobile();
		if (null!=customer.getCustBirthday() && !customer.getCustBirthday().equals("")){
			dataValues[15] = CatchDataFrame.SQLDate2String(customer.getCustBirthday().toString());
		}else{
			dataValues[15] ="";
		}
		dataValues[16] = customer.getCustTarget();
		dataValues[17] = customer.getLensType();
		dataValues[18] = customer.getCustNews();
		dataValues[19] = customer.getCustId().toString();
		return dataValues;
	}
	
	private static String selectCustNo(){
		CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
		String cust_no = customerDao.selectCustNo();
		return cust_no;
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
		if (MENU_ID.equals("1_2")){
			updateFillterCustomer(tfCustNo.getText().trim(),tfCustName.getText().trim(),tfCustLastName.getText().trim(),tfCustPlace.getText().trim()
					,tfCustAddress.getText().trim(),tfCustSoi.getText().trim(),tfCustRoad.getText().trim(),tfCustDistrict.getText().trim()
					,tfCustCity.getText().trim(),tfCustProvince.getText().trim(),tfCustZipcode.getText().trim(),tfCustTel.getText().trim()
					,tfCustMobile.getText().trim(),tfLensType.getText().trim(), tfCustTarget.getText().trim());

		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btShowData) {
			
			updateFillterCustomer(tfCustNo.getText().trim(),tfCustName.getText().trim(),tfCustLastName.getText().trim(),tfCustPlace.getText().trim()
					,tfCustAddress.getText().trim(),tfCustSoi.getText().trim(),tfCustRoad.getText().trim(),tfCustDistrict.getText().trim()
					,tfCustCity.getText().trim(),tfCustProvince.getText().trim(),tfCustZipcode.getText().trim(),tfCustTel.getText().trim()
					,tfCustMobile.getText().trim(),tfLensType.getText().trim(), tfCustTarget.getText().trim());

		}else if(e.getSource() == btPrintBackup){
			CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
			List<Customer> listCustomer = customerDao.selectPrintReportAll();
			
			Reporter reporter = new Reporter(GlassShop.prop.getProperty(Constanst.REPORT_TEMPLATE_CUSTOMER_BACKUP),listCustomer);
			try {
				reporter.printAndPreview(GlassShop.prop.getProperty(Constanst.REPORT_NAME_CUSTOMER_BACKUP));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else if(e.getSource() == btPrint){      
			int rowCount = table.getRowCount();
			List<CustmerAddressReportModel> listCustomer = new ArrayList<CustmerAddressReportModel>();

			String prefix = "";
			if(chkTitleName.isSelected()) prefix="คุณ";
			
			for(int row=0; row<rowCount; row++){
				Boolean selected = (Boolean) table.getValueAt(row,0);
				if(selected != null && selected){
					String custNo = (String)table.getValueAt(row,1);
					String custName = (String)table.getValueAt(row,3);
					String custSurname = (String)table.getValueAt(row,4);
					String custPlace = (String)table.getValueAt(row,5);
					String custAddress = (String)table.getValueAt(row,6);
					String custSoi = (String)table.getValueAt(row,7);
					String custRoad = (String)table.getValueAt(row,8);
					String custDistrict = (String)table.getValueAt(row,9);
					String custCity = (String)table.getValueAt(row,10);
					String custProvince = (String)table.getValueAt(row,11);
					String custZipcode = (String)table.getValueAt(row,12);
					String lensType = (String)table.getValueAt(row,17);
					
					CustmerAddressReportModel reportModel = new CustmerAddressReportModel();
					reportModel.setFullName(prefix+" "+custName+" "+custSurname);
					reportModel.setPlace(custPlace);
					reportModel.setAddressLine1(custAddress+" "+custSoi+" "+custRoad);
					reportModel.setAddressLine2(custDistrict+" "+custCity);
					reportModel.setAddressLine3(custProvince+" "+custZipcode);
					reportModel.setCustNum(custNo);
					reportModel.setLensType(lensType);
					
					listCustomer.add(reportModel);
				}
			}
			
			if(listCustomer.size() > 0){
				Reporter reporter = new Reporter(GlassShop.prop.getProperty(Constanst.REPORT_TEMPLATE_CUSTOMER_ADDRESS),listCustomer);
				try {
					reporter.printAndPreview(GlassShop.prop.getProperty(Constanst.REPORT_NAME_CUSTOMER_ADDRESS));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
//		}  else if (e.getSource() == btAdd) {
//			CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
//			Customer customer = customerValue();
//			CustomerSight custSight = customerSightValue();
//			
//			customerDao.insertCustomerAndSight(customer, custSight);
//			CustomerSearchPanel panel = new CustomerSearchPanel("1_2", glassShopFrame, null);
//			glassShopFrame.changePanel(panel);
//			log.info("success");
//			
		}else if(e.getSource()==cbSelectAll){	
			int rowCount = table.getRowCount();
			for(int row=0; row<rowCount; row++){
				if(cbSelectAll.isSelected()){
					table.setValueAt(new Boolean(true), row, 0);
				}else{
					table.setValueAt(new Boolean(false), row, 0);
				}
			}
			
		}

		table.clearSelection();
		revalidate();

	}
	private Customer customerValue(){
		
		Customer customer = new Customer();
		customer.setCustNo(tfCustNo.getText());
		customer.setCustRecordDate(CatchDataFrame.PickerDat2String(recDatePicker));
		if (chkCustNews.isSelected()){
			customer.setCustNews("True");
		}else{
			customer.setCustNews("False");
		}
		customer.setCustName(tfCustName.getText());
		customer.setCustSurname(tfCustLastName.getText());
		customer.setCustPlace(tfCustPlace.getText());
		customer.setCustAddress(tfCustAddress.getText());
		customer.setCustSoi(tfCustSoi.getText());
		customer.setCustRoad(tfCustRoad.getText());
		customer.setCustDistrict(tfCustDistrict.getText());
		customer.setCustCity(tfCustCity.getText());
		customer.setCustProvince(tfCustProvince.getText());
		customer.setCustZipcode(tfCustZipcode.getText());
		customer.setCustTel(tfCustTel.getText());
		customer.setCustMobile(tfCustMobile.getText());
		if (chkBrithDate.isSelected()){
			customer.setCustBirthday(CatchDataFrame.PickerDat2String(birthDatePicker));
		}else{
			customer.setCustBirthday("09/09/9999");
		}
		
		customer.setCustTarget(tfCustTarget.getText());
		
		return customer;
	}
	private CustomerSight customerSightValue(){
		CustomerSight custSight = null;
		if ((tfRSph.getText()!=null & !tfRSph.getText().equals("")) || (tfRCyl.getText()!=null & !tfRCyl.getText().equals(""))
				||(tfRAxis.getText()!=null & !tfRAxis.getText().equals("")) ||(tfRAdd.getText()!=null & !tfRAdd.getText().equals(""))
				||(tfRVa.getText()!=null & !tfRVa.getText().equals(""))||(tfRPrism.getText()!=null & !tfRPrism.getText().equals(""))
				||(tfRBase.getText()!=null & !tfRBase.getText().equals(""))||(tfLSph.getText()!=null & !tfLSph.getText().equals(""))
				||(tfLCyl.getText()!=null & !tfLCyl.getText().equals(""))||(tfLAxis.getText()!=null & !tfLAxis.getText().equals(""))
				||(tfLAdd.getText()!=null & !tfLAdd.getText().equals(""))||(tfLVa.getText()!=null & !tfLVa.getText().equals(""))
				||(tfLPrism.getText()!=null & !tfLPrism.getText().equals(""))||(tfLBase.getText()!=null & !tfLBase.getText().equals(""))
				||(tfPd.getText()!=null & !tfPd.getText().equals(""))||(tfSegh.getText()!=null & !tfSegh.getText().equals(""))
				||(tfSingle.getText()!=null & !tfSingle.getText().equals(""))||(tfBifocal.getText()!=null & !tfBifocal.getText().equals(""))
				||(tfProgress.getText()!=null & !tfProgress.getText().equals(""))||(tfLens.getText()!=null & !tfLens.getText().equals(""))
				||(tfColor.getText()!=null & !tfColor.getText().equals(""))||(tfBorder.getText()!=null & !tfBorder.getText().equals(""))
				||(tfRemark.getText()!=null & !tfRemark.getText().equals(""))||(tfType.getText()!=null & !tfType.getText().equals(""))){
			custSight = new CustomerSight();
			custSight.setSightDate(CatchDataFrame.PickerDat2String(sightDatePicker));
			custSight.setSightAppoint(CatchDataFrame.PickerDat2String(sightAppointPicker));
			custSight.setSightRSph(tfRSph.getText());
			custSight.setSightRCyl(tfRCyl.getText());
			custSight.setSightRAxis(tfRAxis.getText());
			custSight.setSightRAdd(tfRAdd.getText());
			custSight.setSightRVa(tfRVa.getText());
			custSight.setSightRPrism(tfRPrism.getText());
			custSight.setSightRBase(tfRBase.getText());
			custSight.setSightLSph(tfLSph.getText());
			custSight.setSightLCyl(tfLCyl.getText());
			custSight.setSightLAxis(tfLAxis.getText());
			custSight.setSightLAdd(tfLAdd.getText());
			custSight.setSightLVa(tfLVa.getText());
			custSight.setSightLPrism(tfLPrism.getText());
			custSight.setSightLBase(tfLBase.getText());
			custSight.setSightPd(tfPd.getText());
			custSight.setSightSegsh(tfSegh.getText());
			custSight.setLensSingle(tfSingle.getText());
			custSight.setLensBifocal(tfBifocal.getText());
			custSight.setLensProgress(tfProgress.getText());
			custSight.setLensLensType(tfLens.getText());
			custSight.setLensColor(tfColor.getText());
			custSight.setLensFrame(tfBorder.getText());
			custSight.setLensNote(tfRemark.getText());
			custSight.setLensType(tfType.getText());
			custSight.setSightFrequency("1");
		}
		
		
		return custSight;
	}
	
	private CustomerUpdate tableValue(JTable target){
		int row = target.getSelectedRow();
		String custId = (String)table.getValueAt(row,19);
		String custNo = (String)table.getValueAt(row,1);
		String custRecordDate = (String)table.getValueAt(row,2);
		String custName = (String)table.getValueAt(row,3);
		String custSurname = (String)table.getValueAt(row,4);
		String custPlace = (String)table.getValueAt(row,5);
		String custAddress = (String)table.getValueAt(row,6);
		String custSoi = (String)table.getValueAt(row,7);
		String custRoad = (String)table.getValueAt(row,8);
		String custDistrict = (String)table.getValueAt(row,9);
		String custCity = (String)table.getValueAt(row,10);
		String custProvince = (String)table.getValueAt(row,11);
		String custZipcode = (String)table.getValueAt(row,12);
		String custTel = (String)table.getValueAt(row,13);
		String custMobile = (String)table.getValueAt(row,14);
		String custBirthday = (String)table.getValueAt(row,15);
		String custTarget = (String)table.getValueAt(row,16);
		String lensType = (String)table.getValueAt(row,16);
		String custNews = (String)table.getValueAt(row,18);
		
		CustomerUpdate cm = new CustomerUpdate(custId, custNo, custRecordDate,
		custNews, custName, custSurname,
		custPlace, custAddress, custSoi,
		custRoad, custDistrict, custCity,
		custProvince, custZipcode, custTel,
		custMobile, custBirthday, custTarget,
		lensType);
		
		return cm;
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			//Disable for Edit mode by veena 2013-03-17
			JTable target = (JTable)e.getSource();   
			int row = target.getSelectedRow();
			
			CustomerUpdate cm = tableValue(target);
			Customer customerVal = customerValue();
			setPanelMode(Mode.EDIT);
	       
			CustomerPanel panel = new CustomerPanel("1_2",glassShopFrame, cm, customerVal);
			glassShopFrame.changePanel(panel);
			
		}
		
	}

	private void setTextbox(Customer cm){
		if (null!= cm){
			tfCustNo.setText(cm.getCustNo());
			tfCustName.setText(cm.getCustName());
			tfCustLastName.setText(cm.getCustSurname());
			tfCustPlace.setText(cm.getCustPlace());
			tfCustAddress.setText(cm.getCustAddress());
			tfCustSoi.setText(cm.getCustSoi());
			tfCustRoad.setText(cm.getCustRoad());
			tfCustDistrict.setText(cm.getCustDistrict());
			tfCustCity.setText(cm.getCustCity());
			tfCustProvince.setText(cm.getCustProvince());
			tfCustTel.setText(cm.getCustTel());
			tfCustMobile.setText(cm.getCustMobile());
			tfCustZipcode.setText(cm.getCustZipcode());
			tfCustTarget.setText(cm.getCustTarget());
		
			birthDatePicker.getModel().setDate(CatchDataFrame.StringYear2Int(cm.getCustBirthday())
					, CatchDataFrame.StringMonth2Int(cm.getCustBirthday())-1, CatchDataFrame.StringDay2Int(cm.getCustBirthday()));
		
			if (cm.getCustNews().equals("True")){
				chkCustNews.isSelected();
			}
			updateFillterCustomer(tfCustNo.getText().trim(),tfCustName.getText().trim(),tfCustLastName.getText().trim(),tfCustPlace.getText().trim()
					,tfCustAddress.getText().trim(),tfCustSoi.getText().trim(),tfCustRoad.getText().trim(),tfCustDistrict.getText().trim()
					,tfCustCity.getText().trim(),tfCustProvince.getText().trim(),tfCustZipcode.getText().trim(),tfCustTel.getText().trim()
					,tfCustMobile.getText().trim(),tfLensType.getText().trim(), tfCustTarget.getText().trim());
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
