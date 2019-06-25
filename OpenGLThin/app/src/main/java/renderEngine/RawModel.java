package renderEngine;

public class RawModel {
    //Vao is vertex attribute object, where we store our attributes(color, normal, position) in form of buffers(vbo)
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount){
        this.vaoID=vaoID;
        this.vertexCount=vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
