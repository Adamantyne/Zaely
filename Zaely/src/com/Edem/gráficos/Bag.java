package com.Edem.gráficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Edem.main.Game;

public class Bag {
	
	public int takeIron,take,nIron=0;
	public static int  ferro = 0,frames = 0, maxFrames = 120;

	private BufferedImage Iron;
	
	public Bag() {
		Iron = Game.spritesheetEntity.getSprite(0, 800, 40, 40);
		
	             }
	
	public void atuali() {
		
		
		
		if(take>=1) {
			frames++;
			
			if(takeIron>=1){nIron++;}
			
		    if(frames == maxFrames) {
			      frames=0;
			      take--;
			      takeIron=0;
			      nIron=0;
			      
		                            }
		             }
	                        }
	
	
	public void render(Graphics g) {
		if(Game.bag.takeIron>=1) {g.drawImage(Iron,30,560+Game.bag.nIron, null); }
		 if(nIron>=1) {             
		       g.setColor(Color.RED);
		       g.setFont(new Font("arial", Font.BOLD,15));
		       g.drawString("ferro = "+ferro,20,600+nIron);
		              }
		 
		
		
				                   }
	             


}

	
	
	
	
	


