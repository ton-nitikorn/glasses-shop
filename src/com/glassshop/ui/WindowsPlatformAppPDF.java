package com.glassshop.ui;

public class WindowsPlatformAppPDF {

	public static void main(String[] args) {
		 
		  try {
	 
			Process p = Runtime.getRuntime()
				   .exec("rundll32 url.dll,FileProtocolHandler c:\\BeginingLinuxProgramming.pdf");
//				   .exec("rundll32 url.dll,FileProtocolHandler https://teamsites.fnfis.com/sites/BAY/EXECUTING/Forms/AllItems.aspx?RootFolder=%2Fsites%2FBAY%2FEXECUTING%2FRequirement%20Analysis%2FFunctional%2FSRS");
			p.waitFor();
	 
			System.out.println("Done");
	 
	  	  } catch (Exception ex) {
			ex.printStackTrace();
		  }
	 
		}

	
}
