package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.ArrayList;

import Piece.ChessPiece;
import com.example.darkchess.CanvasUtils;
import javafx.scene.image.Image;
import net.sf.json.JSONObject;

import javax.swing.*;

import static com.example.darkchess.Board.*;

public class Client
{
    private String serverIP = "10.27.96.18";
    static Socket serverSocket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    public static Integer rScore = 0;
    public static Integer bScore = 0;
    public static int currSide = -1;
    public static String oName = null;
    public static String oPic = null;
    public static boolean winOrLose = false;
    public static int onSide = -1;
    public Client()
    {
        try
        {
            serverSocket = new Socket(serverIP, 9019);
            inputStream = new DataInputStream(serverSocket.getInputStream());
            outputStream = new DataOutputStream(serverSocket.getOutputStream());
            new Thread(new ClientThread()).start();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(JSONObject messageInfo)
    {
        try
        {
            DataOutputStream outputStream = new DataOutputStream(serverSocket.getOutputStream());
            outputStream.writeUTF(messageInfo.toString());
//            System.out.println("包装完毕");
        }
        catch (IOException ioException)
        {
            System.out.println(messageInfo.toString());
        }
    }

    ArrayList<JSONObject> setChessQueue=new ArrayList<>();

    class ClientThread implements Runnable
    {
        @Override
        public void run()
        {
            while (true)
            {
                String infoString = null;
                try
                {
                    infoString = inputStream.readUTF();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                JSONObject info = JSONObject.fromObject(infoString);
                int signalType = info.getInt("signalType");
                int actionType = info.getInt("actionType");
                int objectIndex, curX, curY, preX, preY;
                if (signalType == 2)
                {
                    if (actionType == 1)
                    {
                        objectIndex = info.getInt("objectIndex");
                        if(objectIndex>=0)
                            chessPieceArrayList.get(objectIndex).flipAChess();
                    }
                    else if (actionType == 2)
                    {
                        objectIndex = info.getInt("objectIndex");
                        curX = info.getInt("curX");
                        curY = info.getInt("curY");
                        preX = info.getInt("preX");
                        preY = info.getInt("preY");
                        if(objectIndex>=0)
                            chessPieceArrayList.get(objectIndex).transportAChess2(ChessPiece.getChessXFx(preY), ChessPiece.getChessYFx(preX), ChessPiece.getChessXFx(curY), ChessPiece.getChessYFx(curX));
                    }
                    else if (actionType == 3)
                    {
                        objectIndex = info.getInt("objectIndex");
                        curX = info.getInt("curX");
                        curY = info.getInt("curY");
                        if(objectIndex>=0) {
                            try {
                                chessPieceArrayList.get(objectIndex).setEatenNumber();
                                chessPieceArrayList.get(objectIndex).removeAChess2(ChessPiece.getChessXFx(curX), ChessPiece.getChessYFx(curY));
                            } catch (MalformedURLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    else if (actionType == 4)
                    {
                        ArrayList<Integer> allPossibleMoves = new ArrayList<>();
                        String rawString1=info.getString("APM");
                        String rawString2=rawString1.substring(1,rawString1.length()-1);
                        String[] rawStrings=rawString2.split(",");
                        for (int i=1;i<rawStrings.length;++i) {
                            String str=rawStrings[i];
                            CanvasUtils.moveRe(Integer.parseInt(str)/10,Integer.parseInt(str)%10,i);
                        }
                    }
                    else if (actionType == 5)
                    {
                        //cancel high light
                        CanvasUtils.removeRe();
                    }
                    else if (actionType == 6)
                    {
                        objectIndex = info.getInt("objectIndex");
                        curX = info.getInt("curX");
                        curY = info.getInt("curY");
//                        System.out.println(curX + " " + curY);
                        if (setChessQueue.size()==32) {
//                            System.out.println("Prints now!");
                            chessPieceArrayList.get(objectIndex).setTranslateX(ChessPiece.getChessXFx(curY));
                            chessPieceArrayList.get(objectIndex).setTranslateY(ChessPiece.getChessYFx(curX));
                        }
                        else {
                            JSONObject curChess=new JSONObject();
                            curChess.put("objectIndex",objectIndex);
                            curChess.put("curX",curX);
                            curChess.put("curY",curY);
                            setChessQueue.add(curChess);
                            if (setChessQueue.size()==32)
                                for (JSONObject tmpChess:setChessQueue) {
                                    objectIndex=tmpChess.getInt("objectIndex");
                                    curX=tmpChess.getInt("curX");
                                    curY=tmpChess.getInt("curY");
                                    chessPieceArrayList.get(objectIndex).setTranslateX(ChessPiece.getChessXFx(curY));
                                    chessPieceArrayList.get(objectIndex).setTranslateY(ChessPiece.getChessYFx(curX));
                                }
                        }
                    }
                }
                else if (signalType == 3)
                {
                    if (actionType == 1)
                    {
                        objectIndex = info.getInt("objectIndex");
                        //reFlipAChess
                        chessPieceArrayList.get(objectIndex).reFlipAChess();
                    }
                    else if (actionType == 2)
                    {
                        objectIndex = info.getInt("objectIndex");
                        curX = info.getInt("curX");
                        curY = info.getInt("curY");
                        //reRemoveAChess
                        if(objectIndex>=0)
                            chessPieceArrayList.get(objectIndex).reRemoveAChess(ChessPiece.getChessXFx(curY), ChessPiece.getChessYFx(curX));
                    }
                }
                else if(signalType == 1)
                {
                    if(actionType == 3)
                    {
                        Boolean flag = info.getBoolean("result");
                        if(!flag)
                        {
                            System.out.println("正在匹配……");
                        }

                        if(flag)
                        {
                            System.out.println("匹配成功！");
                            oName = info.getString("partnerName");
                            bName.setText(oName);
                            System.out.println("test");
                            rProgressBar.setProgress(1);
                            bProgressBar.setProgress(1);
                            oPic = info.getString("headPic");
                            ib.setImage(new Image(oPic));
                        }
                    }
                }
                else if(signalType == 4)
                {
                    if(actionType == 6)
                    {
                        onSide = info.getInt("yourSide");
                        CanvasUtils.set(2);
                        boolean side = info.getBoolean("infoSide");
                        if (side)
                            bTurn.setText("轮到你了");
                        else
                            bTurn.setText("等待对方");
                        rScore = info.getInt("redScore");
                        bScore = info.getInt("blackScore");
                        if(onSide == 0)
                        {
                            rText.setText("分数 " + Client.rScore);
                            bText.setText("分数 " + Client.bScore);
                            if(1 - rScore / 60f < 0 || 1 - bScore / 60f < 0)
                                return;
                            rProgressBar.setProgress(1 - bScore / 60f);
                            bProgressBar.setProgress(1 - rScore / 60f);

                        }
                        else if(onSide == 1)
                        {
                            rText.setText("分数 " + Client.bScore);
                            bText.setText("分数 " + Client.rScore);
                            if(1 - rScore / 60f < 0 || 1 - bScore / 60f < 0)
                                return;
                            rProgressBar.setProgress(1 - rScore / 60f);
                            bProgressBar.setProgress(1 - bScore / 60f);
                        }

                        if(onSide == 0)
                        {
                            if(rScore >= 60)
                            {
                                bTurn.setText("你赢了！！！");
                            }
                            else if(bScore >= 60)
                            {
                                bTurn.setText("你输了！！！");
                            }

                        }
                        else if(onSide == 1)
                        {
                            if(rScore >= 60)
                            {
                                bTurn.setText("你输了！！！");
                            }
                            else if(bScore >= 60)
                            {
                                bTurn.setText("你赢了！！！");
                            }
                        }
                    }
                }

                //接受来自服务端的信息并做出反映
                //各方法中调用算法部分的接口全部搞成发送信息
            }
        }
    }
}
