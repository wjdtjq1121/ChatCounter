package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.*;

public class FileWriter {

	public void show(HashMap<String, Integer> list) {
		
		for(String name: list.keySet()) {
		String key = name.toString();
		String value = list.get(name).toString();
		System.out.println(key + "," + value);
		}

	}
}
