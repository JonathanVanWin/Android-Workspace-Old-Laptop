package comjonathanvanwin.github.triangle;

import android.app.Activity;
import android.opengl.GLES20;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLObject {

    private String vertexShaderCode;
    private String fragmentShaderCode;

    private FloatBuffer vertexBuffer;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    static float triangleCoords[] = { // in counterclockwise order:
            0.0f, 0.622008459f, 0.0f,   // top
            -0.5f, -0.311004243f, 0.0f,   // bottom left
            0.5f, -0.311004243f, 0.0f    // bottom right
    };
    static final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    int renderProgram;
    int vPositionHandle;
    int vColorHandle;
    int mvpHandle;

    public GLObject(final Activity activity) {
        final InputStream inputVerShader = activity.getResources().openRawResource(R.raw.vertex_shader);
        final InputStream inputFragShader = activity.getResources().openRawResource(R.raw.fragment_shader);

        vertexShaderCode=OpenGLUtils.readShaderFromFile(inputVerShader);
        fragmentShaderCode =OpenGLUtils.readShaderFromFile(inputFragShader);
        // initialize vertex byte buffer for shape coordinates
        // (number of coordinate values * 4 bytes per float)
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        int vertexShader = OpenGLUtils.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = OpenGLUtils.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        renderProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(renderProgram, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(renderProgram, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(renderProgram);
    }

    public void draw(float[] mvpMatrix) {

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(renderProgram);

        // get handle to vertex shader's vPosition member
        vPositionHandle = GLES20.glGetAttribLocation(renderProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(vPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(vPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        vColorHandle = GLES20.glGetUniformLocation(renderProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(vColorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        mvpHandle = GLES20.glGetUniformLocation(renderProgram, "uMVPMatrix");
        OpenGLUtils.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mvpHandle, 1, false, mvpMatrix, 0);
        OpenGLUtils.checkGlError("glUniformMatrix4fv");

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(vPositionHandle);
    }
}