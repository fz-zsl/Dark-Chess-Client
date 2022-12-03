package algorithm;

import Piece.ChessPiece;
import com.example.darkchess.CanvasUtils;
import datum.ChessBoardStatus;
import datum.Operations;
import datum.UserStatus;

import static com.example.darkchess.Board.anchorPane;
import static com.example.darkchess.Board.chessPieceArrayList;

public class GeneralInit {
	public static void generalInit() {
		//restart a game, which won't change the name and password of the game
		UserStatus.AISide = -1;
		ChessBoardStatus.flipCounter=0;
		ChessBoardStatus.clearPossibleMoves();
		Operations.clearStack();
		Operations.copyOfSizeOfStack=-1;
		Operations.loadFileStamp=0;
		UserStatus.setRedScore(0);
		UserStatus.setBlackScore(0);
		UserStatus.currentSide=-1;
		ChessBoardStatus.flipCounter=0;
		UserStatus.resetEatChessCount();
		ChessBoardInit.chessBoardInit();
	}

	//public static void halfInit() {

	//}
}
