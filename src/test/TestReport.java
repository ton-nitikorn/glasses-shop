package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class TestReport {

	public static void main(String[] args) {
		try {
			Object[] cols = { "Subject", "Result" };
			DefaultTableModel dtm = new DefaultTableModel(cols, 0);
			dtm.addRow(new Object[] { "Mathematics", "B" });
			dtm.addRow(new Object[] { "Physics", "A" });
			dtm.addRow(new Object[] { "Chemistry", "B" });

			JRTableModelDataSource jrtAL = new JRTableModelDataSource(dtm); // Add
																			// DefaultTableModel
																			// to
																			// the
																			// //JRTableModel

			Map SUB_DATA1 = new HashMap(); // This will hold A/L Data
			SUB_DATA1.put("jrtAL", jrtAL);
			dtm = new DefaultTableModel(cols, 0);
			dtm.addRow(new Object[] { "Mathematics", "B" });
			dtm.addRow(new Object[] { "Physics", "A" });
			dtm.addRow(new Object[] { "Chemistry", "B" });

			JRTableModelDataSource jrtOL = new JRTableModelDataSource(dtm);
			Map SUB_DATA2 = new HashMap();
			SUB_DATA2.put("jrtOL", jrtOL);
			List data = new ArrayList();
			Map dataMap = new HashMap();
			dataMap.put("topic", "Web Developing\nCertificate");
			dataMap.put("desc",
					"Diploma in Web Developing\n(Global Solution Technologies) ");
			data.add(dataMap);
			dataMap = new HashMap();
			dataMap.put("topic", "Computer Certificates");
			dataMap.put("desc",
					"Diploma in computer Technology\n(Microtech Computer Systems) ");
			data.add(dataMap);

			JRMapCollectionDataSource subDS = new JRMapCollectionDataSource(
					data);

			Map SUB_DATA3 = new HashMap();
			SUB_DATA3.put("subDS", subDS);

			String repSource = "CVReport.jrxml";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("SUB_DATA1", SUB_DATA1);
			param.put("SUB_DATA2", SUB_DATA2);
			param.put("SUB_DATA3", SUB_DATA3);
			// /////////////////////////////////////////////////////
			Map simpleMasterMap = new HashMap();
			String name = "San Buddy CV";
			String address = "No 1, Somewhere,\nSomewhere ";
			String resTel = "091-1234567";
			String phone = "071-6204835";
			String email = "somebody@gmail.com";
			String profile = "To excel in the field of web developing relating to business and academic industry, "
					+ "with highly proven leadership skills involving developing projects, managing projects and ability to"
					+ " work with own initiative or and as part of team, is now seeking a profession in the field of web "
					+ "application development and willing to dedicate myself strictly to adhere the employment ethics and to"
					+ " thrive my best for the respective company’s success. ";

			simpleMasterMap.put("name", name);
			simpleMasterMap.put("address", address);
			simpleMasterMap.put("resTel", resTel);
			simpleMasterMap.put("phone", phone);
			simpleMasterMap.put("email", email);
			simpleMasterMap.put("profile", profile);

			List simpleMasterList = new ArrayList();
			simpleMasterList.add(simpleMasterMap);
			JRMapCollectionDataSource simpleDS = new JRMapCollectionDataSource(
					simpleMasterList);

			JasperReport jr = JasperCompileManager.compileReport(repSource);
			JasperPrint jp = JasperFillManager.fillReport(jr, param, simpleDS);
			JasperViewer.viewReport(jp);
		} catch (JRException ex) {
			JOptionPane.showMessageDialog(null,
					"Jasper Error: \n" + ex.getMessage());
		}
	}

}
