package oop;

public class LoadUserException extends Exception {
	private String info;

	public LoadUserException(String info) {
		this.info=info;
	}

	@Override
	public String toString() {
		return info;
	}
}
