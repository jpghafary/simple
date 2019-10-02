package search.simple.engine;

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
	private ArrayList<SearchResult> results;
	
	/**
	 * Default constructor
	 * @param directoryPath
	 */
	public SearchEngine(String directoryPath) {
		dictionary = new SearchDictionary(directoryPath);
	}
	
	/**
	 * @param SearchQuery query
	 * @return List of results
	 */
	public List<SearchResult> searchDictionary(SearchQuery query) {
		results = new ArrayList<>();
		
		this.dictionary.getIndexedFileContents().keySet().forEach(key -> {
			ArrayList<String> fileContent = this.dictionary.getIndexedFileContents().get(key);
			int totalPercentage = 0;
			
			for(String word : query.getSearchArray()) {
				String lineResult = fileContent.stream().filter(s -> s.toLowerCase().contains(word.toLowerCase())).findAny().orElse(null);
				if(lineResult != null)
					totalPercentage += query.getWordPercentage();
			}
			
			if(totalPercentage != 0) {
				results.add(new SearchResult(key, totalPercentage));
			}
		});
		
		results = sortResults();
		
		if(this.results.size() > Constants.RESULT_SEARCH_LIMIT) {
			results = filterResults();
		}
		return results;
	}
	
	/**
	 * @param results
	 * @return
	 */
	private ArrayList<SearchResult> sortResults() {
		this.results.sort((result1, result2) -> result2.getTotalPercentage() - result1.getTotalPercentage());
		return results;
	}
	
	/**
	 * @param results
	 * @return
	 */
	private ArrayList<SearchResult> filterResults() {
		return (ArrayList<SearchResult>) this.results.stream().limit(Constants.RESULT_SEARCH_LIMIT).collect(Collectors.toList());
	}
}
