package fr.themsou.main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import fr.themsou.main.math.Vector3f;
import fr.themsou.main.render.Camera;
import fr.themsou.main.render.DisplayManager;
import fr.themsou.main.render.Level;
import fr.themsou.main.render.Renderer;
import fr.themsou.main.render.Shaders;

public class Main {

	private boolean running = false;
	
	public static final float fpsMax = 60;
	
	Camera cam;
	Level level;
	
	public Main() {
		DisplayManager.create(1200, 675, "Chargement...");
		cam = new Camera(new Vector3f(0, 0, 0));
		cam.setPerspectiveProjection(70.0f, 0.1f, 1000.0f);
		
		level = new Level("level-1");
	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.start();
	}
	
	public void loop() {

		long lastTpsTime = System.nanoTime();
		long lastFpsTime = System.nanoTime();
		long lastSecTime = System.nanoTime();
		int tps = 0;
		int fps = 0;
		
		while(running){
			if(DisplayManager.isClosed()) stop();

			if(System.nanoTime() - lastTpsTime > (1000000000 / 30)){
				lastTpsTime = System.nanoTime();
				tps++;
				update();
			}else if(System.nanoTime() - lastFpsTime > (1000000000 / fpsMax)){
				lastFpsTime = System.nanoTime();
				fps++;
				render();
			}else{
				try{
					Thread.sleep(1);
				}catch(InterruptedException e){ e.printStackTrace(); }				
			}
			
			if(System.nanoTime() - lastSecTime > 1000000000){
				lastSecTime = System.nanoTime();
				Display.setTitle(tps + " tps "  + fps + " fps");
				tps = 0;
				fps = 0;
			}
				
		}
		
		exit();
	}
	public void update(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Mouse.setGrabbed(false);
		if(Mouse.isButtonDown(0)) Mouse.setGrabbed(true);
		
		if(!Mouse.isGrabbed()) return;
		
		cam.input();
		level.update();
		
		
	}
	public void render(){
		DisplayManager.update();
		if(Display.wasResized()){
			glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}
		DisplayManager.clearBuffers();
		cam.getPerspectiveProjection();
		cam.update();
		
		Shaders.MAIN.bind();
		Renderer.addFog(0.02f, new Vector3f(1, 1, 1));
		
		level.render();
		
	}
	
	public void start(){
		running = true;
		loop();
	}

	public void stop(){
		running = false;
	}
	public void exit(){
		DisplayManager.dispose();
		System.exit(0);
	}
	
}
