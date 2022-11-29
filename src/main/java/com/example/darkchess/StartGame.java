package com.example.darkchess;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGame
{

    public static Stage thePreferenceStageSG;
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
        Board.startGame(3);
        LogIn.theStartGameStage.close();
    }

    @FXML
    void cContactUs(ActionEvent event)
    {
        Showing.Info("关注微信公众号“vitee”");
    }

    @FXML
    void cHalf(MouseEvent event)
    {
        // TODO: 2022/11/15
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
        Board.startGame(1);
        LogIn.theStartGameStage.close();
    }

    @FXML
    void cOnline(MouseEvent event)
    {
        // TODO: 2022/11/15 在线开发
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
        Showing.Info("1.0.0");
    }

}

