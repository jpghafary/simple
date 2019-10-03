package search.simple.ui;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import search.simple.engine.SearchEngine;
import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;
import search.simple.utils.Constants;
/**
 * 
 * @author Jean-Pierre El Ghafary
 *
 */
public class ConsoleApplication {
	private SearchEngine engine;
	
	/**
	 * 
	 * @param rootDirectory
	 */
	public void init(File rootDirectory) {
		engine = new SearchEngine(rootDirectory);
	}
	
	/**
	 * 
	 */
	public void start() {
		try(Scanner keyBoard = new Scanner(System.in)){
			while(true) {
				System.out.println(Constants.DEFAULT_OUTPUT_MESSAGE);
				final String line = keyBoard.nextLine();
				
				if(line.equals(Constants.EXIT_COMMAND))
					break;
				
				SearchQuery query = new SearchQuery(line);
				List<SearchResult> results = engine.searchDictionary(query);
				sortResults(results);
				filterResults(results);
				printResults(results);
			}
		}
	}
	
	/**
	 * @param results
	 * @return
	 */
	private void sortResults(List<SearchResult> results) {
		results.sort((result1, result2) -> result2.getTotalPercentage() - result1.getTotalPercentage());
	}
	
	/**
	 * @param results
	 * @return
	 */
	private void filterResults(List<SearchResult> results) {
		results = results.stream().limit(Constants.RESULT_SEARCH_LIMIT).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param results
	 */
	private void printResults(List<SearchResult> results) {
		if(results.isEmpty())
			System.out.println(Constants.NO_MATCHES_FOUND);
		else
			results.forEach(System.out::println);
	}
}
