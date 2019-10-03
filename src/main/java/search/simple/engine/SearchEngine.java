package search.simple.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;

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
		resetPreviousResults();
		
		this.dictionary.getIndexedFileContents().entrySet().stream().parallel().forEach(entry -> {
			generateSearchResult(query, entry);
		});
		return results;
	}

	private void generateSearchResult(SearchQuery query, Entry<String, List<String>> entry) {
		List<String> fileContent = entry.getValue();
		int totalPercentage = calculateTotalPercentage(query, fileContent);
		appendResult(totalPercentage, entry.getKey());
	}
	
	private void resetPreviousResults() {
		results = new ArrayList<>();
	}
	
	private void appendResult(int totalPercentage, String fileName) {
		if(totalPercentage != 0)
			results.add(new SearchResult(fileName, totalPercentage));
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
}
