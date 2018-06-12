package edu.handong.csee.java.chatcounter;

import java.util.*;
import java.io.*;

/**
 * This class writes name and value. </br>
 * it makes name and value descending order. </br>
 * 
 * @author HAN
 */
public class FileWriter implements Mine{

	/**
	 * constructors, executing super constructor and then stores the outputfile value here. </br> 
	 * @author HAN
	 */
	public FileWriter(String outputFile) {
		super();
		this.outputFile = outputFile;
	}
	String outputFile = "outputa.csv";

	/**
	 * This method does descending order print all the values. </br>
	 * 
	 * @author HAN
	 */
	public void show(HashMap<String, Integer> list) {
		List<String> descend = new ArrayList<String>();
		descend.addAll(list.keySet());


		Collections.sort(descend, new Comparator<Object>() {

			@SuppressWarnings("unchecked")
			/**
			 * This method compared the value and stores the value as descending order. </br>
			 * 
			 * @author HAN
			 */
			public int compare(Object o1, Object o2) {
				Object v1 = list.get(o1);
				Object v2 = list.get(o2);
				return ((Comparable<Object>) v1).compareTo(v2);
			}			
		});
		Collections.reverse(descend);


		// File file = new File("C:\\Users\\HAN\\Desktop\\java\\java lab\\ChatCounter\\output.csv");
		// File file = new File("outputFile");
		PrintWriter printWriter = null;
		try {
			
			printWriter = new PrintWriter("output.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printWriter.println("kakao_id,count");

		for(int i=0; i<descend.size(); i++)
		{
			printWriter.print(descend.get(i));	
			printWriter.println("," + list.get(descend.get(i)));
		}
		printWriter.close();

	}
}
