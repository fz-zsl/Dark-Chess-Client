package datum;

import java.util.ArrayList;

import algorithm.ChessBoardInit;
import oop.*;

public class UserStatus {
	public static int currentSide=-1;
	public static int AISide=-1;
	public static int AIMode=2;
	public static int AIDepth=4;
	public static final int[] scorePerChess={30,10,5,5,5,1,5};
	public static final int[] scorePerChessGreedyA={30,10,5,5,5,1,25};
	public static final int[] scorePerChessGreedyB={30,10,5,5,5,1,25};
	private static int redScore=0;
	private static int blackScore=0;
	private static int[] eatenChessCount=new int[17];
	//private static ArrayList<Integer> eatenChessStack=new ArrayList<>();

	private static String gameKey;

	public static void setGameKey(String str) {
		gameKey=str;
	}

	public static String getGameKey() {
		return gameKey;
	}

	public static void setRedScore(int redScore) {
		UserStatus.redScore=redScore;
	}

	public static int getRedScore() {
		return redScore;
	}

	public static void setBlackScore(int blackScore) {
		UserStatus.blackScore=blackScore;
	}

	public static int getBlackScore() {
		return blackScore;
	}

	public static void resetEatChessCount() {
		for (int i=0;i<17;++i) eatenChessCount[i]=0;
	}

	public static ArrayList<Integer> getEatenChess() {
		ArrayList<Integer> copyOfEatenChessCount=new ArrayList<>();
		for (int i=0;i<17;++i) copyOfEatenChessCount.add(eatenChessCount[i]);
		return copyOfEatenChessCount;
	}

	public static ArrayList<Integer> getEatenRedChess() {
		//be careful that we'll return the red chess eaten by black
		ArrayList<Integer> copyOfEatenChessCount=new ArrayList<>();
		for (int i=0;i<7;++i) copyOfEatenChessCount.add(eatenChessCount[i]);
		return copyOfEatenChessCount;
	}

	public static ArrayList<Integer> getEatenBlackChess() {
		//be careful that we'll return the black chess eaten by red
		ArrayList<Integer> copyOfEatenChessCount=new ArrayList<>();
		for (int i=10;i<17;++i) copyOfEatenChessCount.add(eatenChessCount[i]);
		return copyOfEatenChessCount;
	}

	public static void eatChess(int objectIndex) throws GameEndsException {
		int chessIndex=ChessBoardInit.indexToChess[objectIndex];
		++eatenChessCount[chessIndex];
//		eatenChessStack.add(chessIndex);
		if (chessIndex<10) {//black eats red
			blackScore+=scorePerChess[chessIndex];
			if (blackScore>59) throw new GameEndsException("1");
		}
		else {//red eats black
			redScore+=scorePerChess[chessIndex-10];
			if (redScore>59) throw new GameEndsException("0");
		}
	}

	public static void undoEatChess(int objectIndex) {
		int chessIndex=ChessBoardInit.indexToChess[objectIndex];
//		int chessIndex=eatenChessStack.get(eatenChessStack.size()-1);
		--eatenChessCount[chessIndex];
//		eatenChessStack.remove(eatenChessStack.size()-1);
		if (chessIndex<10) {//undo black eats red
			blackScore-=scorePerChess[chessIndex];
		}
		else {//undo red eats black
			redScore-=scorePerChess[chessIndex-10];
		}
	}

	public static void printEatenChessToCMD() {
		System.out.print("Black eats red chess: ");
		for (int i=0;i<7;++i) System.out.printf("%d, ",eatenChessCount[i]);
		System.out.printf("black score = %d\n",blackScore);
		System.out.print("Red eats black chess: ");
		for (int i=10;i<17;++i) System.out.printf("%d, ",eatenChessCount[i]);
		System.out.printf("red score = %d\n",redScore);
	}
}
