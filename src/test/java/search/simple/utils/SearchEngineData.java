package search.simple.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class SearchEngineData {

	@Rule
	public TemporaryFolder rootDirectory = new TemporaryFolder();
	
	public SearchEngineData() {
		String fileContent1 = "This is the content of the first file";
		String fileContent2 = "This is the content of the second file";
		String fileContent3 = "This is the content of the third file";
		
		try {
			rootDirectory.create();
			
			File file1 = rootDirectory.newFile("file1.txt");
			Files.write(Paths.get(file1.getAbsolutePath()), fileContent1.getBytes());
	
			File subDir1 = rootDirectory.newFolder("baseDir");
			Files.write(Paths.get(subDir1 + File.separator +  "file2.txt"), fileContent2.getBytes());
			
			Path subDir2 = Files.createDirectories(Paths.get(subDir1 + File.separator + "base"));
			Files.write(Paths.get(subDir2.toString() + File.separator + "file3.txt"), fileContent3.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TemporaryFolder getRootDirectory() {
		return rootDirectory;
	}
}
