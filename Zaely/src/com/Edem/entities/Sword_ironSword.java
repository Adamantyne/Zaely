package com.Edem.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Edem.World.Camera;
import com.Edem.main.Game;

public class Sword_ironSword {
	private BufferedImage[] hit1E;
	private BufferedImage[] hit1D;
	private BufferedImage[] hit2D;
	private BufferedImage[] hit2E;
	
	public int Frames, maxFrames=4, Index = 0, maxIndex = 5;
	

	
public Sword_ironSword() {
	hit1D = new BufferedImage[6];
	hit1E = new BufferedImage[6];
	hit2D = new BufferedImage[6];
	hit2E = new BufferedImage[6];
	
	
	for(int i = 0; i<5 ; i++) {
		hit1D[i] = Game.spritesheet.getSprite(0 + (i*170), 800, 170, 200);
		                      }
	for(int i = 0; i<5 ; i++) {
		hit1E[i] = Game.spritesheet.getSprite(0 + (i*170), 1000, 170, 200);
		                      }
	for(int i = 0; i<5 ; i++) {
		hit2D[i] = Game.spritesheet.getSprite(1200 + (i*170), 800, 170, 200);
		                      }
	for(int i = 0; i<5 ; i++) {
		hit2E[i] = Game.spritesheet.getSprite(1200 + (i*170), 1000, 170, 200);
		                      }
                         }




	public void atuali() {
		if(Game.player.cdBA==0) {Frames=0; Index=0;}
		if(Game.player.cdBA>0) {
		    Frames++;
		     if(Frames==maxFrames) {
			     Frames=0;
			     Index++;
			     if(Index==maxIndex) {
			    	 Index=0;
			                         }
		                           }
		                       }
	                     }
	
	
	
	
	public void render(Graphics g) {
		if(Game.player.cdBA>0) {
			System.out.println(Game.player.mov);
			if(Game.player.mov == 1) {
				if(Game.player.BA==1) {g.drawImage(hit1D[Index], Game.player.getX() - Camera.x, Game.player.getY() - Camera.y, null);}
				if(Game.player.BA==2) {g.drawImage(hit2D[Index], Game.player.getX() - Camera.x, Game.player.getY() - Camera.y, null);}
			                         } 
			if(Game.player.mov == 2) {
				if(Game.player.BA==1) {g.drawImage(hit1D[Index], Game.player.getX() - Camera.x, Game.player.getY() - Camera.y, null);}
				if(Game.player.BA==2) {g.drawImage(hit2D[Index], Game.player.getX() - Camera.x, Game.player.getY() - Camera.y, null);}
			                         }
			else if(Game.player.mov == 3) {
				if(Game.player.BA==1) {g.drawImage(hit1E[Index], Game.player.getX() - Camera.x, Game.player.getY() - Camera.y, null);}
				if(Game.player.BA==2) {g.drawImage(hit2E[Index], Game.player.getX() - Camera.x, Game.player.getY() - Camera.y, null);}
			                              }
		                       }
		
		
	                               }
	
	
	
	
	
	
	
	
                           }
