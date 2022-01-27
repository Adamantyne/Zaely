package com.Edem.gráficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.Edem.World.World;
import com.Edem.entities.Enemy_slimeWater;
import com.Edem.entities.Entity;
import com.Edem.entities.Material_ferro;
import com.Edem.entities.Player;
import com.Edem.entities.SwordBA;
import com.Edem.main.Game;

public class Game_Over {
    
	public boolean restartGame=false;
	
	
	
	public void atuali() {
		if(restartGame) {
			String newWorld = "/level"+Game.curlevel+".png";
			Game.world.restartGame(newWorld);
			Game.gameState="normal";
			restartGame=false;
		                }
	                     }
	
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,50));
		g.drawString("Game Over", 550, 100);
		g.setFont(new Font("arial",Font.BOLD,30));
		g.drawString("Pressiona enter para tentar denovo", 430, 300);
	                               }
	

	
                       }
