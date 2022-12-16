package com.example.darkchess;

import Piece.ChessPiece;
import algorithm.ClickOnBoard;
import algorithm.GeneralInit;
import algorithm.UndoPreviousOperation;
import autoPlayer.Greedy;
import datum.ChessBoardStatus;
import datum.Operations;
import datum.UserStatus;
import fileOperations.GetFileList;
import fileOperations.LoadGameFile;
import fileOperations.RankList;
import fileOperations.SaveGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import net.sf.json.JSONObject;
import oop.GameEndsException;
import oop.LoadFileException;
import socket.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static Properties.Property.version;
import static com.example.darkchess.StartGame.modeOfAll;
import static com.example.darkchess.StartPage.preferenceBoolean;
import static com.example.darkchess.StartPage.thePreferenceStage;


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
        public static Text rName = new Text("用户一 ");
        public static Text bName = new Text("用户二 ");
        static protected Text rText = new Text("分数 0");
        static protected Text bText = new Text("分数 0");
        static Stage theBoardStage;
        public static Group group = new Group();
        public static boolean cheatingFlag = false;
        public static Stage theNamingStage;
        public static boolean namingStageFlag = true;

        public static ImageView ir;
        public static ImageView ib;
        public static Button nextButton;

        public static ImageView imageView1;
        public static boolean inFlag = true;
        public static int right = 0;
        public static Menu menu5;

        public static double rX = 100;
        public static double rTextX = 85;
        public static double irX = 80;
        public static double rY = 250;
        public static double rTextY = 380;
        public static double irY = 100;
        public static double rNameX = 90;
        public static double rNameY = 310;
        public static double rProgressBarX = 80;
        public static double rProgressBarY = 70;
        public static ProgressBar rProgressBar = new ProgressBar(0);
        public static ProgressBar bProgressBar = new ProgressBar(0);
        public static Font font;

        static
        {
            try
            {
                font = setFont();
            }
            catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }

    public static void startGame(int mode) throws IOException
    {
        rName.setTextAlignment(TextAlignment.CENTER);
        bName.setTextAlignment(TextAlignment.CENTER);
        chessPieceArrayList = new ArrayList<>();
        Stage stage = new Stage();
        theBoardStage = stage;

        anchorPane.setMinSize(boardW * 3, boardH * 1.5);
        anchorPane.setMinWidth(boardW * 2);
        anchorPane.setMinHeight(boardH * 1.5);

        //页面背景
        imageView1 = new ImageView("file:/" + Preference.pictureAddressUse);
        imageView1.setFitWidth(900);
        imageView1.setFitHeight(600);
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
            MenuItem menuItem6 = new MenuItem("开启作弊模式");
            MenuItem menuItem7 = new MenuItem("重新开始");
            MenuItem menuItem = new MenuItem("上一级");
            MenuItem menuItem2 = new MenuItem("排行榜");
            MenuItem menuItem14 = new MenuItem("保存");
            MenuItem menuItem15 = new MenuItem("个性化设置");

            Menu menu = new Menu("人机难度设置");
            MenuItem menuItem8 = new MenuItem("幼儿园难度");
            MenuItem menuItem9 = new MenuItem("小学二年级");
            MenuItem menuItem10 = new MenuItem("初中三年级");
            MenuItem menuItem11 = new MenuItem("量子力学");
            menu.getItems().addAll(menuItem8, menuItem9, menuItem10, menuItem11);
            menu2.getItems().addAll(menu, menuItem15);


            EventHandler<ActionEvent> eventHandler15 = new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    if (preferenceBoolean)
                    {
                        Stage stage22 = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("preference.fxml"));
                        Scene scene = null;
                        try
                        {
                            scene = new Scene(fxmlLoader.load(), 640, 480);
                        }
                        catch (IOException e)
                        {
                            throw new RuntimeException(e);
                        }
                        stage22.setTitle("个性化设置");
                        stage22.setScene(scene);
                        stage22.show();
                        thePreferenceStage = stage22;
                    }
                    else
                        thePreferenceStage.show();
                }
            };
            menuItem15.setOnAction(eventHandler15);


            menu5 = new Menu("导入");
            for (String str : GetFileList.getFileList())
            {
                MenuItem menuItem12 = new MenuItem(str);
                menu5.getItems().add(menuItem12);
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
                    Board.rText.setText("分数 " + 0);
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
                menuItem12.setOnAction(eventHandler);
            }


            EventHandler<ActionEvent> eventHandler8 = e ->
            {
                UserStatus.AIMode = 0;
                Showing.Info("已设置为幼儿园难度");
            };
            menuItem8.setOnAction(eventHandler8);

            EventHandler<ActionEvent> eventHandler9 = e ->
            {
                UserStatus.AIMode = 1;
                Showing.Info("已设置为小学二年级难度");
            };
            menuItem9.setOnAction(eventHandler9);

            EventHandler<ActionEvent> eventHandler10 = e ->
            {
                UserStatus.AIMode = 2;
                Showing.Info("已设置为初中二年级难度");
            };
            menuItem9.setOnAction(eventHandler10);

            EventHandler<ActionEvent> eventHandler11 = e ->
            {
                UserStatus.AIMode = 3;
                Showing.Info("已设置为量子力学难度");
            };
            menuItem9.setOnAction(eventHandler11);

            menu1.getItems().addAll(menuItem14,menu5, menuItem1,menuItem, menuItem2);
            menu3.getItems().addAll(menuItem3, menuItem4);
            menu4.getItems().addAll(menuItem6, menuItem5, menuItem7);
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
                    CanvasUtils.reRec();
                    try {
                        SaveGame.writeUserFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    anchorPane.getChildren().removeAll(chessPieceArrayList);
                    theBoardStage.close();
                    InitializationApplication.theStartPage.show();
                    rName.setText("用户一");
                    bName.setText("深蓝一号");
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
                if (Preference.soundSwitch)
                    InitializationApplication.mediaPlayerFirst.play();
                ChessPiece.judgeSound = true;
                if (ChessBoardStatus.flipCounter < 1)
                    return;
                UndoPreviousOperation.undoPreviousOperation();
                //重新设定计分板
                if (modeOfAll == 1)
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
                else if (modeOfAll == 3)
                {
                    if (UserStatus.AISide == 0)
                    {
                        Integer redScore = UserStatus.getRedScore();
                        Integer blackScore = UserStatus.getBlackScore();
                        rText.setText("分数 " + blackScore.toString());
                        bText.setText("分数 " + redScore.toString());
                        if (UserStatus.currentSide == 0)
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
                        if (UserStatus.currentSide == 1)
                            bTurn.setText("轮到机器");
                        else
                            bTurn.setText("轮到玩家");
                    }
                }
                else if(modeOfAll == 4)
                {
                    if(Choice.modeOfHalf == 2)
                    {
                        b.setText("机器");
                        if (UserStatus.AISide == 0)
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
                    else if(Choice.modeOfHalf == 1)
                    {
                        //重新设定计分板
                        if(right == 0)
                        {
                            Integer redScore = UserStatus.getRedScore();
                            bText.setText("分数 " + redScore.toString());
                            Integer blackScore = UserStatus.getBlackScore();
                            rText.setText("分数 " + blackScore.toString());
                        }
                        else
                        {
                            Integer redScore = UserStatus.getRedScore();
                            rText.setText("分数 " + redScore.toString());
                            Integer blackScore = UserStatus.getBlackScore();
                            bText.setText("分数 " + blackScore.toString());
                        }
                        if (UserStatus.currentSide == 0)
                            bTurn.setText("轮到红方");
                        else if (UserStatus.currentSide == 1)
                            bTurn.setText("轮到黑方");
                    }
                }
            };

            EventHandler<ActionEvent> eventHandler6 = e ->
            {
                if (!cheatingFlag)
                {
                    Showing.Info("已开启作弊模式，鼠标右击翻看棋子");
                    menuItem6.setText("关闭作弊模式");
                    cheatingFlag = true;
                }
                else
                {
                    Showing.Info("作弊模式关闭");
                    menuItem6.setText("开启作弊模式");
                    cheatingFlag = false;
                }
            };

            EventHandler<ActionEvent> eventHandler7 = e ->
            {
                CanvasUtils.reRec();
                CanvasUtils.cancelHighLight();
                if (Preference.soundSwitch)
                    InitializationApplication.mediaPlayerFirst.play();
                for (ChessPiece c : chessPieceArrayList)
                {
                    anchorPane.getChildren().removeAll(c.getCircle(), c.getText());
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
                    anchorPane.getChildren().remove(nextButton);
                    //anchorPane.getChildren().removeAll(ir,ib);
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
                    anchorPane.getChildren().remove(nextButton);
                    System.out.println("mode3");
                    r.setText("玩家");
                    r.setFill(Color.BLACK);
                    b.setText("机器");
                    b.setFill(Color.BLACK);
                    rText.setFill(Color.BLACK);
                    bText.setFill(Color.BLACK);
                    bTurn.setText("玩家翻棋");
                }
                else if (modeOfAll == 4)
                {
                    for (ChessPiece c : chessPieceArrayList)
                    {
                        anchorPane.getChildren().removeAll(c.getCircle(), c.getText());
                        c.setJudge(true);
                    }
                    anchorPane.getChildren().removeAll(chessPieceArrayList);
                    System.out.println("mode4");
                    r.setText("玩家");
                    r.setFill(Color.BLACK);
                    b.setText("机器");
                    b.setFill(Color.BLACK);
                    rText.setFill(Color.BLACK);
                    bText.setFill(Color.BLACK);
                    bTurn.setText("玩家翻棋");
                }
            };
            menuItem7.setOnAction(eventHandler7);

            EventHandler<ActionEvent> eventHandler = e ->
            {
                CanvasUtils.reRec();
                if (Preference.chessSound)
                    InitializationApplication.mediaPlayerFirst.play();
                for (ChessPiece c : chessPieceArrayList)
                {
                    anchorPane.getChildren().removeAll(c.getCircle(), c.getText());
                    c.setJudge(true);
                }
                anchorPane.getChildren().removeAll(chessPieceArrayList);
                anchorPane.getChildren().removeAll(ir, ib, nextButton);
                LogIn.theStartGameStage.show();
                theBoardStage.close();
            };

            EventHandler<ActionEvent> eventHandler3 = e ->
            {
                if (StartGame.rankJudge)
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

            EventHandler<ActionEvent> eventHandler14 = e ->
            {
                if (namingStageFlag)
                {
                    Stage theNamingStage1 = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(InitializationApplication.class.getResource("naming.fxml"));
                    Scene scene = null;
                    try
                    {
                        scene = new Scene(fxmlLoader.load(), 320, 240);
                    }
                    catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                    theNamingStage1.setTitle("输入");
                    theNamingStage1.setScene(scene);
                    theNamingStage1.show();
                    theNamingStage = theNamingStage1;
                    namingStageFlag = false;
                }
                else
                    theNamingStage.show();
            };

            menuItem1.setOnAction(eventHandler2);
            menuItem3.setOnAction(eventHandler4);
            menuItem4.setOnAction(eventHandler1);
            menuItem5.setOnAction(eventHandler5);
            menuItem.setOnAction(eventHandler);
            menuItem2.setOnAction(eventHandler3);
            menuItem6.setOnAction(eventHandler6);
            menuItem14.setOnAction(eventHandler14);
            anchorPane.getChildren().add(menuBar);
            menuBar.setLayoutX(0);
            menuBar.setLayoutY(0);
            menuBar.setPrefWidth(900.0);
            menuBar.setPrefHeight(26.0);
        }
        //阵营显示
        //红方
        {
            r.setX(rX);
            r.setY(rY);
            r.setText("红方");
            r.setFill(Color.RED);
            r.setFont(font);
            anchorPane.getChildren().add(r);
            //黑方
            b.setX(rX + 600);
            b.setY(rY);
            b.setText("黑方");
            b.setFont(setFont());
            b.setFont(font);
            anchorPane.getChildren().add(b);

            //分数显示
            //红方
            rText.setFill(Color.RED);
            rText.setFont(font);
            rText.setX(rTextX);
            rText.setY(rTextY);
            anchorPane.getChildren().add(rText);
            //黑方
            rText.setFont(font);
            bText.setFont(font);
            bText.setX(rTextX + 600);
            bText.setY(rTextY);
            bText.setFill(Color.BLACK);
            anchorPane.getChildren().add(bText);

            rName.setTranslateX(rNameX);
            bName.setTranslateX(rNameX + 600);
            rName.setTranslateY(rNameY);
            bName.setTranslateY(rNameY);
            rName.setFont(font);
            bName.setFont(font);
            rName.setFill(Color.BURLYWOOD);
            bName.setFill(Color.BURLYWOOD);
            anchorPane.getChildren().addAll(rName,bName);
        }
        //棋盘背景imageView
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
        bTurn.setFont(font);
        anchorPane.getChildren().add(bTurn);
        
        //时间条
        rProgressBar.setTranslateX(rProgressBarX);
        rProgressBar.setTranslateY(rProgressBarY);
        bProgressBar.setTranslateX(rProgressBarX + 600);
        bProgressBar.setTranslateY(rProgressBarY);
        anchorPane.getChildren().addAll(rProgressBar,bProgressBar);

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
        else if (mode == 4)
        {
            halfAction(group);
        }
        else if(mode == 2)
        {
            online(group);
        }
    }


    static public void offlineAction(Group group)
    {
        //设置棋盘，画出棋子
        CanvasUtils.set(1);
        GeneralInit.generalInit();
        //头像
        ir = new ImageView("D:/backgrounds/hb3.png");
        ir.setTranslateX(irX);
        ir.setTranslateY(irY);
        ir.setFitHeight(100);
        ir.setFitWidth(100);
        //ib = new ImageView("file:/D:/backgrounds/hb1.jpg");
        ib = new ImageView("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2F9f%2F6b%2F59%2F9f6b59e3c4e2f9abd632e6f73143d65b.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1673610777&t=2b5744ba74d57757f46369d242ec900c");
        ib.setTranslateX(irX + 600);
        ib.setTranslateY(irY);
        ib.setFitHeight(100);
        ib.setFitWidth(100);
        rName.setText(LogIn.account);
        bName.setText(null);
        if (!anchorPane.getChildren().contains(ir))
        {
            anchorPane.getChildren().add(ir);
            anchorPane.getChildren().add(ib);
        }
        EventHandler<MouseEvent> eventHandler = mouseEvent ->
        {
            int y = (int) ((mouseEvent.getX() - 341.65 - 1f / 6 * gird) / gird + 1);
            int x = (int) ((mouseEvent.getY() - 41.65 - 1f / 6 * gird) / gird + 1);
            //System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());

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
                        Integer redScore = UserStatus.getRedScore();
                        rText.setText("分数 " + redScore.toString());
                        Integer blackScore = UserStatus.getBlackScore();
                        bText.setText("分数 " + blackScore.toString());
                        if (e.getInfo() == 0)
                            Showing.Info("红方胜利");
                        else if (e.getInfo() == 1)
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
        ir = new ImageView("file:/" + Preference.headAddressUse);
        ir.setTranslateX(irX);
        ir.setTranslateY(irY);
        ir.setFitHeight(100);
        ir.setFitWidth(100);
        ib = new ImageView("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2F9f%2F6b%2F59%2F9f6b59e3c4e2f9abd632e6f73143d65b.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1673610777&t=2b5744ba74d57757f46369d242ec900c");
        ib.setTranslateX(irX + 600);
        ib.setTranslateY(irY);
        ib.setFitHeight(100);
        ib.setFitWidth(100);
        anchorPane.getChildren().addAll(ib, ir);
        //玩家在左边，r代表玩家而非颜色
        rName.setText(LogIn.account);
        bName.setText("深蓝一号");

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
                        if (UserStatus.AISide == 1)//ai是黑方
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
                        {
                            Greedy.greedy(UserStatus.AIDepth, true, UserStatus.AIMode);
                        }

                    }
                    catch (GameEndsException e)
                    {
                        if (UserStatus.AISide == 0)
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
                        ChessPiece.judgeSound = false;
                        flip();
                        if (Preference.soundSwitch)
                            InitializationApplication.mediaPlayerFirst.pause();
                        if (e.getInfo() == UserStatus.AISide)
                        {
                            Showing.Info("菜狗，回去多积淀积淀再来挑战爷！");
                            if (Preference.chessSound)
                                ChessPiece.mediaPlayerEnd.pause();
                            if (Preference.endSound)
                                youDied();
                        }
                        else
                        {
                            Showing.Info("厉害啊，小子！居然把爷战胜了？！");
                            if (Preference.chessSound)
                                ChessPiece.mediaPlayerEnd.pause();
                            if (Preference.endSound)
                                youWin();
                        }

                        // Showing.Info(e.toString());
                    }
                    catch (MalformedURLException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
                //重新设定计分板
                if (UserStatus.AISide == 0)
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

    static public void halfAction(Group group)
    {
        r.setText("玩家");
        r.setFill(Color.BLACK);
        b.setText("对手");
        rText.setFill(Color.BLACK);
        bTurn.setText("点击回顾");
        ir = new ImageView("file:/" + Preference.headAddressUse);
        ir.setTranslateX(irX);
        ir.setTranslateY(irY);
        ir.setFitHeight(100);
        ir.setFitWidth(100);
        ib = new ImageView("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2F9f%2F6b%2F59%2F9f6b59e3c4e2f9abd632e6f73143d65b.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1673610777&t=2b5744ba74d57757f46369d242ec900c");
        ib.setTranslateX(irX + 600);
        ib.setTranslateY(irY);
        ib.setFitHeight(100);
        ib.setFitWidth(100);
        anchorPane.getChildren().addAll(ir,ib);
        rName.setText(LogIn.account);
        bName.setText(null);
        // TODO: 2022/12/11 头像图片
        //玩家在左边，r代表玩家而非颜色

        EventHandler<MouseEvent> eventHandler = mouseEvent ->
        {
            if (Operations.copyOfSizeOfStack == Operations.loadFileStamp)
            {
                int y = (int) ((mouseEvent.getX() - 341.65 - 1f / 6 * gird) / gird + 1);
                int x = (int) ((mouseEvent.getY() - 41.65 - 1f / 6 * gird) / gird + 1);
                MouseButton button = mouseEvent.getButton();
                if (button == MouseButton.PRIMARY)//左击
                {
                    //调用控制程序，判断棋子的类型
                    if (x > 0 && x < 9 && y > 0 && y < 9)
                    {
                        if(Choice.modeOfHalf == 2)
                        {
                            b.setText("机器");
                            bName.setText("深蓝一号");
                            if(UserStatus.currentSide == UserStatus.AISide)
                            {
                                try
                                {
                                    Greedy.greedy(UserStatus.AIDepth, true, UserStatus.AIMode);
                                }
                                catch (GameEndsException e)
                                {
                                    throw new RuntimeException(e);
                                }
                                catch (MalformedURLException e)
                                {
                                    throw new RuntimeException(e);
                                }
                            }
                            try
                            {
                                ClickOnBoard.clickOnBoard(x, y);
                                if (UserStatus.AISide == 1)//ai是黑方
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
                                    Greedy.greedy(UserStatus.AIDepth, true, UserStatus.AIMode);
                            }
                            catch (GameEndsException e)
                            {
                                ChessPiece.judgeSound = false;
                                flip();
                                if (Preference.soundSwitch)
                                    InitializationApplication.mediaPlayerFirst.pause();
                                if (e.getInfo() == UserStatus.AISide)
                                {
                                    if (Preference.chessSound)
                                        ChessPiece.mediaPlayerEnd.pause();
                                    if (Preference.endSound)
                                        youWin();
                                    Showing.Info("菜狗，回去多积淀积淀再来挑战爷！");
                                }
                                else
                                {
                                    youWin();
                                    Showing.Info("厉害啊，小子！居然把爷战胜了？！");
                                }
                            }
                            catch (MalformedURLException e)
                            {
                                throw new RuntimeException(e);
                            }
                            //重新设定计分板
                            if (UserStatus.AISide == 0)
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

                        else if(Choice.modeOfHalf == 1)
                        {
                            bName.setText(null);
                            try
                            {
                                ClickOnBoard.clickOnBoard(x, y);
                            }
                            catch (GameEndsException e)
                            {
                                if (e.getInfo() == 0)
                                    Showing.Info("红方胜利");
                                else if (e.getInfo() == 1)
                                    Showing.Info("黑方胜利");
                                ChessPiece.judgeSound = false;
                                flip();
                            }
                            catch (MalformedURLException e)
                            {
                                throw new RuntimeException(e);
                            }
                            //重新设定计分板
                            if(right == 0)
                            {
                                Integer redScore = UserStatus.getRedScore();
                                bText.setText("分数 " + redScore.toString());
                                Integer blackScore = UserStatus.getBlackScore();
                                rText.setText("分数 " + blackScore.toString());
                            }
                            else
                            {
                                Integer redScore = UserStatus.getRedScore();
                                rText.setText("分数 " + redScore.toString());
                                Integer blackScore = UserStatus.getBlackScore();
                                bText.setText("分数 " + blackScore.toString());
                            }
                            if (UserStatus.currentSide == 0)
                                bTurn.setText("轮到红方");
                            else if (UserStatus.currentSide == 1)
                                bTurn.setText("轮到黑方");
                         }

                    }
                }
                else if (button == MouseButton.SECONDARY)
                {
                    chessPieceArrayList.get(ChessBoardStatus.getObjectIndex(x, y)).cheatingFlip();
                }
            }
        };
        group.setOnMouseClicked(eventHandler);
    }

    private static void flip()
    {
        for (ChessPiece c : chessPieceArrayList)
            c.flipAChess();
    }

    private static void youDied()
    {
        String path = "D:/DarkChess/audio/LOSE.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(Preference.volume);
        mediaPlayer.setCycleCount(1);
        System.out.println("you died");
    }

    private static void youWin()
    {
        String path = "D:/arkChess/audio/WIN.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(Preference.volume);
        mediaPlayer.setCycleCount(1);
        System.out.println("you win");
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

        first.setFont(font);
        second.setFont(font);
        third.setFont(font);
        forth.setFont(font);
        fifth.setFont(font);
        yourScore.setFont(font);
        yourRank.setFont(font);

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
        first.setText("bwgg");
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

    public static void online(Group group)
    {
        CanvasUtils.setRec();
        r.setText("玩家A");
        r.setFill(Color.BLACK);
        b.setText("玩家B");
        rText.setFill(Color.BLACK);
        bTurn.setText("请翻棋");
        ir = new ImageView("file:/" + Preference.headAddressUse);
        ir.setTranslateX(irX);
        ir.setTranslateY(irY);
        ir.setFitHeight(100);
        ir.setFitWidth(100);
        ib = new ImageView("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2F9f%2F6b%2F59%2F9f6b59e3c4e2f9abd632e6f73143d65b.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1673610777&t=2b5744ba74d57757f46369d242ec900c");
        ib.setTranslateX(irX + 600);
        ib.setTranslateY(irY);
        ib.setFitHeight(100);
        ib.setFitWidth(100);
        anchorPane.getChildren().addAll(ib, ir);
        //玩家在左边，r代表玩家而非颜色
        rName.setText(LogIn.account);
        bName.setText("网友");

        //设置棋盘，画出棋子
        CanvasUtils.set(1);

        EventHandler<MouseEvent> eventHandler = mouseEvent ->
        {
            int y = (int) ((mouseEvent.getX() - 341.65 - 1f / 6 * gird) / gird + 1);
            int x = (int) ((mouseEvent.getY() - 41.65 - 1f / 6 * gird) / gird + 1);

            MouseButton button = mouseEvent.getButton();
            if (button == MouseButton.PRIMARY)//左击
            {
                //调用控制程序，判断棋子的类型
                if (x > 0 && x < 9 && y > 0 && y < 9)
                {
                    JSONObject message = new JSONObject();
                    message.put("signalType", 2);
                    message.put("actionType",1);
                    message.put("clickX",x);
                    message.put("clickY",y);
                    Client.sendMessage(message);

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

    public static Font setFont() throws FileNotFoundException
    {
        Font font = Font.loadFont(
                new FileInputStream("fonts/font.ttf"),
                40);
        return font;
    }

}
