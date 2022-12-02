package algorithm;

import Piece.ChessPiece;
import com.example.darkchess.CanvasUtils;
import datum.ChessBoardStatus;
import datum.Operations;
import datum.UserStatus;

import static com.example.darkchess.Board.chessPieceArrayList;

public class UndoPreviousOperation {
	public static void undoPreviousOperation() {
		ChessBoardStatus.clearPossibleMoves();
		//Todo: call GUI here
		CanvasUtils.cancelHighLight();
		int lastOperation=Operations.popOperationFromStack();
		int type=lastOperation/10000;
		int srcPosition=lastOperation%10000/100;
		int destPosition=lastOperation%100;
		if (type==0) {
			ChessBoardStatus.flipBackChess(srcPosition/10,srcPosition%10);
			//Todo: call GUI here
			chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(srcPosition/10,srcPosition%10)).reFlipAChess();
		} else if (type==1) {
			ChessBoardStatus.moveChess(destPosition,srcPosition);
			//Todo: call GUI here
			chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(srcPosition/10,srcPosition%10)).transportAChess(ChessPiece.getChessXFx(destPosition%10),ChessPiece.getChessYFx(destPosition/10),ChessPiece.getChessXFx(srcPosition%10),ChessPiece.getChessYFx(srcPosition/10));
		} else if (type==2) {
			System.out.println("Last eat: "+srcPosition+" "+destPosition);
			UserStatus.undoEatChess(destPosition%50);
			lastOperation=Operations.popOperationFromStack();
			int destOfLastOperation=lastOperation%100;
			int srcOfLastOperation=lastOperation%10000/100;
			System.out.println("last step is from "+srcOfLastOperation+" to "+destOfLastOperation);
			ChessBoardStatus.moveChess(destOfLastOperation,srcOfLastOperation);
			ChessBoardStatus.chessInit(srcPosition/10,srcPosition%10,destPosition%50,destPosition/50>0);
			//Todo: call GUI here
			chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(srcOfLastOperation/10,srcOfLastOperation%10)).transportAChess(ChessPiece.getChessXFx(destOfLastOperation%10),ChessPiece.getChessYFx(destOfLastOperation/10),ChessPiece.getChessXFx(srcOfLastOperation%10),ChessPiece.getChessYFx(srcOfLastOperation/10));
			//System.out.println(UserStatus.getEatenChess().get(chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(destOfLastOperation/10,destOfLastOperation%10)).getPieceType().getNum()));
			chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(destOfLastOperation/10,destOfLastOperation%10)).getEatenNumber(UserStatus.getEatenChess().get(chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(destOfLastOperation/10,destOfLastOperation%10)).getPieceType().getNum()));
			chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(destOfLastOperation/10,destOfLastOperation%10)).reRemoveAChess(ChessPiece.getChessXFx(destOfLastOperation%10),ChessPiece.getChessYFx(destOfLastOperation/10));
		}
	}
}
