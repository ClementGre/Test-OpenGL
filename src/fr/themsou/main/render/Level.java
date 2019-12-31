package fr.themsou.main.render;

import static org.lwjgl.opengl.GL11.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import org.lwjgl.util.glu.GLU;

import fr.themsou.main.math.Vector3f;

public class Level {
	
	int renderingList;
	Vector3f[][] blocks;
	private int width, height;
	Texture texture = new Texture("texture", GL_NEAREST);
	
	
	float MatSpec = 1.0f;
	float MatDif = 1.0f;
	float MatAmb = 0.3f;
	float Light1Pos = 0.0f;
	float Light1Dif = 1.0f;
	float Light1Spec = 1.0f;
	float Light1Amb = 0.5f;
	float Spot1Dir = 0.0f; 
	
	public Level(String level){
		compile(level);
	}
	
	public void compile(String level){
		
		BufferedImage map = null;
		
		try{
			map = ImageIO.read(Level.class.getResource("/levels/" + level + ".png"));
		}catch (IOException e){e.printStackTrace(); }
		
		width = map.getWidth();
		height = map.getHeight();
		
		int[] pixels = new int[width * height];
		map.getRGB(0, 0, width, height, pixels, 0, width);
		
		blocks = new Vector3f[width][height];
		
		for(int x = 0; x < width; x++){
			for(int z = 0; z < height; z++){
				
				int i = x + z * width;
				blocks[x][z] = new Vector3f(x, pixels[i], z);
			}
		}
		
		compileRendering(level);
		
	}
	
	public void compileRendering(String level) {
		
		renderingList = glGenLists(1);
		
		glNewList(renderingList, GL_COMPILE);
		glBegin(GL_QUADS);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		GLU.gluLookAt(40, 40, 100, 0, 0, 0, 0, 1, 0);
		glLightf(GL_LIGHT0, GL_POSITION, Light1Pos);
		for(int x = 0; x < width; x++) {
			for(int z = 0; z < height; z++) {
				
				Color north = Color.WHITE;  if(z-1 >= 0) north =     Color.decode(((int) blocks[x][z-1].getY()) + "");
				Color south = Color.WHITE;  if(z+1 < height) south =  Color.decode(((int) blocks[x][z+1].getY()) + "");
				Color east = Color.WHITE;   if(x-1 >= 0) east =      Color.decode(((int) blocks[x-1][z].getY()) + "");
				Color west = Color.WHITE;   if(x+1 < width) west =  Color.decode(((int) blocks[x+1][z].getY()) + "");
						
				new Renderer((float) x, (float) z, Color.decode(((int) blocks[x][z].getY()) + ""), level, north, south, east, west);

			}
		}
		glEnd();
		glEndList();
	}
	
	public void update(){
		
	}
	public void render(){
		glEnable(GL_TEXTURE_2D);
       
		glMaterialf(GL_FRONT_AND_BACK,GL_SPECULAR, MatSpec); 	//On applique les paramètres du matériau
		glMaterialf(GL_FRONT_AND_BACK,GL_DIFFUSE, MatDif);
		glMaterialf(GL_FRONT_AND_BACK,GL_AMBIENT, MatAmb);
		
		glLightf(GL_LIGHT0, GL_DIFFUSE, Light1Dif); 	//Et ceux de la lumière
		glLightf(GL_LIGHT0, GL_SPECULAR, Light1Spec);
		glLightf(GL_LIGHT0, GL_AMBIENT, Light1Amb);
        
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        
		texture.bind();
		glCallList(renderingList);
		
	}
	
	
}
