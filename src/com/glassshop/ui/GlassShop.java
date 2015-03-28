package com.glassshop.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GlassShop {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 700;
	
	public static Font FONT = null;
	public static ApplicationContext CONTEXT = null;
	public static Properties prop = new Properties();
	public static Color bgColor = new Color(95,158,160); ;
	public static void main(String[] args) {

		FONT =  new Font("Tahoma", Font.PLAIN, 14);
		
		UIManager.put( "Button.font", FONT);
		UIManager.put( "Label.font", FONT);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CONTEXT = new ClassPathXmlApplicationContext("config/Spring-Module.xml");
		
		try {
            //load a properties file
	 		prop.load(new FileInputStream("glasses-shop.properties"));
	
	 	} catch (IOException ex) {
	 		ex.printStackTrace();
	    }

		GlassShopFrame glassShopFrame = new GlassShopFrame();
		glassShopFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		glassShopFrame.setVisible(true);
		glassShopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
}
