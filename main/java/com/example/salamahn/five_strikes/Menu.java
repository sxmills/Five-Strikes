package com.example.salamahn.five_strikes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu_layout);
        buttons();
    }

    public void buttons()
    {
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.ca);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        ImageButton play = (ImageButton) findViewById(R.id.play);
        play.setBackgroundColor(Color.TRANSPARENT);
        play.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View view)
            {
                sound.start();
                v.vibrate(32);
                Intent playIntent = new Intent("com.example.salamahn.five_strikes.PLAY");
                startActivity(playIntent);
            }
        });
        ImageButton help = (ImageButton) findViewById(R.id.help);
        help.setBackgroundColor(Color.TRANSPARENT);
        help.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View view)
            {
                v.vibrate(32);
                Intent helpIntent = new Intent("com.example.salamahn.five_strikes.HELP");
                startActivity(helpIntent);
            }
        });
        ImageButton rankings = (ImageButton) findViewById(R.id.rankings);
        rankings.setBackgroundColor(Color.TRANSPARENT);
        rankings.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View view)
            {
                v.vibrate(32);
                Intent rankingsIntent = new Intent("com.example.salamahn.five_strikes.RANKINGS");
                startActivity(rankingsIntent);
            }
        });
        ImageButton settings = (ImageButton) findViewById(R.id.settings);
        settings.setBackgroundColor(Color.TRANSPARENT);
        settings.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View view)
            {
                v.vibrate(32);
                Intent settingsIntent = new Intent("com.example.salamahn.five_strikes.SETTINGS");
                startActivity(settingsIntent);
            }
        });
    }

    @Override protected void onPause(){super.onPause();}
}
