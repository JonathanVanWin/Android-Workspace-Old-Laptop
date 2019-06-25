package comjonathanvanwin.github.opengltest;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TriangleRenderer implements GLSurfaceView.Renderer {

    private Triangle triangle;

    public TriangleRenderer(){
        triangle = new Triangle();
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
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        // Where camera needs to be in 3d, and which direction to look
        // gl, where the eye will be in 3d, where to look at( direction in 3d)
        GLU.gluLookAt(gl, 0, 0,-5, 0,0,0,0,2,0);

        triangle.draw(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //x, y, width, height
        gl.glViewport(0, 0, width, height);
        //For having same ratio in portrait and landscape mode
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        //For performance, we decide from which distance not render object(if it is to far ignore)
        // left, right, bottom, top, zNear: how big the box to be for putting objects(from reference where camera is),
        // zFar: max distance from camera(if to far ignore)
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 1, 25);

    }
}
