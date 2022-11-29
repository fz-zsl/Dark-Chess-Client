package autoPlayer;

import algorithm.ChessBoardInit;
import datum.ChessBoardStatus;
import datum.UserStatus;

import java.util.ArrayList;

public class GreedyB {
	public static int greedyB() {
		int[][] val=new int [10][7];
		int side=UserStatus.currentSide^1;
		for (int i=1;i<=8;++i)
			for (int j=1;j<=4;++j) {
				if (ChessBoardStatus.getChessIndex(i,j)<0) {
					val[i][j]=-1028;//guess whose birthday is it :)
					continue;
				}
				if (ChessBoardStatus.getChessIndex(i,j)/10!=side) {
					val[i][j]=-1028;
					continue;
				}
				if (!ChessBoardStatus.getFlipped(i,j)) {
					val[i][j]=-1;
					continue;
				}
				ArrayList<Integer> APM=new ArrayList<>(ChessBoardStatus.calcPossibleMoves(i,j));
				for (int k=1;k<APM.size();++k) {
					int posX=APM.get(k)/10,posY=APM.get(k)%10;
					int chessStatus=ChessBoardStatus.getWholeChessStatus(posX,posY);
					if (chessStatus>=0)
						val[i][j]=Math.max(val[i][j],UserStatus.scorePerChessGreedyB[ChessBoardInit.indexToChess[chessStatus>>1]%10]);
					else val[i][j]=0;
				}
				ChessBoardStatus.clearPossibleMoves();
			}
		int ans=-1028;
		for (int i=1;i<=8;++i)
			for (int j=1;j<=4;++j)
				ans=Math.max(ans,val[i][j]);
		return ans;
	}
}
