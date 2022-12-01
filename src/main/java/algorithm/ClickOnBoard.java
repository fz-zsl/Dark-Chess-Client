package algorithm;

import Piece.ChessPiece;
import com.example.darkchess.CanvasUtils;
import datum.*;
import oop.*;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.darkchess.Board.chessPieceArrayList;

public class ClickOnBoard {
	public static int clickOnBoard(int clickX, int clickY) throws GameEndsException, MalformedURLException
	{
		System.out.println("A click on"+clickX+" "+clickY);
		/*
		values of clickType:
		-1 | invalid click, which shouldn't appear in the log
		 0 | a click that makes a flip
		 1 | first click for move
		 2 | second click for move
		 */
		int clickIndex=clickX*10+clickY;
		int clickType;
		if (ChessBoardStatus.allPossibleMoves.size()>0) {
			//there is a previous click - second click
			int preX=ChessBoardStatus.allPossibleMoves.get(0)/10;
			int preY=ChessBoardStatus.allPossibleMoves.get(0)%10;
			System.out.println("second click with"+preX+" "+preY);
			//Todo: call GUI here
			CanvasUtils.cancelHighLight();
			if (preX==clickX&&preY==clickY) {
				System.out.println("A same click, which will cancel the previous click.");
				//cancel highlight
				ChessBoardStatus.clearPossibleMoves();
				clickType=-1;
				//Todo: call GUI here
			} else if (ChessBoardStatus.getChessIndex(clickX,clickY)>=0
					&&!ChessBoardStatus.getFlipped(clickX,clickY)
					&&ChessBoardStatus.getChessIndex(preX,preY)%10!=6) {
				//flip
				System.out.println("Let's Flip - second click!");
				ChessBoardStatus.flipChess(clickX,clickY);
				Operations.addOperationToStack(0,clickX*10+clickY);
				ChessBoardStatus.clearPossibleMoves();
				clickType=-1;
				//Todo: call GUI here
				chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(clickX,clickY)).flipAChess();
			} else if (ChessBoardStatus.allPossibleMoves.contains(clickIndex)) {
				//move
				System.out.println("Let's move!");
				//Todo: call GUI here
				ChessPiece preChessPiece=null;
				if (ChessBoardStatus.getWholeChessStatus(clickX,clickY)>=0)
					preChessPiece=chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(clickX,clickY));
				int eatReport=ChessBoardStatus.moveChess(preX,preY,clickX,clickY);
				Operations.addOperationToStack(1,preX*10+preY,clickX*10+clickY);
				if (eatReport>=0) {
					Operations.addOperationToStack(2,clickX*10+clickY,eatReport>>1);
					UserStatus.eatChess(eatReport>>1);
				}
				ChessBoardStatus.clearPossibleMoves();
				clickType=2;
				//Todo: call GUI here
				chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(clickX,clickY)).transportAChess(ChessPiece.getChessXFx(preY),ChessPiece.getChessYFx(preX),ChessPiece.getChessXFx(clickY),ChessPiece.getChessYFx(clickX));
				if (preChessPiece!=null)
				{
					System.out.println("bwgg + "+UserStatus.getEatenChess().get(preChessPiece.getPieceType().getNum()));
					preChessPiece.getEatenNumber(UserStatus.getEatenChess().get(preChessPiece.getPieceType().getNum()));
					preChessPiece.removeAChess(ChessPiece.getChessXFx(clickY),ChessPiece.getChessYFx(clickX));
				}
			} else {
				//cancel highlight
				ChessBoardStatus.clearPossibleMoves();
				clickType=-1;
			}
		} else {
			//there is no previous click - first click
			if (!ChessBoardStatus.getFlipped(clickX,clickY)) {
				//flip

				System.out.println("Let's Flip - first click!");
				ChessBoardStatus.flipChess(clickX,clickY);
				if (ChessBoardStatus.flipCounter==1) {
					UserStatus.currentSide=ChessBoardStatus.getChessIndex(clickX,clickY)/10;
					UserStatus.AISide=UserStatus.currentSide^1;
				}
				Operations.addOperationToStack(0,clickX*10+clickY);
				clickType=0;
				//Todo: call GUI here
				chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(clickX,clickY)).flipAChess();
			} else if (ChessBoardStatus.getChessIndex(clickX,clickY)/10==UserStatus.currentSide) {
				ArrayList<Integer> APM=ChessBoardStatus.calcPossibleMoves(clickX,clickY);
				System.out.printf("All possible moves: %s\n",APM.toString());
				clickType=1;
				//Todo: call GUI here
				CanvasUtils.highLight(APM);
			} else clickType=-1;
		}
		return clickType;
	}
}
