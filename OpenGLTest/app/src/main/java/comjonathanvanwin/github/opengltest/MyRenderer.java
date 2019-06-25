package comjonathanvanwin.github.opengltest;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {

    private TriangleRenderer triangleRenderer;
    private CubeRenderer cubeRenderer;
    private GLSurfaceView.Renderer currentRenderer;
    public MyRenderer(){
        triangleRenderer = new TriangleRenderer();
        cubeRenderer = new CubeRenderer();
        currentRenderer=this.triangleRenderer;
    }

    public void ToggleRenderer() {
        if(this.currentRenderer == this.triangleRenderer) {
            this.currentRenderer = this.cubeRenderer;
        } else if (this.currentRenderer == this.cubeRenderer) {
            this.currentRenderer = this.triangleRenderer;
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        //======For improving performance, lowering quality=======
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        //===============================================================
        gl.glClearColor(.8f, 0f, .2f, 1f);
        gl.glClearDepthf(1f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        currentRenderer.onDrawFrame(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
       currentRenderer.onSurfaceChanged(gl,width,height);
    }
}
