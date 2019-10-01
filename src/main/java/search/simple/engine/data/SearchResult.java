package search.simple.engine.data;

/**
 * This class will be the return value of the search engine.
 * It contains the name of the file containing results from the search query
 * also the total percentage of the search query.
 * @author Jean-Pierre El Ghafary
 *
 */
public class SearchResult {
	private String fileName;
	private int totalPercentage;
	
	/**
	 * The main constructor of this object
	 * @param fileName
	 * @param totalPercentage
	 */
	public SearchResult(String fileName, int totalPercentage) {
		super();
		this.fileName = fileName;
		this.totalPercentage = totalPercentage;
	}
	
	/**
	 * fileName getter
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * fileName setter
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * totalPercentage getter
	 * @return
	 */
	public int getTotalPercentage() {
		return totalPercentage;
	}
	
	/**
	 * totalPercentage setter
	 * @param totalPercentage
	 */
	public void setTotalPercentage(int totalPercentage) {
		this.totalPercentage = totalPercentage;
	}
	
	/**
	 * Overriding toString method
	 */
	@Override
	public String toString() {
		return this.fileName + ":" + this.totalPercentage + "%";
	}

}
