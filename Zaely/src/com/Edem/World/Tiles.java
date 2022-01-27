package com.Edem.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Edem.main.Game;

public class Tiles {

public static BufferedImage parede = Game.spritesheet2.getSprite(0, 300, 150, 150);
public static BufferedImage chão = Game.spritesheet2.getSprite(0, 0, 150, 150);	
public static BufferedImage muro = Game.spritesheet2.getSprite(300, 0, 150, 150);	
public static BufferedImage supparede = Game.spritesheet2.getSprite(0, 150, 150, 150);
public static BufferedImage arvores = Game.spritesheet2.getSprite(150, 0, 150, 150);
public static BufferedImage coparvores = Game.spritesheet2.getSprite(150, 150, 150, 150);
public static BufferedImage rio = Game.spritesheet2.getSprite(300, 300, 150, 150);
public static BufferedImage muroin = Game.spritesheet2.getSprite(450, 0, 150, 150);

private int x,y;
private BufferedImage sprite;

public Tiles(int x, int y, BufferedImage sprite) {
	this.x = x;
	this.y = y;
	this.sprite = sprite;
                                                 }



public void render(Graphics g) {
	g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
                               }
                   }
