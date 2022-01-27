package com.Edem.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Edem.World.Camera;
import com.Edem.main.Game;

public class SwordBA extends Entity {
	
	private int dx,dy,duração=10;

	public SwordBA(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
	                                                                                          }
	public void atuali() {
		if(Game.player.BA > Game.player.maxBA) {Game.player.BA=1;}
		duração--;
		if(duração<=10) {
			Game.swordBA.remove(this);
			return;
		                }
	                     }
	
	
	public void render(Graphics g) {
	 /* if(Game.player.BA==1) {
		g.setColor(Color.RED);
		g.fillRect(this.getX()-Camera.x, this.getY()-Camera.y, width, height);
	                        }
	  if(Game.player.BA==2) {
		g.setColor(Color.BLUE);
		g.fillRect(this.getX()-Camera.x, this.getY()-Camera.y, width, height);
	                        }*/
	                               }
	
	

                                    }
