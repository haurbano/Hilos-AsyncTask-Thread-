package com.example.dianam.hilos;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dianam.hilos.Background.ContadorThread;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ContadorThread.SECONDS)
            {
                int seconds = msg.arg1;
                crono.setText(""+seconds);
            }
        }
    };
    ContadorThread thread;

    TextView crono;
    Button btnPlay,btnPause,btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crono = (TextView) findViewById(R.id.txt_crono);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnStop = (Button) findViewById(R.id.btn_stop);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_play:
                if(thread == null )
                {
                    thread  = new ContadorThread(handler);
                    thread.start();
                }
                else if (thread.isPaused())
                {
                    thread.setPaused(false);
                }

                break;
            case R.id.btn_pause:
                if (thread != null)
                thread.setPaused(true);

                break;
            case R.id.btn_stop:
                if (thread != null) {
                    thread.stopCont();
                    thread = null;
                }
                break;
        }

    }
}
