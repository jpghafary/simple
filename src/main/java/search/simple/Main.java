package search.simple;

import java.io.File;

import search.simple.ui.ConsoleApplication;

/**
 * Application's main class
 * @author Jean-Pierre El Ghafary
 *
 */
public class Main 
{
	/**
	 * This is the start method containing the console application and the user interaction
	 * @param args
	 */
	private void start(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("No directory given to index");
		if(args.length > 1)
			throw new IllegalArgumentException("Too many app arguments:\nMain <path_to_directory>.");
		File root = new File(args[0]);
		if(!root.exists())
			throw new IllegalArgumentException("The specified directory does not exist.");
		else if(!root.isDirectory())
			throw new IllegalArgumentException("The specified path is not a directory.");
		
		ConsoleApplication console = new ConsoleApplication();
		console.init(args[0]);
		console.start();
	}
	
	/***
	 * Main method
	 * @param args
	 */
    public static void main(String[] args)
    {
    	Main mainApplication = new Main();
    	try {
    		mainApplication.start(args);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
}
