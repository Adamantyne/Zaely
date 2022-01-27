     package com.Edem.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.Edem.World.Camera;
import com.Edem.World.World;
import com.Edem.gráficos.Spritesheet;
import com.Edem.main.Game;

public class Player extends Entity {
	
	public boolean right, up, left, down, take,ATACAR=false; 
	public double speed = 4.0;
	public double life = 1000,maxLife=1000, Fpulo;
	private int Frames, maxFrames, Index = 0, maxIndex = 9, damageFrames=0,Hpulo;
	public int mov, direção,atk = 5,impactBA=5,tamanhoBA=75,alcanceBA=60, cdBA=0,BA=0,maxBA=2;
	public boolean moved = false, isDamage = false, hit = false, pulo = false;
	

	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] inertPlayer;
	private BufferedImage playerDamage;
	private BufferedImage playerDamageR;
	private BufferedImage playerDamageL;
	

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	inertPlayer = new BufferedImage[10];
	leftPlayer = new BufferedImage[8];
	rightPlayer =new BufferedImage[8];
	downPlayer = new BufferedImage[8];
	upPlayer = new BufferedImage[8];
	playerDamage = Game.spritesheet.getSprite(0, 600, 100, 200);
	playerDamageR = Game.spritesheet.getSprite(100, 600, 100, 200);
	playerDamageL = Game.spritesheet.getSprite(200, 600, 100, 200);
	 
	
	for(int i = 0; i<10 ; i++) {
	inertPlayer[i] = Game.spritesheet.getSprite(0 + (i*100), 0, 100, 200);
	                           }
	for(int i = 0; i<8 ; i++) {
		rightPlayer[i] = Game.spritesheet.getSprite(0 + (i*100), 200, 100, 200);
		                      }
	for(int i = 0; i<8 ; i++) {
		leftPlayer[i] = Game.spritesheet.getSprite(0 + (i*100), 400, 100, 200);
		                      }
	for(int i = 0; i<8 ; i++) {
		downPlayer[i] = Game.spritesheet.getSprite(800 + (i*100), 200, 100, 200);
		                      }
	for(int i = 0; i<8 ; i++) {
		upPlayer[i] = Game.spritesheet.getSprite(800 + (i*100), 400, 100, 200);
		                      }
	
	
	
       
		                      
	
	                                                                         }
	public void atuali() {
		Hpulo=(int) (1*Fpulo);
		mov = 1;
		moved = false;
		ATACAR=false;
		cdBA--;
		if(cdBA<0) {cdBA=0;}
	
		
		
		if(right && World.isFree((int)(x+speed), this.getY())){
			
			
			x+=speed;
			if(Index>=8) {
				Index = 0;
			             }
			maxIndex = 8;
			direção=2;
			mov = 2;
			moved = true;	
		                                                     }
		
		if(left && World.isFree((int)(x-speed), this.getY())) {
			
			x-=speed;
			if(Index>=8) {
				Index = 0;
			             }
			maxIndex = 8;
			direção=3;
			mov = 3;
			moved = true;		
		                                                      }
		
		if(up && World.isFree(this.getX(), (int) (y-speed))) {
			y-=speed;
			if(Index>=8) {
				Index = 0;
			             }
			maxIndex = 8;
			direção=4;
			mov = 4;
			moved = true;
		                                                     }
		
		if(down && World.isFree(this.getX(), (int) (y+speed))) {
			y+=speed;
			if(Index>=8) {
				Index = 0;
			             }
			maxIndex = 8;
			direção=5;
			mov = 5;
			moved = true;
			                                                  }
			
		if(mov >= 1) {
			Frames++;
			maxFrames=10;
			if(mov>=2) {
				if(Frames>7) {
				     Frames=0;
					         }
				maxFrames = 7;        
		               }           
                     }
            if(Frames==maxFrames) {
				Frames=0;
			    Index++;  
			    if(Index == maxIndex) {
			    	Index=0;
			                         }
			                      }
         
        if(isDamage)   {
        	damageFrames++;
        	if(damageFrames>=15) {
        		isDamage=false;
        		if(damageFrames==20)damageFrames=0;
        	                     }
                       }
        
        
        
        
        if(cdBA>0) {speed = 0.1;}
        else if(cdBA ==0 ) {speed +=0.3;}
        if(speed>4) {speed=4;}
        if(cdBA>10) {
        if(hit) {
        	hit=false;
        	int dx=0,px=0,py=0;
        	if(mov==1) {dx =1; px=60; py=60;}
        	if(mov==2) {dx =2; px=60; py=60;}
        	if(mov==3) {dx =3; px=-10; py=60;}
        	SwordBA swordBA = new SwordBA(this.getX()+px,this.getY()+py,alcanceBA,tamanhoBA,null,dx,0);
        	Game.swordBA.add(swordBA);
        	
        	
        	
                }
	               }
        
        
        if(pulo==true) {
        	Fpulo+=3;
                       }
        if(pulo==false) {
        	Fpulo-=3;
                        }
        if(Fpulo>=60) {
    		pulo=false;
    	              }
        if(Fpulo<0) {
        	Fpulo=0;
                    }
        
        
       if(life<=0) {
    	 Game.gameState="game_over";
                   }
	
    limitCamera();
    death();
    
                     }
	
	
	public void render(Graphics g) {
		if(!isDamage && cdBA==0 ) {
		if(mov == 1) {
			g.drawImage(inertPlayer[Index], this.getX() - Camera.x, this.getY() - Camera.y -Hpulo, null);
		             }
		if(mov == 2) {
			g.drawImage(rightPlayer[Index], this.getX() - Camera.x, this.getY() - Camera.y-Hpulo, null);
		             }
		else if(mov == 3) {
			g.drawImage(leftPlayer[Index], this.getX() - Camera.x, this.getY() - Camera.y-Hpulo, null);
		                  }
		else if(mov == 4) {
			g.drawImage(upPlayer[Index], this.getX() - Camera.x, this.getY() - Camera.y-Hpulo, null);
		                  }
		else if(mov == 5) {
			g.drawImage(downPlayer[Index], this.getX() - Camera.x, this.getY() - Camera.y-Hpulo, null);
		                  }
		                          }
		else if(isDamage && cdBA==0) {
			    if(mov==1)g.drawImage(playerDamage, this.getX() - Camera.x, this.getY() - Camera.y -Hpulo, null);
			    else if(mov==2)g.drawImage(playerDamageR, this.getX() - Camera.x, this.getY() - Camera.y -Hpulo, null);
			    else if(mov==3)g.drawImage(playerDamageL, this.getX() - Camera.x, this.getY() - Camera.y -Hpulo, null);
		                             }
		//g.setColor(Color.YELLOW);
		//g.fillRect(Game.player.getX() + maskx - Camera.x, Game.player.getY()+ masky - Camera.y, maskw, maskh);
		
	                               }
	public void limitCamera() {
		Camera.x = this.getX() - (Game.WIDTH/2)+100;
		Camera.y = this.getY() - (Game.HEIGHT/2)+100;
		if(Camera.x<0) {
			Camera.x = 0;
		               }
		if(Camera.y<0) {
			Camera.y = 0;
		               }
	    if(Camera.x > World.WIDTH*150 - Game.WIDTH) {
	    	Camera.x = World.WIDTH*150 - Game.WIDTH;
	                                                }
	    if(Camera.y > World.HEIGHT*150 - Game.HEIGHT) {
	    	Camera.y = World.HEIGHT*150 - Game.HEIGHT;
	                                                  }
	                          }
	
	public void death() {
		if(life<=0) {
			Game.gameState = "game_over";
		            }
	                    }
	   
	
	
	
                                   }
