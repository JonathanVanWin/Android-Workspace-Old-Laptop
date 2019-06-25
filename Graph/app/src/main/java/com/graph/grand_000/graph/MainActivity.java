package com.graph.grand_000.graph;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    private Handler mHandler;
    int id, height, width;
    Button btRight, btLeft,btUp,btDown;
    ImageView ivPlayer, ivPoint;//ivSpike
    TextView tvScore;
    Random random;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivPlayer = findViewById(R.id.ivplayer);
        tvScore = findViewById(R.id.tvScore);
        btRight = findViewById(R.id.btRight);
        btRight.setOnTouchListener(this);

        btLeft = findViewById(R.id.btLeft);
        btLeft.setOnTouchListener(this);

        btUp = findViewById(R.id.btUp);
        btUp.setOnTouchListener(this);

        btDown = findViewById(R.id.btDown);
        btDown.setOnTouchListener(this);

        LinearLayout layout = findViewById(R.id.layoutRoot);
        ivPoint = new ImageView(this);
        ivPoint.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ivPoint.setImageResource(R.drawable.point);
/*
        ivSpike = new ImageView(this);
        ivSpike.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ivSpike.setImageResource(R.drawable.spike);
        ivSpike.setX(50);
        ivSpike.setY(50);
        //layout.addView(ivSpike);*/

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = 1540;//displayMetrics.heightPixels;
        width = 1030;//displayMetrics.widthPixels;

        random = new Random();
        ivPoint.setX(random.nextInt(width));
        ivPoint.setY(random.nextInt(height));
        layout.addView(ivPoint,1);
    }

   @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mHandler != null) return true;
                mHandler = new Handler();
                id=view.getId();
                mHandler.postDelayed(mAction, 0);
                break;
            case MotionEvent.ACTION_UP:
                if (mHandler == null) return true;
                mHandler.removeCallbacks(mAction);
                mHandler = null;
                break;
        }
        return false;
    }

    Runnable mAction = new Runnable() {
        @Override public void run() {

            Rectangle player = new Rectangle(ivPlayer.getX(), ivPlayer.getY(), ivPlayer.getX()
                    + ivPlayer.getWidth(), ivPlayer.getY() +ivPlayer.getHeight());
            Rectangle point = new Rectangle(ivPoint.getX(), ivPoint.getY(), ivPoint.getX()
                    + ivPoint.getWidth(), ivPoint.getY() +ivPoint.getHeight());

            int delta=0;
            if(id==R.id.btLeft||id==R.id.btUp)
                delta=-5;
            else if(id==R.id.btRight||id==R.id.btDown)
                delta=5;
            if(id==R.id.btLeft||id==R.id.btRight)
                ivPlayer.setX(ivPlayer.getX()+delta);
            if(id==R.id.btUp||id==R.id.btDown)
                ivPlayer.setY(ivPlayer.getY()+delta);
            if(player.x1<point.x2 &&player.x2>point.x1 && player.y1<point.y2&&player.y2>point.y1) {
                String textScore = tvScore.getText().toString();
                int score = Integer.parseInt(textScore.substring(7));
                score++;
                int x,y;
                tvScore.setText(textScore.substring(0, 7) + score);
                do {
                     x=random.nextInt(width);
                    y=random.nextInt(height);
                }while(x<0 || x>width || y<0 || y>height);
                ivPoint.setY(y);
                ivPoint.setX(x);
            }
            mHandler.postDelayed(this, 20);
        }
    };

    class Rectangle{
        float x1,y1, x2,y2;
        public Rectangle(float x1, float y1, float  x2, float y2)
        {
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
    }
}
