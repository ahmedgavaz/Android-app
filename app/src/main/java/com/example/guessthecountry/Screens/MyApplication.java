package com.example.guessthecountry.Screens;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.guessthecountry.R;

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