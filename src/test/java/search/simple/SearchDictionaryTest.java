package search.simple;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import search.simple.engine.SearchDictionary;
import search.simple.utils.SearchEngineData;

public class SearchDictionaryTest {
	SearchDictionary dictionary;
	SearchEngineData data;
	
	@Before
	public void Search_Dictionary_Test_Init() {
		try {
			data = new SearchEngineData();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			dictionary = new SearchDictionary(data.getRootDirectory());
		}
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
}
