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
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import com.glassshop.dao.CustomerDao;
import com.glassshop.dao.CustomerSightDao;
import com.glassshop.model.Customer;
import com.glassshop.model.CustomerSight;
import com.glassshop.model.CustomerUpdate;


public class CustomerPanel extends JPanel implements ListSelectionListener, ActionListener, KeyListener{

	static Logger log = Logger.getLogger(CustomerPanel.class.getName());
	
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
	
	private JTextField tfCustId = new JTextField();
	
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
	private JCheckBox chkTitleName = new JCheckBox("บันทึกนำหน้านาม");
	
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
	private JButton btAdd = new JButton("เพิ่มค่าข้อมูลสายตาและเลนส์");
	private JButton btCancel = new JButton("ยกเลิก");
	private JButton btEdit = new JButton("แก้ไขค่าข้อมูลสายตาและเลนส์");
	private JButton btDelete = new JButton("ลบค่าข้อมูลสายตาและเลนส์");
	private JButton btSearch = new JButton("ค้นหาข้อมูล");
	private JButton btSave = new JButton("บันทึก");
	
	private JButton btCancel1 = new JButton("ยกเลิก");
	private JButton btEdit1 = new JButton("แก้ไขข้อมูลลูกค้า");
	private JButton btDelete1 = new JButton("ลบข้อมูลลูกค้า");
	private JButton btSearch1 = new JButton("ค้นหาข้อมูล");
	
	private JLabel lbTableCount = new JLabel("จำนวนรายการที่พบ 0 รายการ");
	private JTable table;
	private JTable tableEyeSight;
	private JTable tableEyeType;
	private JCheckBox cbSelectAll = new JCheckBox("เลือกทั้งหมด");
	
	// Create columns names
	private String columnNames[] = { "","รหัสลูกค้า", "วันที่สมัคร", "ชื่อ", "นามสกุล",
			"สถานที่", "เลขที่", "ตรอก/ซอย", "ถนน", "ตำบล/แขวง", "อำเภอ/เขต",
			"จังหวัด", "รหัสไปรษณีย์", "โทรศัพท์บ้าน", "โทรศัพท์มือถือ",
			"วันเกิด", "กลุ่มเป้าหมาย", "Lens Type","News" ,""};
	
	private String columnNamesEyesight[] = { "วันที่","R_SPH","R_CYL","R_AXIS","R_ADD","R_V.A.",
			"R_PRISM","R_BASE","L_SPH","L_CYL","L_AXIS","L_ADD","L_V.A.","L_PRISM","L_BASE",
			"P.D.","Segs.H"};
	
	
	private String columnNamesEyetype[] = { "วันที่","Single","Bifocal","Progress","เลนส์","สี",
			"กรอบ","หมายเหตุ","ประเภท"};
	
	private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	private MyTableModel tableModelSight = new MyTableModel(columnNamesEyesight, 0);
	private MyTableModel tableModelType = new MyTableModel(columnNamesEyetype, 0);
	// Create data table
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1 = new JScrollPane(tableEyeSight);
	private JScrollPane scrollPane2 = new JScrollPane(tableEyeType);
	
	private enum Mode {EDIT,ADD};
//	private Mode mode;
	
	private String selectId = "";
	
	private String MENU_ID = "1_1";
	
	private CustomerUpdate custUdp = null;
	private Customer custVal = null;
	
	public CustomerPanel(String menuId, GlassShopFrame glassShopFrame, CustomerUpdate cm, Customer custVal){
		MENU_ID = menuId;
		this.glassShopFrame = glassShopFrame;
		this.custUdp = cm;
		this.custVal = custVal;
		onload(cm);
		buildScreen();
		
		
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
		chkBrithDate.setSelected(true);
		chkBrithDate.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			if (chkBrithDate.isSelected()){
				lbBirthDate.setVisible(true);
				birthDatePicker.setVisible(true);
			}else{
				lbBirthDate.setVisible(false);
				birthDatePicker.setVisible(false);
			}
		} });
		
		
		datePicker1.getModel().setSelected(true);
		datePicker2.getModel().setSelected(true);
		recDatePicker.getModel().setSelected(true);
		birthDatePicker.getModel().setSelected(true);
		sightDatePicker.getModel().setSelected(true);
		sightAppointPicker.getModel().setSelected(true);
		
		datePicker1.setTextEditable(true);
		datePicker2.setTextEditable(true);
		recDatePicker.setTextEditable(true);
		birthDatePicker.setTextEditable(true);
		sightDatePicker.setTextEditable(true);
		sightAppointPicker.setTextEditable(true);
		
		chkCustNews.setBackground(GlassShop.bgColor);
		chkBrithDate.setBackground(GlassShop.bgColor);
		chkTitleName.setBackground(GlassShop.bgColor);
		cbSelectAll.setBackground(GlassShop.bgColor);
		// Add List Selection Listener to table
		btShowData.addActionListener(this);
		btPrint.addActionListener(this);
		btPrintBackup.addActionListener(this);
		
		cbSelectAll.addActionListener(this);
		
		//Add Action Listener to Edit button
		btAdd.addActionListener(this);
		//Add Action Listener to Edit button
		btEdit.addActionListener(this);
		//Add Action Listener to Delete button
		btDelete.addActionListener(this);
		//Add Action Listener to Cancel button
		btCancel.addActionListener(this);
		
		btEdit1.addActionListener(this);
		btDelete1.addActionListener(this);
		btCancel1.addActionListener(this);
		btSearch1.addActionListener(this);
		
		btSave.addActionListener(this);
		
		btSearch.addActionListener(this);
		
		tfCustName.addKeyListener(this);
		tfCustLastName.addKeyListener(this);
		
//		table.setAutoCreateRowSorter(true);
//		tableEyeSight.setAutoCreateRowSorter(true);
//		tableEyeType.setAutoCreateRowSorter(true);
		
	}
	private void onload( CustomerUpdate cm){
		log.info("[Start Customer Panel]");
		
		if(cm != null){
			
			setTextbox(cm);
			setPanelMode(Mode.EDIT);
		}else{
			tfCustNo.setText(selectCustNo());
			setPanelMode(Mode.ADD);
		}
		
	}
	public void buildScreen(){
		
		setLayout(new BorderLayout(0, 0));
		
		createTextbox();
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(lbCustNo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustNo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		topPanel.add(chkTitleName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustLastName, new GridBagConstraints(2, 2, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustLastName, new GridBagConstraints(3, 2, 3, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustPlace, new GridBagConstraints(0, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustPlace, new GridBagConstraints(1, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustAddress, new GridBagConstraints(2, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustAddress, new GridBagConstraints(3, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustSoi, new GridBagConstraints(4, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustSoi, new GridBagConstraints(5, 3, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustRoad, new GridBagConstraints(0, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustRoad, new GridBagConstraints(1, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustDistrict, new GridBagConstraints(2, 4, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustDistrict, new GridBagConstraints(3, 4, 3, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustCity, new GridBagConstraints(0, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustCity, new GridBagConstraints(1, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustProvince, new GridBagConstraints(2, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustProvince, new GridBagConstraints(3, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustZipcode, new GridBagConstraints(4, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustZipcode, new GridBagConstraints(5, 5, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustTel, new GridBagConstraints(0, 6, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustTel, new GridBagConstraints(1, 6, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustMobile, new GridBagConstraints(2, 6, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustMobile, new GridBagConstraints(3, 6, 3, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbCustTarget, new GridBagConstraints(0, 7, 1, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(tfCustTarget, new GridBagConstraints(1, 7, 5, 1, 0.0,0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 0, 5, 0), 0, 0));
		
		topPanel.add(lbRecordDate, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(recDatePicker, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		chkCustNews.setText("ต้องการรับข่าวสาร");
		topPanel.add(chkCustNews, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		topPanel.add(chkBrithDate, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(lbBirthDate, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		topPanel.add(birthDatePicker, new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		if (MENU_ID.equals("1_2")){
			JPanel AddButtonPanel = new JPanel();
			AddButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			AddButtonPanel.add(btEdit1);
			AddButtonPanel.add(btDelete1);
			AddButtonPanel.add(btCancel1);
			AddButtonPanel.add(btSearch1);
			
			topPanel.add(AddButtonPanel, new GridBagConstraints(0, 8, 5, 1, 0.0, 0.0,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			AddButtonPanel.setBackground(GlassShop.bgColor);
		}
//		tfCustNo.setText(selectCustNo());
		
		topPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
		"ข้อมูลส่วนตัว",0,0,GlassShop.FONT));
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
		tempPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
				"ข้อมูลค่าสายตา และเลนส์",0,0,GlassShop.FONT));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new GridBagLayout());
		
		tempPanel.add(tablePanel);
		
		buildEyesightLensScreen();
		
		tablePanel.add(lbSph, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbCyl, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbAxis, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbAdd, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbVa, new GridBagConstraints(9, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbPrism, new GridBagConstraints(10, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbBase, new GridBagConstraints(11, 0, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbSightDate, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(sightDatePicker, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		
		
		tablePanel.add(lbR, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfRSph, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfRCyl, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfRAxis, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfRAdd, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfRVa, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfRPrism, new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfRBase, new GridBagConstraints(11, 1, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		
		tablePanel.add(lbSightAppoint, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(sightAppointPicker, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(btSave, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		
		tablePanel.add(lbL, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfLSph, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfLCyl, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfLAxis, new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfLAdd, new GridBagConstraints(8, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfLVa, new GridBagConstraints(9, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfLPrism, new GridBagConstraints(10, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfLBase, new GridBagConstraints(11, 2, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbPd, new GridBagConstraints(8, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfPd, new GridBagConstraints(9, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(lbSegh, new GridBagConstraints(10, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		tablePanel.add(tfSegh, new GridBagConstraints(11, 3, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		
		JPanel printTablePanel = new JPanel();
		printTablePanel.setLayout(new BorderLayout());
		
		tempPanel.add(printTablePanel);
		if (MENU_ID.equals("1_2")){
		
			scrollPane1 = new JScrollPane(tableEyeSight);
			scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			printTablePanel.add(scrollPane1,BorderLayout.CENTER);
		}
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		tempPanel.add(centerPanel);
		
		centerPanel.add(lbSingle, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfSingle, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(lbBifocal, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfBifocal, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(lbProgress, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfProgress, new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		
		centerPanel.add(lbLens, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfLens, new GridBagConstraints(1, 6, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(lbColor, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfColor, new GridBagConstraints(5, 6, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		
		centerPanel.add(lbBorder, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfBorder, new GridBagConstraints(1, 7, 5, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		
		centerPanel.add(lbType, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfType, new GridBagConstraints(1, 8, 5, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(lbRemark, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));
		centerPanel.add(tfRemark, new GridBagConstraints(3,8, 3, 1, 0.0, 0.0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 0));

		if (MENU_ID.equals("1_2")){
			scrollPane2 = new JScrollPane(tableEyeType);
			scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			tempPanel.add(scrollPane2);
		}
		JPanel printButtonPanel = new JPanel();
		printButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		printButtonPanel.add(btAdd);
		printButtonPanel.add(btEdit);
		printButtonPanel.add(btDelete);
		printButtonPanel.add(btCancel);
		printButtonPanel.add(btSearch);
		
		add(topPanel, BorderLayout.NORTH);
		add(tempPanel, BorderLayout.CENTER);
		add(printButtonPanel, BorderLayout.SOUTH);
		
		
		tablePanel.setBackground(GlassShop.bgColor);
		topPanel.setBackground(GlassShop.bgColor);
		printTablePanel.setBackground(GlassShop.bgColor);
		printButtonPanel.setBackground(GlassShop.bgColor);
		centerPanel.setBackground(GlassShop.bgColor);
		tempPanel.setBackground(GlassShop.bgColor);
	}
	private void createTextbox(){
		tfCustNo.setPreferredSize(new Dimension(150,20));
		tfCustName.setPreferredSize(new Dimension(300, 20));
		tfCustLastName.setPreferredSize(new Dimension(300,20));
		tfCustPlace.setPreferredSize(new Dimension(300,20));
		tfCustAddress.setPreferredSize(new Dimension(150,20));
		tfCustSoi.setPreferredSize(new Dimension(200,20));
		tfCustRoad.setPreferredSize(new Dimension(300,20));
		tfCustDistrict.setPreferredSize(new Dimension(300,20));
		tfCustCity.setPreferredSize(new Dimension(300,20));
		tfCustProvince.setPreferredSize(new Dimension(150,20));
		tfCustZipcode.setPreferredSize(new Dimension(200,20));
		tfCustTel.setPreferredSize(new Dimension(300,20));
		tfCustMobile.setPreferredSize(new Dimension(200,20));
		tfCustTarget.setPreferredSize(new Dimension(300,20));
		cboSearch.setPreferredSize(new Dimension(300,20));
		tfLensType.setPreferredSize(new Dimension(100,20));
	}
	private static String selectCustNo(){
		CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
		String cust_no = customerDao.selectCustNo();
		return cust_no;
	}
	public void buildEyesightLensScreen(){
		
		tfRSph.setPreferredSize(new Dimension(50,20));
		tfRCyl.setPreferredSize(new Dimension(50,20));
		tfRAxis.setPreferredSize(new Dimension(50,20));
		tfRAdd.setPreferredSize(new Dimension(50,20));
		tfRVa.setPreferredSize(new Dimension(50,20));
		tfRPrism.setPreferredSize(new Dimension(50,20));
		tfRBase.setPreferredSize(new Dimension(50,20));
		
		tfLSph.setPreferredSize(new Dimension(50,20));
		tfLCyl.setPreferredSize(new Dimension(50,20));
		tfLAxis.setPreferredSize(new Dimension(50,20));
		tfLAdd.setPreferredSize(new Dimension(50,20));
		tfLVa.setPreferredSize(new Dimension(50,20));
		tfLPrism.setPreferredSize(new Dimension(50,20));
		tfLBase.setPreferredSize(new Dimension(50,20));
		
		tfPd.setPreferredSize(new Dimension(50,20));
		tfSegh.setPreferredSize(new Dimension(50,20));
		
		tfSingle.setPreferredSize(new Dimension(150,20));
		tfBifocal.setPreferredSize(new Dimension(150,20));
		tfProgress.setPreferredSize(new Dimension(150,20));
		tfLens.setPreferredSize(new Dimension(300,20));
		tfColor.setPreferredSize(new Dimension(150,20));
		tfBorder.setPreferredSize(new Dimension(400,20));
		tfType.setPreferredSize(new Dimension(150,20));
		tfRemark.setPreferredSize(new Dimension(300,20));
		
	}
	private void setPanelMode(Mode mode){
		if(mode == Mode.ADD){
			btAdd.setVisible(true);
			btCancel.setVisible(true);
			btEdit.setVisible(false);
			btDelete.setVisible(false);
			btSave.setVisible(false);
			tfCustNo.setEnabled(false);
			scrollPane1.setVisible(false);
			scrollPane2.setVisible(false);
			sightAppointPicker.getModel().setDate(CatchDataFrame.PickerYear2Int(sightAppointPicker)+1
					, CatchDataFrame.PickerMonth2Int(sightAppointPicker)-1, CatchDataFrame.PickerDay2Int(sightAppointPicker));
		}else if(mode == Mode.EDIT){
			btAdd.setVisible(false);
			btCancel.setVisible(true);
			btEdit.setVisible(true);
			btDelete.setVisible(true);
			btSave.setVisible(true);
			tfCustNo.setEnabled(false);
			scrollPane1.setVisible(true);
			scrollPane2.setVisible(true);
			
		}
	}
	private Customer customerValue(){
		
		Customer customer = new Customer();
		customer.setCustId(tfCustId.getText());
		customer.setCustNo(tfCustNo.getText());
		customer.setCustRecordDate(CatchDataFrame.PickerDat2String(recDatePicker));
		if (chkCustNews.isSelected()){
			customer.setCustNews("True");
		}else{
			customer.setCustNews("False");
		}
		if (chkTitleName.isSelected()){
			customer.setCustTitle("คุณ");
		}else{
			customer.setCustTitle("");
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
	private void setTextbox(CustomerUpdate cm){
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
			updateTableEyeSight(cm.getCustId());
			updateTableEyeType(cm.getCustId());
			tfCustId.setText(cm.getCustId());
		}
	}
	private void updateTableEyeSight(String id) {
		CustomerSightDao customerSightDao = (CustomerSightDao) GlassShop.CONTEXT.getBean("customerSightDAO");
		List<CustomerSight> listCustSight = customerSightDao.selectByIdAll(id);

		tableModelSight.setRowCount(0);

		for (int i = 0; i < listCustSight.size(); i++) {
			CustomerSight custSight = listCustSight.get(i);

			if (custSight != null) {
				Object[] dataValues = dataValueEyeSight(custSight);
				tableModelSight.addRow(dataValues);
			}
		}
		tableEyeSight = new JTable(tableModelSight);
//		lbTableCount.setText("จำนวนรายการที่พบ " + listCustSight.size() + " รายการ");
		validate();
	}
	private void updateTableEyeType(String id) {
		CustomerSightDao customerSightDao = (CustomerSightDao) GlassShop.CONTEXT.getBean("customerSightDAO");
		List<CustomerSight> listCustSight = customerSightDao.selectByIdAll(id);

		tableModelType.setRowCount(0);

		for (int i = 0; i < listCustSight.size(); i++) {
			CustomerSight custSight = listCustSight.get(i);

			if (custSight != null) {
				if (i == 0)
					setEyesightTextbox(custSight);
				
				Object[] dataValues = dataValueEyeType(custSight);
				tableModelType.addRow(dataValues);
			}
		}
		
		tableEyeType = new JTable(tableModelType);
//		lbTableCount.setText("จำนวนรายการที่พบ " + listCustSight.size() + " รายการ");
		validate();
	}
	private Object[] dataValueEyeSight(CustomerSight cs){
		Object[] dataValues = new String[columnNamesEyesight.length];
		dataValues = new String[columnNamesEyesight.length];
		
		dataValues[0] = CatchDataFrame.SQLDate2String(cs.getSightDate());
		dataValues[1] = cs.getSightRSph();
		dataValues[2] = cs.getSightRCyl();
		dataValues[3] = cs.getSightRAxis();
		dataValues[4] = cs.getSightRAdd();
		dataValues[5] = cs.getSightRVa();
		dataValues[6] = cs.getSightRPrism();
		dataValues[7] = cs.getSightRBase();
		dataValues[8] = cs.getSightLSph();
		dataValues[9] = cs.getSightLCyl();
		dataValues[10] = cs.getSightLAxis();
		dataValues[11] = cs.getSightLAdd();
		dataValues[12] = cs.getSightLVa();
		dataValues[13] = cs.getSightLPrism();
		dataValues[14] = cs.getSightLBase();
		dataValues[15] = cs.getSightPd();
		dataValues[16] = cs.getSightSegsh();
		return dataValues;
		
	}
	private void setEyesightTextbox(CustomerSight cs){
		
		sightDatePicker.getModel().setDate(CatchDataFrame.StringYear2Int(CatchDataFrame.SQLDate2String(cs.getSightDate()))
				, CatchDataFrame.StringMonth2Int(CatchDataFrame.SQLDate2String(cs.getSightDate()))-1
				, CatchDataFrame.StringDay2Int(CatchDataFrame.SQLDate2String(cs.getSightDate())));
		
		sightAppointPicker.getModel().setDate(CatchDataFrame.StringYear2Int(CatchDataFrame.SQLDate2String(cs.getSightAppoint()))
				, CatchDataFrame.StringMonth2Int(CatchDataFrame.SQLDate2String(cs.getSightAppoint()))-1
				, CatchDataFrame.StringDay2Int(CatchDataFrame.SQLDate2String(cs.getSightAppoint())));
		
		tfRSph.setText(cs.getSightRSph());
		tfRCyl.setText(cs.getSightRCyl());
		tfRAxis.setText(cs.getSightRAxis());
		tfRAdd.setText(cs.getSightRAdd());
		tfRVa.setText(cs.getSightRVa());
		tfRPrism.setText(cs.getSightRPrism());
		tfRBase.setText(cs.getSightRBase());
		
		tfPd.setText(cs.getSightPd());
		tfSegh.setText(cs.getSightSegsh());
		
		tfLSph.setText(cs.getSightLSph());
		tfLCyl.setText(cs.getSightLCyl());
		tfLAxis.setText(cs.getSightLAxis());
		tfLAdd.setText(cs.getSightLAdd());
		tfLVa.setText(cs.getSightLVa());
		tfLPrism.setText(cs.getSightLPrism());
		tfLBase.setText(cs.getSightLBase());
		
		tfSingle.setText(cs.getLensSingle());
		tfBifocal.setText(cs.getLensBifocal());
		tfProgress.setText(cs.getLensProgress());
		tfLensType.setText(cs.getLensLensType());
		tfColor.setText(cs.getLensColor());
		tfBorder.setText(cs.getLensFrame());
		tfType.setText(cs.getLensType());
		tfRemark.setText(cs.getLensNote());
	}
	private Object[] dataValueEyeType(CustomerSight cs){
		Object[] dataValues = new String[columnNamesEyetype.length];
		dataValues = new String[columnNamesEyetype.length];
		
		dataValues[0] = CatchDataFrame.SQLDate2String(cs.getSightDate());
		dataValues[1] = cs.getLensSingle();
		dataValues[2] = cs.getLensBifocal();
		dataValues[3] = cs.getLensProgress();
		dataValues[4] = cs.getLensLensType();
		dataValues[5] = cs.getLensColor();
		dataValues[6] = cs.getLensFrame();
		dataValues[7] = cs.getLensNote();
		dataValues[8] = cs.getLensType();
		return dataValues;
		
	}
	private CustomerSight customerSightValue(){
		CustomerSight custSight = null;
			
		custSight = new CustomerSight();
		log.info("sight date = " + CatchDataFrame.PickerDat2String(sightDatePicker));
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
		
		return custSight;
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
		if (e.getSource() == btAdd) {
			CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
			Customer customer = customerValue();
			CustomerSight custSight = customerSightValue();
			
			if (customerDao.insertCustomerAndSight(customer, custSight)){
				JOptionPane.showMessageDialog(this, "เพิ่มข้อมูลเรียบร้อยแล้ว", "Success", JOptionPane.INFORMATION_MESSAGE);
				CustomerSearchPanel panel = new CustomerSearchPanel("1_2", glassShopFrame, null, this.custVal);
				glassShopFrame.changePanel(panel);
				log.info("success");
			}
			
		}else if (e.getSource() == btSave) {
			int row = tableEyeSight.getRowCount();
			if(row ==0){
				JOptionPane.showMessageDialog(this, "ไม่สามารถแก้ไขข้อมูลได้ กรุณาลองใหม่อีกครั้ง", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				CustomerSightDao custsightDao = (CustomerSightDao) GlassShop.CONTEXT.getBean("customerSightDAO");
				custsightDao.updateAppointmentDate(tfCustId.getText(),CatchDataFrame.PickerDat2String(sightAppointPicker));
				JOptionPane.showMessageDialog(this, "บันทึกข้อมูลเรียบร้อยแล้ว", "Success", JOptionPane.INFORMATION_MESSAGE);
				
			}
		}else if (e.getSource() == btEdit) {
			//Update sight
			CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
			Customer customer = customerValue();
			CustomerSight custSight = customerSightValue();
			if (customerDao.updateSight(customer, custSight)){
				JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลเรียบร้อยแล้ว", "Success", JOptionPane.INFORMATION_MESSAGE);
				CustomerSearchPanel panel = new CustomerSearchPanel("1_2", glassShopFrame, null, this.custVal);
				glassShopFrame.changePanel(panel);
				log.info("success");
			}
		}else if (e.getSource() == btEdit1) {
			//Update customer
			CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
			Customer customer = customerValue();
			if (customerDao.updateCustomer(customer)){
				JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลเรียบร้อยแล้ว", "Success", JOptionPane.INFORMATION_MESSAGE);
				CustomerSearchPanel panel = new CustomerSearchPanel("1_2", glassShopFrame, null, this.custVal);
				glassShopFrame.changePanel(panel);
				log.info("success");
			}
		}else if (e.getSource() == btDelete) {
			CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
			
			if (customerDao.deleteSight(tfCustId.getText())){
				JOptionPane.showMessageDialog(this, "ลบข้อมูลค่าสายตาและเลนส์เรียบร้อยแล้ว", "Success", JOptionPane.INFORMATION_MESSAGE);
				CustomerSearchPanel panel = new CustomerSearchPanel("1_2", glassShopFrame, null, this.custVal);
				glassShopFrame.changePanel(panel);
				log.info("success");
			}
		}else if (e.getSource() == btDelete1) {
			CustomerDao customerDao = (CustomerDao) GlassShop.CONTEXT.getBean("customerDAO");
			
			if (customerDao.deleteCustomer(tfCustId.getText())){
				JOptionPane.showMessageDialog(this, "ลบข้อมูลลูกค้าเรียบร้อยแล้ว", "Success", JOptionPane.INFORMATION_MESSAGE);
				CustomerSearchPanel panel = new CustomerSearchPanel("1_2", glassShopFrame, null, this.custVal);
				glassShopFrame.changePanel(panel);
				log.info("success");
			}
		}else if (e.getSource() == btCancel1) {
			setTextbox(this.custUdp);
		}else if ((e.getSource() == btSearch) || (e.getSource() == btSearch1)) {
			CustomerSearchPanel panel = new CustomerSearchPanel("1_2", glassShopFrame, null, this.custVal);
			glassShopFrame.changePanel(panel);
			
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
