package com.example.darkchess;

import Piece.ChessPiece;
import algorithm.ClickOnBoard;
import algorithm.GeneralInit;
import algorithm.UndoPreviousOperation;
import autoPlayer.Greedy;
import datum.ChessBoardStatus;
import datum.UserStatus;
import fileOperations.RankList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oop.GameEndsException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Currency;

import static Properties.Property.version;
import static com.example.darkchess.StartGame.modeOfAll;


public class Board
{
    static public double gird = 50;
    static public double boardW = 4 * gird + 1f / 3 * gird;
    static public double boardH = 8 * gird + 1f / 3 * gird;
    static Canvas canvas = new Canvas(boardW + 2 * 41.65, boardH + 41.65);
    static public AnchorPane anchorPane = new AnchorPane();
    static public GraphicsContext gc = canvas.getGraphicsContext2D();
    static protected Text bTurn = new Text("先手翻棋");
    static public ArrayList<ChessPiece> chessPieceArrayList;
    static protected Text r = new Text("红方");
    static protected Text b = new Text("黑方");
    static protected Text rText = new Text("分数 0");
    static protected Text bText = new Text("分数 0");
    static Stage theBoardStage;
    static Group group = new Group();


    public static void startGame(int mode) throws IOException
    {
        chessPieceArrayList = new ArrayList<>();
        Stage stage = new Stage();
        theBoardStage = stage;

        anchorPane.setMinSize(boardW * 3, boardH * 1.5);
        anchorPane.setMinWidth(boardW * 2);
        anchorPane.setMinHeight(boardH * 1.5);

        //页面背景
        ImageView imageView1 = new ImageView("file:/D:/backgrounds/1.jpg");
        anchorPane.getChildren().add(imageView1);

        //菜单
        {
            MenuBar menuBar = new MenuBar();
            Menu menu1 = new Menu("文件");
            Menu menu2 = new Menu("设置");
            Menu menu4 = new Menu("操作");
            Menu menu3 = new Menu("帮助");
            MenuItem menuItem1 = new MenuItem("退出登录");
            MenuItem menuItem3 = new MenuItem("版本信息");
            MenuItem menuItem4 = new MenuItem("联系我们");
            MenuItem menuItem5 = new MenuItem("悔棋");
            MenuItem menuItem6 = new MenuItem("作弊模式");
            MenuItem menuItem7 = new MenuItem("重新开始");
            MenuItem menuItem = new MenuItem("返回模式选择页面");
            MenuItem menuItem2 = new MenuItem("排行榜");
            menu1.getItems().addAll(menuItem, menuItem1,menuItem2);
            menu3.getItems().addAll(menuItem6, menuItem3, menuItem4);
            menu4.getItems().addAll(menuItem5, menuItem7);
            menuBar.getMenus().addAll(menu1, menu2, menu4, menu3);

            EventHandler<ActionEvent> eventHandler1 = e ->
            {
                Showing.Info("关注微信公众号“Vitee Town”");
            };
            EventHandler<ActionEvent> eventHandler2 = new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quit.fxml"));
                    Scene scene = null;
                    try
                    {
                        scene = new Scene(fxmlLoader.load(), 320, 240);
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                    stage.setTitle("退出");
                    stage.setScene(scene);
                    stage.show();
                }
            };

            EventHandler<ActionEvent> eventHandler4 = e ->
            {
                Showing.Info(version);
            };

            EventHandler<ActionEvent> eventHandler5 = e ->
            {
                System.out.println("按下了悔棋按钮");
                CanvasUtils.cancelHighLight();
                if(Preference.soundSwitch)
                    InitializationApplication.mediaPlayerFirst.play();
                ChessPiece.judgeSound = true;
                if (ChessBoardStatus.flipCounter<1)
                    return;
                UndoPreviousOperation.undoPreviousOperation();
                //重新设定计分板
                if(modeOfAll == 1)
                {
                    Integer redScore = UserStatus.getRedScore();
                    rText.setText("分数 " + redScore.toString());
                    Integer blackScore = UserStatus.getBlackScore();
                    bText.setText("分数 " + blackScore.toString());
                    if (UserStatus.currentSide == 0)
                        bTurn.setText("轮到红方");
                    else if (UserStatus.currentSide == 1)
                        bTurn.setText("轮到黑方");
                }
                else  if(modeOfAll == 3)
                {
                    if(UserStatus.AISide == 0)
                    {
                        Integer redScore = UserStatus.getRedScore();
                        Integer blackScore = UserStatus.getBlackScore();
                        rText.setText("分数 " + blackScore.toString());
                        bText.setText("分数 " + redScore.toString());
                        if(UserStatus.currentSide == 0)
                            bTurn.setText("轮到机器");
                        else
                            bTurn.setText("轮到玩家");
                    }
                    else if (UserStatus.AISide == 1)
                    {
                        Integer redScore = UserStatus.getRedScore();
                        rText.setText("分数 " + redScore.toString());
                        Integer blackScore = UserStatus.getBlackScore();
                        bText.setText("分数 " + blackScore.toString());
                        if(UserStatus.currentSide == 1)
                            bTurn.setText("轮到机器");
                        else
                            bTurn.setText("轮到玩家");
                    }
                }

            };

            EventHandler<ActionEvent> eventHandler6 = e ->
            {
                // TODO: 2022/12/3
                Showing.Info("鼠标右击翻看棋子");
            };

            EventHandler<ActionEvent> eventHandler7 = e ->
            {
                System.out.println("restart");
                CanvasUtils.cancelHighLight();
                if(Preference.soundSwitch)
                    InitializationApplication.mediaPlayerFirst.play();
                for (ChessPiece c:chessPieceArrayList)
                {
                    anchorPane.getChildren().removeAll(c.getCircle(),c.getText());
                    c.setJudge(true);
                }
                anchorPane.getChildren().removeAll(chessPieceArrayList);
                chessPieceArrayList = CanvasUtils.setAllChess();
                CanvasUtils.set(modeOfAll);
                GeneralInit.generalInit();
                //重新设定计分板
                Integer redScore = UserStatus.getRedScore();
                rText.setText("分数 " + redScore.toString());
                Integer blackScore = UserStatus.getBlackScore();
                bText.setText("分数 " + blackScore.toString());
                if (modeOfAll == 1)
                {
                    System.out.println("mode1");
                    r.setText("红方");
                    r.setFill(Color.RED);
                    b.setText("黑方");
                    b.setFill(Color.BLACK);
                    bText.setFill(Color.BLACK);
                    rText.setFill(Color.RED);
                    bTurn.setText("先手翻棋");
                }

                else if (modeOfAll == 3)
                {
                    System.out.println("mode3");
                    r.setText("玩家");
                    r.setFill(Color.BLACK);
                    b.setText("机器");
                    b.setFill(Color.BLACK);
                    rText.setFill(Color.BLACK);
                    bText.setFill(Color.BLACK);
                    bTurn.setText("玩家翻棋");
                }

            };

            EventHandler<ActionEvent> eventHandler = e ->
            {
                System.out.println("go back");
                if(Preference.chessSound)
                    InitializationApplication.mediaPlayerFirst.play();
                for (ChessPiece c:chessPieceArrayList)
                {
                    anchorPane.getChildren().removeAll(c.getCircle(),c.getText());
                    c.setJudge(true);
                }
                anchorPane.getChildren().removeAll(chessPieceArrayList);
                LogIn.theStartGameStage.show();
                theBoardStage.close();
            };

            EventHandler<ActionEvent> eventHandler3 = e ->
            {
                if(StartGame.rankJudge)
                {
                    startRank();
                    setRank();
                }
                else
                {
                    setRank();
                    StartGame.theRankPage.show();
                }
            };

            menuItem1.setOnAction(eventHandler2);
            menuItem3.setOnAction(eventHandler4);
            menuItem4.setOnAction(eventHandler1);
            menuItem5.setOnAction(eventHandler5);
            menuItem7.setOnAction(eventHandler7);
            menuItem.setOnAction(eventHandler);
            menuItem2.setOnAction(eventHandler3);
            anchorPane.getChildren().add(menuBar);
            menuBar.setLayoutX(0);
            menuBar.setLayoutY(1);
            menuBar.setPrefWidth(900.0);
            menuBar.setPrefHeight(26.0);
        }
        //阵营显示
        //红方
        {
            r.setX(100);
            r.setY(100);
            r.setText("红方");
            r.setFill(Color.RED);
            r.setFont(new Font("verdana", 30));
            anchorPane.getChildren().add(r);
            //黑方
            b.setX(700);
            b.setY(100);
            b.setText("黑方");
            b.setFont(new Font("verdana", 30));
            anchorPane.getChildren().add(b);

            //分数显示
            //红方
            rText.setFill(Color.RED);
            rText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
            rText.setX(85);
            rText.setY(180);
            anchorPane.getChildren().add(rText);
            //黑方
            bText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
            bText.setX(685);
            bText.setY(180);
            anchorPane.getChildren().add(bText);
        }
        //棋盘背景
        ImageView imageView = new ImageView("file:D:/DarkChess/wood.jpg");
        imageView.setFitHeight(boardH);
        imageView.setFitWidth(boardW);
        imageView.setX(41.65 + 300);
        imageView.setY(41.65);
        anchorPane.getChildren().add(imageView);
        //imageView.toBack();

        //棋盘
        CanvasUtils.drawChessBoard(gc);
        canvas.setHeight(458.35);
        canvas.setWidth(300);
        canvas.setTranslateX(300);
        anchorPane.getChildren().add(canvas);
        canvas.toFront();

        //画出棋子
        chessPieceArrayList = CanvasUtils.setAllChess();

        //轮到提示
        bTurn.setFill(Color.BLACK);
        bTurn.setTranslateX(393.04);
        bTurn.setTranslateY(505.40515);
        bTurn.setFont(Font.font("verdana", FontWeight.BOLD, 30));
        anchorPane.getChildren().add(bTurn);

        //游戏初始化
        //显示界面
        group.getChildren().add(anchorPane);
        Scene scene = new Scene(group, 900, 600);
        stage.setTitle("游戏界面");
        stage.setScene(scene);
        stage.show();

        //设置棋盘，画出棋子
        if (mode == 1)
            offlineAction(group);
        else if (mode == 3)
            aiAction(group);
    }


    static public void offlineAction(Group group)
    {
        //设置棋盘，画出棋子
        CanvasUtils.set(1);
        GeneralInit.generalInit();
        EventHandler<MouseEvent> eventHandler = mouseEvent ->
        {
            int y = (int) ((mouseEvent.getX() - 341.65 - 1f / 6 * gird) / gird + 1);
            int x = (int) ((mouseEvent.getY() - 41.65 - 1f / 6 * gird) / gird + 1);
            //Showing.Info(mouseEvent.getX()+" "+mouseEvent.getY());
            System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());

            MouseButton button = mouseEvent.getButton();

            if (button == MouseButton.PRIMARY)//左击
            {
                //调用控制程序，判断棋子的类型
                if (x > 0 && x < 9 && y > 0 && y < 9)
                {
                    try
                    {
                        ClickOnBoard.clickOnBoard(x, y);
                    }
                    catch (GameEndsException e)
                    {
                        if(e.getInfo() == 0)
                            Showing.Info("红方胜利");
                        else if(e.getInfo() == 1)
                            Showing.Info("黑方胜利");
                        ChessPiece.judgeSound = false;
                        flip();
                    }
                    catch (MalformedURLException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
                //重新设定计分板
                Integer redScore = UserStatus.getRedScore();
                rText.setText("分数 " + redScore.toString());
                Integer blackScore = UserStatus.getBlackScore();
                bText.setText("分数 " + blackScore.toString());
                if (UserStatus.currentSide == 0)
                    bTurn.setText("轮到红方");
                else if (UserStatus.currentSide == 1)
                    bTurn.setText("轮到黑方");
            }
            else if (button == MouseButton.SECONDARY)
                chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(x, y)).cheatingFlip();
        };
        group.setOnMouseClicked(eventHandler);
    }

    static public void aiAction(Group group)
    {
        r.setText("玩家");
        r.setFill(Color.BLACK);
        b.setText("机器");
        rText.setFill(Color.BLACK);
        bTurn.setText("玩家翻棋");
        //玩家在左边，r代表玩家而非颜色

        //设置棋盘，画出棋子
        GeneralInit.generalInit();
        EventHandler<MouseEvent> eventHandler = mouseEvent ->
        {
            int y = (int) ((mouseEvent.getX() - 341.65 - 1f / 6 * gird) / gird + 1);
            int x = (int) ((mouseEvent.getY() - 41.65 - 1f / 6 * gird) / gird + 1);
            //Showing.Info(mouseEvent.getX()+" "+mouseEvent.getY());
            System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());

            MouseButton button = mouseEvent.getButton();

            if (button == MouseButton.PRIMARY)//左击
            {
                //调用控制程序，判断棋子的类型
                if (x > 0 && x < 9 && y > 0 && y < 9)
                {
                    try
                    {
                        ClickOnBoard.clickOnBoard(x, y);

                        //这里是GUI
                        if(UserStatus.AISide == 1)//ai是黑方
                        {
                            r.setFill(Color.RED);
                            rText.setFill(Color.RED);
                        }
                        else if (UserStatus.AISide == 0)//ai为红方
                        {
                            b.setFill(Color.RED);
                            bText.setFill(Color.RED);
                        }
                        CanvasUtils.set(3);

                        if (UserStatus.AISide == UserStatus.currentSide)
                            Greedy.greedy(UserStatus.AIDepth,true,UserStatus.AIMode);
                    }
                    catch (GameEndsException e)
                    {
                        ChessPiece.judgeSound = false;
                        flip();
                        if(Preference.soundSwitch)
                            InitializationApplication.mediaPlayerFirst.pause();
                        if(e.getInfo() == UserStatus.AISide)
                        {
                            Showing.Info("菜狗，回去多积淀积淀再来挑战爷！");
                            if(Preference.chessSound)
                                ChessPiece.mediaPlayerEnd.pause();
                            if(Preference.endSound)
                                youDied();
                        }
                        else
                        {
                            Showing.Info("厉害啊，小子！居然把爷战胜了？！");
                            //youWin();
                        }

                       // Showing.Info(e.toString());
                    }
                    catch (MalformedURLException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
                //重新设定计分板
                if(UserStatus.AISide == 0)
                {
                    Integer redScore = UserStatus.getRedScore();
                    Integer blackScore = UserStatus.getBlackScore();
                    rText.setText("分数 " + blackScore.toString());
                    bText.setText("分数 " + redScore.toString());
                }
                else if (UserStatus.AISide == 1)
                {
                    Integer redScore = UserStatus.getRedScore();
                    rText.setText("分数 " + redScore.toString());
                    Integer blackScore = UserStatus.getBlackScore();
                    bText.setText("分数 " + blackScore.toString());
                }

                if (UserStatus.AISide == UserStatus.currentSide)
                    bTurn.setText("轮到机器");
                else
                    bTurn.setText("轮到玩家");
            }
            else if (button == MouseButton.SECONDARY)
                chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(x, y)).cheatingFlip();
        };
        group.setOnMouseClicked(eventHandler);
    }

     private static void flip()
    {
        for(ChessPiece c: chessPieceArrayList)
            c.flipAChess();
    }

    private static void youDied()
    {
        String path = "D:/DarkChess/src/audio/结束1.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(Preference.volume);
        mediaPlayer.setCycleCount(1);
        System.out.println("you died");
    }

    private static void  youWin()
    {
        String path = "D:/CloudMusic/百石元 - Spilled tea.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(Preference.volume);
        mediaPlayer.setCycleCount(1);
        System.out.println("you died");
    }


    private static Text first = new Text("空");
    private static Text second = new Text("空");
    private static Text third = new Text("空");
    private static Text forth = new Text("空");
    private static Text fifth = new Text("空");
    private static Text yourScore = new Text("空");
    private static Text yourRank = new Text("空");
    public static void startRank()
    {
        Stage stage1 = new Stage();
        StartGame.theRankPage = stage1;
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setMinSize(600, 400);
        anchorPane1.setMinWidth(600);
        anchorPane1.setMinHeight(400);
        //页面背景
        ImageView imageView = new ImageView("file:/D://backgrounds//4.jpg");
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);
        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        anchorPane1.getChildren().add(imageView);

        first.setFill(Color.GREEN);
        second.setFill(Color.GRAY);
        third.setFill(Color.RED);

        first.setTranslateX(206);
        second.setTranslateX(206);
        third.setTranslateX(206);
        forth.setTranslateX(206);
        fifth.setTranslateX(206);
        yourScore.setTranslateX(273);
        yourRank.setTranslateX(273);

        first.setTranslateY(87);
        second.setTranslateY(131);
        third.setTranslateY(181);
        forth.setTranslateY(233);
        fifth.setTranslateY(279);
        yourScore.setTranslateY(342);
        yourRank.setTranslateY(373);

        first.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 27));
        second.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 27));
        third.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 27));
        forth.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 27));
        fifth.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 27));
        yourScore.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        yourRank.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));

        anchorPane1.getChildren().addAll(first, second, third, forth, fifth, yourRank, yourScore);

        Group group1 = new Group();
        group1.getChildren().add(anchorPane1);
        Scene scene1 = new Scene(group1, 600, 400);
        stage1.setTitle("排行榜");
        stage1.setScene(scene1);
        stage1.show();
    }

    public static void setRank()
    {
        System.out.println(RankList.getRankList().size());
        System.out.println(RankList.getRankList().get(0).userName + " " + RankList.getRankList().get(0).credit);
        first.setText("slgg");
        first.setText(RankList.getRankList().get(0).userName + " " + RankList.getRankList().get(0).credit);
        second.setText(RankList.getRankList().get(1).userName + " " + RankList.getRankList().get(0).credit);
        third.setText(RankList.getRankList().get(2).userName + " " + RankList.getRankList().get(0).credit);
        forth.setText(RankList.getRankList().get(3).userName + " " + RankList.getRankList().get(0).credit);
        fifth.setText(RankList.getRankList().get(4).userName + " " + RankList.getRankList().get(0).credit);
        Integer a = RankList.getRankList().get(RankList.findUserRank()).credit;
        yourScore.setText(a.toString());
        a = RankList.findUserRank() + 1;
        yourRank.setText(a.toString());
    }
}
