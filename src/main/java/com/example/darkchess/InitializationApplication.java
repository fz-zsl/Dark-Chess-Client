package com.example.darkchess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class InitializationApplication extends Application
{
    public static Stage theStartPage;
    public static MediaPlayer mediaPlayerFirst;
    @Override


    public void start(Stage stage) throws IOException
    {
        if(Preference.soundSwitch)
            startMusic();
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

    private static void startMusic()
    {
        String path = "D:/DarkChess/src/audio/BGM.mp3";
        //String path = "D:/DarkChess/src/audio/王利 - 一剪梅（8bit版）.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayerFirst = new MediaPlayer(media);
        mediaPlayerFirst.setAutoPlay(true);
        mediaPlayerFirst.setVolume(Preference.volume);
        mediaPlayerFirst.setCycleCount((int)Double.MAX_VALUE);
    }
}