package com.example.zhy.mvvmdemo.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.zhy.mvvmdemo.utils.CloseStreamUtil;
import com.example.zhy.mvvmdemo.utils.MyToast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServiceService extends Service {

    private boolean mIsServiceDestoryed = false;

    private String[] mDefinedMessages = new String[]{"你好啊，哈哈","请问你叫什么啊？","今天北京天气不错啊","我听不懂啊","你是谁啊"};

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new TcpSerice()).start();
    }

    private class TcpSerice implements Runnable{

        @Override
        public void run() {
            ServerSocket mServerSocket = null;
            try {
                mServerSocket =  new ServerSocket(8080);
            } catch (IOException e) {
                System.out.println("faile");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestoryed){
                try {
                    final Socket client = mServerSocket.accept();
                    System.out.println("accept");
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 接受客户端消息
     * @param client
     */
    @SuppressLint("NewApi")
    private void responseClient(Socket client)throws IOException {
        //获取输入流
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
        System.out.println("欢迎来到聊天室");


        while (!mIsServiceDestoryed){
            String str = in.readLine();
            System.out.println("来自客户端消息"+str);

            if(str==null){
                System.out.println("客戶端中断连接");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            System.out.println("向客户端要发送的消息"+ msg);

        }
        //关闭流
        CloseStreamUtil.close(out);
        CloseStreamUtil.close(in);
        CloseStreamUtil.close(client);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;
        super.onDestroy();

    }
}
