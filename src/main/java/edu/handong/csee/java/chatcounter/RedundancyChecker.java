package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.List;

public class RedundancyChecker {
	static ArrayList<String> finalResult;

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
