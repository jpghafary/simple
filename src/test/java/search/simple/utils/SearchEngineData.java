package search.simple.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SearchEngineData {

	public static File rootDirectory = new File("testdata");
	public static File file1 = new File(rootDirectory + "/baseDir/file1.txt");
	public static File file2 = new File(rootDirectory + "/file2.txt");
	public static File file3 = new File(rootDirectory + "/baseDir/base/file3.txt");
	
	public static String fileContent1 = "This is the content of the first file";
	public static String fileContent2 = "This is the content of the second file";
	public static String fileContent3 = "This is the content of the third file";
	
	public SearchEngineData() throws IOException {
		Files.createDirectories(Paths.get("testData/baseDir/base"));
		
		Files.write(Paths.get(file1.getAbsolutePath()), fileContent1.getBytes());
		Files.write(Paths.get(file2.getAbsolutePath()), fileContent2.getBytes());
		Files.write(Paths.get(file3.getAbsolutePath()), fileContent3.getBytes());
	}
	
	public File getRootDirectory() {
		return rootDirectory;
	}
}
