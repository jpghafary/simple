package search.simple.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;
import search.simple.utils.Constants;

/**
 * @author Jean-Pierre El Ghafary
 *
 */
public class SearchEngine {
	private SearchDictionary dictionary;
	private List<SearchResult> results;
	
	/**
	 * @param directoryPath
	 */
	public SearchEngine(File rootDirectory) {
		dictionary = new SearchDictionary(rootDirectory);
	}
	
	/**
	 * @param SearchQuery query
	 * @return List of results
	 */
	public List<SearchResult> searchDictionary(SearchQuery query) {
		results = new ArrayList<>();
		
		this.dictionary.getIndexedFileContents().entrySet().stream().parallel().forEach(entry -> {
			List<String> fileContent = entry.getValue();
			int totalPercentange = calculateTotalPercentage(query, fileContent);
			
			if(totalPercentange != 0)
				results.add(new SearchResult(entry.getKey(), totalPercentange));
		});
		
		results = sortResults();
		results = filterResults();
		
		return results;
	}
	
	/**
	 * 
	 * @param query
	 * @param fileContent
	 * @return
	 */
	private int calculateTotalPercentage(SearchQuery query, List<String> fileContent) {
		int totalPercentage = 0;
		for(String word : query.getSearchArray()) {
			String line = checkIfWordExists(word, fileContent);
			totalPercentage += (line != null) ? query.getWordPercentage() : 0;
		}
		return totalPercentage;
	}

	/**
	 * 
	 * @param word
	 * @param fileContent
	 * @return
	 */
	private String checkIfWordExists(String word, List<String> fileContent) {
		return fileContent.stream().filter(s -> s.toLowerCase().contains(word.toLowerCase())).findAny().orElse(null);
	}
	
	/**
	 * @param results
	 * @return
	 */
	private List<SearchResult> sortResults() {
		this.results.sort((result1, result2) -> result2.getTotalPercentage() - result1.getTotalPercentage());
		return results;
	}
	
	/**
	 * @param results
	 * @return
	 */
	private List<SearchResult> filterResults() {
		return this.results.stream().limit(Constants.RESULT_SEARCH_LIMIT).collect(Collectors.toList());
	}
}
