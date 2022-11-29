package fileOperations;

import datum.ChessBoardStatus;
import datum.Operations;
import datum.UserStatus;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CreateGameFile {
	public static void createGameFile() throws IOException {
		String name=Operations.gameName;
		String key=UserStatus.getGameKey();
		File gameFile=new File("database/"+name+".game.txt");
		gameFile.createNewFile();
		PrintStream printStream=new PrintStream(gameFile);
		System.setOut(printStream);
		System.out.println(key);
		ChessBoardStatus.printInitChessBoardToFile();
		Operations.printOperationsToFile();
		System.out.println("N");
		System.out.println(UserStatus.currentSide>0?"B":"R");
	}
}
