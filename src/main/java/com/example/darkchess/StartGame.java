package com.example.darkchess;


import Piece.ChessPiece;
import algorithm.GeneralInit;
import datum.UserStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.darkchess.Board.anchorPane;
import static com.example.darkchess.Board.chessPieceArrayList;

public class StartGame
{

    private static Boolean judge = true;
    public static Stage thePreferenceStageSG;
    public static int modeOfAll = 0;
    @FXML
    private MenuItem contectUs;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem preference;

    @FXML
    private MenuItem version;

    @FXML
    void cComputer(MouseEvent event)throws IOException
    {
        if(Preference.soundSwitch)
            InitializationApplication.mediaPlayerFirst.pause();
        modeOfAll = 3;
        if(judge)
        {
            Board.startGame(3);
            LogIn.theStartGameStage.close();
        }
        else
        {
            chessPieceArrayList = CanvasUtils.setAllChess();
            CanvasUtils.set(3);
            GeneralInit.generalInit();
            //重新设定计分板
            Integer redScore = UserStatus.getRedScore();
            Board.rText.setText("分数 " + redScore.toString());
            Integer blackScore = UserStatus.getBlackScore();
            Board.bText.setText("分数 " + blackScore.toString());
            Board.r.setText("玩家");
            Board.r.setFill(Color.BLACK);
            Board.rText.setFill(Color.BLACK);
            Board.b.setText("机器");
            Board.b.setFill(Color.BLACK);
            Board.bText.setFill(Color.BLACK);
            Board.bTurn.setText("玩家翻棋");
            Board.theBoardStage.show();
            LogIn.theStartGameStage.close();
            Board.aiAction(Board.group);
        }
        judge = false;
    }

    @FXML
    void cContactUs(ActionEvent event)
    {
        Showing.Info("关注微信公众号“Vitee Town”");
    }

    @FXML
    void cHalf(MouseEvent event)
    {

        // TODO: 2022/11/15
        judge = false;
    }

    @FXML
    void cLogOut(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("退出");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cOffline(MouseEvent event) throws IOException
    {
        if(Preference.soundSwitch)
            InitializationApplication.mediaPlayerFirst.pause();
        modeOfAll = 1;
        if(judge)
        {
            Board.startGame(1);
            LogIn.theStartGameStage.close();
        }
        else
        {
            chessPieceArrayList = CanvasUtils.setAllChess();
            CanvasUtils.set(1);
            GeneralInit.generalInit();
            //重新设定计分板
            Integer redScore = UserStatus.getRedScore();
            Board.rText.setText("分数 " + redScore.toString());
            Integer blackScore = UserStatus.getBlackScore();
            Board.bText.setText("分数 " + blackScore.toString());
            Board.r.setText("红方");
            Board.r.setFill(Color.RED);
            Board.rText.setFill(Color.RED);
            Board.b.setText("黑方");
            Board.b.setFill(Color.BLACK);
            Board.bText.setFill(Color.BLACK);
            Board.bTurn.setText("先手翻棋");
            Board.theBoardStage.show();
            LogIn.theStartGameStage.close();
            Board.offlineAction(Board.group);
        }
        judge = false;
    }

    @FXML
    void cOnline(MouseEvent event)
    {
        // TODO: 2022/11/15 在线开发
        judge = false;
    }

    @FXML
    void cPreference(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("preference.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("偏好设置");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cVersion(ActionEvent event)
    {
        Showing.Info(Preference.version);
    }

}

