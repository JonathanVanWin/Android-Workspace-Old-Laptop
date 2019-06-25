package comjonathanvanwin.github.gametools;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {
    int rectVerts;
    private float positions[]= {-0.5f, -0.5f,
                                 0.0f,  0.5f,
                                 0.5f,  -0.5f};
    private FloatBuffer vertBuff;
    public MyRenderer(){
        ByteBuffer bBuff = ByteBuffer.allocateDirect(positions.length * 4);
        bBuff.order(ByteOrder.nativeOrder());
        vertBuff = bBuff.asFloatBuffer();
        vertBuff.put(positions);
        vertBuff.position(0);

    }

    public void draw(){

    }
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        int buffer;
        int[] buffers = new int[1];
        GLES20.glGenBuffers(1, buffers,0);
        this.rectVerts = buffers[0];
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, rectVerts);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, (vertBuff.limit() * 4), vertBuff, GLES20.GL_STATIC_DRAW);
    }

    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        clearScreenWithColor(Counter.getUpDownValue());

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.rectVerts);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 0, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

    }

    public void clearScreenWithColor(int i) {
        GLES20.glClearColor(0.0f, 0.0f, Math.abs(i) * 0.1f, 1);
    }
}