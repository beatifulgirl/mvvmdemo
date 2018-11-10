package com.example.zhy.mvvmdemo.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zhy.mvvmdemo.adapter.CharAdapter;
import com.example.zhy.mvvmdemo.base.BaseModel;
import com.example.zhy.mvvmdemo.bean.ChartBean;
import com.example.zhy.mvvmdemo.databinding.ActivityChartBinding;
import com.example.zhy.mvvmdemo.service.TCPServiceService;
import com.example.zhy.mvvmdemo.utils.CloseStreamUtil;
import com.example.zhy.mvvmdemo.view.ChartActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartVM extends BaseModel implements View.OnClickListener {
    private Socket mClentSocket = null;
    private PrintWriter mPrintWriter = null;

    private RecyclerView listView;

    private ActivityChartBinding dataBinding;

    private CharAdapter mCharAdapter;
    private List<ChartBean> mList = new ArrayList<>();

    private Activity activity;

    @SuppressLint({"HandlerLeank", "HandlerLeak"})
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1://我發的消息
                    mCharAdapter.setData(mList);
                    dataBinding.listView.smoothScrollToPosition(mList.size()-1);
                    break;
                case 3:
                    dataBinding.buttons.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };


    public void setData(Activity activity, ActivityChartBinding dataBinding) {
        super.setData(activity);
        this.activity = activity;
        this.dataBinding = dataBinding;
        listView = dataBinding.listView;
        mCharAdapter = new CharAdapter(activity);
        RecyclerView.LayoutManager layout=new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);//(垂直布局、水平布局)
        listView.setLayoutManager(layout);
        listView.setAdapter(mCharAdapter);
        dataBinding.buttons.setOnClickListener(this);
        Intent service = new Intent(activity, TCPServiceService.class);
        activity.startService(service);

        new Thread(new Runnable() {
            @Override
            public void run() {
                connectTcpServer();
            }
        } ).start();


    }

    @SuppressLint("NewApi")
    private void connectTcpServer() {
        Socket socket = null;
        while (socket==null){
            try {
                socket = new Socket("localhost",8080);
                mClentSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                Log.e("TAG","连接成功");
                mHandler.sendEmptyMessage(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!activity.isFinishing()){
                String msg =  br.readLine();
                if(msg!=null){
                    String time = formatDataTime(System.currentTimeMillis());
                    String showedMsg  = time + msg;
                    mList.add(new ChartBean(false,showedMsg));
                    mHandler.sendEmptyMessage(1);
                }
            }
            CloseStreamUtil.close(mPrintWriter);
            CloseStreamUtil.close(br);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destory(){
        if(mClentSocket!=null){
            try {
                mClentSocket.shutdownOutput();
                mClentSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==dataBinding.buttons){
            String charMessage =  dataBinding.editText2.getText().toString();
            if(!charMessage.equals("")){
                mPrintWriter.println(charMessage);
                dataBinding.editText2.setText("");
                String time = formatDataTime(System.currentTimeMillis());
                String sowedMesg = time +":" + charMessage;
                mList.add(new ChartBean(true,sowedMesg));
                mHandler.sendEmptyMessage(1);
            }
        }

    }

    private String formatDataTime(long time){
        return  new SimpleDateFormat("HH:mm:ss").format(new Date(time));// HH:mm:ss
    }
}
