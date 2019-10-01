package search.simple;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import search.simple.engine.SearchEngine;
import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;

public class SearchEngineTest {
	
	SearchEngine engine;
	
	@Before
	public void init() {
		String directory = "D:\\temp\\dir_dict";
		
		File root = new File(directory);
		assertTrue("The provided path exists.",root.exists());
		assertTrue("The provided path is a directory", root.isDirectory());
		
		engine = new SearchEngine(directory);
	}

	@Test
	public void TestExistingWord() {
		SearchQuery query = new SearchQuery("void ifile main");
		List<SearchResult> results = engine.search(query);
		results.forEach(System.out::println);
	}
	
	@Test
	public void TextNoneExistingWord() {
		SearchQuery query = new SearchQuery("jobess");
		List<SearchResult> results = engine.search(query);
		results.forEach(System.out::println);
	}

}
