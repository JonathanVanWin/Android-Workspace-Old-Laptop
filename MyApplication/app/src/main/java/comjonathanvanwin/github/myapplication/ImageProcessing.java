package comjonathanvanwin.github.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.*;   // VideoCapture

import java.io.IOException;

import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_MEAN_C;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;


public class ImageProcessing {

    private Mat src;
    public Bitmap bm;

    public ImageProcessing(Context context) {
        //Toast.makeText(context, context.getResources().getDrawable(R.drawable.road).toString(), Toast.LENGTH_LONG).show();
        try {
            src = Utils.loadResource(context, R.drawable.road, Imgcodecs.CV_LOAD_IMAGE_COLOR);
        }catch (IOException e){
            e.printStackTrace();
        }
        Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2BGRA);
        bm = Bitmap.createBitmap(src.cols(), src.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(src, bm);
    }

    public void blur(){
        Imgproc.adaptiveThreshold(src, src, 255, ADAPTIVE_THRESH_MEAN_C, THRESH_BINARY, 15, 40);
        updateBitmap();
    }

    private void updateBitmap(){
        bm = Bitmap.createBitmap(src.cols(), src.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(src, bm);
    }
}
