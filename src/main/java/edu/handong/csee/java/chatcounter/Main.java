package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

	public File directoryFile = null;	
	Scanner inputStream = null;
	ArrayList<String> messageCSV = new ArrayList<String>();

	public static void main(String[] args) {
		Main runner = new Main();
		runner.run();	
	}

	public void run() {

		String path = "C:\\Users\\HAN\\Desktop\\java\\java lab\\chatcounter materials\\drive-download-20180526T060231Z-001";



		FileLoader fileloader = new FileLoader(path);
		MacParser readMac = new MacParser();
		WindowParser readWin = new WindowParser();
		RedundancyChecker check = new RedundancyChecker();
		Mine write = new FileWriter();

		ArrayList<String> data = new ArrayList<String>();
		HashMap<String, Integer> list = new HashMap<String, Integer>();

		ArrayList<String> temp = new ArrayList<String>();


		fileloader.getMac(path);
		fileloader.getWindow(path);
		readMac.parseCSV(fileloader.messageCSV);
		readWin.parseWin(fileloader.messageTXT);

		data.addAll(readMac.parsedCSVMessage);
		data.addAll(readWin.parsedTXTMessage);


		for(String showLine: data){
			//   if(!showLine.contains("joined this chatroom")) {
			temp.add(showLine);
			//	}
		}


		temp = check.removeRedun(temp);

		//		  for(String showLine: temp){
		//			  System.out.println(showLine);
		//			  }


		//		  for(String showLine: temp){
		//			  if(showLine.contains("남재창"))
		//		  System.out.println(showLine);
		//		  }

		list = fileloader.countData(temp);
		write.show(list);

	}

}
