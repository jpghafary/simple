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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchDictionary {
	private String rootDirectoryPath;
	private Map<String, List<String>> indexedFileContents;

	/**
	 * Default constructor
	 * @param rootDirectoryPath
	 */
	public SearchDictionary(String rootDirectoryPath) {
		this.rootDirectoryPath = rootDirectoryPath;
		populate();
	}
	
	/**
	 * Populates the dictionary in a HashMap<FileName as key, FileContent as value>
	 */
	private void populate() {
		if(indexedFileContents == null)
			indexedFileContents = new HashMap<String, List<String>>();

		try {
			Files.walkFileTree(Paths.get(rootDirectoryPath), EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
				@Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
					List<String> fileContent = readFileContent(path);
					if(!fileContent.isEmpty())
						indexedFileContents.put(path.getFileName().toString(), fileContent);
					return FileVisitResult.CONTINUE;
				}
			});
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(this.indexedFileContents.keySet().size() + " files read in directory " + this.rootDirectoryPath);
	}

	private List<String> readFileContent(Path zFile) {
		List<String> list = new ArrayList<String>();
		
		try (Stream<String> stream = Files.lines(zFile, StandardCharsets.UTF_8)) {
			list = (ArrayList<String>) stream.collect(Collectors.toList());
		}catch(IOException e) {
			System.out.println("Unable to read : " + zFile.getFileName().toString());
		}
		return list;
	}
	
	public String getDirectoryPath() {
		return rootDirectoryPath;
	}
	
	public void setDirectoryPath(String rootDirectoryPath) {
		this.rootDirectoryPath = rootDirectoryPath;
	}
	
	public Map<String, List<String>> getIndexedFileContents() {
		return indexedFileContents;
	}

	public void setIndex(Map<String, List<String>> indexedFileContents) {
		this.indexedFileContents = indexedFileContents;
	}

}
