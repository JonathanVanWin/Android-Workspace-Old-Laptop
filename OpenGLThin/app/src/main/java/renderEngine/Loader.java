package renderEngine;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

//store data of model into a VAO
public class Loader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();

    public RawModel loadToVAO(float[] positions, int[] indices) {
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions);
        unbindVAO();
        return new RawModel(vaoID, indices.length);
    }

    public void cleanUp() {
        for (int vao : vaos) {
            //Only accepts int arrays
            int[] temp = {vao};
            GLES30.glDeleteVertexArrays(1, temp, 0);
        }

        for (int vbo : vbos) {
            //Only accepts int arrays
            int[] temp = {vbo};
            GLES30.glDeleteBuffers(1, temp, 0);
        }
    }

    private int createVAO() {
        int[] vaoID = new int[1];
        vaos.add(vaoID[0]);
        GLES30.glGenVertexArrays(1, vaoID, 0);
        return vaoID[0];
    }

    private void storeDataInAttributeList(int attributeNumber, float[] data) {
        int[] vboID = new int[1];
        GLES30.glGenBuffers(1, vboID, 0);
        vbos.add(vboID[0]);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vboID[0]);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, (buffer.limit() * 4), buffer, GLES30.GL_STATIC_DRAW);
        GLES30.glVertexAttribPointer(attributeNumber, 3, GLES30.GL_FLOAT, false, 0, 0);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }

    //After using we need to unbind
    private void unbindVAO() {
        GLES30.glBindVertexArray(0);
    }

    private void bindIndicesBuffer(int[] indices) {
        int[] vboID = new int[1];
        GLES30.glGenBuffers(1, vboID, 0);
        vbos.add(vboID[0]);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, vboID[0]);
        IntBuffer intBuffer = storeDataInIntBuffer(indices);
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, (intBuffer.limit() * 4), intBuffer, GLES30.GL_STATIC_DRAW);
    }

    private IntBuffer storeDataInIntBuffer(int[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(data.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());

        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);
        //Prepare to read from buffer
        intBuffer.flip();

        //Where to start(position)
        intBuffer.position(0);
        return intBuffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(data.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());

        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(data);
        //Prepare to read from buffer
        floatBuffer.flip();

        //Where to start(position)
        floatBuffer.position(0);
        return floatBuffer;
    }


}
