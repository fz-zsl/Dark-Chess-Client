package oop;

import datum.UserStatus;

public class GameEndsException extends Exception {
	private String info;

	public GameEndsException(String info) {
		this.info=info;
		//if (Integer.parseInt(info)!=UserStatus.AISide) ++
		//Todo
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
