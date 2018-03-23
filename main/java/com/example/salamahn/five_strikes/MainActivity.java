package com.example.salamahn.five_strikes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        SharedPreferences data = this.getSharedPreferences("com.example.salamahn.five_strikes", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = data.edit();
        if (data.getString("rankings_0_easy_1st", null) == null)
        {
            editor.putString("rankings_0_easy_1st", "00000000");
            editor.putString("rankings_0_easy_2nd", "00000000");
            editor.putString("rankings_0_easy_3rd", "00000000");
            editor.putString("rankings_0_easy_4th", "00000000");
            editor.putString("rankings_0_easy_5th", "00000000");
            editor.putString("rankings_1_normal_1st", "00000000");
            editor.putString("rankings_1_normal_2nd", "00000000");
            editor.putString("rankings_1_normal_3rd", "00000000");
            editor.putString("rankings_1_normal_4th", "00000000");
            editor.putString("rankings_1_normal_5th", "00000000");
            editor.putString("rankings_2_hard_1st", "00000000");
            editor.putString("rankings_2_hard_2nd", "00000000");
            editor.putString("rankings_2_hard_3rd", "00000000");
            editor.putString("rankings_2_hard_4th", "00000000");
            editor.putString("rankings_2_hard_5th", "00000000");
            editor.putInt("settings_0_difficulty", 2);
            editor.putInt("settings_1_sound", 2);
            editor.putInt("settings_2_vibrate", 2);
        }
        editor.apply();
        Thread splashTimer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(4096);
                    Intent menuIntent = new Intent("com.example.salamahn.five_strikes.MENU");
                    startActivity(menuIntent);
                }
                catch(InterruptedException e){e.printStackTrace();}
                finally{finish();}
            }
        };
        splashTimer.start();
    }

    @Override protected void onPause(){super.onPause();}
}