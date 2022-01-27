package com.Edem.gráficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.Edem.main.Game;

public class Pause {
	public String[] options = {"Continuar","Sair"};
	
	public int currentOptions = 0, maxOptions = options.length-1;
	public boolean up,down,enter;

	public void atuali() {
		if(up) {currentOptions--;if(currentOptions<0) {currentOptions=maxOptions;}up=false;}
		else if(down) {currentOptions++;if(currentOptions>maxOptions) {currentOptions=0;}down=false;}
		
		if(enter) {
			enter=false;
			if(options[currentOptions]=="Continuar") {Game.gameState="normal";}
			else if(options[currentOptions]=="Sair") {System.exit(1);}
		          }
	                     }
	
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLUE);
		g.setFont(new Font("arial",Font.BOLD,25));
		g.drawString("Continuar",595, 300);
		g.drawString("Sair",635, 330);
		
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		if(options[currentOptions]=="Continuar") {
			g.setColor(Color.BLUE);
			g.setFont(new Font("arial",Font.BOLD,25));
			g.drawString("Continuar",595, 300);
		                                         }
		else if(options[currentOptions]=="Sair") {
			g.setColor(Color.BLUE);
			g.setFont(new Font("arial",Font.BOLD,25));
			g.drawString("Sair",635, 330);
		                                         }
	                               }
	
                    }
