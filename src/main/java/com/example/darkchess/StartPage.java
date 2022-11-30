package com.example.darkchess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPage {
    @FXML
    private MenuItem contectUs;

    @FXML
    private Button login;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem preference;

    @FXML
    private Button register;

    @FXML
    private MenuItem version;

    public static Stage theRegisterStage;
    public static Stage theLogInStage;
    public static Stage thePreferenceStageSP;


    @FXML
    void cContactUs(ActionEvent event)
    {
        Showing.Info("关注微信公众号“vitee”");
    }

    @FXML
    void cLogOut(ActionEvent event) throws IOException
    {
        // TODO: 2022/11/15 添加保存信息的功能

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
    void cRegister(MouseEvent event) throws IOException
    {
        InitializationApplication.theStartPage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("登录界面");
        stage.setScene(scene);
        stage.show();
        StartPage.theRegisterStage = stage;
    }

    @FXML
    void cLogIn(MouseEvent event)throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("登录界面");
        stage.setScene(scene);
        stage.show();
        StartPage.theLogInStage = stage;
        InitializationApplication.theStartPage.close();
    }

    @FXML
    void cVersion(ActionEvent event)
    {
        Showing.Info(Preference.version);
    }

}
