package fileOperations;

import Piece.ChessPiece;
import algorithm.ChessBoardInit;
import algorithm.ClickOnBoard;
import algorithm.GeneralInit;
import com.example.darkchess.Board;
import com.example.darkchess.CanvasUtils;
import datum.ChessBoardStatus;
import datum.Operations;
import datum.UserStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import oop.GameEndsException;
import oop.LoadFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.darkchess.Board.anchorPane;
import static com.example.darkchess.Board.chessPieceArrayList;

public class LoadGameFile
{
    public static void loadGameFile(String name) throws LoadFileException, GameEndsException, MalformedURLException
    {
        GeneralInit.generalInit();
        //chessPieceArrayList = CanvasUtils.setAllChess();
        //name should include the suffix
        Operations.gameName = name;
        String gameFileName = "database/" + name;
        if (!gameFileName.endsWith(".game.txt"))
            throw new LoadFileException("错误编码 #101 | 文件格式错误，日志文件必须为.game.txt类型。");
        File gameFile = new File(gameFileName);
        Scanner sc = null;
        try
        {
            sc = new Scanner(gameFile);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            throw new LoadFileException("错误编码 #100 | 日志文件不存在，系统信息：" + fileNotFoundException);
        }
        String rawString = sc.nextLine();
//		if (!rawString.equals(UserStatus.getGameKey()))
//			throw new LoadFileException("错误编码 #200 | 日志打开失败，请选择自己的残局继续游戏。");
        rawString = sc.nextLine();
        if (!rawString.equals("P"))
        {
            throw new LoadFileException("错误编码 #201 | 日志文件格式错误，请导入由软件自动生成的游戏日志。");
        }
        int[] cntChess = new int[]{1, 2, 2, 2, 2, 5, 2, 0, 0, 0, 1, 2, 2, 2, 2, 5, 2};
        for (int i = 1; i <= 8; ++i)
        {
            rawString = sc.nextLine();
            String[] Ints = rawString.split(" ");
            //System.out.println(rawString);
            if (Ints.length != 4)
            {
                throw new LoadFileException("错误编码 #102 | 棋盘错误，第 " + i + " 行的棋子个数不为 4 个。");
            }
            int curInt = -1;
            for (int j = 1; j <= 4; ++j)
            {
                try
                {
                    curInt = Integer.parseInt(Ints[j - 1]);
                }
                catch (NumberFormatException numberFormatException)
                {
                    throw new LoadFileException("错误编码 #102 | 棋盘错误，仅读取到 " + (i - 1) + " 行有效棋盘，棋盘行数不足 8 行。");
                }
                if (curInt < 0 || curInt > 31)
                    throw new LoadFileException("错误编码 #103 | 棋子错误，位于第 " + i + " 行，第 " + j + " 列的棋子不属于红黑 7 种棋子之一。");
                --cntChess[ChessBoardInit.indexToChess[curInt]];

                ChessBoardStatus.chessInit(i, j, curInt);
                ChessBoardStatus.initObjectIndex[i][j] = curInt;
                chessPieceArrayList.get(curInt).setTranslateX(ChessPiece.getChessXFx(j));
                chessPieceArrayList.get(curInt).setTranslateY(ChessPiece.getChessYFx(i));
            }
        }
        rawString = sc.nextLine();
        if (!rawString.equals("N"))
        {
            throw new LoadFileException("错误编码 #104 | 缺少行棋方，请导入由软件自动生成的游戏日志。");
        }
        rawString = sc.nextLine();
        int side = 0;
        if (rawString.equals("R"))
        {
            side = 0;
        }
        else if (rawString.equals("B"))
        {
            side = 1;
        }
        else
        {
            throw new LoadFileException("错误编码 #104 | 缺少行棋方，请导入由软件自动生成的游戏日志。");
        }
        rawString = sc.nextLine();
        if (!rawString.equals("O"))
        {
            throw new LoadFileException("错误编码 #201 | 日志文件格式错误，请导入由软件自动生成的游戏日志。");
        }
        rawString = sc.nextLine();
        int numberOfOperations;
        try
        {
            numberOfOperations = Integer.parseInt(rawString);
        }
        catch (NumberFormatException numberFormatException)
        {
            throw new LoadFileException("错误编码 #201 | 日志文件格式错误，请导入由软件自动生成的游戏日志。");
        }
        int operationType = 0, srcPosition = 0, destPosition = 0;
        for (int i = 0; i < numberOfOperations; ++i)
        {
            rawString = sc.nextLine();
            String[] Ints = rawString.split(" ");
            try
            {
                operationType = Integer.parseInt(Ints[0]);
            }
            catch (NumberFormatException numberFormatException)
            {
                throw new LoadFileException("错误编码 #201 | 日志文件格式错误，请导入由软件自动生成的游戏日志。");
            }
            try
            {
                srcPosition = Integer.parseInt(Ints[1]);
            }
            catch (NumberFormatException numberFormatException)
            {
                throw new LoadFileException("错误编码 #201 | 日志文件格式错误，请导入由软件自动生成的游戏日志。");
            }
            if (operationType > 0)
            {
                try
                {
                    destPosition = Integer.parseInt(Ints[2]);
                }
                catch (NumberFormatException numberFormatException)
                {
                    throw new LoadFileException("错误编码 #201 | 日志文件格式错误，请导入由软件自动生成的游戏日志。");
                }
            }
            if (operationType == 0 && ClickOnBoard.clickOnBoardFast(srcPosition / 10, srcPosition % 10) != 0)
                throw new LoadFileException("错误编码 #105 | 行棋步骤错误。");
            if (operationType == 1)
            {
                if (ClickOnBoard.clickOnBoardFast(srcPosition / 10, srcPosition % 10) != 1)
                    throw new LoadFileException("错误编码 #105 | 行棋步骤错误。");
                if (ClickOnBoard.clickOnBoardFast(destPosition / 10, destPosition % 10) != 2)
                    throw new LoadFileException("错误编码 #105 | 行棋步骤错误。");
            }
//			if (operationType==0) Operations.addOperationToStack(Integer.parseInt(Ints[0]),Integer.parseInt(Ints[1]));
//			else Operations.addOperationToStack(Integer.parseInt(Ints[0]),Integer.parseInt(Ints[1]),Integer.parseInt(Ints[2]));
            if (i == 0) CanvasUtils.set(4);
        }
//		if (side!=UserStatus.currentSide)
//			throw new LoadFileException("错误编码 #204 | 行棋方与记录种操作结果不符。");
        try
        {
            rawString = sc.nextLine();
            if (!rawString.equals("A")) UserStatus.AIMode = 2;
            else UserStatus.AIMode = sc.nextInt();
        }
        catch (Exception e)
        {
//			System.out.println("No AI mark!");
        }
        //GeneralInit.halfInit();
        anchorPane.getChildren().removeAll(chessPieceArrayList);
        chessPieceArrayList = CanvasUtils.setAllChess();
        Operations.copyOfSizeOfStack = Operations.sizeOfStack;
        ChessBoardStatus.flipCounter = 0;
        ChessBoardStatus.clearPossibleMoves();
        UserStatus.setRedScore(0);
        UserStatus.setBlackScore(0);
        UserStatus.currentSide = -1;
        ChessBoardStatus.flipCounter = 0;
        UserStatus.resetEatChessCount();
        int objIndex = -1;
        for (int i = 1; i <= 8; ++i)
        {
            for (int j = 1; j <= 4; ++j)
            {
                objIndex = ChessBoardStatus.initObjectIndex[i][j];
//				System.out.println(objIndex);
                ChessBoardStatus.chessInit(i, j, objIndex);
//				System.out.println(chessPieceArrayList.get(objIndex));
                chessPieceArrayList.get(objIndex).setTranslateX(ChessPiece.getChessXFx(j));
//				System.out.println(ChessPiece.getChessXFx(j));
                chessPieceArrayList.get(objIndex).setTranslateY(ChessPiece.getChessYFx(i));
//				System.out.println(ChessPiece.getChessYFx(j));
            }
        }
    }
}
