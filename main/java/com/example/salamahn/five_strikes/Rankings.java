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
import android.widget.ImageView;
import android.widget.TextView;

public class Rankings extends Activity
{
    public void rankings()
    {
        final SharedPreferences data = this.getSharedPreferences("com.example.salamahn.five_strikes", Context.MODE_PRIVATE);

        final ImageButton left = (ImageButton) findViewById(R.id.rankings_arrow_left);
        left.setBackgroundColor(Color.TRANSPARENT);
        final ImageButton right = (ImageButton) findViewById(R.id.rankings_arrow_right);
        right.setBackgroundColor(Color.TRANSPARENT);
        final ImageView rankings_1st = (ImageView) findViewById(R.id.rankings_1st);
        final ImageView rankings_2nd = (ImageView) findViewById(R.id.rankings_2nd);
        final ImageView rankings_3rd = (ImageView) findViewById(R.id.rankings_3rd);
        final ImageView rankings_4th = (ImageView) findViewById(R.id.rankings_4th);
        final ImageView rankings_5th = (ImageView) findViewById(R.id.rankings_5th);
        final TextView rankings_1st_text = (TextView) findViewById(R.id.rankings_1st_text);
        final TextView rankings_2nd_text = (TextView) findViewById(R.id.rankings_2nd_text);
        final TextView rankings_3rd_text = (TextView) findViewById(R.id.rankings_3rd_text);
        final TextView rankings_4th_text = (TextView) findViewById(R.id.rankings_4th_text);
        final TextView rankings_5th_text = (TextView) findViewById(R.id.rankings_5th_text);
        final ImageView rankings_difficulty = (ImageView) findViewById(R.id.rankings_difficulty);

        if (data.getInt("settings_0_difficulty", 0) == 1)
        {
            left.setImageDrawable(null);
            left.setTag(null);
            rankings_1st.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_1st, null));
            rankings_2nd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_2nd, null));
            rankings_3rd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_3rd, null));
            rankings_4th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_4th, null));
            rankings_5th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_5th, null));
            rankings_1st_text.setText(data.getString("rankings_0_easy_1st", null));
            rankings_2nd_text.setText(data.getString("rankings_0_easy_2nd", null));
            rankings_3rd_text.setText(data.getString("rankings_0_easy_3rd", null));
            rankings_4th_text.setText(data.getString("rankings_0_easy_4th", null));
            rankings_5th_text.setText(data.getString("rankings_0_easy_5th", null));
            rankings_difficulty.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_easy_1, null));
            right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_r0_normal, null));
            right.setTag(0);
        }
        else if (data.getInt("settings_0_difficulty", 0) == 2)
        {
            left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_l0_easy, null));
            left.setTag(0);
            rankings_1st.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_1st, null));
            rankings_2nd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_2nd, null));
            rankings_3rd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_3rd, null));
            rankings_4th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_4th, null));
            rankings_5th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_5th, null));
            rankings_1st_text.setText(data.getString("rankings_1_normal_1st", null));
            rankings_2nd_text.setText(data.getString("rankings_1_normal_2nd", null));
            rankings_3rd_text.setText(data.getString("rankings_1_normal_3rd", null));
            rankings_4th_text.setText(data.getString("rankings_1_normal_4th", null));
            rankings_5th_text.setText(data.getString("rankings_1_normal_5th", null));
            rankings_difficulty.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_normal_1, null));
            right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_r1_hard, null));
            right.setTag(1);
        }
        else
        {
            left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_l1_normal, null));
            left.setTag(1);
            rankings_1st.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_1st, null));
            rankings_2nd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_2nd, null));
            rankings_3rd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_3rd, null));
            rankings_4th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_4th, null));
            rankings_5th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_5th, null));
            rankings_1st_text.setText(data.getString("rankings_2_hard_1st", null));
            rankings_2nd_text.setText(data.getString("rankings_2_hard_2nd", null));
            rankings_3rd_text.setText(data.getString("rankings_2_hard_3rd", null));
            rankings_4th_text.setText(data.getString("rankings_2_hard_4th", null));
            rankings_5th_text.setText(data.getString("rankings_2_hard_5th", null));
            rankings_difficulty.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_hard_1, null));
            right.setImageDrawable(null);
            right.setTag(null);
        }

        final Integer zero = 0;
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.cd);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        left.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if(left.getTag() == zero)
                {
                    left.setImageDrawable(null);
                    left.setTag(null);
                    rankings_1st.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_1st, null));
                    rankings_2nd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_2nd, null));
                    rankings_3rd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_3rd, null));
                    rankings_4th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_4th, null));
                    rankings_5th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_0_easy_5th, null));
                    rankings_1st_text.setText(data.getString("rankings_0_easy_1st", null));
                    rankings_2nd_text.setText(data.getString("rankings_0_easy_2nd", null));
                    rankings_3rd_text.setText(data.getString("rankings_0_easy_3rd", null));
                    rankings_4th_text.setText(data.getString("rankings_0_easy_4th", null));
                    rankings_5th_text.setText(data.getString("rankings_0_easy_5th", null));
                    rankings_difficulty.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_easy_1, null));
                    right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_r0_normal, null));
                    right.setTag(0);
                    sound.start();
                    v.vibrate(32);
                }
                else
                {
                    left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_l0_easy, null));
                    left.setTag(0);
                    rankings_1st.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_1st, null));
                    rankings_2nd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_2nd, null));
                    rankings_3rd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_3rd, null));
                    rankings_4th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_4th, null));
                    rankings_5th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_5th, null));
                    rankings_1st_text.setText(data.getString("rankings_1_normal_1st", null));
                    rankings_2nd_text.setText(data.getString("rankings_1_normal_2nd", null));
                    rankings_3rd_text.setText(data.getString("rankings_1_normal_3rd", null));
                    rankings_4th_text.setText(data.getString("rankings_1_normal_4th", null));
                    rankings_5th_text.setText(data.getString("rankings_1_normal_5th", null));
                    rankings_difficulty.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_normal_1, null));
                    right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_r1_hard, null));
                    right.setTag(1);
                    sound.start();
                    v.vibrate(32);
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if(right.getTag() == zero)
                {
                    left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_l0_easy, null));
                    left.setTag(0);
                    rankings_1st.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_1st, null));
                    rankings_2nd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_2nd, null));
                    rankings_3rd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_3rd, null));
                    rankings_4th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_4th, null));
                    rankings_5th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_1_normal_5th, null));
                    rankings_1st_text.setText(data.getString("rankings_1_normal_1st", null));
                    rankings_2nd_text.setText(data.getString("rankings_1_normal_2nd", null));
                    rankings_3rd_text.setText(data.getString("rankings_1_normal_3rd", null));
                    rankings_4th_text.setText(data.getString("rankings_1_normal_4th", null));
                    rankings_5th_text.setText(data.getString("rankings_1_normal_5th", null));
                    rankings_difficulty.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_normal_1, null));
                    right.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_r1_hard, null));
                    right.setTag(1);
                    sound.start();
                    v.vibrate(32);
                }
                else
                {
                    left.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_arrow_l1_normal, null));
                    left.setTag(1);
                    rankings_1st.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_1st, null));
                    rankings_2nd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_2nd, null));
                    rankings_3rd.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_3rd, null));
                    rankings_4th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_4th, null));
                    rankings_5th.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rankings_2_hard_5th, null));
                    rankings_1st_text.setText(data.getString("rankings_2_hard_1st", null));
                    rankings_2nd_text.setText(data.getString("rankings_2_hard_2nd", null));
                    rankings_3rd_text.setText(data.getString("rankings_2_hard_3rd", null));
                    rankings_4th_text.setText(data.getString("rankings_2_hard_4th", null));
                    rankings_5th_text.setText(data.getString("rankings_2_hard_5th", null));
                    rankings_difficulty.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings_difficulty_hard_1, null));
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
        setContentView(R.layout.rankings_layout);
        rankings();
    }

    @Override protected void onPause(){super.onPause();}
}
