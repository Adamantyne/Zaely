package com.Edem.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Edem.entities.Enemy_slimeWater;
import com.Edem.entities.Entity;
import com.Edem.entities.Material_ferro;
import com.Edem.entities.Player;
import com.Edem.entities.Rio1;
import com.Edem.entities.SwordBA;
import com.Edem.gráficos.Spritesheet;
import com.Edem.main.Game;

public class World {
	
public static int WIDTH,HEIGHT;

public static final int TILE_SIZE = 150;
	
public static Tiles[] tiles;	

public World(String path) {
	try {
		BufferedImage map = ImageIO.read(getClass().getResource(path));
		int pixels[] = new int[map.getWidth()*map.getHeight()];
		WIDTH = map.getWidth();
		HEIGHT = map.getHeight();
		tiles = new Tiles[map.getWidth()*map.getHeight()];
		map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
		
		for(int xx = 0; xx < map.getWidth(); xx++ ) {
			for(int yy = 0; yy < map.getHeight(); yy++) {
				
				int pixelsAtual =pixels[xx + (yy*map.getWidth())];
				
				tiles[xx+(yy*WIDTH)] = new Tile_chão(xx*150,yy*150,Tiles.chão);
			
				if(pixelsAtual == 0xFF000000) {
					//chão	
					tiles[xx+(yy*WIDTH)] = new Tile_chão(xx*150,yy*150,Tiles.chão);
				                              }
				else if(pixelsAtual == 0xFFC0C0C0) {
					//muro
					tiles[xx+(yy*WIDTH)] = new Tile_muro(xx*150,yy*150,Tiles.muro);
				                                   }
				
				else if(pixelsAtual == 0xFF969696) {
					//muro invertido
					tiles[xx+(yy*WIDTH)] = new Tile_muroin(xx*150,yy*150,Tiles.muroin);
				                                   }
				
				else if(pixelsAtual == 0xFF404040) {
					//parede 
					tiles[xx+(yy*WIDTH)] = new Tile_parede(xx*150,yy*150,Tiles.parede);
				                                   }
				else if(pixelsAtual == 0xFFFFFFFF) {
					//parte superior da parede
					tiles[xx+(yy*WIDTH)] = new Tile_supparede(xx*150,yy*150,Tiles.supparede);
				                                   }
				else if(pixelsAtual == 0xFF7F0000) {
					//arvores
					tiles[xx+(yy*WIDTH)] = new Tile_arvores(xx*150,yy*150,Tiles.arvores);
				                                   }
				else if(pixelsAtual == 0xFF267F00) {
					//copa das arvores 
					tiles[xx+(yy*WIDTH)] = new Tile_coparvores(xx*150,yy*150,Tiles.coparvores);
				                                   }
				
				else if(pixelsAtual == 0xFFB6FF00) {
					//copa das arvores inferiores
				                                   }
				//else if(pixelsAtual == 0xFF0094FF) {
					//rio
					//tiles[xx+(yy*WIDTH)] = new Tile_rio(xx*150,yy*150,Tiles.rio);
				           //                        }
				else if(pixelsAtual == 0xFF0094FF) {
				//rio corrente
					Rio1 rio =new Rio1(xx*150,yy*150,150,150,Entity.rio);
					Game.entities.add(rio);
				                                   }
				else if(pixelsAtual == 0xFF7F3300) {
					//ponte
				                                   }
				else if(pixelsAtual == 0xFFFF0000) {
					//inimigo slimeWater
					Enemy_slimeWater en =new Enemy_slimeWater(xx*150,yy*150,150,150,Entity.slimeWater);
					Game.entities.add(en);
				    Game.slimeWater.add(en);                               }
				
				else if(pixelsAtual == 0xFF8E8E8E) {
					//ferro
					Material_ferro ferro = new Material_ferro(xx*150,yy*150,40,40,Entity.iron);
					Game.entities.add(ferro);
					ferro.seyMask(10, 10, 40, 40);
					Game.material.add(ferro);
					
				                                   }
				else if(pixelsAtual == 0xFFFF006E) {
					//personagem
					Game.player.setX(xx*150);
					Game.player.sety(yy*150); 
					                               }
		
			                                            }
		                                            }
	    } 
	catch (IOException e) {
		
		e.printStackTrace();
	                      }
                          }

public void render(Graphics g) {
	int n1 = 150;
	int n2 = 150;
	int xstart = Camera.x/n1;
	int ystart = Camera.y/n2;
	
	int xfinal = xstart + (Game.WIDTH/n1); 
    int yfinal = ystart + (Game.HEIGHT/n2);
	
	
	
	for(int xx = xstart; xx <= xfinal; xx++) {
		for(int yy = ystart; yy <= yfinal; yy++) {
			if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
				continue;
			                                                    }
		Tiles tile = tiles[xx+(yy*WIDTH)];	
		tile.render(g);                                
		                                   }
	                                  }
                               }
//colisões:
public static boolean isFree(int xnext, int ynext) {
	int x1 = xnext / TILE_SIZE;
	int y1 = ynext / TILE_SIZE;
	
	int x2 = (xnext+TILE_SIZE - 1)/ TILE_SIZE;
	int y2 = ynext / TILE_SIZE;
	
	int x3 = xnext / TILE_SIZE;
	int y3 = (ynext+TILE_SIZE - 1) / TILE_SIZE;
	
	int x4 = (xnext+TILE_SIZE - 1) / TILE_SIZE;
	int y4 = (ynext+TILE_SIZE - 1) / TILE_SIZE;
	
	return!((tiles[x1 + (y1*World.WIDTH)] instanceof Tile_muro)||
			(tiles[x2 + (y2*World.WIDTH)] instanceof Tile_muro)||
			(tiles[x3 + (y3*World.WIDTH)] instanceof Tile_muro)||
			(tiles[x4 + (y4*World.WIDTH)] instanceof Tile_muro)||
			
			(tiles[x1 + (y1*World.WIDTH)] instanceof Tile_muroin)||
			(tiles[x2 + (y2*World.WIDTH)] instanceof Tile_muroin)||
			(tiles[x3 + (y3*World.WIDTH)] instanceof Tile_muroin)||
			(tiles[x4 + (y4*World.WIDTH)] instanceof Tile_muroin)||
			
			(tiles[x1 + (y1*World.WIDTH)] instanceof Tile_supparede)||
			(tiles[x2 + (y2*World.WIDTH)] instanceof Tile_supparede)||
			(tiles[x3 + (y3*World.WIDTH)] instanceof Tile_supparede)||
			(tiles[x4 + (y4*World.WIDTH)] instanceof Tile_supparede)||
			
			(tiles[x1 + (y1*World.WIDTH)] instanceof Tile_coparvores)||
			(tiles[x2 + (y2*World.WIDTH)] instanceof Tile_coparvores)||
			(tiles[x3 + (y3*World.WIDTH)] instanceof Tile_coparvores)||
			(tiles[x4 + (y4*World.WIDTH)] instanceof Tile_coparvores)||
			
			(tiles[x1 + (y1*World.WIDTH)] instanceof Tile_rio)||
			(tiles[x2 + (y2*World.WIDTH)] instanceof Tile_rio)||
			(tiles[x3 + (y3*World.WIDTH)] instanceof Tile_rio)||
			(tiles[x4 + (y4*World.WIDTH)] instanceof Tile_rio)
			);
                                                   }


public static void restartGame(String level) {
	   Game.entities.clear();
	   Game.slimeWater.clear();
	   Game.entities = new ArrayList<Entity>();
	   Game.slimeWater = new ArrayList<Enemy_slimeWater>();
	   Game.material = new ArrayList<Material_ferro>();
	   Game.swordBA = new ArrayList<SwordBA>();
	   Game.spritesheet2 = new Spritesheet("/Tiles.png");
	   Game.spritesheet = new Spritesheet("/personagem2.png");
	   Game.spritesheetEntity = new Spritesheet("/Entity.png");
	   Game.player = new Player(0,0,100,165,Game.spritesheet.getSprite(0, 0, 150, 150));
	   Game.entities.add(Game.player);
	   Game.world = new World(level);
	   return;
                                             }







                   }
