package com.example.guessthecountry;

import android.app.Application;
import android.media.MediaPlayer;

public class MyApplication extends Application {
    private static MyApplication instance;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mediaPlayer = MediaPlayer.create(this, R.raw.melody);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
