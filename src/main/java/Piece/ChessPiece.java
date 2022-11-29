package Piece;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static com.example.darkchess.Board.anchorPane;
import static com.example.darkchess.Board.gird;

public class ChessPiece extends ImageView
{
    public PieceType pieceType;
    protected double eatenX = 0;
    protected double eatenY = 0;
    static private double time = 0.2;

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

    private Boolean status = false;

    public static void setTime(double peakingTime)
    {
        time = peakingTime;
        // TODO: 2022/11/27 属性设置
    }

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

    public void transportAChess(double initialX, double initialY, double destinationX, double destinationY)
    {
        this.toFront();
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(this);
        tt.setDuration(Duration.seconds(0.8));
        tt.setFromX(initialX);
        tt.setFromY(initialY);
        tt.setToX(destinationX);
        tt.setToY(destinationY);
        tt.play();
        System.out.println("bwgg txdy!");
    }

    public void removeAChess(double initialX, double initialY)
    {
        this.status = true;
        Timeline timeline = new Timeline();
        KeyValue keyValue1 = new KeyValue(this.translateXProperty(),this.getEatenX());
        KeyValue keyValue2 = new KeyValue(this.translateYProperty(),this.getEatenY());
        KeyValue keyValue3 = new KeyValue(this.viewOrderProperty(),0);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), "move", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {}
        },keyValue1, keyValue2);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.1), "toFront", new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {}
        },keyValue3);
        timeline.getKeyFrames().addAll(keyFrame,keyFrame1);
        timeline.setDelay(Duration.seconds(0.8));
        timeline.play();
    }

    public void reRemoveAChess(double finalX, double finalY)
    {
        this.status = true;
        TranslateTransition tt= new TranslateTransition();
        tt.setDuration(Duration.seconds(0.8));
        tt.setNode(this);
        tt.setFromY(this.getEatenY());
        tt.setFromY(this.getEatenY());
        tt.setToX(finalX);
        tt.setToY(finalY);
        tt.play();
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
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.2 + time), "flip2", new EventHandler<ActionEvent>()
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
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.1 + time), "reaction", new EventHandler<ActionEvent>()
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
        this.status = true;
        ChessPiece chessPiece = this;
        double time = 0.1;
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
    }
}
