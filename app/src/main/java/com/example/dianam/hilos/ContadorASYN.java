package com.example.dianam.hilos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dianam.hilos.Background.ContadorAsyncTask;

public class ContadorASYN extends AppCompatActivity implements View.OnClickListener, ContadorAsyncTask.ProgressI {


    TextView crono;
    Button btnPlay,btnPause,btnStop;

    ContadorAsyncTask contadorAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_asyn);

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
                if (contadorAsyncTask == null) {
                    contadorAsyncTask = new ContadorAsyncTask(this);
                    contadorAsyncTask.execute(0);
                }
                else if (contadorAsyncTask.isPaused())
                {
                    contadorAsyncTask.setPaused(false);
                }
                break;
            case R.id.btn_pause:
                if (contadorAsyncTask != null)
                    contadorAsyncTask.setPaused(true);
                break;
            case R.id.btn_stop:
                if (contadorAsyncTask != null) {
                    contadorAsyncTask.setStop();
                    contadorAsyncTask = null;
                }
                break;
        }

    }

    @Override
    public void setProgressNow(int i) {
        crono.setText(""+i);
    }
}
