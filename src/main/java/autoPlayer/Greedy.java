package autoPlayer;

import algorithm.ChessBoardInit;
import algorithm.ClickOnBoard;
import datum.ChessBoardStatus;
import datum.UserStatus;
import oop.GameEndsException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

public class Greedy {
	//dep is the counter of remaining steps, including itself
	public static int greedy(int dep,boolean directUse,int level) throws GameEndsException, MalformedURLException
	{
		int[] modulo=new int[]{1,10,100,1000};
		int[] randomSteps=new int[]{10000,10,5,0};
		int[][] val=new int[10][7];
		int[][] nxt=new int[10][7];
		int side=UserStatus.currentSide^(dep&1);
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
					if (dep<2) val[i][j]=-1;
					else {
						ChessBoardStatus.flipChess(i,j);
						val[i][j]=-Greedy.greedy(dep-1,false,level);
						ChessBoardStatus.flipBackChess(i,j);
					}
					nxt[i][j]=-1;
					continue;
				}
				ArrayList<Integer> APM=new ArrayList<>(ChessBoardStatus.calcPossibleMovesForAI(i,j));
				ChessBoardStatus.clearPossibleMoves();
				for (int k=1;k<APM.size();++k) {
					int posX=APM.get(k)/10,posY=APM.get(k)%10;
					int chessStatus=ChessBoardStatus.getWholeChessStatus(posX,posY);
					if (chessStatus<0) {
						ChessBoardStatus.moveChess(i,j,posX,posY);
						int curVal=0;
						if (dep>1) curVal=-Greedy.greedy(dep-1,false,level);
						if (val[i][j]<curVal) {
							val[i][j]=curVal;
							nxt[i][j]=posX*10+posY;
						}
						ChessBoardStatus.moveChess(posX,posY,i,j);
						continue;
					}
					int posStatus=ChessBoardStatus.getWholeChessStatus(posX,posY);
					ChessBoardStatus.moveChess(i,j,posX,posY);
					int curVal=(dep%2==1?UserStatus.scorePerChessGreedyB[ChessBoardInit.indexToChess[chessStatus%50]%10]:UserStatus.scorePerChessGreedyA[ChessBoardInit.indexToChess[chessStatus%50]%10])
							-(dep>1?Greedy.greedy(dep-1,false,level):0);
					if (val[i][j]<curVal) {
						val[i][j]=curVal;
						nxt[i][j]=posX*10+posY;
					}
					ChessBoardStatus.moveChess(posX,posY,i,j);
					ChessBoardStatus.chessInit(posX,posY,posStatus%50,posStatus>49);
				}
			}
		int ans=-1028, cnt=0;
		for (int i=1;i<=8;++i) {
			for (int j=1;j<=4;++j) {
				if (val[i][j]>ans) {
					ans=val[i][j];
					cnt=1;
				} else if (val[i][j]==ans)
					++cnt;
			}
		}
		if (!directUse) return ans;
		Random rand=new Random(System.currentTimeMillis());
		cnt=rand.nextInt(36);//guess why it is 36 :)
		for (int i=1;i<=8;i=(i==8?1:i+1))
			for (int j=1;j<=4;++j)
				if (val[i][j]>=ans-3||(val[i][j]>-30&&(rand.nextInt(1118)%modulo[level]==0||ChessBoardStatus.flipCounter<randomSteps[level]))) {
					rand=new Random(System.currentTimeMillis());
					if (cnt<1) {
//						System.out.print("[AI]");
						ClickOnBoard.clickOnBoard(i,j);
						if (nxt[i][j]>0) {
//							System.out.print("[AI]");
							ClickOnBoard.clickOnBoard(nxt[i][j]/10,nxt[i][j]%10);
						}
						return ans;
					}
					--cnt;
				}
		return -1028;
	}
}
