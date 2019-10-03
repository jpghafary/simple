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
	 * @param args
	 */
	private void start(String[] args) throws IllegalArgumentException {
		validateAppArgument(args);
		
		ConsoleApplication console = new ConsoleApplication();
		console.init(new File(args[0]));
		console.start();
	}
	
	/**
	 * 
	 * @param args
	 */
	private void validateAppArgument(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("No directory given to index");
		else if(args.length > 1)
			throw new IllegalArgumentException("Too many arguments:\nMain <path_to_directory>.");
		
		File rootDirectory= new File(args[0]);
		if(!rootDirectory.exists())
			throw new IllegalArgumentException("The specified directory does not exist.");
		else if(!rootDirectory.isDirectory())
			throw new IllegalArgumentException("The specified path is not a directory.");
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
