package fileOperations;

//import com.example.darkchess.InitializationApplication;
//import com.example.darkchess.LogIn;
//import com.example.darkchess.StartPage;
import datum.UserStatus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.LoadUserException;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class LoginInterface {
	public static void userRegister(String userName,String plainPassword) throws Exception {
		File userFile=new File("database/",userName+".user.txt");
		try {
			userFile.createNewFile();
		} catch (IOException ioe) {
			throw new LoadUserException("There's a user with the same name, please choose a new one");
		}
		PrintStream systemOut=System.out;
		PrintStream ps=new PrintStream(userFile);
		System.setOut(ps);
		System.out.println(Encrypt.encryptByMD5(plainPassword));
		ps.close();
		System.setOut(systemOut);
		UserStatus.setGameKey(Encrypt.encryptByMD5(userName+"vit"+Encrypt.encryptByMD5(plainPassword)));
	}

	public static void userLogIn(String userName,String plainPassword) throws Exception {
		File userFile=new File("database/",userName+".user.txt");
		System.out.println(userFile.getPath()+" "+userFile.getName()+" "+userFile.getAbsolutePath());
		if (!userFile.exists())
			throw new LoadUserException("User does not exist.");
		Scanner sc=new Scanner(userFile);
		String encryptedCorrectPassword=sc.next();
		if (!encryptedCorrectPassword.equals(Encrypt.encryptByMD5(plainPassword)))
			throw new LoadUserException("User name or password error!");
		UserStatus.setGameKey(Encrypt.encryptByMD5(userName+"vit"+Encrypt.encryptByMD5(plainPassword)));
//		Stage stage = new Stage();
//		FXMLLoader fxmlLoader = new FXMLLoader(InitializationApplication.class.getResource("startGame.fxml"));
//		Scene scene = new Scene(fxmlLoader.load(), 900, 600);
//		stage.setTitle("登录界面");
//		stage.setScene(scene);
//		stage.show();
//		LogIn.theStartGameStage = stage;
//		StartPage.theLogInStage.close();
	}
}