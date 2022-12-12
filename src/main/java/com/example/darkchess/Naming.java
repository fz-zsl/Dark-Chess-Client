package com.example.darkchess;

import Piece.ChessPiece;
import datum.Operations;
import datum.UserStatus;
import fileOperations.LoadGameFile;
import fileOperations.SaveGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.GameEndsException;
import oop.LoadFileException;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.example.darkchess.Board.*;

public class Naming
{

    @FXML
    private TextField name;
    private String str;

    @FXML
    void clickOK(MouseEvent event)
    {
        try
        {
            System.out.println(str);
            SaveGame.writeGameFile(str);
            SaveGame.writeUserFile();
            MenuItem menuItem21 = new MenuItem(str + ".game.txt");
            Board.menu5.getItems().add(menuItem21);
            str = str + ".game.txt";
            EventHandler<ActionEvent> eventHandler = e ->
            {
                inFlag = true;
                r.setText("玩家");
                r.setFill(Color.BLACK);
                rText.setFill(Color.BLACK);
                b.setText("对手");
                b.setFill(Color.BLACK);
                bText.setFill(Color.BLACK);
                bTurn.setText("点击回顾");
                rText.setText("分数 " + 0);
                Board.bText.setText("分数 " + 0);
                for (ChessPiece c : chessPieceArrayList)
                {
                    anchorPane.getChildren().removeAll(c.getCircle(), c.getText());
                    c.setJudge(true);
                }
                anchorPane.getChildren().removeAll(chessPieceArrayList);

                try
                {
                    LoadGameFile.loadGameFile(str);
                    nextButton.setOnMouseClicked(new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent mouseEvent)
                        {
                            right = UserStatus.AISide;
                            if(!inFlag)
                                return;
                            try
                            {
                                if (!Operations.loadNextMove())
                                {
                                    inFlag = false;
                                    if(Choice.choiceStageFlage)
                                    {
                                        if(UserStatus.AISide == 0)
                                        {
                                            Integer redScore = UserStatus.getRedScore();
                                            Integer blackScore = UserStatus.getBlackScore();
                                            rText.setText("分数 " + blackScore.toString());
                                            bText.setText("分数 " + redScore.toString());
                                            bText.setFill(Color.RED);
                                            b.setFill(Color.RED);
                                            rText.setFill(Color.BLACK);
                                            r.setFill(Color.BLACK);
                                        }
                                        else if (UserStatus.AISide == 1)
                                        {
                                            Integer redScore = UserStatus.getRedScore();
                                            rText.setText("分数 " + redScore.toString());
                                            Integer blackScore = UserStatus.getBlackScore();
                                            bText.setText("分数 " + blackScore.toString());
                                            rText.setFill(Color.RED);
                                            r.setFill(Color.RED);
                                            bText.setFill(Color.BLACK);
                                            b.setFill(Color.BLACK);
                                        }
                                        else if(UserStatus.AISide == -1)
                                        {
                                            Integer redScore = UserStatus.getRedScore();
                                            rText.setText("分数 " + redScore.toString());
                                            Integer blackScore = UserStatus.getBlackScore();
                                            bText.setText("分数 " + blackScore.toString());
                                            rText.setFill(Color.RED);
                                            r.setFill(Color.RED);
                                            bText.setFill(Color.BLACK);
                                            b.setFill(Color.BLACK);
                                        }

                                        if (UserStatus.AISide == UserStatus.currentSide)
                                            bTurn.setText("轮到对手");
                                        else
                                            bTurn.setText("轮到玩家");
                                        Stage stage33 = new Stage();
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choice.fxml"));
                                        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                                        stage33.setTitle("选择模式");
                                        stage33.setScene(scene);
                                        stage33.show();
                                        Choice.choiceStageFlage = false;
                                        Choice.theChoiceStage = stage33;
                                    }
                                    else
                                        Choice.theChoiceStage.show();
                                }

                                if(UserStatus.AISide == 0)
                                {
                                    Integer redScore = UserStatus.getRedScore();
                                    Integer blackScore = UserStatus.getBlackScore();
                                    rText.setText("分数 " + blackScore.toString());
                                    bText.setText("分数 " + redScore.toString());
                                    bText.setFill(Color.RED);
                                    b.setFill(Color.RED);
                                    rText.setFill(Color.BLACK);
                                    r.setFill(Color.BLACK);
                                }
                                else if (UserStatus.AISide == 1)
                                {
                                    Integer redScore = UserStatus.getRedScore();
                                    rText.setText("分数 " + redScore.toString());
                                    Integer blackScore = UserStatus.getBlackScore();
                                    bText.setText("分数 " + blackScore.toString());
                                    rText.setFill(Color.RED);
                                    r.setFill(Color.RED);
                                    bText.setFill(Color.BLACK);
                                    b.setFill(Color.BLACK);
                                }
                                else if(UserStatus.AISide == -1)
                                {
                                    Integer redScore = UserStatus.getRedScore();
                                    rText.setText("分数 " + redScore.toString());
                                    Integer blackScore = UserStatus.getBlackScore();
                                    bText.setText("分数 " + blackScore.toString());
                                    rText.setFill(Color.RED);
                                    r.setFill(Color.RED);
                                    bText.setFill(Color.BLACK);
                                    b.setFill(Color.BLACK);
                                }

                                if (UserStatus.AISide == UserStatus.currentSide)
                                    bTurn.setText("轮到对手");
                                else
                                    bTurn.setText("轮到玩家");


                            }
                            catch (Exception ex)
                            {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                }
                catch (LoadFileException ex)
                {
                    Showing.Alarm(ex.toString());
                }
                catch (GameEndsException ex)
                {
                    Showing.Alarm("游戏已经结束");
                }
                catch (MalformedURLException ex)
                {
                    throw new RuntimeException(ex);
                }
            };
            menuItem21.setOnAction(eventHandler);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Showing.Info("进度已保存");
        Board.theNamingStage.close();
    }

    @FXML
    void keyPressed(KeyEvent event)
    {
        str = name.getText();
    }

}
