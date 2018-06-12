package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * it reads csv file data with using thread technology. </br>
 * 
 * @author HAN
 */
public class CSVFileReader implements Runnable {

	/**
	 * declares file instance directory as null. </br>
	 * 
	 * @author HAN
	 */
	private File directory = null;
	Scanner inputStream = null;
	ArrayList<String> messageCSV = new ArrayList<String>();
	File path;


	/**
	 * constructor, executed before program runs. </br>
	 * 
	 * @author HAN
	 */
	public CSVFileReader(File path)
	{
		this.path = path;
	}

	@Override
	public void run() {
		readFile(path);
	}

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

			messageCSV.add(line);
		}
		inputStream.close();

	}

}