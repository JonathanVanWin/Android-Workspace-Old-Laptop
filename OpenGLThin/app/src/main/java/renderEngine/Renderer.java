package renderEngine;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import shaders.StaticShader;

public class Renderer implements GLSurfaceView.Renderer {

    private RawModel model;
    private StaticShader shader;

    public Renderer(RawModel model, StaticShader shader) {
        this.model = model;
        this.shader = shader;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES30.glClearColor(1, 0, 0, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        this.shader.start();
        render(this.model);
        this.shader.stop();
    }

    private void render(RawModel model) {
        GLES30.glBindVertexArray(model.getVaoID());
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, model.getVertexCount(), GLES30.GL_UNSIGNED_INT, 0);
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glBindVertexArray(0);
    }
}
