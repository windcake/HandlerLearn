package com.windcake.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView textView;
    private Button button;
    private static int JOB_OVER = 15;
    private static int DELAY_MSG = 16;

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 15:
                    textView.setText("2.后台任务完毕");
                    break;
                case 16:
                   String s = (String) msg.obj;
                    textView.setText(s);
                    break;
                case 17:
                    doJobs();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inintView();
    }

    private void inintView()
    {
        textView = (TextView) findViewById(R.id.main_text);
        button = (Button) findViewById(R.id.main_count_down_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.main_count_down_button:

//                doJobs();

                break;
        }
    }

    private void doJobs()
    {
        textView.setText("1.开始后台任务");

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);
                    Message msgJob = new Message();
                    msgJob.what = JOB_OVER;
                    handler.sendMessage(msgJob);

                    Message msgDelay = handler.obtainMessage();
                    msgDelay.what = DELAY_MSG;
                    msgDelay.obj = "3.延迟消息并携带对象";
                    handler.sendMessageDelayed(msgDelay,1000);

                    Thread.sleep(2000);
                    handler.post(runnable);

                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            textView.setText("4.在Runnable里更新");

            handler.sendEmptyMessageDelayed(17,1000);
        }
    };

    private boolean isDone = false;

    private void loop()
    {
        for (;;)
        {
            if (isDone)
            {

            }
        }
    }


    private void doThings()
    {
        textView.setText("开始搞事情");
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);
                    isDone = true;

                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
