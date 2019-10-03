package search.simple.ui;

import java.util.List;
import java.util.Scanner;

import search.simple.engine.SearchEngine;
import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;
import search.simple.utils.Constants;

public class UserInterface {
	private SearchEngine engine;

	public UserInterface() {
		super();
	}
	
	public void start() {
		try(Scanner keyBoard = new Scanner(System.in)){
			while(true) {
				System.out.println(Constants.DEFAULT_OUTPUT_MESSAGE);
				final String line = keyBoard.nextLine();
				
				if(line.equals(Constants.EXIT_COMMAND))
					break;
				
				SearchQuery query = new SearchQuery(line);
				List<SearchResult> results = engine.searchDictionary(query);
				printResults(results);
			}
		}
	}
	
	private void printResults(List<SearchResult> results) {
		if(results.isEmpty())
			System.out.println(Constants.NO_MATCHES_FOUND);
		else
			results.forEach(System.out::println);
	}

	public SearchEngine getEngine() {
		return engine;
	}

	public void setEngine(SearchEngine engine) {
		this.engine = engine;
	}
	
}
