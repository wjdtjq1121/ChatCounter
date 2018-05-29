package edu.handong.csee.java.chatcounter;

//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This main class runs a file to do all basic work here. </br>
 * 
 * @author HAN
 */
public class Main {

	
	String path;
	String output;
	boolean help;
	/**
	 * initialize the value null public file. </br>
	 * 
	 * @author HAN
	 */
	// public File directoryFile = null;	
	Scanner inputStream = null;
	ArrayList<String> messageCSV = new ArrayList<String>();

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
	 * 
	 * @author HAN
	 */
	public void run(String[] args) {
	
		Options options = createOptions();
		if(parseOptions(options, args)){			
			if (help){
				printHelp(options);
				return;
			}
			
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
			
			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided \"" + path + "\" as the value of the option p");
			
			// TODO show the number of files in the path
			
			
		}
		
		
		// String path = "C:\\Users\\HAN\\Desktop\\java\\java lab\\chatcounter materials\\drive-download-20180526T060231Z-001";
		

	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
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
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set a file path which having a csv file that has count information for each user from the all message files")
				.hasArg()
				.argName("A file path")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}
	
	
	
}
