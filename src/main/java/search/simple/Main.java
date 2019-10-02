package search.simple;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import search.simple.engine.SearchEngine;
import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;
import search.simple.utils.Constants;

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
		
		SearchEngine engine = new SearchEngine(args[0]);
		
		try(Scanner keyBoard = new Scanner(System.in)){
			while(true) {
				System.out.print(Constants.DEFAULT_OUTPUT_MESSAGE);
				final String line = keyBoard.nextLine();
				if(line.equals(Constants.EXIT_COMMAND))
					break;
				
				SearchQuery query = new SearchQuery(line);
				List<SearchResult> results = engine.searchDictionary(query);
				if(results.isEmpty())
					System.out.println(Constants.NO_MATCHES_FOUND);
				else
					results.forEach(System.out::println);
			}
		}
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
