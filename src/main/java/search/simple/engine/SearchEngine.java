package search.simple.engine;

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
	
	public SearchEngine(SearchDictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public List<SearchResult> searchDictionary(SearchQuery query) {
		resetPreviousResults();
		
		this.dictionary.getIndexedFileContents().entrySet().stream().forEach(entry -> generateSearchResult(query, entry));
		return results;
	}
	
	private void resetPreviousResults() {
		results = new ArrayList<>();
	}
	
	private void generateSearchResult(SearchQuery query, Entry<String, List<String>> entry) {
		List<String> fileContent = entry.getValue();
		int totalPercentage = calculateTotalPercentage(query, fileContent);
		appendResult(totalPercentage, entry.getKey());
	}
	
	private void appendResult(int totalPercentage, String fileName) {
		if(totalPercentage != 0)
			results.add(new SearchResult(fileName, totalPercentage));
	}
	
	private int calculateTotalPercentage(SearchQuery query, List<String> fileContent) {
		int totalPercentage = 0;
		for(String word : query.getSearchArray()) {
			String line = checkIfWordExists(word, fileContent);
			totalPercentage += (line != null) ? query.getWordPercentage() : 0;
		}
		return totalPercentage;
	}

	private String checkIfWordExists(String word, List<String> fileContent) {
		return fileContent.stream().filter(s -> s.toLowerCase().contains(word.toLowerCase())).findAny().orElse(null);
	}
}
