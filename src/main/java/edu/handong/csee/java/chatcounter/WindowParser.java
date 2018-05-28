package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WindowParser{


	public ArrayList<String> parsedTXTMessage = new ArrayList<String>();
	
	public void parseWin(ArrayList<String> messageTXT) {

		String date = null;
				
		int year1 = 0;
		int month1 = 0;
		int day1 = 0;
		String year;
		String month;
		String day;
		
		
		for(String readLine : messageTXT) {
			String pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s-+";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(readLine);
			
			if (m.find()) {
				year1 = Integer.parseInt(m.group(1));
				month1 = Integer.parseInt(m.group(2));
				day1 = Integer.parseInt(m.group(3));
			}
			year = String.valueOf(year1);
			month =  String.valueOf(month1);
			day = String.valueOf(day1);
			
			date = year + "-" + month + "-" + day;
			if(readLine.startsWith("["))	{
				readLine = readLine.replace("\"", "\"\"");
				 // System.out.println(readLine);
				parsedTXTMessage.add(readLine);
			}			
		}
	}
}
