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
}
