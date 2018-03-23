package com.example.salamahn.five_strikes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Help extends Activity
{
    public void help()
    {
        final ImageButton left = (ImageButton) findViewById(R.id.help_arrow_left);
        left.setBackgroundColor(Color.TRANSPARENT);
        final ImageButton right = (ImageButton) findViewById(R.id.help_arrow_right);
        right.setBackgroundColor(Color.TRANSPARENT);
        final ImageView help_screen = (ImageView) findViewById(R.id.help_screen);

        final Integer zero = 0;
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.cd);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        left.setTag(null);
        right.setTag(0);

        left.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (left.getTag() == zero)
                {
                    left.setImageDrawable(null);
                    left.setTag(null);
                    help_screen.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_screen_0, null));
                    right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_arrow_r0, null));
                    right.setTag(0);
                    sound.start();
                    v.vibrate(32);
                } else
                {
                    left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_arrow_l0, null));
                    left.setTag(0);
                    help_screen.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_screen_1, null));
                    right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_arrow_r1, null));
                    right.setTag(1);
                    sound.start();
                    v.vibrate(32);
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (right.getTag() == zero)
                {
                    left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_arrow_l0, null));
                    left.setTag(0);
                    help_screen.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_screen_1, null));
                    right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_arrow_r1, null));
                    right.setTag(1);
                    sound.start();
                    v.vibrate(32);
                } else
                {
                    left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_arrow_l1, null));
                    left.setTag(1);
                    help_screen.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.help_screen_2, null));
                    right.setImageDrawable(null);
                    right.setTag(null);
                    sound.start();
                    v.vibrate(32);
                }
            }
        });
    }
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.help_layout);
        help();
    }

    @Override protected void onPause(){super.onPause();}
}
