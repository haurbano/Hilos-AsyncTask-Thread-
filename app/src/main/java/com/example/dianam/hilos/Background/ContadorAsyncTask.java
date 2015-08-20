package com.example.dianam.hilos.Background;

import android.os.AsyncTask;

/**
 * Created by DianaM on 20/08/2015.
 */
public class ContadorAsyncTask extends AsyncTask<Integer,Integer,Integer> {



    public interface ProgressI
    {
     void setProgressNow( int i);
    }

    int cont;
    boolean ruuning, paused;
    ProgressI progressI;

    public ContadorAsyncTask(ProgressI progressI)
    {
        this.progressI = progressI;
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        ruuning = true;
        cont = 0;
        while(ruuning)
        {
            try {
                Thread.sleep(1000);
                if (!paused)
                cont++;
                publishProgress(cont);
                publishProgress(cont);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        return cont;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressI.setProgressNow(values[0]);
    }

    public void setStop()
    {
        ruuning = false;
    }
    public void setPaused(boolean f)
    {
        paused = f;
    }

    public boolean isPaused()
    {
        return paused;
    }

    public boolean isRuuning()
    {
        return ruuning;
    }
}
