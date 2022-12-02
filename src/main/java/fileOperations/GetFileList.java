package fileOperations;

import java.io.File;
import java.util.ArrayList;

public class GetFileList {
	public static ArrayList<String> getFileList() {
		ArrayList<String> setOfFileNames=new ArrayList<>();
		File[] files=(new File("database/")).listFiles();
		for (File file: files) {
			if (file.isFile()) {
				setOfFileNames.add(file.getName());
			}
		}
		return setOfFileNames;
	}
}
