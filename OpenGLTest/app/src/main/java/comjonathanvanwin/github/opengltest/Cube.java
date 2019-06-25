package comjonathanvanwin.github.opengltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube {
    private float vertices[] = {
            1f, 1f, -1f, //p0 -topfrontright
            1f, -1f, -1f, //p1 - bottomfront right
            -1f, -1f, -1f, //p2 - bottomfront left
            -1f, 1f, -1f, //p3 - top front left
            1f, 1f, 1f, //p4 -topbackright
            1f, -1f, 1f, //p5 - bottomback right
            -1f, -1f, 1f, //p6 - bottomback left
            -1f, 1f, 1f //p7 - top back left
    };

    private float rgbaVals[] = {
      .5f, 1, 0, 1,
      .2f, .5f, .8f, 1,
      1, .3f, .2f, .8f,
      .65f, .7f, .5f, .5f,
      .3f, .4f, .5f, .6f,
      .2f, .4f, 1, 1,
      1, 0, .75f, 1,
      .5f, .8f, .3f, 1
    };

    private FloatBuffer vertBuff, colorBuff;

    private short[] pIndex = {3, 4, 0,   0, 4, 1,   3, 0,1,
                              3, 7, 4,   7, 6, 4,   7, 3, 6,
                              3, 1, 2,   1, 6, 2,   6, 3, 2,
                              1, 4, 5,   5, 6, 1,   6, 5, 4 };

    private ShortBuffer pBuff;

    public Cube(){
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
        //Remove the back face to improve efficiency
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        //Lets us work with vertex array
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        /* parameterd: size(in how many dimensions: 2d, 3d), type we use (int, float...), stride: if we need
           to skip at the end of each point some values, pointer (Buffer) our vertices
       */gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
        gl.glColorPointer(4, GL10.GL_FLOAT,0,colorBuff);
        /*Mode: Triangles (normal for drawing one triangle), Triangle strip builds triangles connecting lines in zigzag,
          Triangle Fan builds them from center point, like a circle; Count: how many points; type: float, int, short; and
          our indices buffer
        */
        gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);

        //Always remember disabling when finished using
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
