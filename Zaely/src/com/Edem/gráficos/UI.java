package com.Edem.gráficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Edem.entities.Player;
import com.Edem.main.Game;

public class UI {
	
	
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(50,20,100, 5);
		g.setColor(Color.GREEN);
		g.fillRect(50,20,(int)((Game.player.life/Game.player.maxLife)*100), 5);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD,20));
		g.drawString("vida",10,27);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD,15));
		g.drawString((int)Game.player.life+"/"+(int)Game.player.maxLife,70,40);
	                               }

                }
