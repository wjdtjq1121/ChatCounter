package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class removes the redundant statement so it does not have same message. </br>
 * 
 * @author HAN
 */
public class RedundancyChecker {

	/**
	 * static arraylist to store the finalresult. </br>
	 * 
	 * @author HAN
	 */
	static ArrayList<String> finalResult;

	/**
	 * method removing the redundant message. </br>
	 * 
	 * @author HAN
	 */
	public ArrayList<String> removeRedun(ArrayList<String> dataList){
		finalResult = new ArrayList<String>();
		for(int i=0; i < dataList.size(); i++) {

			if(!finalResult.contains(dataList.get(i))) {
				finalResult.add(dataList.get(i));				
			}

		}
		return finalResult;
	}		
}
