package autoPlayer;

import algorithm.ChessBoardInit;
import algorithm.ClickOnBoard;
import datum.ChessBoardStatus;
import datum.UserStatus;
import oop.GameEndsException;

import java.util.ArrayList;
import java.util.Random;

public class GreedyA {
	private static void waitMS(int timeInMS) {
		timeInMS*=100000;
		while (timeInMS-->0);
	}

	public static void greedyA() throws GameEndsException {
		int[][] val=new int[10][7];
		int[][] nxt=new int[10][7];
		int side=UserStatus.currentSide;
		for (int i=1;i<=8;++i)
			for (int j=1;j<=4;++j) {
				val[i][j]=-1028;
				if (ChessBoardStatus.getChessIndex(i,j)<0) {
					val[i][j]=-1028;//guess whose birthday is it :)
					nxt[i][j]=-1;
					continue;
				}
				if (ChessBoardStatus.getChessIndex(i,j)/10!=side) {
					val[i][j]=-1028;
					nxt[i][j]=-1;
					continue;
				}
				if (!ChessBoardStatus.getFlipped(i,j)) {
					ChessBoardStatus.flipChess(i,j);
					val[i][j]=-GreedyB.greedyB();
					nxt[i][j]=-1;
					ChessBoardStatus.flipBackChess(i,j);
					continue;
				}
				ArrayList<Integer> APM=new ArrayList<>(ChessBoardStatus.calcPossibleMoves(i,j));
				for (int k=1;k<APM.size();++k) {
					int posX=APM.get(k)/10,posY=APM.get(k)%10;
					int chessStatus=ChessBoardStatus.getWholeChessStatus(posX,posY);
					if (chessStatus<0) {
						ChessBoardStatus.moveChess(i,j,posX,posY);
						int curVal=-GreedyB.greedyB();
						if (val[i][j]<curVal) {
							val[i][j]=curVal;
							nxt[i][j]=posX*10+posY;
						}
						ChessBoardStatus.moveChess(posX,posY,i,j);
						continue;
					}
					int posStatus=ChessBoardStatus.getWholeChessStatus(posX,posY);
					ChessBoardStatus.moveChess(i,j,posX,posY);
					int curVal=UserStatus.scorePerChessGreedyA[ChessBoardInit.indexToChess[chessStatus>>1]%10]-GreedyB.greedyB();
					if (val[i][j]<curVal) {
						val[i][j]=curVal;
						nxt[i][j]=posX*10+posY;
					}
					ChessBoardStatus.moveChess(posX,posY,i,j);
					ChessBoardStatus.chessInit(posX,posY,posStatus>>1,(posStatus&1)>0);
				}
				ChessBoardStatus.clearPossibleMoves();
			}
		int ans=-1028,cnt=0;
		for (int i=1;i<=8;++i) {
			for (int j=1;j<=4;++j) {
				//System.out.printf("%d\t",val[i][j]);
				if (val[i][j]>ans) {
					ans=val[i][j];
					cnt=1;
				} else if (val[i][j]==ans)
					++cnt;
			}
			//System.out.println();
		}
		Random rand=new Random(System.currentTimeMillis());
		cnt=rand.nextInt(cnt);
		for (int i=1;i<=8;++i)
			for (int j=1;j<=4;++j)
				if (val[i][j]==ans) {
					if (cnt<1) {
						//System.out.print("[AI]");
						ClickOnBoard.clickOnBoard(i,j);
						waitMS(500);
						if (nxt[i][j]>0) {
							//System.out.print("[AI]");
							ClickOnBoard.clickOnBoard(nxt[i][j]/10,nxt[i][j]%10);
						}
						return;
					}
					--cnt;
				}
	}
}
