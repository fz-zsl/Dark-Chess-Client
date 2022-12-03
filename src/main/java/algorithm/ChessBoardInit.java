package algorithm;

import Piece.ChessPiece;
import com.example.darkchess.CanvasUtils;
import datum.*;
import static com.example.darkchess.Board.*;

import java.util.Random;
import java.util.ArrayList;

public class ChessBoardInit {
	public static int[] indexToChess=new int[]{
			 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 5, 6, 6,
			10,11,11,12,12,13,13,14,14,15,15,15,15,15,16,16
	};
	private static class pairs {
		private final int index;
		private final double val;

		private pairs(int index,double val) {
			this.index=index;
			this.val=val;
		}
	}

	public static void chessBoardInit() {
		Random rand=new Random(System.currentTimeMillis());
		ArrayList<pairs> chessStatus=new ArrayList<>();
		for (int i=0;i<32;++i) {
			chessStatus.add(new pairs(i,rand.nextDouble()));
		}
		chessStatus.sort((o1, o2) -> {
			return Double.compare(o1.val,o2.val);
		});
		for (int i=1;i<=8;++i) {
			for (int j=1;j<=4;++j) {
				ChessBoardStatus.chessInit(i,j,chessStatus.get((i-1)*4+j-1).index);
				ChessBoardStatus.initObjectIndex[i][j]=chessStatus.get((i-1)*4+j-1).index;
				chessPieceArrayList.get(chessStatus.get((i-1)*4+j-1).index).setTranslateX(ChessPiece.getChessXFx(j));
				chessPieceArrayList.get(chessStatus.get((i-1)*4+j-1).index).setTranslateY(ChessPiece.getChessYFx(i));
			}
		}
	}
}
