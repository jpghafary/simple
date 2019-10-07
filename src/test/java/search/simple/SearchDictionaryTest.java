package search.simple;

import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import search.simple.engine.SearchDictionary;
import search.simple.utils.SearchEngineData;

public class SearchDictionaryTest {
	SearchDictionary dictionary;
	SearchEngineData data;
	
	@Before
	public void SEARCH_DICTIONARY_TEST_INIT_DATA() {
		data = new SearchEngineData();
		dictionary = new SearchDictionary(data.getRootDirectory().getRoot());
	}

	@Test
	public void SEARCH_DICTIONARY_INDEXING_TEST() {
		assertTrue(dictionary.getIndexedFileContents().keySet().size() == 3);
	}
	
	@Test
	public void SEARCH_DICTIONARY_CONTENT_OUTPUT() {
		dictionary.getIndexedFileContents().forEach( (key, value) -> {
			System.out.println(key + " = " + value.stream().map(Object::toString).collect(Collectors.joining(" ")));
		});
	}
	
	@After
	public void SEARCH_DICTIONARY_TEST_DETE_DATA() {
//		data.delete();
	}
}
