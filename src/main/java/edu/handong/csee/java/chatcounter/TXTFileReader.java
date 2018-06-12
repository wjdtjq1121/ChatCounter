package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TXTFileReader implements Runnable{

	private File directory = null;
	Scanner inputStream = null;
	ArrayList<String> messageTXT = new ArrayList<String>();
	File path;



	public TXTFileReader(File path)
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

			messageTXT.add(line);
		}
		inputStream.close();

	}

}
