package socket;

import java.awt.*;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.ArrayList;

import static com.example.darkchess.Board.anchorPane;
import static com.example.darkchess.Board.chessPieceArrayList;

import Piece.ChessPiece;
import com.example.darkchess.CanvasUtils;
import com.example.darkchess.Showing;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class Client
{
    private String serverIP = "10.27.96.18";
    static Socket serverSocket;
    DataInputStream inputStream;
    DataOutputStream outputStream;

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
        }
        catch (IOException ioException)
        {
//            System.out.printf("[%s]Message is rejected by the client.",getServerTime());
            System.out.println(messageInfo.toString());
        }
    }


    class ClientThread implements Runnable
    {
        @Override
        public void run()
        {
            chessPieceArrayList = CanvasUtils.setAllChess();
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
                        chessPieceArrayList.get(objectIndex).flipAChess();
                    }
                    else if (actionType == 2)
                    {
                        objectIndex = info.getInt("objectIndex");
                        curX = info.getInt("curX");
                        curY = info.getInt("curY");
                        preX = info.getInt("preX");
                        preY = info.getInt("preY");
                        chessPieceArrayList.get(objectIndex).transportAChess(ChessPiece.getChessXFx(preY), ChessPiece.getChessYFx(preX), ChessPiece.getChessXFx(curY), ChessPiece.getChessYFx(curX));
                    }
                    else if (actionType == 3)
                    {
                        objectIndex = info.getInt("objectIndex");
                        curX = info.getInt("curX");
                        curY = info.getInt("curY");
                        try
                        {
                            chessPieceArrayList.get(objectIndex).removeAChess(ChessPiece.getChessXFx(curX), ChessPiece.getChessYFx(curY));
                        }
                        catch (MalformedURLException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                    else if (actionType == 4)
                    {
                        ArrayList<Integer> allPossibleMoves = new ArrayList<>((ArrayList<Integer>) info.get("allPossibleMoves"));
                        //show all possible moves
                        CanvasUtils.highLight(allPossibleMoves);
                    }
                    else if (actionType == 5)
                    {
                        //cancel high light
                        CanvasUtils.cancelHighLight();
                    }
                    else if (actionType == 6)
                    {
                        //System.out.println("set a chess");
                        objectIndex = info.getInt("objectIndex");
                        curX = info.getInt("curX");
                        curY = info.getInt("curY");
                        //set a chess
                        //System.out.println(chessPieceArrayList.size());
                        chessPieceArrayList.get(objectIndex).setTranslateX(ChessPiece.getChessXFx(curY));
                        chessPieceArrayList.get(objectIndex).setTranslateX(ChessPiece.getChessXFx(curX));
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
                        chessPieceArrayList.get(objectIndex).reRemoveAChess(ChessPiece.getChessXFx(curY), ChessPiece.getChessYFx(curX));
                    }
                }
                else if(signalType == 1)
                {
                    if(actionType == 3)
                    {
                        Boolean flag = info.getBoolean("result");
                        Text textField = null;
                        if(!flag)
                        {
                            System.out.println("正在匹配……");
                        }

                        if(flag)
                        {
                            anchorPane.getChildren().remove(textField);
                        }
                    }
                }
                //接受来自服务端的信息并做出反映
                //各方法中调用算法部分的接口全部搞成发送信息
            }
        }
    }
}
