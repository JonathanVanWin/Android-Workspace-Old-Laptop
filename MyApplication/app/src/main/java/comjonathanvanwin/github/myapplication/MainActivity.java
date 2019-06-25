package comjonathanvanwin.github.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        OpenCVLoader.initDebug();

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.ivRoad);
        ImageProcessing imageProcessing = new ImageProcessing(this);
        imageProcessing.blur();
        iv.setImageBitmap(imageProcessing.bm);

    }
}
