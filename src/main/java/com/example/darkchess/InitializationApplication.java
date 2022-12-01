package com.example.darkchess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class InitializationApplication extends Application
{
    public static Stage theStartPage;
    @Override


    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(InitializationApplication.class.getResource("startPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("登录界面");
        stage.setScene(scene);
        stage.show();
        InitializationApplication.theStartPage = stage;
    }

    public static void main(String[] args)
    {
        launch();
    }
}