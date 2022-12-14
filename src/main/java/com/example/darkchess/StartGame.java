package com.example.darkchess;


import Piece.ChessPiece;
import algorithm.GeneralInit;
import datum.UserStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import net.sf.json.JSONObject;
import socket.Client;

import java.io.IOException;
import java.net.Socket;

import static com.example.darkchess.Board.*;
import static com.example.darkchess.StartPage.preferenceBoolean;
import static com.example.darkchess.StartPage.thePreferenceStage;

public class StartGame
{
    public static Boolean rankJudge = true;
    public static Stage theRankPage;
    private static Boolean judge = true;
    public static Stage thePreferenceStageSG;
    public static int modeOfAll = 0;
    @FXML
    private MenuItem contactUs;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem preference;

    @FXML
    private MenuItem version;

    @FXML
    void cComputer(MouseEvent event)throws IOException
    {
        if(Preference.gamingSwitch)
            InitializationApplication.mediaPlayerFirst.pause();
        modeOfAll = 3;
        if(judge)
        {
            Board.startGame(3);
            LogIn.theStartGameStage.close();
        }
        else
        {
            CanvasUtils.cancelHighLight();
            chessPieceArrayList = CanvasUtils.setAllChess();
            GeneralInit.generalInit();
            Board.imageView1.setImage(new Image("file:/" + Preference.pictureAddressUse));
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
    void cHalf(MouseEvent event) throws IOException
    {
        //下一步的按钮
        nextButton = new Button("下一步");
        nextButton.setTranslateX(380.04);
        nextButton.setTranslateY(525.40515);
        nextButton.setPrefWidth(180);
        nextButton.setPrefHeight(50);
        nextButton.setFont(Board.font);

        if(Preference.gamingSwitch)
            InitializationApplication.mediaPlayerFirst.pause();
        modeOfAll = 4;
        if(judge)
        {
            Board.startGame(4);
            anchorPane.getChildren().add(nextButton);
            LogIn.theStartGameStage.close();
        }
        else
        {
            CanvasUtils.cancelHighLight();
            GeneralInit.generalInit();
            Board.imageView1.setImage(new Image("file:/" + Preference.pictureAddressUse));
            for(ChessPiece c:chessPieceArrayList)
            {
                anchorPane.getChildren().removeAll(c.getCircle(),c.getText());
                c.setJudge(true);
            }
            anchorPane.getChildren().removeAll(chessPieceArrayList);
            anchorPane.getChildren().add(nextButton);
            System.out.println("mode4");
            Integer redScore = UserStatus.getRedScore();
            Board.rText.setText("分数 " + redScore.toString());
            Integer blackScore = UserStatus.getBlackScore();
            Board.bText.setText("分数 " + blackScore.toString());
            Board.r.setText("玩家");
            Board.r.setFill(Color.BLACK);
            Board.rText.setFill(Color.BLACK);
            Board.b.setText("对手");
            Board.b.setFill(Color.BLACK);
            Board.bText.setFill(Color.BLACK);
            Board.bTurn.setText("场景回顾");
            Board.theBoardStage.show();
            LogIn.theStartGameStage.close();
            Board.halfAction(Board.group);
        }
        judge = false;
    }

    @FXML
    void cLogOut(ActionEvent event) throws IOException
    {
        LogIn.theStartGameStage.close();
        InitializationApplication.theStartPage.show();
    }

    @FXML
    void cOffline(MouseEvent event) throws IOException

    {
        System.out.println(StartGame.judge);
        if(Preference.gamingSwitch)
            InitializationApplication.mediaPlayerFirst.pause();
        modeOfAll = 1;
        if(judge)
        {
            Board.startGame(1);
            LogIn.theStartGameStage.close();
        }
        else
        {
            CanvasUtils.cancelHighLight();
            chessPieceArrayList = CanvasUtils.setAllChess();
            CanvasUtils.set(1);
            GeneralInit.generalInit();
            Board.imageView1.setImage(new Image("file:/" + Preference.pictureAddressUse));
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
    void cOnline(MouseEvent event) throws IOException
    {
        if(Preference.gamingSwitch)
            InitializationApplication.mediaPlayerFirst.pause();
        modeOfAll = 2;
        new Client();
        JSONObject message = new JSONObject();
        message.put("signalType",1);
        message.put("actionType",2);
        message.put("password", LogIn.password);
        message.put("userName", LogIn.account);
        Client.sendMessage(message);
        chessPieceArrayList = CanvasUtils.setAllChess();
        if(judge)
        {
            Board.startGame(2);
        }
        else
        {
            Board.theBoardStage.show();
            LogIn.theStartGameStage.close();
        }
       LogIn.theStartGameStage.close();
        judge = false;
    }

    @FXML
    void cPreference(ActionEvent event) throws IOException
    {
        if(preferenceBoolean)
        {
            Stage stage22 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("preference.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            stage22.setTitle("个性化设置");
            stage22.setScene(scene);
            stage22.show();
            thePreferenceStage = stage22;
        }
        else
            thePreferenceStage.show();
    }

    @FXML
    void cVersion(ActionEvent event)
    {
        Showing.Info(Preference.version);
    }

}

