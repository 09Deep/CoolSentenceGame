package com.coolsentencegame.ui;

import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import java.util.TimerTask;

public class TimerUI extends TimerTask {

    private final Handler handler;

    public TimerUI(Handler handler)
    {
        this.handler = handler;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run()
    {
        handler.obtainMessage(1).sendToTarget();
    }

}
