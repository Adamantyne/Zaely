package com.Edem.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Edem.World.Camera;
import com.Edem.gráficos.Bag;
import com.Edem.main.Game;

public class Material_ferro extends Entity{
	private int Frames, maxFrames = 5, Index = 0, maxIndex = 9;
	private BufferedImage ferro[];
	

	public Material_ferro(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		ferro = new BufferedImage[10];
		
		for(int i = 0; i<10 ; i++) {
			ferro[i] = Game.spritesheetEntity.getSprite(0 + (i*40), 800, 40, 40);
			                       }
	                                                                                 }
	
	public void atuali() {
		
		Frames++;         
        if(Frames==maxFrames) {
			Frames=0;
		    Index++;  
		    if(Index == maxIndex) {
		    	Index=0;
		                          }                 
		                      }
        takeIron();
		
	                     }
	public void render(Graphics g) {
		g.drawImage(ferro[Index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		
		//g.setColor(Color.YELLOW);
		//g.fillRect(this.getX()-Camera.x+10, this.getY()-Camera.y+10, 40, 40 );
		
	    
		                     }
	                               
	public void takeIron() {
	    for(int i = 0; i<Game.material.size(); i++) {
		    Entity atual = Game.material.get(i);
	         if(atual instanceof Material_ferro) {
	    	     if(Entity.Colliding(Game.player, atual)) {
	    	    	 if(Game.player.take) {
	    		         Bag.ferro+=10;  
	    		         Game.bag.takeIron++;
	    		         Game.bag.take++;
	    		         Game.bag.nIron = 0;
	    		         Game.bag.frames=0;
	    		         //System.out.println("ferro: " + ferro);
	    		         Game.material.remove(atual);
	    		         Game.entities.remove(atual);
	    	    	                      }
	    	                                              }
	                                             }
	    	                                        }
                           }
	
	

                                          }
