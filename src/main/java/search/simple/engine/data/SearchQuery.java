package search.simple.engine.data;

/**
 * This class will contain the Search query sent to the search engine.
 * It contains the user's search query and the calculated percentage of each word
 * of this sentence.
 * The search array is the split on " " character considering that the delimiter of our application is " " (space character)
 * @author Jean-Pierre El Ghafary
 *
 */
public class SearchQuery {
	private String queryString;
	private int wordPercentage;
	private String[] searchArray;
	
	/**
	 * The constructor: will create a new instance of SearchResult object,
	 * will calculate the word percentage, and will initialize the search array.
	 * @param searchQuery
	 */
	public SearchQuery(String searchQuery) {
		super();
		this.queryString = searchQuery;
		this.wordPercentage = calculateWordPercentage();
		this.searchArray = searchQuery.split(" ");
	}
	
	/**
	 * Considering that the world are delimited by " " (space)
	 * @return double the world percentage
	 */
	private int calculateWordPercentage() {
		return 100/this.queryString.split(" ").length;
	}

	/**
	 * searchQuery getter
	 * @return
	 */
	public String getSearchQuery() {
		return queryString;
	}
	
	/**
	 * searchQuery setter
	 * @param queryString
	 */
	public void setSearchQuery(String queryString) {
		this.queryString = queryString;
	}
	
	/**
	 * wordPercentage getter
	 * @return
	 */
	public int getWordPercentage() {
		return wordPercentage;
	}
	
	/**
	 * wordPercentage setter
	 * @param wordPercentage
	 */
	public void setWordPercentage(int wordPercentage) {
		this.wordPercentage = wordPercentage;
	}
	
	/**
	 * searchArray getter
	 * @return
	 */
	public String[] getSearchArray() {
		return searchArray;
	}

	/**
	 * searchArray setter
	 * @param searchArray
	 */
	public void setSearchArray(String[] searchArray) {
		this.searchArray = searchArray;
	}
	
	/**
	 * Overriding the toString() method.
	 */
	@Override
	public String toString() {
		return this.queryString + ":" + this.wordPercentage ;
	}
}