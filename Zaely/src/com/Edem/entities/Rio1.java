package com.Edem.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Edem.World.Camera;
import com.Edem.World.World;
import com.Edem.main.Game;

public class Rio1 extends Entity {
	private static int arw = 100, arh = 30, arx = 0, ary = 130;
	//public int collidingRio = 0;
	private int Frames, maxFrames = 17, Index = 0, maxIndex = 9;
	private BufferedImage[] rio;

	public Rio1(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rio = new BufferedImage[10];
		Game.entities.add(this);
		for(int i = 0; i<10 ; i++) {
			rio[i] = Game.spritesheet2.getSprite(0 + (i*150), 450, 150, 150);
			                       }
	                                                                       }
	
	public void atuali() {
		//collidingRio = 0;
		Rectangle ARbaixo = new Rectangle(Game.player.getX() + arx - Camera.x, Game.player.getY()+ ary - Camera.y, arw,arh);
		Rectangle ARcima = new Rectangle(Game.player.getX() + arx - Camera.x, Game.player.getY()+ ary-5 - Camera.y, arw, arh);
		Rectangle Rio = new Rectangle(this.getX() - Camera.x, this.getY()- Camera.y, 150, 150);
		
		if(ARbaixo.intersects(Rio)) {
			Game.player.y-=2;
		                            }
		if(ARcima.intersects(Rio)) {
			Game.player.y+=2;
		                           }
		
		
		Frames++;         
        if(Frames==maxFrames) {
			Frames=0;
		    Index++;  
		    if(Index == maxIndex) {
		    	Index=0;
		                          }                 
		                      }   
		
	                  
		                 }
	public void render(Graphics g) {
		
		g.drawImage(rio[Index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		            
		//g.setColor(Color.GREEN);
		//g.fillRect(Game.player.getX() + arx - Camera.x, Game.player.getY()+ ary - Camera.y, arw, arh);
		//g.setColor(Color.RED);
		//g.fillRect(this.getX() - Camera.x, this.getY()- Camera.y, 150, 150);
		//g.setColor(Color.BLUE);
		//g.fillRect(Game.player.getX() + arx - Camera.x, Game.player.getY()+ ary-5 - Camera.y, arw, arh);
	                               }

	
	

                                                                            }





