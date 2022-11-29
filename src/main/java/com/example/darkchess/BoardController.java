package com.example.darkchess;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.darkchess.Board.gird;

public class BoardController
{

    @FXML
    private Text bScore;

    @FXML
    private MenuItem contectUs;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem preference;

    @FXML
    private Text rScore;

    @FXML
    private MenuItem version;

    @FXML
    void cContactUs(ActionEvent event)
    {

    }

    @FXML
    void cLogOut(ActionEvent event)
    {

    }

    @FXML
    void cPreference(ActionEvent event)
    {

    }

    @FXML
    void cVersion(ActionEvent event)
    {

    }

    @FXML
    void onMouseClicked(MouseEvent event)throws IOException
    {
        int x = (int) ((event.getX() - 1f / 6 * gird)/gird + 1);
        int y = (int) ((event.getY() - 1f / 6 * gird)/gird + 1);

        Timer timer = new Timer();//先new一个定时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {//我测试的是自动更新一个函数结果
                        //这一块设置更新的代码
                        rScore.setText(x +" "+ y);
                    }
                });

            }
        },100,500);//定时器的延迟时间及间隔时间，不要设置太小

    }

}
