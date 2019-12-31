uniform sampler2D tex;
uniform float fogDensity;
uniform vec3 fogColor;

varying vec4 fragPos;
varying vec4 view;
varying vec2 texCoord;

void main(){
	
	float dist = length(view);
	dist = dist - 40.0;
	float fog = exp(- dist * fogDensity);
	if(fog < 0.5) fog = 0.5;
	fog = clamp(fog, 0.0, 1.0);


	gl_FragColor = texture2D(tex, texCoord);

	gl_FragColor = mix(vec4(fogColor, 1.0), gl_FragColor, vec4(fog));

}
