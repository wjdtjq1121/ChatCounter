package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses txt file make the format name time data. </br>
 * 
 * @author HAN
 */
public class WindowParser{

	/**
	 * makes a new arraylist to store the new value. </br>
	 * 
	 * @author HAN
	 */
	public ArrayList<String> parsedTXTMessage = new ArrayList<String>();

	/**
	 * this method parses txt files and store the value name time value </br>
	 * 
	 * @author HAN
	 */
	public void parseWin(ArrayList<String> messageTXT) {

		int token1_start;
		int token1_end;
		int time_start;
		int time_end;
		int num_start;
		int num_end;
		String token1;
		String data;
		String time;

		String num = null;

		for(String readLine : messageTXT) {

			if(readLine.startsWith("["))	{

				readLine = readLine.replace("\"", "\"\"");

				token1_start = readLine.indexOf("[");
				token1_end = readLine.indexOf("]", token1_start+1);
				time_start = readLine.indexOf("]");
				time_end = readLine.indexOf("]", time_start+1);

				time = readLine.substring(time_start+3, time_end);

				if(time.contains("AM") || time.contains("PM")) {
					num_start = readLine.indexOf("]");

					num = readLine.substring(num_start+3, time_end-3);

				}

				data = readLine.substring(time_end+2, readLine.length());

				token1 = readLine.substring(token1_start+1, token1_end);

				if(time.contains("AM"))
					time = "오전 " + num.substring(0, num.length());
				if(time.contains("PM"))
					time = "오후 " + num.substring(0, num.length());	
				// System.out.println("[" + token1 + "]" + " " + "[" + time + "]" + " " + data);
				parsedTXTMessage.add("[" + token1 + "]" + " " + "[" + time + "]" + " " + data.trim());

				// System.out.println(readLine);
				// parsedTXTMessage.add(readLine);
			}
		}
	}
}
