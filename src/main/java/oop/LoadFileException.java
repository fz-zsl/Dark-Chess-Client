package oop;

public class LoadFileException extends Exception {
	private String info;

	public LoadFileException(String info) {
		this.info=info;
	}

	@Override
	public String toString() {
		return info;
	}
}
