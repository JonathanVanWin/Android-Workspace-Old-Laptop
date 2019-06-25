package shaders;

import android.content.Context;

import java.io.InputStream;

import comjonathanvanwin.github.openglthin.R;

public class StaticShader extends ShaderProgram {

    public StaticShader(InputStream vertexFile, InputStream fragmentFile) {
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void getAllUniformLocations() {

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
