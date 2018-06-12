package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashMap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.util.*;


/**
 * This main class runs a file to do all basic work here. </br>
 * 
 * @author HAN
 */
public class Main {


	String path;
	String outputPath;
	boolean help;
	int threadNum;
	String getThreadNum;

	/**
	 * declares file instance directory value as null. </br>
	 * 
	 * @author HAN
	 */
	private File directory = null;

	/**
	 * hashmap key value is string (name) and value is the number of counts which each people speaks. </br>
	 * 
	 * @author HAN
	 */
	public HashMap<String, Integer> counted = new HashMap<String, Integer>();

	/**
	 * initialize the value null public file. </br>
	 * 
	 * @author HAN
	 */
	// public File directoryFile = null;	
	Scanner inputStream = null;

	/**
	 * static void method calls runner method to do all the work. </br>
	 * 
	 * @author HAN
	 */
	public static void main(String[] args) {
		Main runner = new Main();
		runner.run(args);
	}

	/**
	 * this run method actually runs the program. </br>
	 * it brings the file parses the file, remove the redundancy, and write the file. </br>
	 * add commons cli options i o help, i reads path of a directory o is a path of a file. </br>
	 * @author HAN
	 */
	public void run(String[] args) {

		// revive this later!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		Options options = createOptions();
		if(parseOptions(options, args)){			
			if (help){
				printHelp(options);
				return;
			}

			// disable this bottom string path when you submit the project ----------------------------------------------
			//	String path = "C:\\Users\\HAN\\Desktop\\java\\java lab\\chatcounter materials\\drive-download-20180526T060231Z-001";

			//   outputPath = "C:\\Users\\HAN\\Desktop\\java\\java lab\\ChatCounter\\output.csv"; 	

			ArrayList<CSVFileReader> csvReader = new ArrayList<CSVFileReader>(); 
			// ArrayList<String> messageCSV = new ArrayList<String>();

			ArrayList<TXTFileReader> txtReader = new ArrayList<TXTFileReader>();


			this.directory = new File(path);

			threadNum = Integer.parseInt(getThreadNum);

			int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors();

			System.out.println("The number of cores of my system: " + numOfCoresInMyCPU);
			System.out.println("Please enter the number 4 which is the number of cores of my system: " + threadNum);


			ExecutorService executor = Executors.newFixedThreadPool(threadNum);

			for(File file:directory.listFiles())
			{
				if (file.getName().contains(".csv")) {

					Runnable worker = new CSVFileReader(file);
					executor.execute(worker);
					csvReader.add((CSVFileReader)worker);
				}
			}

			executor.shutdown();
			while(!executor.isTerminated()) {

			}			  	

			ArrayList<String> messageCSVAll = new ArrayList<String>();
			for(CSVFileReader runner : csvReader) {
				messageCSVAll.addAll(runner.messageCSV);
			}


			// from here, start reading txt file

			ExecutorService executor2 = Executors.newFixedThreadPool(threadNum);

			for(File file:directory.listFiles())
			{
				if (file.getName().contains(".txt")) {

					Runnable worker = new TXTFileReader(file);
					executor2.execute(worker);
					txtReader.add((TXTFileReader)worker);
				}
			}

			executor2.shutdown();
			while(!executor2.isTerminated()) {

			}			  	

			ArrayList<String> messageTXTAll = new ArrayList<String>();
			for(TXTFileReader runner : txtReader) {
				messageTXTAll.addAll(runner.messageTXT);
			}



			//			for(String showLine: messageTXTAll){
			//				 
			//			  System.out.println(showLine);
			//			  }


			MacParser readMac = new MacParser();
			WindowParser readWin = new WindowParser();
			RedundancyChecker check = new RedundancyChecker();
			Mine write = new FileWriter(outputPath);

			ArrayList<String> data = new ArrayList<String>();
			HashMap<String, Integer> list = new HashMap<String, Integer>();

			ArrayList<String> temp = new ArrayList<String>();


			readMac.parseCSV(messageCSVAll);
			readWin.parseWin(messageTXTAll);  

			data.addAll(readMac.parsedCSVMessage);
			data.addAll(readWin.parsedTXTMessage);


			// lower 4 lines print out thread csv lines

			//			  for(String showLine: testCsv.messageCSV){
			//							 
			//						  System.out.println(showLine);
			//						  }




			for(String showLine: data){
				//   if(!showLine.contains("joined this chatroom")) {
				temp.add(showLine);
				//	}
			}


			temp = check.removeRedun(temp);

			//		  for(String showLine: temp){
			//			  System.out.println(showLine);
			//			  }


			//					  for(String showLine: temp){
			//						  if(showLine.contains("[ㅇ]"))
			//					  System.out.println(showLine);
			//					  }

			list = countData(temp);
			write.show(list);

			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided \"" + path + "\" as the value of the option i");

			// revive this later!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11	
		}
	}

	/**
	 * method private return type is boolean, has two argument parameters. </br>
	 * brought this from mvnrepository commons cli, making 3 options i o h, bring a path directory and file. </br> 
	 * @author HAN
	 */
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("i");
			outputPath = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			getThreadNum = cmd.getOptionValue("c");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
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

	// Definition Stage
	/**
	 * method private return type is boolean, has two argument parameters. </br>
	 * brought this from mvnrepository commons cli, making 3 options i o h, bring a path directory and file. </br> 
	 * @author HAN
	 */
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set a path of a directory containing chat data files")
				.hasArg()
				.argName("A directory path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("outputPath")
				.desc("Set a file path which having a csv file that has count information for each user from the all message files")
				.hasArg()
				.argName("A file path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("c").longOpt("number of threads")
				.desc("Getting the number of threads which is four in my computer system")
				.hasArg()
				.argName("the number of thread which is 4")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

	/**
	 * help options if there is no file path which coming from the user, it prints the message help. </br>
	 * @author HAN
	 */
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

}
