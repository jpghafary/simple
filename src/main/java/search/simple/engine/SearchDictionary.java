package search.simple.engine;

import java.io.File;
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
	private File rootDirectory;
	private Map<String, List<String>> indexedFileContents;

	/**
	 * Default constructor
	 * @param rootDirectory
	 */
	public SearchDictionary(File rootDirectory) {
		this.rootDirectory = rootDirectory;
		initIndexFileContents();
		populateDictionary();
	}
	
	/**
	 * Populates the dictionary in a HashMap<FileName as key, FileContent as value>
	 */
	private void populateDictionary() {
		try{
			SimpleFileVisitor<Path> visitor = initSimpleVisitor();
			Files.walkFileTree(Paths.get(rootDirectory.getCanonicalPath()), EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, visitor);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(this.indexedFileContents.keySet().size() + " files read in directory " + this.rootDirectory);
	}

	private SimpleFileVisitor<Path> initSimpleVisitor(){
		return new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				addToDictionary(path);
				return FileVisitResult.CONTINUE;
			}
		};
	}
	
	private void initIndexFileContents() {
		if(indexedFileContents == null)
			indexedFileContents = new HashMap<String, List<String>>();
	}

	private void addToDictionary(Path path) {
		List<String> fileContent = readFileContent(path);
		if(!fileContent.isEmpty())
			indexedFileContents.put(path.getFileName().toString(), fileContent);
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
	
	public File getRootDirectory() {
		return rootDirectory;
	}
	
	public void setDirectoryPath(File rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	public Map<String, List<String>> getIndexedFileContents() {
		return indexedFileContents;
	}

	public void setIndex(Map<String, List<String>> indexedFileContents) {
		this.indexedFileContents = indexedFileContents;
	}

}
