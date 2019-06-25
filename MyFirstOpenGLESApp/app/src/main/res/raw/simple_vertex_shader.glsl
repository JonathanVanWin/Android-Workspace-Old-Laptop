attribute vec4 a_Position;

void main()
{
    gl_Position = a_Position;
    gl_PointSize = 10.0; //We tell OpenGL how large the points should appear on the screen. This will the length of a side of a square.
}