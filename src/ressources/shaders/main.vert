varying vec4 fragPos;
varying vec2 texCoord;
varying vec4 view;

void main(){
	
	fragPos = gl_Vertex;
	texCoord = gl_MultiTexCoord0.xy;
	view = gl_ModelViewMatrix * fragPos;

	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;

}
