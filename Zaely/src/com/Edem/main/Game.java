package com.Edem.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.Edem.World.World;
import com.Edem.entities.Enemy_slimeWater;
import com.Edem.entities.Entity;
import com.Edem.entities.Material_ferro;
import com.Edem.entities.Player;

import com.Edem.entities.Rio1;
import com.Edem.entities.SwordBA;
import com.Edem.entities.Sword_ironSword;
import com.Edem.gráficos.Bag;
import com.Edem.gráficos.Game_Over;
import com.Edem.gráficos.Menu;
import com.Edem.gráficos.Pause;
import com.Edem.gráficos.Spritesheet;
import com.Edem.gráficos.UI;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public static JFrame frame;	

private boolean isRunning = true;

private Thread thread;

private BufferedImage image;

public static Spritesheet spritesheet;
public static Spritesheet spritesheet2;
public static Spritesheet spritesheetEntity;
public static Player player;
public static Bag bag;
public static UI ui;
public static World world;
public static Game_Over game_over;
public static Sword_ironSword ironsword;
public static Menu menu;
public static Pause pause;

public static Random rand;

public final static int WIDTH = 1366;
public final static int HEIGHT = 768;
public final static int SCALE = 1;

public static int curlevel=1, maxlevel=2;
	
public static List<Entity> entities;	
public static List<Enemy_slimeWater> slimeWater;
public static List<Material_ferro> material;
public static List<SwordBA> swordBA;

public static String gameState = "menu";

	
public Game() {
	addKeyListener(this);
	addMouseListener(this);
	this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	initFrame();
	rand = new Random();
	image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	entities = new ArrayList<Entity>();
	slimeWater = new ArrayList<Enemy_slimeWater>();
	material = new ArrayList<Material_ferro>();
	swordBA = new ArrayList<SwordBA>();
	
	spritesheet2 = new Spritesheet("/Tiles.png");
	spritesheet = new Spritesheet("/personagem2.png");
	spritesheetEntity = new Spritesheet("/Entity.png");
	player = new Player(0,0,100,165,spritesheet.getSprite(0, 0, 150, 150));
	entities.add(player);
	world = new World("/level1.png");
	ui = new UI();
	bag = new Bag();
	game_over = new Game_Over();
	menu = new Menu();
	pause = new Pause();
	
	ironsword = new Sword_ironSword ();
              }

public void initFrame() {
    frame = new JFrame("Zaely");
    frame.add(this);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
                        }
	
	
	
	
public static void main(String[] args) {
	Game game = new Game();
	game.start();
                                       }
	
	
	
	
	
	
public synchronized void start() {
	thread = new Thread(this);
    thread.start();             
    isRunning = true;
                    }
	
public synchronized void stop() {
	isRunning = false;
	try {
		thread.join();
	    } 
	catch (InterruptedException e) {
		
		e.printStackTrace();
	                               }
                                }
                   
	
	
	
	
	
public void atualizar() {
	if(gameState=="normal"){
	for(int i = 0; i < entities.size(); i++) {
		Entity e = entities.get(i);
	    e.atuali();                 
	                                         }
	bag.atuali();
	ironsword.atuali();
	for(int i = 0; i<swordBA.size(); i++) {
		swordBA.get(i).atuali();
	                                      }
	if(slimeWater.size()==0) {
		curlevel++;
		if(curlevel>maxlevel) {curlevel=1;}
		String newWorld = "/level"+curlevel+".png";
		world.restartGame(newWorld);
	                         }
                           }
	
	else if(gameState=="game_over") {
		//System.out.println("Game Over");
		game_over.atuali();
	                                }
	
	else if(gameState=="menu") {
		menu.atuali();
		
	                           }
	else if(gameState=="pause") {
		pause.atuali();
	                            }
	
	
                        }



public void renderizar() {
	BufferStrategy bs = this.getBufferStrategy();
	if(bs == null) {
		this.createBufferStrategy(3);
		return;
	               }
	Graphics g = image.getGraphics();
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, WIDTH, HEIGHT);
	world.render(g);
	for(int i = 0; i < entities.size(); i++) {
		Entity e = entities.get(i);
	    e.render(g);                     
	                                         }
	
	bag.render(g);
	ui.render(g);
	ironsword.render(g);
	
	
	
	if(gameState=="game_over") {
		game_over.render(g);
                               }
	
	
	else if(gameState=="menu") {
		menu.render(g);
		
                               }
	else if(gameState=="pause") {
		pause.render(g);
	                            }
	
	for(int i = 0; i<swordBA.size(); i++) {
		swordBA.get(i).render(g);
	                                      }
	g.dispose();
	g = bs.getDrawGraphics();
	g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
	bs.show();
	
	
	
                         }
	
	
	
	
	
	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();	
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		 while(isRunning){
	    	 
			 long now = System.nanoTime();
				delta+= (now - lastTime) / ns;
				lastTime = now;
				if(delta >= 1) {
					atualizar();
					renderizar();
					frames ++;
				    delta --;           }
				
				
				
				if(System.currentTimeMillis() - timer >=1000) {
					System.out.println("FPS " +frames);
					frames = 0;
				    timer +=1000;                             }
	                       }
		 stop();
	                  }

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	                                 }

	@Override
	public void keyPressed(KeyEvent e) {
		
	if(e.getKeyCode() == KeyEvent.VK_D) {
		player.right = true;
	                                    }
	else if(e.getKeyCode() == KeyEvent.VK_A) {
		player.left = true;
	                                         }
	if(e.getKeyCode() == KeyEvent.VK_S) {
		player.down = true;
	                                    }
	else if(e.getKeyCode() == KeyEvent.VK_W) {
		player.up = true;
	                                         }
	if(e.getKeyCode() == KeyEvent.VK_F) {
		player.take = true;
	                                    }
	
	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		if(gameState=="game_over") {
			game_over.restartGame=true;
		                           }
		else if(gameState=="menu") {
			menu.enter=true;
		                           }
		else if(gameState=="pause") {
			pause.enter=true;
		                            }
	                                        }
	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
		if(gameState=="menu") {
			menu.down = true;
		                      }
		else if(gameState=="pause") {
			pause.down = true;
		                            }
	                                       }
	if(e.getKeyCode() == KeyEvent.VK_UP) {
		if(gameState=="menu") {
			menu.up = true;
		                      }
		else if(gameState=="pause") {
			pause.up = true;
		                            }
	                                     }
	if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		if(gameState=="normal") {
			gameState="pause";
		                        }
		else if(gameState=="pause") {
			gameState="normal";
		                            }
	                                         }
	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		if(player.Fpulo==0)player.pulo=true;
		//System.out.println("pulando");
	                                        }
	
		
	                                   }

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		                                    }
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		                                         }
		if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		                                    }
		else if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		                                         }
		if(e.getKeyCode() == KeyEvent.VK_F) {
			player.take = false;
		                                    }
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(gameState=="menu") {
				menu.down = false;
			                      }
			if(gameState=="menu") {
				menu.down = false;
			                      }
		                                       }
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(gameState=="menu") {
				menu.up = false;
			                      }
			if(gameState=="menu") {
				menu.up = false;
			                      }
		                                     }

		
		
	                                    }

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	                                       }

	@Override
	public void mousePressed(MouseEvent e) {
	  if(player.Fpulo==0) {	
	   player.hit=true;
	   if(player.cdBA<=10) {player.BA++; player.cdBA=20; ironsword.Frames=0; ironsword.Index=0;}
	                      }
		            
	                                       }

	@Override
	public void mouseReleased(MouseEvent e) {
		//player.ATACAR = false;
		
	                                        }

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	                                       }

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	                                      }

	                                             }
