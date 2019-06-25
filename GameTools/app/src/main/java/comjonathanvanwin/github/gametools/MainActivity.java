package comjonathanvanwin.github.gametools;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.stream.DoubleStream;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView _surfaceView;
    private TextView _textView;
    public static final String path = "Basic.txt";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        _surfaceView = new GLSurfaceView(this);
        _surfaceView.setEGLContextClientVersion(2);
        _surfaceView.setRenderer(new MyRenderer());
        setContentView(_surfaceView);

        LinearLayout layout = new LinearLayout(this);

        LinearLayout.LayoutParams layoutParamsUpDown = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setGravity(Gravity.BOTTOM | Gravity.LEFT);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linearLayoutView = inflater.inflate(R.layout.activity_main, layout, false);
        layout.addView(linearLayoutView);
        View view = inflater.inflate(R.layout.counter, layout, false);
        layout.addView(view);

        addContentView(layout, layoutParamsUpDown);
        _textView = (TextView) findViewById(R.id.tvcounter);
        InputStream input = getResources().openRawResource(R.raw.vertex);
        Toast.makeText(MainActivity.this,ShaderUtils.readTextFile(input),Toast.LENGTH_LONG).show();
        setUpDownClickListeners();
    }

        public void doShaderStuff(){
            int buffer;
            float[] positions= {-0.5f, -0.5f,
                                0.0f,  0.5f,
                                0.5f,  -0.5f};

            ByteBuffer bBuff = ByteBuffer.allocateDirect(positions.length * 4);
            bBuff.order(ByteOrder.nativeOrder());
            FloatBuffer vertBuff = bBuff.asFloatBuffer();
            vertBuff.put(positions);
            vertBuff.position(0);

            int[] buffers = new int[1];
            GLES20.glGenBuffers(1, buffers,0);
            int rectVerts = buffers[0];
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, rectVerts);
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, (vertBuff.limit()*4), vertBuff, GLES20.GL_STATIC_DRAW);

            //https://www.codeproject.com/Articles/1007973/Basic-Shader-Programming-on-Android
        }

        public void setUpDownClickListeners() {
        Button buttonUp, buttonDown;

        buttonUp = (Button) findViewById(R.id.up);
        buttonDown = (Button) findViewById(R.id.down);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Counter.getUpDownNextValue();
                _textView.setText(String.valueOf(Counter.getUpDownValue()));
            }
        });
        buttonDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Counter.getUpDownPreviousValue();
                _textView.setText(String.valueOf(Counter.getUpDownValue()));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        _surfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _surfaceView.onPause();
    }
}
