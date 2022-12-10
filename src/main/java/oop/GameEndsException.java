package oop;

import com.example.darkchess.StartGame;
import datum.UserStatus;
import fileOperations.LoginInterface;
import fileOperations.SaveGame;

public class GameEndsException extends Exception {
	private String info;

	public GameEndsException(String info) {
		this.info=info;
		if (StartGame.modeOfAll==3) {
			if (Integer.parseInt(info)!=UserStatus.AISide) ++LoginInterface.gameCounter[4];
			else ++LoginInterface.gameCounter[5];
		}
	}

	public int getInfo()
	{
		return Integer.parseInt(info);
	}
/*
	@Override
	public String toString() {
		return info+" wins the game.\n";
	}

 */

}
