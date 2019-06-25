package comjonathanvanwin.github.gametools;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ShaderUtils {
    public static String readTextFile(InputStream inputStream) {
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
        }
        return outputStream.toString();
    }

    public static int loadShader(Context context,int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        if(shader!=0){
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == GLES20.GL_FALSE) {
                Toast.makeText(context, "Could not compile shader " + shaderType + ":", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, " " + GLES20.glGetShaderInfoLog(shader), Toast.LENGTH_SHORT).show();
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    public static int createProgram(Context context,InputStream vertex, InputStream fragment){
        String vertexShaderCode = readTextFile(vertex);
        String fragmentShaderCode = readTextFile(fragment);

        int vertexShader = loadShader(context, GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(context, GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        int program = GLES20.glCreateProgram();

        if(program != 0) {
            GLES20.glAttachShader(program, vertexShader);
            GLES20.glAttachShader(program, fragmentShader);

            /*if (attributes != null)
            {
                final int size = attributes.length;
                for (int i = 0; i < size; i++)
                {
                    GLES20.glBindAttribLocation(
                            programHandle, i,
                            attributes[i]);
                }
            }*/
            GLES20.glLinkProgram(program);

            int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES20.GL_TRUE) {
                Toast.makeText(context, "Could not link program: ", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, GLES20.glGetProgramInfoLog(program), Toast.LENGTH_SHORT).show();
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }



}
