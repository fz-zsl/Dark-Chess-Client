package socket;

import com.example.darkchess.InitializationApplication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public void client() throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("等待连接服务器...");
        Socket socket = new Socket("192.168.43.193",2121);
        System.out.println("连接服务器成功！");
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        InitializationApplication initializationApplication = new InitializationApplication();
        //JSONObject jsonObject = JSONObject


    }
}
