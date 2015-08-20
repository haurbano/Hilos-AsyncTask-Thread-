package com.example.dianam.hilos.Background;

import android.os.Handler;
import android.os.Message;



/**
 * Created by DianaM on 20/08/2015.
 */
public class ContadorThread extends Thread {

    public static final int SECONDS = 0;

    boolean running, paused;
    Handler handler;
    public ContadorThread(Handler handler)
    {
        this.handler = handler;

    }

    @Override
    public void run(){

        running = true;
        int cont=0;
        while (running)
        {
            try {
                Thread.sleep(1000);
                if (!paused)
                cont++;

                Message msg = handler.obtainMessage();
                msg.what = SECONDS;
                msg.arg1 = cont;

                handler.sendMessage(msg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void stopCont()
    {
        running = false;
    }

    public  void setPaused(boolean paused)
    {
     this.paused = paused;
    }

    public boolean isPaused()
    {
        return paused;
    }

    public boolean isRunning()
    {
        return running;
    }
}
