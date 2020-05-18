package com.poly.gestion;

public class Color {

	public static String getRed()
	{		 
		if (MainSystem.testLinux())	        	
			return "\033[31m";

		return "";
	}
	 
	public static String getGreen()
	{	 
		if (MainSystem.testLinux())
			return "\033[32m";
		
		return "";
	}
	
	public static String getYellow()
	{
		if (MainSystem.testLinux())
			return "\033[33m";

		return "";
	}
	
	public static String getBlue()
	{		 
		if (MainSystem.testLinux())	        	
			return "\033[34m";

		return "";
	}
	 
	public static String getMagenta()
	{		 
		if (MainSystem.testLinux())      	
			return "\033[35m";
		
		return "";
	}
	 
	public static String getCyan()		 
	{
		if (MainSystem.testLinux())
			return "\033[36m";
		
		return "";
	}
	 
	public static String getWhite()
	{		 
		if (MainSystem.testLinux())
			return "\033[37m";	        
		 
		return "";
	}
	 	
	public static String getGrey()
	{		 
		if (MainSystem.testLinux())
			return "\033[1m";        
		 
		return "";
	}
	 
	public static String getClignote()
	{		 
		if (MainSystem.testLinux())
			return "\033[5m";
		 
		return "";
	 }
	 
	public static String getIntensity()
	{
		if (MainSystem.testLinux())   	
			return "\033[1m";
		 
		return "";	
	}
	 
	public static String getReverse()
	{
		if (MainSystem.testLinux())
			return "\033[7m";
		
		return "";	        	
	}
	
	public static String Reboot()
	{
		if (MainSystem.testLinux())
			return "\033[0m";
		
		return "";	        	
	}
}