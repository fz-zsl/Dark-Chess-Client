package com.example.darkchess;

import fileOperations.LoginInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn
{
    private static String account;
    private static String password;
    public static Stage theStartGameStage;

    @FXML
    private Button logIn;

    @FXML
    private MenuItem contectUs;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem preference;

    @FXML
    private MenuItem version;

    @FXML
    void cContactUs(ActionEvent event)
    {
        Showing.Info("关注微信公众号“vitee”");
    }


    @FXML
    void cLogOut(ActionEvent event)throws IOException
    {
        StartPage.theLogInStage.close();
        InitializationApplication.theStartPage.show();
        /*
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("退出");
        stage.setScene(scene);
        stage.show();

         */
    }

    @FXML
    void cPreference(ActionEvent event)throws IOException
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
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldUser;

    @FXML
    void cLogIn(MouseEvent event) throws IOException
    {
        try
        {
            LoginInterface.userLogIn(account, password);
        }
        catch (Exception e)
        {
            Showing.Alarm(e.toString());
            return;
        }
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(InitializationApplication.class.getResource("startGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("模式选择");
        stage.setScene(scene);
        stage.show();
        LogIn.theStartGameStage = stage;
        StartPage.theLogInStage.close();

    }



    @FXML
    void onKeyTypedAccount(KeyEvent event)
    {
        account = textFieldUser.getText();
        //Showing.Info(account);
    }

    @FXML
    void onKeyTypedPassword(KeyEvent event)
    {
        password = textFieldPassword.getText();
        //Showing.Info(password);
    }

}


