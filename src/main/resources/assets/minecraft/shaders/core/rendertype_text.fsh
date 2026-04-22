#version 150

uniform sampler2D Sampler0;
uniform vec4 ColorModulator;
in vec4 vertexColor;
in vec2 texCoord0;
out vec4 fragColor;

void main() {
    vec4 texColor = texture(Sampler0, texCoord0);
    bool isMagicColor = all(greaterThanEqual(vertexColor.rgb, vec3(77.0/255.0, 91.0/255.0, 35.0/255.0))) && all(lessThanEqual(vertexColor.rgb, vec3(79.0/255.0, 93.0/255.0, 37.0/255.0)));

    if (isMagicColor) {
        fragColor = texColor * ColorModulator;

    } else {
        fragColor = texColor * vertexColor * ColorModulator;
    }

    if (fragColor.a < 0.1) {
        discard;
    }
}