#version 300 es

in vec3 position;

out vec3 colour;

void main(void){
    gl_position = vec4(position.xyz, 1.0);
    colour = vec3(position.x+0.5, 1.0, position.y+0.5);
}
