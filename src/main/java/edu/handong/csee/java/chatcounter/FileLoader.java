package edu.handong.csee.java.chatcounter; // set the package where this belongs to

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class defines brings data csv, txt and hashmap. </br>
 * it has arraylist to store the value in messagecsv and message txt, also has scanner inputstream to bring the file.</br>
 * 
 * @author HAN
 */
public class FileLoader{

	/**
	 * This gets a file directory initial value is null. </br>
	 * @author HAN
	 */
	private File directory = null;

	Scanner inputStream = null;
	ArrayList<String> messageCSV = new ArrayList<String>();
	ArrayList<String> messageTXT = new ArrayList<String>();
	/**
	 * hashmap to store name value and number of value how many user speaks. </br>
	 * 
	 * @author HAN
	 */
	public HashMap<String, Integer> counted = new HashMap<String, Integer>();

	/**
	 * This method brings path and store path value in directory </br>
	 * 
	 * @author HAN
	 */
	public FileLoader(String path){
		directory = new File(path);
	}

	/**
	 * This method get path value and bring csv file. </br>
	 * 
	 * @author HAN
	 */
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

					// System.out.println(line);

					messageCSV.add(line);
				}
				inputStream.close();

			}
		}
	}

	/**
	 * This method get path value and bring txt file. </br>
	 * 
	 * @author HAN
	 */
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

					// System.out.println(line);

					messageTXT.add(line);
				}
				inputStream.close();

			}
		}			
	}

	/**
	 * This method returns how many times user speaks. </br>
	 * 
	 * @author HAN
	 */
	public HashMap<String, Integer> countData(ArrayList<String> list){
		for(String readLine : list) {
			int mess1 = readLine.indexOf('['); 
			int mess2 = readLine.indexOf(']');
			int count;
			if(mess1>=0) {
				String name = readLine.substring(mess1+1, mess2);
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

	/**
	 * getters returning csv method. </br>
	 * 
	 * @author HAN
	 */
	public ArrayList<String> getMessageCSV() {
		return messageCSV;
	}

	/**
	 * setters setting csv method. </br>
	 * 
	 * @author HAN
	 */
	public void setMessageCSV(ArrayList<String> messageCSV) {
		this.messageCSV = messageCSV;
	}

	/**
	 * getters returning txt method. </br>
	 * 
	 * @author HAN
	 */
	public ArrayList<String> getMessageTXT() {
		return messageTXT;
	}

	/**
	 * setters setting txt method. </br>
	 * 
	 * @author HAN
	 */
	public void setMessageTXT(ArrayList<String> messageTXT) {
		this.messageTXT = messageTXT;
	}

}
