package comjonathanvanwin.github.a2dgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;

public class DrawingTheBall extends View {

    Bitmap bball;
    int x, y;

    public DrawingTheBall(Context context) {
        super(context);

        Paint mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setAntiAlias(true);

        bball = BitmapFactory.decodeResource(getResources(), R.drawable.ball).copy(Bitmap.Config.ARGB_8888, true);
        changeColor(bball, 0xFFFFFFFF, Color.TRANSPARENT,  0xFF2E37B1, 0x32000000);
        bball.setHasAlpha(true);
        Canvas canvas = new Canvas(bball);
        canvas.drawBitmap(bball, 0, 0, mPaint);
        x = 0;
        y = 0;
    }

    // Change oldColor and replaces with newColor, need to put an even number of parameters
    public static void changeColor(Bitmap bit, int... oldAndNewColors) {
        for (int x = 0; x < bit.getWidth(); x++) {
            for (int y = 0; y < bit.getHeight(); y++) {
                for (int i = 0; i < oldAndNewColors.length; i+=2) {
                    if (bit.getPixel(x, y) == oldAndNewColors[i]) {
                        bit.setPixel(x, y, oldAndNewColors[i+1]);
                    }
                }
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect ourRect = new Rect();
        ourRect.set(0, 0, canvas.getWidth(), canvas.getHeight() / 2);

        Paint blue = new Paint();
        blue.setColor(Color.RED);
        blue.setStyle(Paint.Style.FILL);

        canvas.drawRect(ourRect, blue);
        if (x < canvas.getWidth()) {
            x += 10;
        } else {
            x = 0;
        }
        if (y < canvas.getHeight()) {
            y += 10;
        } else {
            y = 0;
        }
        Paint p = new Paint();

        canvas.drawBitmap(bball, x, y, p);
        invalidate();
    }
}
