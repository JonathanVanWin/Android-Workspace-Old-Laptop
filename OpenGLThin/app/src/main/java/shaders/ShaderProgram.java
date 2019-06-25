package shaders;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class ShaderProgram {

    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    //private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public ShaderProgram(InputStream vertexFile, InputStream fragmentFile) {
        vertexShaderID = loadShader(GLES30.GL_VERTEX_SHADER, vertexFile);
        fragmentShaderID = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentFile);

        programID = GLES30.glCreateProgram();
        if(programID!=0) {
            GLES30.glAttachShader(programID, vertexShaderID);
            GLES30.glAttachShader(programID, fragmentShaderID);
            GLES30.glLinkProgram(programID);
            GLES30.glValidateProgram(programID);

            bindAttributes();
            getAllUniformLocations();
        }
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName) {
        return GLES30.glGetUniformLocation(programID, uniformName);
    }

    public void start() {
        GLES30.glUseProgram(programID);
    }

    public void stop() {
        GLES30.glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        GLES30.glDetachShader(programID, vertexShaderID);
        GLES30.glDetachShader(programID, fragmentShaderID);
        GLES30.glDeleteShader(vertexShaderID);
        GLES30.glDeleteShader(fragmentShaderID);
        GLES30.glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {
        GLES30.glBindAttribLocation(programID, attribute, variableName);
    }

    /*protected void loadFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }

    protected void loadInt(int location, int value){
        GL20.glUniform1i(location, value);
    }

    protected void loadVector(int location, Vector3f vector){
        GL20.glUniform3f(location,vector.x,vector.y,vector.z);
    }

    protected void loadVector(int location, Vector4f vector){
        GL20.glUniform4f(location,vector.x,vector.y,vector.z, vector.w);
    }

    protected void load2DVector(int location, Vector2f vector){
        GL20.glUniform2f(location,vector.x,vector.y);
    }

    protected void loadBoolean(int location, boolean value){
        float toLoad = 0;
        if(value){
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, Matrix4f matrix){
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }*/

    private static int loadShader(int shaderType, InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        String shaderSource = outputStream.toString();
        int shaderID = GLES30.glCreateShader(shaderType);
        GLES30.glShaderSource(shaderID, shaderSource);
        GLES30.glCompileShader(shaderID);

        //Error checking
        /*int[] compiled = new int[1];
        GLES30.glGetShaderiv(shaderID, GLES30.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == GLES30.GL_FALSE) {
            Toast.makeText(context, "Could not compile shader " + shaderType + ":", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, " " + GLES20.glGetShaderInfoLog(shaderID), Toast.LENGTH_SHORT).show();
            GLES20.glDeleteShader(shaderID);
            shaderID = 0;
            System.exit(-1);
        }*/
        return shaderID;
    }
}