package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.*;

/**
 * This class writes name and value. </br>
 * it makes name and value descending order. </br>
 * 
 * @author HAN
 */
public class FileWriter implements Mine{

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

		for(int i=0; i<descend.size(); i++)
		{
			System.out.print(descend.get(i));	
			System.out.println(" " + list.get(descend.get(i)));
		}



	}
}
