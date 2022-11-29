package datum;

import algorithm.ChessBoardInit;

public class Operations {
	public static String gameName;
	private static int[] operationType=new int[100005];
	private static int[] srcPosition=new int[100005];
	private static int[] destPosition=new int[100005];
	private static int sizeOfStack=0;

	public static void clearStack() {
		sizeOfStack=0;
	}

	/*
	0 - flip (pos)
	1 - move (src,dest)
	2 - eat  (pos,object index of the eaten chess)
	 */

	public static void addOperationToStack(int op,int... positions) {
		operationType[sizeOfStack]=op;
		if (op==0) {//flip a chess
			srcPosition[sizeOfStack]=positions[0];
		} else if (op==1) {//move a chess
			srcPosition[sizeOfStack]=positions[0];
			destPosition[sizeOfStack]=positions[1];
		} else if (op==2) {//eat a chess
			srcPosition[sizeOfStack]=positions[0];
			destPosition[sizeOfStack]=positions[1];
			if (ChessBoardStatus.getFlipped(positions[0]/10,positions[0]%10))
				destPosition[sizeOfStack]+=50;
			//+50 for uncovered pieces
		}
		//System.out.printf("%d %d",op,srcPosition[sizeOfStack]);
		//if (op<2) System.out.printf(" %d",destPosition[sizeOfStack]);
		//System.out.println();
		++sizeOfStack;
		if (op<2) {
			UserStatus.currentSide^=1;
			System.out.println("Wait for a click: "+UserStatus.currentSide);
		}
	}

	public static int popOperationFromStack() {
		--sizeOfStack;
		if (operationType[sizeOfStack]<2) UserStatus.currentSide^=1;
		return operationType[sizeOfStack]*10000+
				srcPosition[sizeOfStack]*100+
				destPosition[sizeOfStack];
	}

	public static void printOperationsToCMD() {
		//Todo: mute after using file to record
		System.out.printf("%d steps in total:\n",sizeOfStack);
		for (int i=0;i<sizeOfStack;++i) {
			if (operationType[i]==0) {
				System.out.printf("Flip (%d, %d)\n",srcPosition[i]/10,srcPosition[i]%10);
			} else if (operationType[i]==1) {
				System.out.printf("Move (%d, %d) -> (%d, %d)\n",srcPosition[i]/10,srcPosition[i]%10,destPosition[i]/10,destPosition[i]%10);
			} else if (operationType[i]==2) {
				System.out.printf("%d on (%d, %d) has been eaten.\n", ChessBoardInit.indexToChess[destPosition[i]%100],srcPosition[i]/10,srcPosition[i]%10);
			}
		}
	}

	public static void printOperationsToFile(){
		System.out.println('O');
		System.out.println(sizeOfStack);
		for (int i=0;i<sizeOfStack;++i) {
			System.out.printf("%d ",operationType[i]);
			if (operationType[i]==0) {
				System.out.printf("%d\n",srcPosition[i]);
			} else if (operationType[i]>0) {
				System.out.printf("%d %d\n",srcPosition[i],destPosition[i]);
			}
		}
	}
}
