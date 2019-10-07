package search.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import search.simple.engine.SearchDictionary;
import search.simple.engine.SearchEngine;
import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;
import search.simple.utils.SearchEngineData;

public class SearchEngineTest {
	SearchEngineData data;
	SearchEngine engine;
	
	@Before
	public void INIT_SEARCH_ENGINE_TEST() {
		try {
			data = new SearchEngineData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SearchDictionary dictionary = new SearchDictionary(data.getRootDirectory());
			engine = new SearchEngine(dictionary);
		}
	}
	
	@Test
	public void SEARCH_ENGINE_TEST_EXISTING_SENTENCE() {
		SearchQuery query = new SearchQuery("content the");
		List<SearchResult> results = engine.searchDictionary(query); 
		assertNotNull(results);
		assertNotEquals(results, 0);
		printResults(results);
	}
	
	private void printResults(List<SearchResult> results) {
		results.forEach(System.out::println);
	}
	
	@Test
	public void SEARCH_ENGINE_TEST_NONE_EXISTING_WORD() {
		SearchQuery query = new SearchQuery("to be or not to be");
		List<SearchResult> results = engine.searchDictionary(query);
		assertNotNull(results);
		assertEquals(results.size(), 0);
		printResults(results);
	}

}
