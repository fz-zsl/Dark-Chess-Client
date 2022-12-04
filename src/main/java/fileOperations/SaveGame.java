package fileOperations;

import datum.ChessBoardStatus;
import datum.Operations;
import datum.UserStatus;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class SaveGame {
	public static void writeGameFile() throws IOException {
		String name=Operations.gameName;
		String key=UserStatus.getGameKey();
		File gameFile=new File("database/"+name+".game.txt");
		if (gameFile.exists()) gameFile.delete();
		gameFile.createNewFile();
		PrintStream printStream=new PrintStream(gameFile);
		System.setOut(printStream);
		System.out.println(key);
		ChessBoardStatus.printInitChessBoardToFile();
		Operations.printOperationsToFile();
		System.out.println("N");
		System.out.println(UserStatus.currentSide>0?"B":"R");
	}

	public static void writeUserFile() throws IOException {
		LoginInterface.userFile.delete();
		LoginInterface.userFile.createNewFile();
		LoginInterface.printStreamToUser=new PrintStream(LoginInterface.userFile);
		System.setOut(LoginInterface.printStreamToUser);
		System.out.println(LoginInterface.encryptedCorrectPassword);
		System.out.printf("%d %d\n",LoginInterface.gameCounter[0],LoginInterface.gameCounter[1]);
		System.out.printf("%d %d\n",LoginInterface.gameCounter[2],LoginInterface.gameCounter[3]);
		System.out.printf("%d %d\n",LoginInterface.gameCounter[4],LoginInterface.gameCounter[5]);
		System.out.printf("%d %d\n",LoginInterface.gameCounter[6],LoginInterface.gameCounter[7]);
	}
}
