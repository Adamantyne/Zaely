package com.Edem.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Edem.World.Camera;
import com.Edem.main.Game;

public class Entity {

protected double x;
protected double y;
protected int width;
protected int height;
protected BufferedImage sprite;

public static BufferedImage slimeWater = Game.spritesheetEntity.getSprite(0, 0, 130, 200);
public static BufferedImage iron = Game.spritesheet2.getSprite(150, 1350, 150, 150);
public static BufferedImage rio = Game.spritesheet2.getSprite(0, 450, 150, 150);

public int maskx,masky,maskw,maskh;

public Entity(int x, int y, int width, int height, BufferedImage sprite) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    this.sprite = sprite;  
    
    this.maskx = 0;
    this.masky = 0;
    this.maskw = width;
    this.maskh = height;
                                                                         }
public void seyMask(int maskx, int masky, int maskw, int maskh) {
	this.maskx = maskx;
	this.masky = masky;
	this.maskw = maskw;
	this.maskh = maskh;
                                                                }  
	
public void setX(int newX) {
	this.x = newX;
                           }
public void sety(int newy) {
	this.y = newy;
                           }


public int getX() {
	return (int)this.x;
                  }
	
public int getY() {	
    return (int)this.y;
	              }
public int getWidth() {
	return this.width;
                      }
public int getHeight() {
	return this.height;
                       }


public void atuali() {
	
                     }

public void render(Graphics g) {
	g.drawImage(sprite, this.getX() - Camera.x, this.getY()-Camera.y, null);
	//g.setColor(Color.YELLOW);
	//g.fillRect(this.getX()-Camera.x + maskx, this.getY()-Camera.y + masky, maskw, maskh );
	
                               }


public static boolean Colliding(Entity e1, Entity e2) {
	Rectangle e1Mask = new Rectangle (e1.getX()+e1.maskx, e1.getY()+e1.masky, e1.maskw, e1.maskh);
	Rectangle e2Mask = new Rectangle (e2.getX()+e2.maskx, e2.getY()+e2.masky, e2.maskw, e2.maskh);
	
	return e1Mask.intersects(e2Mask);
                                                      }


	
}

