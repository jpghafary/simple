package search.simple.engine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import search.simple.engine.data.SearchQuery;
import search.simple.engine.data.SearchResult;
import search.simple.utils.Constants;

/**
 * The main class of the SerachEngine. This class will create an in memory map populated by the files 
 * contained under the user specific directory passed as application argument.
 * The map's key is the file name and the value is the file content represented as ArrayList<String>.
 * This class assumes that the directory specified exists and is a directory.
 * @author Jean-Pierre El Ghafary
 *
 */
public class SearchEngine {
	private String directoryPath;
	private HashMap<String, ArrayList<String>> index;
	private ArrayList<SearchResult> results;
	
	/**
	 * The object's constructor
	 */
	public SearchEngine(String directoryPath) {
		this.directoryPath = directoryPath;
		populateIndexes();
	}
	
	/**
	 * This method will populate the map after the user has specified the needed directory.
	 * This method assumes that the value of the directory passed as application argument exists and is a directory.
	 * When done indexing this method will display the number of files indexed.
	 */
	private void populateIndexes() {
		if(index == null)
			index = new HashMap<String, ArrayList<String>>();

		try {
			Files.walkFileTree(Paths.get(directoryPath), EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
				@Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
					ArrayList<String> fileContent = readFileContent(path);
					if(!fileContent.isEmpty())
						index.put(path.getFileName().toString(), fileContent);
					return FileVisitResult.CONTINUE;
				}

				private ArrayList<String> readFileContent(Path zFile) {
					ArrayList<String> list = new ArrayList<String>();
					
					try (Stream<String> stream = Files.lines(zFile, StandardCharsets.UTF_8)) {
						list = (ArrayList<String>) stream.collect(Collectors.toList());
					}catch(IOException e) {
						System.out.println("Unable to read : " + zFile.getFileName().toString());
					}
					return list;
				}
			});
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(this.index.keySet().size() + " files read in directory " + this.directoryPath);
	}
	
	/**
	 * This method is the engine's search method. It loops on all the file contents comparing each 
	 * value in the split search query and using the method indexOf to determine if this world is found
	 * in the file content or not, increasing at each iteration the total percentage.
	 * Then creating a new SearchResult object that will be added to the main array List.
	 * After all the iterations are done, the result will be sorted by totalPercentage ascending to descending.
	 * @param SearchQuery query
	 * @return List of results
	 */
	public List<SearchResult> search(SearchQuery query) {
		results = new ArrayList<>();
		
		this.index.keySet().forEach(key -> {
			ArrayList<String> fileContent = this.index.get(key);
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
	 * This method will sort the result by total percentage descending
	 * @param results
	 * @return
	 */
	private ArrayList<SearchResult> sortResults() {
		this.results.sort((result1, result2) -> result2.getTotalPercentage() - result1.getTotalPercentage());
		return results;
	}
	
	/**
	 * This method will filter the results and limit the size to 10
	 * @param results
	 * @return
	 */
	private ArrayList<SearchResult> filterResults() {
		return (ArrayList<SearchResult>) this.results.stream().limit(Constants.RESULT_SEARCH_LIMIT).collect(Collectors.toList());
	}
	
	
}
