package datum;

import algorithm.ChessBoardInit;

import java.util.ArrayList;

public class ChessBoardStatus {
	public static int flipCounter=0;
	private static int[][] chessIndex=new int[10][7];
	private static int[][] objectIndex=new int[10][7];
	public static int[][] initObjectIndex=new int[10][7];
	private static boolean[][] chessFlipped=new boolean[10][7];

	public static ArrayList<Integer> allPossibleMoves=new ArrayList<>();

	public static int getChessIndex(int x, int y) {
		return chessIndex[x][y];
	}

	public static int getObjectIndex(int x,int y) {
		return objectIndex[x][y];
	}

	public static boolean getFlipped(int x,int y) {
		return chessFlipped[x][y];
	}

	public static int getWholeChessStatus(int x,int y) {
		return (objectIndex[x][y]<<1)|(chessFlipped[x][y]?1:0);
	}

	public static void chessInit(int x,int y,int chessObjectIndex,boolean flip) {
		if (chessObjectIndex<0) chessIndex[x][y]=objectIndex[x][y]=chessObjectIndex;
		else {
			chessIndex[x][y]=ChessBoardInit.indexToChess[chessObjectIndex];
			objectIndex[x][y]=chessObjectIndex;
		}
		chessFlipped[x][y]=flip;
	}

	public static void chessInit(int x,int y,int chessObjectIndex) {
		chessInit(x,y,chessObjectIndex,false);
	}

	public static void flipChess(int x,int y) {
		chessFlipped[x][y]=true;
		++flipCounter;
	}

	public static void flipBackChess(int x,int y) {
		//interface for undo process
		chessFlipped[x][y]=false;
		--flipCounter;
	}

	public static int moveChess(int srcX,int srcY,int destX,int destY) {
		//use MoveChess + ChessInit to undo the MoveChess process
		int eatReport=(objectIndex[destX][destY]<<1)|(chessFlipped[destX][destY]?1:0);
		//if no eat process happens, eatReport = -2
		chessIndex[destX][destY]=chessIndex[srcX][srcY];
		objectIndex[destX][destY]=objectIndex[srcX][srcY];
		chessFlipped[destX][destY]=chessFlipped[srcX][srcY];
		chessIndex[srcX][srcY]=-1;
		objectIndex[srcX][srcY]=-1;
		chessFlipped[srcX][srcY]=false;
		return eatReport;
	}

	public static int moveChess(int src,int dest) {
		return moveChess(src/10,src%10,dest/10,dest%10);
	}

	private static boolean canEat(int curIndex,int nextIndex) {
		if (curIndex/10==nextIndex/10) return false;
		curIndex%=10;
		nextIndex%=10;
		if (curIndex<=nextIndex&&nextIndex<6) return true;
		if (curIndex==5&&nextIndex==0) return true;
		return nextIndex==6 && curIndex!=5;
	}

	public static ArrayList<Integer> calcPossibleMoves(int clickX,int clickY) {
		//the first element will be itself
		allPossibleMoves.clear();
		allPossibleMoves.add(clickX*10+clickY);
		final int[] moveX=new int[]{-1,0,0,1};
		final int[] moveY=new int[]{0,-1,1,0};
		int curIndex=chessIndex[clickX][clickY];
		if (curIndex%10!=6) {
			//not a cannon
			for (int i=0;i<4;++i) {
				int xx=clickX+moveX[i];
				int yy=clickY+moveY[i];
				if (xx<1||xx>8||yy<1||yy>4) continue;
				if (chessIndex[xx][yy]<0) allPossibleMoves.add(xx*10+yy);//move
				else if (canEat(curIndex,chessIndex[xx][yy])&&chessFlipped[xx][yy])
					allPossibleMoves.add(xx*10+yy);//move and eat
			}
		}
		else {
			//is a cannon
			for (int i=0;i<4;++i) {
				int xx=clickX+moveX[i];
				int yy=clickY+moveY[i];
				boolean screen=false;
				if (xx<1||xx>8||yy<1||yy>4) continue;
				if (chessIndex[xx][yy]>=0) {
					//eat near: an empty chess or a chess of another side
					if ((!chessFlipped[xx][yy])||chessIndex[xx][yy]/10!=curIndex/10)
						allPossibleMoves.add(xx*10+yy);
					screen=true;
				}
				while (true) {
					xx+=moveX[i];
					yy+=moveY[i];
					if (xx<1||xx>8||yy<1||yy>4) break;//out of boarder
					if (chessIndex[xx][yy]<0) continue;//no chess, no problem
					//then there's a chess
					if (!screen) {//the chess will be a screen
						screen=true;
						continue;
					}
					//jump and eat: an empty chess or a chess of another side
					if ((!chessFlipped[xx][yy])||chessIndex[xx][yy]/10!=curIndex/10)
						allPossibleMoves.add(xx * 10 + yy);
					break;
				}
			}
		}
		return allPossibleMoves;
	}

	public static void clearPossibleMoves() {
		allPossibleMoves=new ArrayList<>();
	}

	public static void printChessBoardToCMD() {
		//Todo: mute after using file to record
		for (int i=1;i<=8;++i) {
			for (int j=1;j<=4;++j) {
				System.out.printf("%02d(%s) ",chessIndex[i][j],chessFlipped[i][j]?"正":"反");
			}
			System.out.println();
		}
	}

	public static void printObjectBoardToCMD() {
		//Todo: mute after using file to record
		for (int i=1;i<=8;++i) {
			for (int j=1;j<=4;++j) {
				System.out.printf("%02d(%s) ",objectIndex[i][j],chessFlipped[i][j]?"正":"反");
			}
			System.out.println();
		}
	}

	public static void printInitChessBoardToFile(){
		System.out.println('P');
		for (int i=1;i<=8;++i) {
			for (int j=1;j<=8;++j) {
				System.out.printf("%d ",ChessBoardStatus.initObjectIndex[i][j]);
			}
			System.out.println();
		}
	}
}
