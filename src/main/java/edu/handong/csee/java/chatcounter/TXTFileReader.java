package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * it makes a new arraylist to store the new value name time date, it inherits runnable interface. </br>
 * 
 * @author HAN
 */
public class TXTFileReader implements Runnable{

	/**
	 * declares file type instance value as null. </br>
	 * 
	 * @author HAN
	 */
	private File directory = null;
	Scanner inputStream = null;
	ArrayList<String> messageTXT = new ArrayList<String>();
	File path;


	/**
	 * a constructor, executed before program runs, has arugument path. </br>
	 * 
	 * @author HAN
	 */
	public TXTFileReader(File path)
	{
		this.path = path;
	}

	/**
	 * this method is thread program, called by main class with execute instance. </br>
	 * 
	 * @author HAN
	 */
	@Override
	public void run() {
		readFile(path);
	}

	/**
	 * it makes a new arraylist to store the new value name time date. </br>
	 * 
	 * @author HAN
	 */
	void readFile(File path)
	{
		// File[] file = directory.listFiles();

		try {
			inputStream = new Scanner (path);
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Error opening the file");
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
