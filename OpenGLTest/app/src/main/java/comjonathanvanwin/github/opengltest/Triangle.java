package comjonathanvanwin.github.opengltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {
    private float vertices[] = {
      0f, 1f, //p0
      1f, -1f, //p1
      -1f, -1f //p2
    };

    private float rgbaVals[] = {
            1, 1, 0, .5f,
            .25f, 0, .85f, 1,
            0, 1, 1, 1
    };

    private FloatBuffer vertBuff, colorBuff;

    private short[] pIndex = {0, 1, 2};

    private ShortBuffer pBuff;

    public Triangle(){
        ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
        bBuff.order(ByteOrder.nativeOrder());
        vertBuff = bBuff.asFloatBuffer();
        vertBuff.put(vertices);
        //Where to start(position)
        vertBuff.position(0);

        ByteBuffer pointByteBuffer = ByteBuffer.allocateDirect(pIndex.length * 2);
        pointByteBuffer.order(ByteOrder.nativeOrder());
        pBuff=pointByteBuffer.asShortBuffer();
        pBuff.put(pIndex);
        pBuff.position(0);

        ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
        cBuff.order(ByteOrder.nativeOrder());
        colorBuff = cBuff.asFloatBuffer();
        colorBuff.put(rgbaVals);
        colorBuff.position(0);
    }

    public void draw(GL10 gl){
        //Which face is facing us? We decide this with the order of vertices: clockWise or counter clockWise
        gl.glFrontFace(GL10.GL_CW);
        //Lets us work with vertex array, and add color
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        /* parameterd: size(in how many dimensions: 2d, 3d), type we use (int, float...), stride: if we need
           to skip at the end of each point some values, pointer (Buffer) our vertices
       */gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
        gl.glColorPointer(4, GL10.GL_FLOAT,0,colorBuff);
        /*Mode: Triangles (normal for drawing one triangle), Triangle strip builds triangles connecting lines in zigzag,
          Triangle Fan builds them from center point, like a circle; Count: how many points; type: float, int, short; and
          our indices buffer
        */
        gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);

        //Always remember disabling when finished using
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }
}
