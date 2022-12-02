package com.example.darkchess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Preference
{
    public static double time = 0.1;
    public static String version = "v 1.1.6";
    public static double volume = 0.3;
    public static double volumeOfFlip = 1;
    public static Boolean cheatingSoundInfo = true;
    public static Boolean filpSoundInfo = true;
    public static Boolean reRemoveSoundInfo = true;
    public static Boolean soundSwitch = false;
    public static Boolean chessSound = false;
    public static Boolean endSound = false;

    @FXML
    private MenuItem contectUs;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem preference;

    @FXML
    private MenuItem mversion;


    @FXML
    private TextField timeField;

    @FXML
    void cContactUs(ActionEvent event)
    {
        Showing.Info("关注微信公众号“vitee”");
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

    @FXML
    void setTime(KeyEvent event)
    {
        time = Integer.parseInt(timeField.getText());
    }

}

