package com.Edem.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Edem.World.Camera;
import com.Edem.World.World;
import com.Edem.main.Game;

public class Enemy_slimeWater extends Entity {
	
	public double speed = 1;
	private int maskx = 30, masky = 90, maskw = 70, maskh = 70, aah=690, aaw=690, aax=250, aay=250 ,arw = 100, arh = 100, arx = 0, ary = 50;
	public int ATACAR,hp=100,MAXhp=100;
	
	private int Frames, maxFrames = 20, Index = 0, maxIndex = 4,hited=0,DFrames,DmaxFrames=20,DIndex=0,DmaxIndex=6;
	private static int mov;
	private BufferedImage enemyhited;
	private BufferedImage[] inertEnemy;
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private BufferedImage[] ataque1;
	private BufferedImage[] enemyDeath;

	public Enemy_slimeWater(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		
	    inertEnemy = new BufferedImage[5]; 
	    rightEnemy = new BufferedImage[5];
	    leftEnemy = new BufferedImage[5];
	    ataque1 = new BufferedImage[5];
	    enemyDeath = new BufferedImage[8];
	    enemyhited = Game.spritesheetEntity.getSprite(650, 0, 130, 200);
	    Game.entities.add(this);
	    
	    for(int i = 0; i<5 ; i++) {
	    	inertEnemy[i] = Game.spritesheetEntity.getSprite(0 + (i*130), 0, 130, 200);
	    	                       }
	    for(int i = 0; i<5 ; i++) {
			rightEnemy[i] = Game.spritesheetEntity.getSprite(0 + (i*130), 400, 130, 200);
			                       }
		for(int i = 0; i<5 ; i++) {
			leftEnemy[i] = Game.spritesheetEntity.getSprite(0 + (i*130), 200, 130, 200);
			                       }
		for(int i = 0; i<5 ; i++) {
			ataque1[i] = Game.spritesheetEntity.getSprite(0 + (i*130), 600, 130, 200);
			                      }
		 for(int i = 0; i<8 ; i++) {
		    	enemyDeath[i] = Game.spritesheetEntity.getSprite(0 + (i*130), 840, 130, 200);
		    	                   }
		
		
	                                                                                    }
	
	     public void areas() {
	       Rectangle AR = new Rectangle(Game.player.getX() + arx - Camera.x, Game.player.getY()+ ary - Camera.y, arw,arh);
	  	   Rectangle AA = new Rectangle(this.getX() - aax - Camera.x, this.getY() - aay - Camera.y, aaw, aah);
	  	 	
	       if(AA.intersects(AR)) {
			        ATACAR = 1;
		                         }
				
           
	                           }
      
	
	public void atuali() {
	   mov = 1;
	   ATACAR = 0;
	   areas();
	   if(hited<=1) {hited=0;}
	   if(World.isFree((int)(x+speed), this.getY())) {hited--;}
		                                             
	 
	                 
	  if(playerCollidding() == false) {
		 
	  if(ATACAR == 1){
		
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())) {
			mov = 2;
		    //hited--;
			if(Game.rand.nextInt(100) < 50 ) {x+=speed;}
			
			                                                                         }
		
			
		                                                                                                                           
		else if((int)x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())) {
			mov = 2;
			//hited--;             
			if(Game.rand.nextInt(100) < 50 ) {x-=speed;}
			
		                                                                                   }
		
		
		if ((int)y > Game.player.getY() && World.isFree (this.getX(),(int)(y-speed))) {
			//mov = 4;
			//hited--;
			y-= speed;
			
		                                                                               }
		
		else if((int)y < Game.player.getY() && World.isFree (this.getX(),(int)(y+speed))) {
			//mov = 5;
			//hited--;
			y+= speed;                                                                     
			
		                                                                                   }
		
	                   }         
	                                 }
	  else if(playerCollidding() == true && hited<=0) {
		  
		  ATACAR =2;
		  if(Game.rand.nextInt(100)<10) { 
		        Game.player.life--;
		        Game.player.isDamage = true;
		                                }
	          //System.out.println("vida: "+ Game.player.life);
	                                                  }
	                                      
	  if(mov >= 1) {
			Frames++;
	  
          if(Frames==maxFrames) {
				Frames=0;
			    Index++; 
			   
			    if(Index == maxIndex) {
			    	Index=0;
			                          }
			    
                                }
	               }
	  //isColidding();
	  colisãoParede();
	  if(hp>=0) {hited();}
	  else if(hp<0) {
		       ATACAR=3;
		       speed=0;
		       DFrames++;
		        if(DIndex<=7) {
		          if(DFrames==DmaxFrames) {
			          DFrames=0; 
			          DIndex++;
			  
			                              }
		                      }
	                 }
	  if(DIndex==7) {
		  Game.entities.remove(this);
		  Game.slimeWater.remove(this);
		  return;
	                }
	 
	                      }
	
	public void render(Graphics g) {
		if(hited<=0){
		if(ATACAR ==0) {
		     g.drawImage(inertEnemy[Index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		               }
		
		if(ATACAR ==1) {
		  if(this.getX()<Game.player.getX()) {
			  g.drawImage(rightEnemy[Index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		                                     }
		
		  if(this.getX()>Game.player.getX()) {
			  g.drawImage(leftEnemy[Index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		                                          }
		
		   if(this.getX()==Game.player.getX()) {
			  g.drawImage(inertEnemy[Index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			                                   }
		                } 
		else if(ATACAR ==2) {
			g.drawImage(ataque1[Index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		                    }
	               }
		else if(hited>0) {
			g.drawImage(enemyhited, this.getX() - Camera.x, this.getY() - Camera.y, null);
		                 }
		if(ATACAR==3) {
			g.drawImage(enemyDeath[DIndex], this.getX() - Camera.x, this.getY() - Camera.y, null);
		              }
	                  
		//g.setColor(Color.GREEN); 
		//g.fillRect(this.getX()- Camera.x,this.getY()- Camera.y,(hp/MAXhp)*100,5);
		//super.render(g);
		//g.setColor(Color.GREEN);
		//g.fillRect(Game.player.getX() + arx - Camera.x, Game.player.getY()+ ary - Camera.y, arw, arh);
		//g.setColor(Color.RED);
		//g.fillRect(this.getX() - aax - Camera.x, this.getY() - aay - Camera.y, aaw, aah);
	    //g.setColor(Color.BLUE);  
	    //g.fillRect(this.getX() + maskx - Camera.x, this.getY()+ masky+40 - Camera.y, maskw, maskh-40);
	    
	                               }
	
	
	
	
	
	
	
	/*public void isColidding() {
		
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw, maskh);
		for(int i = 0;i < Game.slimeWater.size(); i++) {
			Enemy_slimeWater e = Game.slimeWater.get(i);
			if(e == this) {
			   continue;
			              }
			
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx,e.getY() + masky,maskw, maskh);
			if(enemyCurrent.intersects(targetEnemy)) {
				targetEnemy.x-=10;
				
			                                          }
		                                               }
		
	                           }*/
	
	public boolean playerCollidding(){
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx - Camera.x, this.getY()+ masky+40 - Camera.y, maskw, maskh-40);
		Rectangle Player = new Rectangle(Game.player.getX() + arx - Camera.x, Game.player.getY()+ ary - Camera.y, arw,arh);
		if(enemyCurrent.intersects(Player)) {
			return true;
		                                    }
	
		else {return false;}
		     
	                                 }
	
	public void hited() {
		for(int i = 0; i < Game.swordBA.size(); i++) {
			Entity e = Game.swordBA.get(i);
			if(e instanceof SwordBA) {
				if(Entity.Colliding(this, e)) {
					hited=20;
					hp-=Game.player.atk;
					if(this.x<Game.player.getX()) {this.x-=Game.player.impactBA;}
					else if (this.x>Game.player.getX()) {this.x+=Game.player.impactBA;}
					return;
				                              }
			                         }
		                                             }
	                    }
	public void death() {
		Game.entities.remove(this);
		                 
	                    }
	
	public void colisãoParede() {
		        //colisão
				if(hited >= 1 && !World.isFree (this.getX(),(int)(y-speed))) {
				if((int)x < Game.player.getX() && !World.isFree((int)(x+speed), this.getY())) {
					x+=1;				
				                                                                              }
			                                }
				//colisão
				if(hited >= 1 && !World.isFree (this.getX(),(int)(y-speed))) {
				if((int)x > Game.player.getX() && !World.isFree((int)(x-speed), this.getY())) {
					x-=1;
				                                                                              }
				                            }
	                            }
	
	
	
			 
	    	
                   
	
	
	
	
	
	
	
	

                                             }
