package com.example.darkchess;
import Piece.ChessPiece;
import Piece.PieceType;
import datum.UserStatus;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static com.example.darkchess.Board.*;

public class CanvasUtils
{
    static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    public static void drawChessBoard(GraphicsContext gc)//画出棋盘线
    {
        //画最外层的粗线
        gc.setLineWidth(4);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(41.65 ,41.65,41.65, boardH + 41.65);
        gc.strokeLine(41.65,41.65, boardW + 41.65,41.65);
        gc.strokeLine(41.65 , boardH + 41.65, boardW + 41.65, boardH + 41.65);
        gc.strokeLine(boardW + 41.65,41.65, boardW + 41.65, boardH + 41.65);

        //画内层的细线
        //竖线
        gc.setLineWidth(1);
        for(int i = 0 ; i < 5 ; i++)
        {
            gc.strokeLine(gird / 6 + gird * i + 41.65,gird / 6 + 41.65,gird / 6 + gird * i + 41.65, boardH - gird / 6 + 41.65);
        }
        //横线
        for(int i = 0 ; i < 9 ; i++)
        {
            gc.strokeLine(gird / 6 + 41.65,gird / 6 + gird * i + 41.65, boardW - gird / 6 + 41.65,gird / 6 + gird * i + 41.65);
        }

    }

    /*
1 RGeneral
2 RAdvisor1
3 RAdvisor2
4 RMinister1
5 RMinister2
6 RChariot1
7 RChariot2
8 RHorse1
9 RHorse2
10 Rsoldier1
11 Rsoldier2
12 Rsoldier3
13 Rsoldier4
14 Rsoldier5
15 RCannon1
16 RCannon2


17 BGeneral
18 BAdvisor1
19 BAdvisor2
20 BMinister1
21 BMinister2
22 BChariot1
23 BChariot2
24 BHorse1
25 BHorse2
26 Bsoldier1
27 Bsoldier2
28 Bsoldier3
29 Bsoldier4
30 Bsoldier5
31 BCannon1
32 BCannon2

     */
    public static ArrayList<ChessPiece> setAllChess(int mode)
    {
        ArrayList<ChessPiece> chessPieceArrayList = new ArrayList<>();
        ChessPiece RHorse1 = new ChessPiece(PieceType.RHORSE);
        ChessPiece RHorse2 = new ChessPiece(PieceType.RHORSE);
        ChessPiece RMinister1 = new ChessPiece(PieceType.RMINISTER);
        ChessPiece RMinister2 = new ChessPiece(PieceType.RMINISTER);
        ChessPiece RAdvisor1 = new ChessPiece(PieceType.RADVISOR);
        ChessPiece RAdvisor2 = new ChessPiece(PieceType.RADVISOR);
        ChessPiece RChariot1 = new ChessPiece(PieceType.RCHARIOT);
        ChessPiece RChariot2 = new ChessPiece(PieceType.RCHARIOT);
        ChessPiece RSoldier1 = new ChessPiece(PieceType.RSOLDIER);
        ChessPiece RSoldier2 = new ChessPiece(PieceType.RSOLDIER);
        ChessPiece RSoldier3 = new ChessPiece(PieceType.RSOLDIER);
        ChessPiece RSoldier4 = new ChessPiece(PieceType.RSOLDIER);
        ChessPiece RSoldier5 = new ChessPiece(PieceType.RSOLDIER);
        ChessPiece RCannon1 = new ChessPiece(PieceType.RCANNON);
        ChessPiece RCannon2 = new ChessPiece(PieceType.RCANNON);
        ChessPiece RGeneral = new ChessPiece(PieceType.RGENERAL);

        chessPieceArrayList.add(RGeneral);
        chessPieceArrayList.add(RAdvisor1);
        chessPieceArrayList.add(RAdvisor2);
        chessPieceArrayList.add(RMinister1);
        chessPieceArrayList.add(RMinister2);
        chessPieceArrayList.add(RChariot1);
        chessPieceArrayList.add(RChariot2);
        chessPieceArrayList.add(RHorse1);
        chessPieceArrayList.add(RHorse2);
        chessPieceArrayList.add(RSoldier1);
        chessPieceArrayList.add(RSoldier2);
        chessPieceArrayList.add(RSoldier3);
        chessPieceArrayList.add(RSoldier4);
        chessPieceArrayList.add(RSoldier5);
        chessPieceArrayList.add(RCannon1);
        chessPieceArrayList.add(RCannon2);

        ChessPiece BHorse1 = new ChessPiece(PieceType.BHORSE);
        ChessPiece BHorse2 = new ChessPiece(PieceType.BHORSE);
        ChessPiece BMinister1 = new ChessPiece(PieceType.BMINISTER);
        ChessPiece BMinister2 = new ChessPiece(PieceType.BMINISTER);
        ChessPiece BAdvisor1 = new ChessPiece(PieceType.BADVISOR);
        ChessPiece BAdvisor2 = new ChessPiece(PieceType.BADVISOR);
        ChessPiece BChariot1 = new ChessPiece(PieceType.BCHARIOT);
        ChessPiece BChariot2 = new ChessPiece(PieceType.BCHARIOT);
        ChessPiece BSoldier1 = new ChessPiece(PieceType.BSOLDIER);
        ChessPiece BSoldier2 = new ChessPiece(PieceType.BSOLDIER);
        ChessPiece BSoldier3 = new ChessPiece(PieceType.BSOLDIER);
        ChessPiece BSoldier4 = new ChessPiece(PieceType.BSOLDIER);
        ChessPiece BSoldier5 = new ChessPiece(PieceType.BSOLDIER);
        ChessPiece BCannon1 = new ChessPiece(PieceType.BCANNON);
        ChessPiece BCannon2 = new ChessPiece(PieceType.BCANNON);
        ChessPiece BGeneral = new ChessPiece(PieceType.BGENERAL);

        chessPieceArrayList.add(BGeneral);
        chessPieceArrayList.add(BAdvisor1);
        chessPieceArrayList.add(BAdvisor2);
        chessPieceArrayList.add(BMinister1);
        chessPieceArrayList.add(BMinister2);
        chessPieceArrayList.add(BChariot1);
        chessPieceArrayList.add(BChariot2);
        chessPieceArrayList.add(BHorse1);
        chessPieceArrayList.add(BHorse2);
        chessPieceArrayList.add(BSoldier1);
        chessPieceArrayList.add(BSoldier2);
        chessPieceArrayList.add(BSoldier3);
        chessPieceArrayList.add(BSoldier4);
        chessPieceArrayList.add(BSoldier5);
        chessPieceArrayList.add(BCannon1);
        chessPieceArrayList.add(BCannon2);


        if(mode == 1 || (mode == 3 && UserStatus.AISide == 1))
        {
            RGeneral.setCo(100 , 508.35);
            RAdvisor1.setCo(0,458.35);
            RAdvisor2.setCo(0,458.35);
            RMinister1.setCo(50,458.35);
            RMinister2.setCo(50,458.35);
            RChariot1.setCo(100,458.35);
            RChariot2.setCo(100,458.35);
            RHorse1.setCo(150,458.35);
            RHorse2.setCo(150,458.35);
            RSoldier1.setCo(200,458.355);
            RSoldier2.setCo(200,458.355);
            RSoldier3.setCo(200,458.355);
            RSoldier4.setCo(200,458.355);
            RSoldier5.setCo(200,458.355);
            RCannon1.setCo(250,458.35);
            RCannon2.setCo(250,458.35);
            BGeneral.setCo(100 + 600, 508.35);
            BAdvisor1.setCo(600,458.35);
            BAdvisor2.setCo(600,458.35);
            BMinister1.setCo(50 + 600,458.35);
            BMinister2.setCo(50 + 600,458.35);
            BChariot1.setCo(100 + 600,458.35);
            BChariot2.setCo(100 + 600,458.35);
            BHorse1.setCo(150 + 600,458.35);
            BHorse2.setCo(150 + 600,458.35);
            BSoldier1.setCo(200 + 600,458.35);
            BSoldier2.setCo(200 + 600,458.35);
            BSoldier3.setCo(200 + 600,458.35);
            BSoldier4.setCo(200 + 600,458.35);
            BSoldier5.setCo(200 + 600,458.35);
            BCannon1.setCo(250 + 600,458.35);
            BCannon2.setCo(250 + 600,458.35);
        }
        else if(mode == 3)
        {
            if(UserStatus.AISide == 0)
            {
                BGeneral.setCo(100 , 508.35);
                BAdvisor1.setCo(0,458.35);
                BAdvisor2.setCo(0,458.35);
                BMinister1.setCo(50,458.35);
                BMinister2.setCo(50,458.35);
                BChariot1.setCo(100,458.35);
                BChariot2.setCo(100,458.35);
                BHorse1.setCo(150,458.35);
                BHorse2.setCo(150,458.35);
                BSoldier1.setCo(200,458.355);
                BSoldier2.setCo(200,458.355);
                BSoldier3.setCo(200,458.355);
                BSoldier4.setCo(200,458.355);
                BSoldier5.setCo(200,458.355);
                BCannon1.setCo(250,458.35);
                BCannon2.setCo(250,458.35);
                RGeneral.setCo(100 + 600, 508.35);
                RAdvisor1.setCo(600,458.35);
                RAdvisor2.setCo(600,458.35);
                RMinister1.setCo(50 + 600,458.35);
                RMinister2.setCo(50 + 600,458.35);
                RChariot1.setCo(100 + 600,458.35);
                RChariot2.setCo(100 + 600,458.35);
                RHorse1.setCo(150 + 600,458.35);
                RHorse2.setCo(150 + 600,458.35);
                RSoldier1.setCo(200 + 600,458.35);
                RSoldier2.setCo(200 + 600,458.35);
                RSoldier3.setCo(200 + 600,458.35);
                RSoldier4.setCo(200 + 600,458.35);
                RSoldier5.setCo(200 + 600,458.35);
                RCannon1.setCo(250 + 600,458.35);
                RCannon2.setCo(250 + 600,458.35);
            }
        }


// TODO: 2022/11/23 基本的想法是，在初始化棋子对象的时候，就可以把棋子的图片打印在anchorPane上了，这个时候打印的图片都是背面朝上的，但是由于对象确定，每个棋子实际上是有区别的。但是棋子的图像并没有放在对应的网格中，就是缺一步setX，用ArrayList可以遍历setX。在父类中加入set坐标方法
        return chessPieceArrayList;
    }

    public static void highLight(int x, int y)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(null);
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(3);
        rectangle.setX(ChessPiece.getChessXFx(x));
        rectangle.setY(ChessPiece.getChessYFx(y));
        rectangle.setHeight(gird);
        rectangle.setWidth(gird);
        anchorPane.getChildren().add(rectangle);
        rectangles.add(rectangle);
    }

    public static void highLight(ArrayList<Integer> APM) {
        int size=APM.size();
        for (int i=1;i<size;++i) {
            highLight(APM.get(i)%10,APM.get(i)/10);
        }
    }

    public static void cancelHighLight()
    {
        anchorPane.getChildren().removeAll(rectangles);
    }

}
