package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;

/**
 * This class parses csv file. </br>
 * it makes the format name time date. </br>
 * 
 * @author HAN
 */
public class MacParser {
	/**
	 * it makes a new arraylist to store the new value name time date. </br>
	 * 
	 * @author HAN
	 */
	public ArrayList<String> parsedCSVMessage = new ArrayList<String>();

	/**
	 * to do work making new list name time date. </br>
	 * 
	 * @author HAN
	 */
	public void parseCSV(ArrayList<String> messageCSV2) {

		int date2;
		int name1;
		int name2;
		int time1;
		int time2;
		int data1;
		int hour;
		String name;
		String date;
		String time;
		String data;


		for(String readLine : messageCSV2) {
			//System.out.println(readLine);
			//if(readLine.contains("\"����â\"")) System.out.println(readLine);
			if(readLine.startsWith("201")) {
				name1 = readLine.indexOf("\"");
				name2 = readLine.indexOf("\"", name1+1);
				time1 = readLine.indexOf(" ")+1;
				time2 = readLine.indexOf(":")+2;
				data1 = readLine.indexOf("\"", name2 +1);
				time = readLine.substring(time1, time2+1);
				hour = Integer.parseInt(time.substring(0, 2));

				// System.out.println("[" + time + "]");

				if(hour == 0) time = "오전 " + "12" + time.substring(2, time.length());
				else if(0 < hour && hour < 10) time = 
						"오전 " +time.substring(1, time.length());
				else if(hour == 11 || hour == 10) time = "오전 " + time;
				else if(hour == 12) time = "오후 " + time.substring(0, time.length());
				else if(hour > 12) time = "오후 " + (hour-12) + time.substring(2, time.length());

				name = readLine.substring(name1+1, name2);
				if(readLine.endsWith("\"")) data = readLine.substring(data1+1, readLine.length()-1); 
				else data = readLine.substring(data1+1, readLine.length());
				if(data.equals("Photo"))data = "사진";

				// System.out.println("[" + name + "] "+"[" + time + "] " + data);
				parsedCSVMessage.add("[" + name + "] "+"[" + time + "] " + data.trim());		
			} 
		}

	}
}

