package search.simple;

import org.junit.Test;

import search.simple.engine.data.SearchQuery;

public class SearchQueryTest {

	@Test
	public void testSearchQuery() {
		SearchQuery query = new SearchQuery("to be or not to be");
		System.out.println("query.getWordPercentage() == " + query.getWordPercentage());
	}
}
