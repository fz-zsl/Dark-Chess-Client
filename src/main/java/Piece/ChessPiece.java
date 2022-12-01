package Piece;

import com.example.darkchess.Preference;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.darkchess.Board.anchorPane;
import static com.example.darkchess.Board.gird;

public abstract class ChessPiece extends ImageView
{
    public PieceType pieceType;
    protected double eatenX = 0;
    protected double eatenY = 0;
    static public double peakingTime = 0.1;
    static public double time = 0.2;
    protected Integer i = -1;

    public void setEatenX(double eatenX)
    {
        this.eatenX = eatenX;
    }

    public void setEatenY(double eatenY)
    {
        this.eatenY = eatenY;
    }

    public double getEatenX()
    {
        return eatenX;
    }

    public double getEatenY()
    {
        return eatenY;
    }

    protected Boolean status = false;//false代表没有翻开


    public ChessPiece(PieceType pieceType)
    {
        this.pieceType = pieceType;
        Image image = new Image("file:/D:/DarkChess/ChessImages/EmptyChess.png");
        this.setImage(image);
        this.setFitWidth(49);
        this.setFitHeight(49);
        anchorPane.getChildren().add(this);
        this.toFront();
    }

    public PieceType getPieceType()
    {
        return this.pieceType;
    }

    public void transportAChess(double initialX, double initialY, double destinationX, double destinationY)
    {
        this.toFront();
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(this);
        tt.setDuration(Duration.seconds(0.5));
        tt.setFromX(initialX);
        tt.setFromY(initialY);
        tt.setToX(destinationX);
        tt.setToY(destinationY);
        tt.play();
        System.out.println("slgg txdy!");
    }

    public void removeAChess(double initialX, double initialY) throws MalformedURLException
    {
        ChessPiece chessPiece = this;
        Timeline timeline = new Timeline();

        KeyValue keyValue1 = new KeyValue(this.translateXProperty(),this.getEatenX());
        KeyValue keyValue2 = new KeyValue(this.translateYProperty(),this.getEatenY());
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3), "move", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {}
        },keyValue1, keyValue2);

        KeyValue keyValue3 = new KeyValue(this.scaleXProperty(),1.2);
        KeyValue keyValue4 = new KeyValue(this.scaleYProperty(),1.2);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.4), "flip", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chessPiece.setImage(new Image(chessPiece.pieceType.getAddress()));
                System.out.println("SLGG YYDS 1!");
            }
        }, keyValue3,keyValue4);//放大

        KeyValue keyValue5 = new KeyValue(this.scaleXProperty(), 1.1);
        KeyValue keyValue6 = new KeyValue(this.scaleYProperty(), 1.1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.4 + time), "reaction", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("SLGG YYDS 2!");
            }
        },keyValue5, keyValue6);//显示

        KeyValue keyValue7 = new KeyValue(this.scaleXProperty(), 1);
        KeyValue keyValue8 = new KeyValue(this.scaleYProperty(), 1);
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.5 + time), "flip2", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("SLGG YYDS 3!");
            }
        }, keyValue7,keyValue8);//缩小

        KeyValue keyValue9 = new KeyValue(this.getCircle().fillProperty(),Color.RED);
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.5 + time), "cirlce", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {

            }
        },keyValue9);
        KeyValue keyValue10 = new KeyValue(this.getText().fillProperty(),Color.GREEN);
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(0.51 + time), "text", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {

            }
        },keyValue10);

        timeline.getKeyFrames().addAll(keyFrame, keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5);
        timeline.setDelay(Duration.seconds(0.8));
        timeline.play();
        this.getCircle().toFront();
        this.getText().toFront();
        sound();
    }

    public void reRemoveAChess(double finalX, double finalY)
    {
        TranslateTransition tt= new TranslateTransition();
        tt.setDuration(Duration.seconds(0.8));
        tt.setNode(this);
        tt.setFromY(this.getEatenY());
        tt.setFromY(this.getEatenY());
        tt.setToX(finalX);
        tt.setToY(finalY);
        tt.play();
        this.re();
    }

    public void cheatingFlip()
    {
        if(this.status)
            return;
        ChessPiece chessPiece = this;
        Timeline timeline = new Timeline();
        KeyValue keyValue1 = new KeyValue(this.scaleXProperty(),1.1);
        KeyValue keyValue2 = new KeyValue(this.scaleYProperty(),1.1);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.1), "flip", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chessPiece.setImage(new Image(chessPiece.pieceType.getAddress()));
                System.out.println("cheating flip over");
            }
        }, keyValue1,keyValue2);//放大

        KeyValue keyValue3 = new KeyValue(this.scaleXProperty(), 1);
        KeyValue keyValue4 = new KeyValue(this.scaleYProperty(), 1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.2 + peakingTime), "flip2", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chessPiece.setImage(new Image(PieceType.BACK.getAddress()));
                System.out.println("cheating flip down");
            }
        }, keyValue3,keyValue4);//缩小

        KeyValue keyValue = new KeyValue(this.scaleXProperty(), 1.1);
        KeyValue keyValue5 = new KeyValue(this.scaleYProperty(), 1.1);
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.1 + peakingTime), "reaction", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("SLGG YYDS!");
                System.out.println( chessPiece.getTranslateX() + " " + chessPiece.getTranslateY());
            }
        },keyValue, keyValue5);//显示


        timeline.getKeyFrames().addAll(keyFrame1,keyFrame3,keyFrame2);
        timeline.play();
    }
    public void flipAChess()
    {
        if(status)
            return;
        ChessPiece chessPiece = this;
        Timeline timeline = new Timeline();
        KeyValue keyValue1 = new KeyValue(this.scaleXProperty(),1.2);
        KeyValue keyValue2 = new KeyValue(this.scaleYProperty(),1.2);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.1), "flip", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chessPiece.setImage(new Image(chessPiece.pieceType.getAddress()));
                System.out.println("SLGG YYDS 1!");
            }
        }, keyValue1,keyValue2);//放大

        KeyValue keyValue3 = new KeyValue(this.scaleXProperty(), 1);
        KeyValue keyValue4 = new KeyValue(this.scaleYProperty(), 1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.2 + time), "flip2", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("SLGG YYDS 3!");
            }
        }, keyValue3,keyValue4);//缩小

        KeyValue keyValue = new KeyValue(this.scaleXProperty(), 1.2);
        KeyValue keyValue5 = new KeyValue(this.scaleYProperty(), 1.2);
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.1 + time), "reaction", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("SLGG YYDS 2!");
            }
        },keyValue, keyValue5);//显示


        timeline.getKeyFrames().addAll(keyFrame1,keyFrame3,keyFrame2);
        timeline.play();
        this.status = true;
    }

    public void reFlipAChess()
    {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(this);
        scaleTransition.setDuration(Duration.seconds(0.1));
        scaleTransition.setFromX(1.1);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        this.setImage(new Image(PieceType.BACK.getAddress()));
        scaleTransition.setDuration(Duration.seconds(0.1));
        scaleTransition.setFromX(0.83);
        scaleTransition.setFromY(0.83);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        this.status = false;
    }

    static public double getChessXFx(int x)
    {
        return gird * (x - 1) + gird / 6 + 341.65;
    }
    //对应坐标棋子的中心坐标（像素）
    static public double getChessYFx(int y)
    {
        return gird * (y - 1) + gird / 6 + 41.65;
    }

    public void setCo(double x, double y)
    {
        setEatenX(x);
        setEatenY(y);
        this.getCircle().setCenterX(this.eatenX + 45);
        this.getCircle().setCenterY(this.eatenY + 6);
        this.getCircle().setRadius(10);
        this.getText().setTranslateX(this.eatenX + 39.5);
        this.getText().setTranslateY(this.eatenY + 12);
        this.getText().setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));
    }

    public void getEatenNumber(int x)
    {
        i = x;
        System.out.println("Eaten number is " + i);
        this.getText().setText(i.toString());
    }

    public abstract Circle getCircle();
    public abstract Text getText();//小心！没有{}

    public abstract void re();
    public abstract void setJudge(boolean judge1);

    public void sound()
    {
        System.out.println("play sound begin");
        String path = "D://DarkChess//demo//src//audio//a.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.3);
        //mediaPlayer.setVolume(Preference.volume);
        //mediaPlayer.play();
        mediaPlayer.setCycleCount(1);
        System.out.println("play sound end");
    }

}
