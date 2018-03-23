package com.example.salamahn.five_strikes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;

public class Play extends Activity
{
    public class PlayView extends View
    {
        class Category
        {
            Rect border, inner;
            String text;
            Category(String s)
            {
                border = new Rect();
                inner = new Rect();
                text = s;
            }
            public void draw(Canvas canvas)
            {
                border.set(canvas.getWidth(), canvas.getHeight() * 3 / 8, canvas.getWidth() * 9 / 8, canvas.getHeight());
                inner.set(canvas.getWidth() * 257 / 256, canvas.getHeight() * 55 / 144, canvas.getWidth() * 287 / 256, canvas.getHeight() * 143 / 144);
                canvas.drawRect(border, category_border_paint);
                canvas.drawRect(inner, health_pt_paint[0]);
                canvas.save();
                canvas.rotate(90.0f, canvas.getWidth() * 17 / 16, canvas.getHeight() * 11 / 16 + category_text.descent());
                canvas.drawText(text, canvas.getWidth() * 17 / 16, canvas.getHeight() * 11 / 16, category_text);
                canvas.restore();
            }
            public void draw(Canvas canvas, Paint paint, int x)
            {
                border.set(canvas.getWidth() * x / 8, canvas.getHeight() * 3 / 8, canvas.getWidth() * (1 + x) / 8, canvas.getHeight());
                inner.set(canvas.getWidth() * (1 + 32 * x) / 256, canvas.getHeight() * 55 / 144, canvas.getWidth() * (31 + 32 * x)/ 256, canvas.getHeight() * 143 / 144);
                canvas.drawRect(border, category_border_paint);
                canvas.drawRect(inner, paint);
                canvas.save();
                canvas.rotate(90.0f, canvas.getWidth() * (1 + 2 * x) / 16, canvas.getHeight() * 11 / 16);
                canvas.drawText(text, canvas.getWidth() * (1 + 2 * x) / 16, canvas.getHeight() * 11 / 16 + category_text.descent(), category_text);
                canvas.restore();
            }
        }
        class TileColumn
        {
            class Tile
            {
                Rect border, inner;
                String text;
                boolean guess;
                Tile()
                {
                    border = new Rect();
                    inner = new Rect();
                    text = "";
                    guess = false;
                }
                public void draw(Canvas canvas, int y)
                {
                    border.set(canvas.getWidth(), canvas.getHeight() * (9 + 5 * y) / 24, canvas.getWidth() * 9 / 8, canvas.getHeight() * (14 + 5 * y) / 24);
                    inner.set(canvas.getWidth() * 257 / 256, canvas.getHeight() * (55 + 30 * y) / 144, canvas.getWidth() * 287 / 256, canvas.getHeight() * (83 + 30 * y)/ 144);
                    canvas.drawRect(border, health_pt_border_paint);
                    canvas.drawRect(inner, hud_bg_paint);
                    canvas.drawText(text, canvas.getWidth() * 17 / 16, (canvas.getHeight() * (23 + 10 * y) / 48) + tile_text.descent(), tile_text);
                }
                public void draw(Canvas canvas, int x, int y)
                {
                    border.set(canvas.getWidth() * x / 8, canvas.getHeight() * (9 + 5 * y) / 24, canvas.getWidth() * (1 + x) / 8, canvas.getHeight() * (14 + 5 * y) / 24);
                    inner.set(canvas.getWidth() * (1 + 32 * x) / 256, canvas.getHeight() * (55 + 30 * y) / 144, canvas.getWidth() * (31 + 32 * x)/ 256, canvas.getHeight() * (83 + 30 * y)/ 144);
                    canvas.drawRect(border, health_pt_border_paint);
                    canvas.drawRect(inner, hud_bg_paint);
                    canvas.drawText(text, canvas.getWidth() * (1 + 2 * x) / 16, (canvas.getHeight() * (23 + 10 * y) / 48) + tile_text.descent(), tile_text);
                }
            }
            Tile a, b, c;
            char answer;
            String category;
            char index;
            TileColumn()
            {
                a = new Tile();
                b = new Tile();
                c = new Tile();
                category = "";
                index = 0;
            }
            public void setString(String x, String y, String z)
            {
                a.text = x;
                b.text = y;
                c.text = z;
            }
            public void draw(Canvas canvas)
            {
                a.draw(canvas, 0);
                b.draw(canvas, 1);
                c.draw(canvas, 2);
            }
            public void draw(Canvas canvas, int x)
            {
                a.draw(canvas, x, 0);
                b.draw(canvas, x, 1);
                c.draw(canvas, x, 2);
            }
        }

        Rect hud_bg;
        Paint hud_bg_paint = new Paint();

        Bitmap hud_0_hi_bm = BitmapFactory.decodeResource(getResources(), R.drawable.hud_0_hi);
        BitmapDrawable hud_0_hi = new BitmapDrawable(getResources(), hud_0_hi_bm);
        Bitmap hud_1_score_bm = BitmapFactory.decodeResource(getResources(), R.drawable.hud_1_score);
        BitmapDrawable hud_1_score = new BitmapDrawable(getResources(), hud_1_score_bm);
        Bitmap hud_2_time_bm = BitmapFactory.decodeResource(getResources(), R.drawable.hud_2_time);
        BitmapDrawable hud_2_time = new BitmapDrawable(getResources(), hud_2_time_bm);
        Paint hud_text = new Paint();

        Rect health_bg = new Rect();
        Paint health_bg_paint = new Paint();
        Paint health_pt_border_paint = new Paint();
        Paint[] health_pt_paint = new Paint[5];
        Rect health_pt_border = new Rect();
        Rect health_pt = new Rect();
        Bitmap expression_0_lose_bm = BitmapFactory.decodeResource(getResources(), R.drawable.expression_0_lose);
        Bitmap expression_1_red_bm = BitmapFactory.decodeResource(getResources(), R.drawable.expression_1_red);
        Bitmap expression_2_orange_bm = BitmapFactory.decodeResource(getResources(), R.drawable.expression_2_orange);
        Bitmap expression_3_yellow_bm = BitmapFactory.decodeResource(getResources(), R.drawable.expression_3_yellow);
        Bitmap expression_4_green_bm = BitmapFactory.decodeResource(getResources(), R.drawable.expression_4_green);
        Bitmap expression_5_blue_bm = BitmapFactory.decodeResource(getResources(), R.drawable.expression_5_blue);
        Bitmap expression_6_max_bm = BitmapFactory.decodeResource(getResources(), R.drawable.expression_6_max);
        BitmapDrawable[] expression = new BitmapDrawable[7];

        SharedPreferences data = getContext().getSharedPreferences("com.example.salamahn.five_strikes", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = data.edit();
        Paint category_border_paint = new Paint();
        Category[] category = new Category[3];
        TileColumn[] tilecolumn = new TileColumn[9];
        Paint category_text = new Paint();
        Paint tile_text = new Paint();
        String score = "00000000";
        String time = "00:00";
        String[] categories = new String[4];
        String[] alph_u = new String[26];
        String[] alph_l = new String[26];
        String[] elems = new String[36];
        String[] pi = new String[16];
        char health, level, cat, col, rand;

        public PlayView(Context context)
        {
            super(context);

            hud_bg = new Rect();
            hud_bg_paint.setColor(Color.argb(255, 255, 230, 213));
            hud_bg_paint.setStyle(Paint.Style.FILL);
            hud_text.setColor(Color.argb(255, 0, 0, 0));
            hud_text.setStyle(Paint.Style.FILL);
            hud_text.setTypeface(Typeface.MONOSPACE);
            hud_text.setTextSize(88);
            hud_text.setTextAlign(Paint.Align.LEFT);

            health_bg_paint.setColor(Color.argb(255, 222, 170, 135));
            health_bg_paint.setStyle(Paint.Style.FILL);
            for (char c = 0; c < 5; ++c) health_pt_paint[c] = new Paint();
            health_pt_border_paint.setColor(Color.argb(255, 0, 0, 0));
            health_pt_border_paint.setStyle(Paint.Style.FILL);
            health_pt_paint[0].setColor(Color.argb(255, 255, 47, 47)); //red
            health_pt_paint[0].setStyle(Paint.Style.FILL);
            health_pt_paint[1].setColor(Color.argb(255, 255, 127, 47)); //orange
            health_pt_paint[1].setStyle(Paint.Style.FILL);
            health_pt_paint[2].setColor(Color.argb(255, 255, 255, 47)); //yellow
            health_pt_paint[2].setStyle(Paint.Style.FILL);
            health_pt_paint[3].setColor(Color.argb(255, 15, 191, 63)); //green
            health_pt_paint[3].setStyle(Paint.Style.FILL);
            health_pt_paint[4].setColor(Color.argb(255, 47, 47, 255)); //blue
            health_pt_paint[4].setStyle(Paint.Style.FILL);
            expression[0] = new BitmapDrawable(getResources(), expression_0_lose_bm);
            expression[1] = new BitmapDrawable(getResources(), expression_1_red_bm);
            expression[2] = new BitmapDrawable(getResources(), expression_2_orange_bm);
            expression[3] = new BitmapDrawable(getResources(), expression_3_yellow_bm);
            expression[4] = new BitmapDrawable(getResources(), expression_4_green_bm);
            expression[5] = new BitmapDrawable(getResources(), expression_5_blue_bm);
            expression[6] = new BitmapDrawable(getResources(), expression_6_max_bm);

            category_border_paint.setColor(Color.argb(255, 255, 255, 255));
            category_border_paint.setStyle(Paint.Style.FILL);
            for (char c = 0; c < 3; ++c) category[c] = new Category("");
            for (char c = 0; c < 9; ++c) tilecolumn[c] = new TileColumn();
            category_text.setColor(Color.argb(255, 255, 255, 255));
            category_text.setStyle(Paint.Style.FILL);
            category_text.setTypeface(Typeface.MONOSPACE);
            category_text.setTextSize(160);
            category_text.setTextAlign(Paint.Align.CENTER);
            tile_text.setColor(Color.argb(255, 0, 0, 0));
            tile_text.setStyle(Paint.Style.FILL);
            tile_text.setTypeface(Typeface.MONOSPACE);
            tile_text.setTextSize(256);
            tile_text.setTextAlign(Paint.Align.CENTER);
            categories[0] = "ALPHABET";
            categories[1] = "alphabet";
            categories[2] = "Elements";
            categories[3] = "\u03C0";
            for (char c = 0; c < 26; ++c){alph_u[c] = String.valueOf(c + 65);}
            for (char c = 0; c < 26; ++c){alph_l[c] = String.valueOf(c + 97);}
            elems[0] = "H";
            elems[1] = "He";
            elems[2] = "Li";
            elems[3] = "Be";
            elems[4] = "B";
            elems[5] = "C";
            elems[6] = "N";
            elems[7] = "O";
            elems[8] = "F";
            elems[9] = "Ne";
            elems[10] = "Na";
            elems[11] = "Mg";
            elems[12] = "Al";
            elems[13] = "Si";
            elems[14] = "P";
            elems[15] = "S";
            elems[16] = "Cl";
            elems[17] = "Ar";
            elems[18] = "K";
            elems[19] = "Ca";
            elems[20] = "Sc";
            elems[21] = "Ti";
            elems[22] = "V";
            elems[23] = "Cr";
            elems[24] = "Mn";
            elems[25] = "Fe";
            elems[26] = "Co";
            elems[27] = "Ni";
            elems[28] = "Cu";
            elems[29] = "Zn";
            elems[30] = "Ga";
            elems[31] = "Ge";
            elems[32] = "As";
            elems[33] = "Se";
            elems[34] = "Br";
            elems[35] = "Kr";
            pi[0] = "3.";
            pi[1] = "1";
            pi[2] = "4";
            pi[3] = "1";
            pi[4] = "5";
            pi[5] = "9";
            pi[6] = "2";
            pi[7] = "6";
            pi[8] = "5";
            pi[9] = "3";
            pi[10] = "5";
            pi[11] = "8";
            pi[12] = "9";
            pi[13] = "7";
            pi[14] = "9";
            pi[15] = "3";
        }

        @Override protected void onDraw(Canvas canvas)
        {
            hud_bg.set(0, 0, canvas.getWidth(), canvas.getHeight() / 12);
            canvas.drawRect(hud_bg, hud_bg_paint);
            hud_0_hi.setBounds(canvas.getWidth() / 128, 0, canvas.getWidth() * 7 / 128, canvas.getHeight() / 12);
            hud_0_hi.draw(canvas);
            hud_1_score.setBounds(canvas.getWidth() * 33 / 128, 0, canvas.getWidth() * 39 / 128, canvas.getHeight() / 12);
            hud_1_score.draw(canvas);
            canvas.drawText(score, canvas.getWidth() * 82 / 256, canvas.getHeight() / 16, hud_text);
            hud_2_time.setBounds(canvas.getWidth() * 65 / 128, 0, canvas.getWidth() * 71 / 128, canvas.getHeight() / 12);
            hud_2_time.draw(canvas);
            canvas.drawText(time, canvas.getWidth() * 80 / 128, canvas.getHeight() / 16, hud_text);

            health_bg.set(0, canvas.getHeight() / 12, canvas.getWidth() / 4, canvas.getHeight() * 3 / 8);
            canvas.drawRect(health_bg, health_bg_paint);
            for (char c = 0; c < 5; ++c)
            {
                health_pt_border.set(canvas.getWidth() / 128, canvas.getHeight() * (13 + 8 * c) / 144, canvas.getWidth() * 11 / 128, canvas.getHeight() * (21 + 8 * c) / 144);
                health_pt.set(canvas.getWidth() * 3 / 256, canvas.getHeight() * (7 + 4 * c) / 72, canvas.getWidth() * 21 / 256, canvas.getHeight() * (5 + 2 * c) / 36);
                canvas.drawRect(health_pt_border, health_pt_border_paint);
                canvas.drawRect(health_pt, health_pt_paint[4 - c]);
            }
            for (char c = 0; c < 7; ++c) expression[c].setBounds(canvas.getWidth() * 27 / 256, canvas.getHeight() * 17 / 144, canvas.getWidth() * 59 / 256, canvas.getHeight() * 49 / 144);
            expression[5].draw(canvas);

            if (data.getInt("settings_0_difficulty", 0) == 1)
            {
                level = 3;
                canvas.drawText(data.getString("rankings_0_easy_1st", null), canvas.getWidth() * 18 / 256, canvas.getHeight() / 16, hud_text);
            }
            else if (data.getInt("settings_0_difficulty", 0) == 2)
            {
                level = 5;
                canvas.drawText(data.getString("rankings_1_normal_1st", null), canvas.getWidth() * 18 / 256, canvas.getHeight() / 16, hud_text);
            }
            else
            {
                level = 7;
                canvas.drawText(data.getString("rankings_2_hard_1st", null), canvas.getWidth() * 18 / 256, canvas.getHeight() / 16, hud_text);
            }
            cat = 0;
            col = 0;
            int count = 0;
            class Test
            {
                String a, b;
                boolean c;
                Test(String x, String y, boolean z)
                {
                    a = x;
                    b = y;
                    c = z;
                }
            }
            Test[] test = new Test[9];
            for (char c = 0; c < 9; ++c)
            {
                if (col % level == 0)
                {
                    if (c == 0)
                    {
                        rand = (char)(Math.random() * categories.length);
                        category[0].text = categories[rand];
                        category[0].draw(canvas, health_pt_paint[3], c);
                    }
                    else
                    {
                        rand = (char)(Math.random() * categories.length);
                        test[c] = new Test(categories[rand], categories[cat - 1], categories[rand].equals(categories[cat - 1]));
                        while (categories[rand].equals(categories[cat - 1]))
                        {
                            rand = (char)(Math.random() * categories.length); //to ensure no repeating categories
                        }
                        category[cat].text = categories[rand];
                        category[cat].draw(canvas, health_pt_paint[0], c);
                    }
                    ++cat;
                    ++c;
                }
                tilecolumn[col].draw(canvas, c);
                ++col;
            }
            Timer timer = new Timer();
            int x = count;
            int y = 3;
        }
    }

    PlayView PlayView;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PlayView = new PlayView(this);
        setContentView(PlayView);
    }

    @Override protected void onPause(){super.onPause();}
}