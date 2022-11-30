package oop;

public class GameEndsException extends Exception {
	private String info;

	public GameEndsException(String info) {
		this.info=info;
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
