package comjonathanvanwin.github.openglthin;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Render;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private Loader loader;
    private StaticShader shader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = new Loader();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(3);

        shader = new StaticShader(getResources().openRawResource(R.raw.vertex_shader), getResources().openRawResource(R.raw.fragment_shader));
        //RawModel model = loader.loadToVAO(vertices, indices);
        glSurfaceView.setRenderer(new Render(this)/*new Renderer(model, shader)*/);

        setContentView(glSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

}
