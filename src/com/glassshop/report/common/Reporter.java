package com.glassshop.report.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.glassshop.common.Constanst;
import com.glassshop.ui.GlassShop;

public class Reporter {
	
	static Logger log = Logger.getLogger(Reporter.class.getName());
	private String templateName;
	private List dataBeanList;
	private Map parameters;
	
	public Reporter(String templateName, List dataBeanList){
		this.templateName = templateName;
		this.dataBeanList = dataBeanList;
		this.parameters = new HashMap();
	}
	
	public Reporter(String templateName, List dataBeanList, Map parameters){
		this.templateName = templateName;
		this.dataBeanList = dataBeanList;
		this.parameters = parameters;
	}
	
	private JasperPrint compileReport() throws Exception{
		InputStream inputStream = new FileInputStream(templateName);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, beanColDataSource);
		
		log.info("Jasper compile "+templateName+" completed.");
		
		return jasperPrint;
	}
	
	public void printReport(String outputFile) throws Exception{	
		JasperExportManager.exportReportToPdfFile(compileReport(),outputFile);
		log.info("Pinting report to "+outputFile+" completed.");
	}
	
	public void printPreview()  throws Exception{
		JasperViewer.viewReport(compileReport(),false);
	}
	
	public void printAndPreview(String outputFile) throws Exception{
		String repoetDir = GlassShop.prop.getProperty(Constanst.REPORT_OUTPUT_PATH);
		
		prepareReportDir();
		
		JasperExportManager.exportReportToPdfFile(compileReport(),repoetDir+outputFile);
		log.info("Pinting report to "+repoetDir+outputFile+" completed.");
		
		Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+repoetDir+outputFile);
		p.waitFor();
		log.info("Open report "+repoetDir+outputFile+" completed.");
	}
	
	private void prepareReportDir(){
		File file = new File(GlassShop.prop.getProperty(Constanst.REPORT_OUTPUT_PATH));

		if (!file.exists()) {
			if (file.mkdir()) {
				log.info("Report directory ["+GlassShop.prop.getProperty(Constanst.REPORT_OUTPUT_PATH)+"] is created!");
			} else {
				log.info("Failed to create ["+GlassShop.prop.getProperty(Constanst.REPORT_OUTPUT_PATH)+"] directory!");
			}
		}

	}

}
