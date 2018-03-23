package com.example.salamahn.five_strikes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class Settings extends Activity
{
    public void settings()
    {
        SharedPreferences data = this.getSharedPreferences("com.example.salamahn.five_strikes", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = data.edit();

        final ImageButton easy = (ImageButton) findViewById(R.id.settings_difficulty_easy);
        easy.setBackgroundColor(Color.TRANSPARENT);
        if (data.getInt("settings_0_difficulty", 0) == 1)
        {
            easy.setTag(true);
            easy.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_easy_1, null));
        }
        else easy.setTag(false);
        final ImageButton normal = (ImageButton) findViewById(R.id.settings_difficulty_normal);
        normal.setBackgroundColor(Color.TRANSPARENT);
        if (data.getInt("settings_0_difficulty", 0) == 2)
        {
            normal.setTag(true);
            normal.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_normal_1, null));
        }
        else normal.setTag(false);
        final ImageButton hard = (ImageButton) findViewById(R.id.settings_difficulty_hard);
        hard.setBackgroundColor(Color.TRANSPARENT);
        if (data.getInt("settings_0_difficulty", 0) == 3)
        {
            hard.setTag(true);
            hard.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_hard_1, null));
        }
        else hard.setTag(false);
        easy.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if (easy.getTag() == Boolean.FALSE)
                {
                    if (normal.getTag() == Boolean.TRUE)
                    {
                        normal.setTag(false);
                        normal.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_normal_0, null));
                    }
                    else
                    {
                        hard.setTag(false);
                        hard.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_hard_0, null));
                    }
                    easy.setTag(true);
                    easy.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_easy_1, null));
                    editor.putInt("settings_0_difficulty", 1);
                    editor.apply();
                }
            }
        });
        normal.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if (normal.getTag() == Boolean.FALSE)
                {
                    if (hard.getTag() == Boolean.TRUE)
                    {
                        hard.setTag(false);
                        hard.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_hard_0, null));
                    }
                    else
                    {
                        easy.setTag(false);
                        easy.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_easy_0, null));
                    }
                    normal.setTag(true);
                    normal.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_normal_1, null));
                    editor.putInt("settings_0_difficulty", 2);
                    editor.apply();
                }
            }
        });
        hard.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if (hard.getTag() == Boolean.FALSE)
                {
                    if (easy.getTag() == Boolean.TRUE)
                    {
                        easy.setTag(false);
                        easy.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_easy_0, null));
                    }
                    else
                    {
                        normal.setTag(false);
                        normal.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_normal_0, null));
                    }
                    hard.setTag(true);
                    hard.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_hard_1, null));
                    editor.putInt("settings_0_difficulty", 3);
                    editor.apply();
                }
            }
        });

        final ImageButton sound_off = (ImageButton) findViewById(R.id.settings_option_sound_off);
        sound_off.setBackgroundColor(Color.TRANSPARENT);
        if (data.getInt("settings_1_sound", 0) == 1)
        {
            sound_off.setTag(true);
            sound_off.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_off_1, null));
        }
        else sound_off.setTag(false);
        final ImageButton sound_on = (ImageButton) findViewById(R.id.settings_option_sound_on);
        sound_on.setBackgroundColor(Color.TRANSPARENT);
        if (data.getInt("settings_1_sound", 0) == 2)
        {
            sound_on.setTag(true);
            sound_on.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_on_1, null));
        }
        else sound_on.setTag(false);
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.cd);
        sound_off.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if (sound_off.getTag() == Boolean.FALSE)
                {
                    sound_on.setTag(false);
                    sound_on.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_on_0, null));
                    sound_off.setTag(true);
                    sound_off.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_off_1, null));
                    editor.putInt("settings_1_sound", 1);
                    editor.apply();
                }
            }
        });
        sound_on.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if (sound_on.getTag() == Boolean.FALSE)
                {
                    sound_off.setTag(false);
                    sound_off.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_off_0, null));
                    sound_on.setTag(true);
                    sound_on.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_on_1, null));
                    editor.putInt("settings_1_sound", 2);
                    editor.apply();
                    sound.start();
                }
            }
        });

        final ImageButton vibrate_off = (ImageButton) findViewById(R.id.settings_option_vibrate_off);
        vibrate_off.setBackgroundColor(Color.TRANSPARENT);
        if (data.getInt("settings_2_vibrate", 0) == 1)
        {
            vibrate_off.setTag(true);
            vibrate_off.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_off_1, null));
        }
        else vibrate_off.setTag(false);
        final ImageButton vibrate_on = (ImageButton) findViewById(R.id.settings_option_vibrate_on);
        vibrate_on.setBackgroundColor(Color.TRANSPARENT);
        if (data.getInt("settings_2_vibrate", 0) == 2)
        {
            vibrate_on.setTag(true);
            vibrate_on.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_on_1, null));
        }
        else vibrate_on.setTag(false);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrate_off.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if (vibrate_off.getTag() == Boolean.FALSE)
                {
                    vibrate_on.setTag(false);
                    vibrate_on.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_on_0, null));
                    vibrate_off.setTag(true);
                    vibrate_off.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_off_1, null));
                    editor.putInt("settings_2_vibrate", 1);
                    editor.apply();
                }
            }
        });
        vibrate_on.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if (vibrate_on.getTag() == Boolean.FALSE)
                {
                    vibrate_off.setTag(false);
                    vibrate_off.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_off_0, null));
                    vibrate_on.setTag(true);
                    vibrate_on.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_option_on_1, null));
                    editor.putInt("settings_2_vibrate", 2);
                    editor.apply();
                    v.vibrate(256);
                }
            }
        });

        final ImageButton about = (ImageButton) findViewById(R.id.settings_about);
        about.setBackgroundColor(Color.TRANSPARENT);
        final Toast about_toast = Toast.makeText(getApplicationContext(), "XO Entertainment 2016", Toast.LENGTH_SHORT);
        about.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View view){about_toast.show();}});
    }

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.settings_layout);
        settings();
    }

    @Override protected void onPause(){super.onPause();}
}
