package edu.handong.csee.java.chatcounter; // set the package where this belongs to

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileLoader {

	private File directory = null;
	
	Scanner inputStream = null;
	ArrayList<String> messageCSV = new ArrayList<String>();
	ArrayList<String> messageTXT = new ArrayList<String>();
	public HashMap<String, Integer> counted = new HashMap<String, Integer>();
	public String name;
	public String date;
	public String time;
	public String data;
	
	
		public FileLoader(String path){
			directory = new File(path);
	}


		public void getMac(String path) {
			for(File file:directory.listFiles())
			{
				if (file.getName().contains(".csv")) {

					try {
						inputStream = new Scanner (new File(path + "\\" + file.getName()));
					} catch(FileNotFoundException e) {
						System.out.println(e.getMessage());
						System.out.println("Error opening the file" + file.getName());
						System.exit(0);
					}
					
					while(inputStream.hasNextLine()) {
					String line = inputStream.nextLine();
					messageCSV.add(line);

						
					}
					inputStream.close();
													
					}
			}
		}
		
		public void getWindow(String path) {
			
			for(File file:directory.listFiles())
			{
				if (file.getName().contains(".txt")) {


					try {
						inputStream = new Scanner (new File(path + "\\" + file.getName()));
					} catch(FileNotFoundException e) {
						System.out.println(e.getMessage());
						System.out.println("Error opening the file" + file.getName());
						System.exit(0);
					}
					
					while(inputStream.hasNextLine()) {
					String line = inputStream.nextLine();
					messageTXT.add(line);
						
					}
					inputStream.close();
													
					}
			}			
		}
		
		public HashMap<String, Integer> countData(ArrayList<String> list){
			for(String readLine : list) {
				int io_start = readLine.indexOf('['); 
				int io_end = readLine.indexOf(']');
				int count;
				if(io_start>=0) {
					String name = readLine.substring(io_start+1, io_end);
					if(counted.get(name)!=null) {
						count = counted.get(name);
						count ++;
						counted.put(name, count);
					}
					else counted.put(name, 1);
				}
			}
			return counted;
		}
		
		public ArrayList<String> getMessageCSV() {
			return messageCSV;
		}


		public void setMessageCSV(ArrayList<String> messageCSV) {
			this.messageCSV = messageCSV;
		}
		
		
		public ArrayList<String> getMessageTXT() {
			return messageTXT;
		}


		public void setMessageTXT(ArrayList<String> messageTXT) {
			this.messageTXT = messageTXT;
		}
		
		
		
}
