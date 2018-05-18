package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.util.ArrayList;



public class FileLoader {

	private ArrayList<String> messages;
	private ArrayList<File> getFileNames(String path){
	
		ArrayList<File> fileNames = new ArrayList<File>();
		// get file 
		File myPath = new File(path);
		for(File fileName:myPath.listFiles()) {
			fileNames.add(fileName);
		}
		return fileNames;
	}
	
	
	
	
	public void readDirectory(String path) {
	// get files name from the path
		
		ArrayList<File> fileNames = getFileNames(path);
		
		//read string from each file
		
		for(File fileName:fileNames) {
			
		}
	}
	
}

