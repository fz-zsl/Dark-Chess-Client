package algorithm;

import datum.ChessBoardStatus;
import datum.Operations;
import datum.UserStatus;

public class GeneralInit {
	public static void generalInit() {
		//restart a game, which won't change the name and password of the game
		UserStatus.AISide = -1;
		ChessBoardStatus.flipCounter=0;
		ChessBoardStatus.clearPossibleMoves();
		Operations.clearStack();
		UserStatus.setRedScore(0);
		UserStatus.setBlackScore(0);
		UserStatus.currentSide=-1;
		ChessBoardStatus.flipCounter=0;
		UserStatus.resetEatChessCount();
		ChessBoardInit.chessBoardInit();
	}
}
