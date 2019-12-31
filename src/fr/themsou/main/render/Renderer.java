package fr.themsou.main.render;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import fr.themsou.main.math.Vector3f;

public class Renderer{
	
	float texWidth = 1.0f/8.0f;
	public Renderer(float x, float z, Color color, String level, Color north, Color south, Color east, Color west) {
		
		if(level.equals("level-1")){
			
			float width = 1;
			
			if(!color.equals(Color.WHITE)){
				
				if(color.equals(Color.GREEN) || color.equals(new Color(255, 255, 128)) || color.equals(new Color(255, 128, 255))  || color.equals(new Color(128, 255, 255))){
					setFloor(x, 0, z, width);
					if(!north.equals(Color.GREEN) && !north.equals(new Color(255, 255, 128)) && !north.equals(new Color(255, 128, 255)) && !north.equals(new Color(128, 255, 255))){
						setFloorWall(x, 0, z, width, true);
					}
					if(!south.equals(Color.GREEN) && !south.equals(new Color(255, 255, 128)) && !south.equals(new Color(255, 128, 255)) && !south.equals(new Color(128, 255, 255))){
						setFloorWall(x, 0, z + 1, width, true);
					}
					if(!east.equals(Color.GREEN) && !east.equals(new Color(255, 255, 128)) && !east.equals(new Color(255, 128, 255)) && !east.equals(new Color(128, 255, 255))){
						setFloorWall(x, 0, z, width, false);
					}
					if(!west.equals(Color.GREEN) && !west.equals(new Color(255, 255, 128)) && !west.equals(new Color(255, 128, 255)) && !west.equals(new Color(128, 255, 255))){
						setFloorWall(x + 1, 0, z, width, false);
					}
				}
				if(color.equals(new Color(0, 128, 0))){
					setFloor(x, 4, z, width);
					if(!north.equals(new Color(0, 128, 0))){
						setFloorWall(x, 4, z, width, true);
					}
					if(!south.equals(new Color(0, 128, 0))){
						setFloorWall(x, 4, z + 1, width, true);
					}
					if(!east.equals(new Color(0, 128, 0))){
						setFloorWall(x, 4, z, width, false);
					}
					if(!west.equals(new Color(0, 128, 0))){
						setFloorWall(x + 1, 4, z, width, false);
					}
				}
				if(color.equals(new Color(128, 0, 0))){
					setCaveUD(x, 4, z, width);
					if(!north.equals(new Color(128, 0, 0))){
						setCaveWall(x, 4, z, width, true);
					}
					if(!south.equals(new Color(128, 0, 0))){
						setCaveWall(x, 4, z + 1, width, true);
					}
					if(!east.equals(new Color(128, 0, 0))){
						setCaveWall(x, 4, z, width, false);
					}
					if(!west.equals(new Color(128, 0, 0))){
						setCaveWall(x + 1, 4, z, width, false);
					}
				}
				
				setTexture(20, 20);
				
				
			}
			
		}else{
			setColor(x, -10, z, 10, color);
		}
		
	}
	
	public void setFloor(float x, float y, float z, float width){
		
		glColor3f(1, 1, 1);
		
		x *= width;
		y *= width;
		z *= width;
		
		float texStartX = texWidth * 0;
		float texStartY = texWidth * 0;
		
		glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x+width, y, z);
		glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x      , y, z);
		glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x      , y, z+width);
		glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x+width, y, z+width);
		
	}
	public void setFloorWall(float x, float y, float z, float width, boolean SN){
		
		glColor3f(1, 1, 1);
		
		x *= width;
		y *= width;
		z *= width;
		
		float texStartX = texWidth * 1;
		float texStartY = texWidth * 0;
		
		if(SN){
			glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x+width, y      , z);
			glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x      , y      , z);
			glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x      , y-width, z);
			glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x+width, y-width, z);
		}else{
			glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x, y      , z+width);
			glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x, y      , z);
			glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x, y-width, z);
			glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x, y-width, z+width);
		}
		
		setFloorDownWall(x, y - width    , z, width, SN);
		setFloorDownWall(x, y - width * 2, z, width, SN);
		setFloorDownWall(x, y - width * 3, z, width, SN);
		setFloorDownWall(x, y - width * 4, z, width, SN);
		setFloorDownWall(x, y - width * 5, z, width, SN);
		setFloorDownWall(x, y - width * 6, z, width, SN);
		setFloorDownWall(x, y - width * 7, z, width,SN);
		setFloorDownWall(x, y - width * 8, z, width,SN);
		setFloorDownWall(x, y - width * 9, z, width,SN);
		setFloorDownWall(x, y - width * 10, z, width,SN);
		setFloorDownWall(x, y - width * 11, z, width,SN);
		
	}
	public void setFloorDownWall(float x, float y, float z, float width, boolean SN){
		
		glColor3f(1, 1, 1);
		
		float texStartX = texWidth * 2;
		float texStartY = texWidth * 0;
		
		
		if(SN){
			glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x+width, y      , z);
			glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x      , y      , z);
			glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x      , y-width, z);
			glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x+width, y-width, z);
		}else{
			glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x, y      , z+width);
			glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x, y      , z);
			glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x, y-width, z);
			glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x, y-width, z+width);
		}
		
		
		
	}
	
	public void setCaveUD(float x, float y, float z, float width){
		
		glColor3f(1, 1, 1);
		
		x *= width;
		y *= width;
		z *= width;
		
		float texStartX = texWidth * 3;
		float texStartY = texWidth * 0;
		
		glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x+width, y, z);
		glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x      , y, z);
		glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x      , y, z+width);
		glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x+width, y, z+width);
		
		y += width * 3;
		glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x+width, y, z);
		glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x      , y, z);
		glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x      , y, z+width);
		glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x+width, y, z+width);
		
	}
	
	private void setCaveWall(float x, int ya, float z, float width, boolean SN) {
		
		glColor3f(1, 1, 1);
		
		x *= width;
		ya *= width;
		z *= width;
		
		float texStartX = texWidth * 3;
		float texStartY = texWidth * 0;
		
		for(int y = (int) ya; y <= ya + width * 2; y += width){
			if(SN){
				glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x+width, y      , z);
				glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x      , y      , z);
				glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x      , y+width, z);
				glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x+width, y+width, z);
			}else{
				glTexCoord2f(texStartX + texWidth, texStartY           ); glVertex3f(x, y      , z+width);
				glTexCoord2f(texStartX           , texStartY           ); glVertex3f(x, y      , z);
				glTexCoord2f(texStartX           , texStartY + texWidth); glVertex3f(x, y+width, z);
				glTexCoord2f(texStartX + texWidth, texStartY + texWidth); glVertex3f(x, y+width, z+width);
			}
		}
		
		
	}
	
	public void setTexture(float x, float z){
		
		glColor3f(1, 1, 1);
		
		glTexCoord2f(1, 0); glVertex3f(x + 1, -10, z);
		glTexCoord2f(0, 0); glVertex3f(x, -10, z);
		glTexCoord2f(0, 1); glVertex3f(x, -10, z + 1);
		glTexCoord2f(1, 1); glVertex3f(x + 1, -10, z + 1);
		
	}
	
	public void setColor(float x, float y, float z, float width, Color color){
		
		glColor3f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255);
		
		x *= width - 1;
		z *= width - 1;
		
		glVertex3f(x+width, -10, z);
		glVertex3f(x      , -10, z);
		glVertex3f(x      , -10, z+width);
		glVertex3f(x+width, -10, z+width);
		
	}
	
	public static void addFog(float density, Vector3f color){
		Shaders.MAIN.setUniform("fogColor", color);
		Shaders.MAIN.setUniform("fogDensity", density);
		
	}
	
}
