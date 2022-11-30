package com.example.darkchess;
import Piece.*;
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
    public static ArrayList<ChessPiece> setAllChess()
    {
        ArrayList<ChessPiece> chessPieceArrayList = new ArrayList<>();
        ChessPiece RHorse1 = new Horse(PieceType.RHORSE);
        ChessPiece RHorse2 = new Horse(PieceType.RHORSE);
        ChessPiece RMinister1 = new Minister(PieceType.RMINISTER);
        ChessPiece RMinister2 = new Minister(PieceType.RMINISTER);
        ChessPiece RAdvisor1 = new Advisor(PieceType.RADVISOR);
        ChessPiece RAdvisor2 = new Advisor(PieceType.RADVISOR);
        ChessPiece RChariot1 = new Chariot(PieceType.RCHARIOT);
        ChessPiece RChariot2 = new Chariot(PieceType.RCHARIOT);
        ChessPiece RSoldier1 = new Soldier(PieceType.RSOLDIER);
        ChessPiece RSoldier2 = new Soldier(PieceType.RSOLDIER);
        ChessPiece RSoldier3 = new Soldier(PieceType.RSOLDIER);
        ChessPiece RSoldier4 = new Soldier(PieceType.RSOLDIER);
        ChessPiece RSoldier5 = new Soldier(PieceType.RSOLDIER);
        ChessPiece RCannon1 = new Cannon(PieceType.RCANNON);
        ChessPiece RCannon2 = new Cannon(PieceType.RCANNON);
        ChessPiece RGeneral = new General(PieceType.RGENERAL);

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

        ChessPiece BHorse1 = new BHorse(PieceType.BHORSE);
        ChessPiece BHorse2 = new BHorse(PieceType.BHORSE);
        ChessPiece BMinister1 = new BMinister(PieceType.BMINISTER);
        ChessPiece BMinister2 = new BMinister(PieceType.BMINISTER);
        ChessPiece BAdvisor1 = new BAdvisor(PieceType.BADVISOR);
        ChessPiece BAdvisor2 = new BAdvisor(PieceType.BADVISOR);
        ChessPiece BChariot1 = new BChariot(PieceType.BCHARIOT);
        ChessPiece BChariot2 = new BChariot(PieceType.BCHARIOT);
        ChessPiece BSoldier1 = new BSoldier(PieceType.BSOLDIER);
        ChessPiece BSoldier2 = new BSoldier(PieceType.BSOLDIER);
        ChessPiece BSoldier3 = new BSoldier(PieceType.BSOLDIER);
        ChessPiece BSoldier4 = new BSoldier(PieceType.BSOLDIER);
        ChessPiece BSoldier5 = new BSoldier(PieceType.BSOLDIER);
        ChessPiece BCannon1 = new BCannon(PieceType.BCANNON);
        ChessPiece BCannon2 = new BCannon(PieceType.BCANNON);
        ChessPiece BGeneral = new BGeneral(PieceType.BGENERAL);

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
// TODO: 2022/11/23 基本的想法是，在初始化棋子对象的时候，就可以把棋子的图片打印在anchorPane上了，这个时候打印的图片都是背面朝上的，但是由于对象确定，每个棋子实际上是有区别的。但是棋子的图像并没有放在对应的网格中，就是缺一步setX，用ArrayList可以遍历setX。在父类中加入set坐标方法
        return chessPieceArrayList;
    }


    public static void set(int mode)
    {
        int t = 10;
        if (mode == 1 || (mode == 3 && UserStatus.AISide == 1))
        {
            chessPieceArrayList.get(0).setCo(100 + 600 - t , 508.35);
            chessPieceArrayList.get(1).setCo(600 - t, 458.35);
            chessPieceArrayList.get(2).setCo(600 - t, 458.35);
            chessPieceArrayList.get(3).setCo(50 - t+ 600, 458.35);
            chessPieceArrayList.get(4).setCo(50 - t + 600, 458.35);
            chessPieceArrayList.get(5).setCo(100 - t + 600, 458.35);
            chessPieceArrayList.get(6).setCo(100 - t + 600, 458.35);
            chessPieceArrayList.get(7).setCo(150 - t + 600, 458.35);
            chessPieceArrayList.get(8).setCo(150 - t + 600, 458.35);
            chessPieceArrayList.get(9).setCo(200 - t + 600, 458.355);
            chessPieceArrayList.get(10).setCo(200 - t + 600, 458.355);
            chessPieceArrayList.get(11).setCo(200 - t + 600, 458.355);
            chessPieceArrayList.get(12).setCo(200 - t + 600, 458.355);
            chessPieceArrayList.get(13).setCo(200 - t + 600, 458.355);
            chessPieceArrayList.get(14).setCo(250 - t + 600, 458.35);
            chessPieceArrayList.get(15).setCo(250 - t + 600, 458.35);
            chessPieceArrayList.get(16).setCo(100, 508.35);
            chessPieceArrayList.get(17).setCo(0, 458.35);
            chessPieceArrayList.get(18).setCo(0, 458.35);
            chessPieceArrayList.get(19).setCo(50, 458.35);
            chessPieceArrayList.get(20).setCo(50, 458.35);
            chessPieceArrayList.get(21).setCo(100, 458.35);
            chessPieceArrayList.get(22).setCo(100, 458.35);
            chessPieceArrayList.get(23).setCo(150, 458.35);
            chessPieceArrayList.get(24).setCo(150, 458.35);
            chessPieceArrayList.get(25).setCo(200, 458.35);
            chessPieceArrayList.get(26).setCo(200, 458.35);
            chessPieceArrayList.get(27).setCo(200, 458.35);
            chessPieceArrayList.get(28).setCo(200, 458.35);
            chessPieceArrayList.get(29).setCo(200, 458.35);
            chessPieceArrayList.get(30).setCo(250, 458.35);
            chessPieceArrayList.get(31).setCo(250, 458.35);

        }
        else if (mode == 3 && UserStatus.AISide == 0)
        {
            chessPieceArrayList.get(0).setCo(100, 508.35);
            chessPieceArrayList.get(1).setCo(0, 458.35);
            chessPieceArrayList.get(2).setCo(0, 458.35);
            chessPieceArrayList.get(3).setCo(50, 458.35);
            chessPieceArrayList.get(4).setCo(50, 458.35);
            chessPieceArrayList.get(5).setCo(100, 458.35);
            chessPieceArrayList.get(6).setCo(100, 458.35);
            chessPieceArrayList.get(7).setCo(150, 458.35);
            chessPieceArrayList.get(8).setCo(150, 458.35);
            chessPieceArrayList.get(9).setCo(200, 458.355);
            chessPieceArrayList.get(10).setCo(200, 458.355);
            chessPieceArrayList.get(11).setCo(200, 458.355);
            chessPieceArrayList.get(12).setCo(200, 458.355);
            chessPieceArrayList.get(13).setCo(200, 458.355);
            chessPieceArrayList.get(14).setCo(250, 458.35);
            chessPieceArrayList.get(15).setCo(250, 458.35);
            chessPieceArrayList.get(16).setCo(100 + 600, 508.35);
            chessPieceArrayList.get(17).setCo(600 - t, 458.35);
            chessPieceArrayList.get(18).setCo(600 - t,458.35);
            chessPieceArrayList.get(19).setCo(50 - t + 600, 458.35);
            chessPieceArrayList.get(20).setCo(50 - t + 600, 458.35);
            chessPieceArrayList.get(21).setCo(100 - t + 600, 458.35);
            chessPieceArrayList.get(22).setCo(100 - t + 600, 458.35);
            chessPieceArrayList.get(23).setCo(150 - t + 600, 458.35);
            chessPieceArrayList.get(24).setCo(150 - t + 600, 458.35);
            chessPieceArrayList.get(25).setCo(200 - t + 600, 458.35);
            chessPieceArrayList.get(26).setCo(200 - t + 600, 458.35);
            chessPieceArrayList.get(27).setCo(200 - t + 600, 458.35);
            chessPieceArrayList.get(28).setCo(200 - t + 600, 458.35);
            chessPieceArrayList.get(29).setCo(200 - t + 600, 458.35);
            chessPieceArrayList.get(30).setCo(250 - t + 600, 458.35);
            chessPieceArrayList.get(31).setCo(250 - t + 600, 458.35);
        }
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
