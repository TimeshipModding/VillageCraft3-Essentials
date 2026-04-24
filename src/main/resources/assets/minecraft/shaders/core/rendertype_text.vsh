#version 150

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV2;
uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
out vec4 vertexColor;
out vec2 texCoord0;

void main() {
    vertexColor = Color;
    texCoord0 = UV0;
    vec3 pos = Position;
    bool isMagicColor = all(greaterThanEqual(Color.rgb, vec3(77.5/255.0, 91.5/255.0, 35.5/255.0))) && all(lessThanEqual(Color.rgb, vec3(78.5/255.0, 92.5/255.0, 36.5/255.0)));
    bool isShadow = Color.r > (18.0/255.0) && Color.r < (21.0/255.0);

    if (isMagicColor || isShadow) {
        if (isShadow) {
            pos.x += 10000.0;
        }
    }

    gl_Position = ProjMat * ModelViewMat * vec4(pos, 1.0);
}