package fileOperations;

//import com.example.darkchess.InitializationApplication;
//import com.example.darkchess.LogIn;
//import com.example.darkchess.StartPage;
import com.example.darkchess.Showing;
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
	public static PrintStream systemOut=System.out;
	public static PrintStream printStreamToUser;
	public static File userFile;
	public static String encryptedCorrectPassword;
	public static int[] gameCounter=new int[8];
	public static void userRegister(String userName,String plainPassword) throws Exception {
		//Showing.Info(userName + " " + plainPassword);
		userFile=new File("database/",userName+".user.txt");
		try {
			userFile.createNewFile();
		} catch (IOException ioe) {
			throw new LoadUserException("There's a user with the same name, please choose a new one");
		}
		printStreamToUser=new PrintStream(userFile);
		System.setOut(printStreamToUser);
		System.out.println(Encrypt.encryptByMD5(plainPassword));
		for (int i=0;i<4;++i) System.out.println("0 0");
		printStreamToUser.close();
		System.setOut(systemOut);
		UserStatus.setGameKey(Encrypt.encryptByMD5(userName+"vit"+Encrypt.encryptByMD5(plainPassword)));
	}

	public static void userLogIn(String userName,String plainPassword) throws Exception {
		userFile=new File("database/",userName+".user.txt");
		//System.out.println(userFile.getPath()+" "+userFile.getName()+" "+userFile.getAbsolutePath());
		if (!userFile.exists()) throw new LoadUserException("用户不存在！");
		Scanner sc=new Scanner(userFile);
		encryptedCorrectPassword=sc.next();
//		Showing.Info("Check Point1050");
		if (!encryptedCorrectPassword.equals(Encrypt.encryptByMD5(plainPassword)))
			throw new LoadUserException("用户名或密码错误！");
		UserStatus.setGameKey(Encrypt.encryptByMD5(userName+"vit"+Encrypt.encryptByMD5(plainPassword)));
		for (int i=0;i<8;++i) gameCounter[i]=sc.nextInt();
		sc.close();
		//Todo
//		printStreamToUser=new PrintStream(userFile);
	}
}
