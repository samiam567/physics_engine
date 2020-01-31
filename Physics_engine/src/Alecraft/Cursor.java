package Alecraft;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Physics_engine.Physics_drawable;
import Physics_engine.Physics_engine_toolbox;
import Physics_engine.Settings;
import Physics_engine.Vector3D;
import Physics_engine.drawable;
import Physics_engine.movable;
import Physics_engine.object_draw;
import Physics_engine.point;
import Physics_engine.rotatable;
import zortex.Enemy;

public class Cursor extends Physics_drawable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7871054511354443848L;
	private object_draw drawer;
	public static int prevMouseX = Settings.width/2, prevMouseY = Settings.height/2;
	
	private static int cursorLength = Alecraft_runner.blockSize * 5;
	public Cursor(object_draw drawer,int size) {
		super(drawer);
		this.drawer = drawer;
		addListeners();
		
		
	}
	
	public void addBlockAtCursor(BlockTypes blockTypeToAdd) {
		Block.changeBlockType(getBlockAtCursor(true), blockTypeToAdd);
	}
	
	public void removeBlockAtCursor() {
		Block.changeBlockType(getBlockAtCursor(false), BlockTypes.Air);
	}
	
	public Block getBlockAtCursor(boolean addMode) {
		Block prevBlock = null;
		point probePoint = new point(drawer,Settings.width/2,Settings.height/2,0);
		
		for (int z = 0; z <= cursorLength; z += Alecraft_runner.blockSize/2) {
			for (Block cBlock : Alecraft_runner.blockList) {
				probePoint.setZ(z);
				try {
					if (Physics_engine_toolbox.distance(probePoint, cBlock.getCenter()) < Alecraft_runner.blockSize) {
						if (! cBlock.blockType.equals(BlockTypes.Air)) {
							if (addMode) {
								if (prevBlock.equals(null)) System.out.println("cannot add block, out of bounds");
								return prevBlock;
							}else {//removeMode
								return cBlock;
							}
						}
						prevBlock = cBlock;
					}
				}catch(NullPointerException n) {
					System.out.println("nullBlock");
					}
				
				
				
			}
		}
		
		System.out.println("no block found");
		
		return null;
	}
	
	
	
	@Override
	public void paint(Graphics page) {
		page.drawLine((int)(getCenterX() - getXSize()/2),(int)( getCenterY()),(int)( getCenterX() + getXSize()/2),(int)( getCenterY()));
	}
	
	private void addListeners() {
		drawer.addMouseMotionListener( new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {	
				//nothing
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				Vector3D mouseMoveVec = new Vector3D(drawer,prevMouseY-e.getY(),e.getX()-prevMouseX,0);
				mouseMoveVec.multiply(0.01);
				for (drawable cOb : drawer.getDrawables()) {
					if (cOb.getType().equals("enviro-move")) {
						try {
						((rotatable)cOb).addVectorRotation(mouseMoveVec);
						}catch(ClassCastException c) {}
						
					}
					
					
				}
				for (drawable cOb : drawer.getDrawables()) {
					if (cOb.getType().equals("enviro-move")) {
						try {
							cOb.Update(0.000000000000001);
						}catch(ClassCastException c) {}
						
					}
					
					
				}
				
			
				
				prevMouseX = e.getX();
				prevMouseY = e.getY();
			}
			
		});
		
		drawer.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent m) {
				System.out.println("keypressed");
				switch(m.getKeyCode()) {
			
	        	  	case(87): //w
	        	  		
	        	  		
	        	  		break;
	        	  	
	        	  	case(65): //a
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(Alecraft_runner.charSpeed,((movable) cOb).getYSpeed(),((movable) cOb).getZSpeed());
	    					}
	    				}
	        	  	
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}
	        	  	break;
	        	  	
	        	  	case(83): //s
	        	  		
	        	  	break;
	        	  	
	        	  	case(32): //space
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(((movable) cOb).getXSpeed(),Alecraft_runner.charSpeed,((movable) cOb).getZSpeed());
	    					}
	    				}
	        	  	
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}

	        	  	break;
	        	  	
	        	  	case(16): //Shift
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(((movable) cOb).getXSpeed(),-Alecraft_runner.charSpeed,((movable) cOb).getZSpeed());
	    					}
	    				}
		        	  		
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}
	        	  	break;
	        	  	
	        	  	
	        	  	case(10): //ENTER
	        	  		System.out.println("add block requested");
	        	  		addBlockAtCursor(BlockTypes.Ground);
	        	  	break;
	        	  	
	        	  	case(8): //Backspace
	        	  		System.out.println("remove block requested");
	        	  		removeBlockAtCursor();
	        	  		break;
	        	  	case(68): //d
	        	  		for (drawable cOb : drawer.getDrawables()) {
	    					if (cOb.getType().equals("enviro-move")) {
	    						((movable) cOb).setSpeed(-Alecraft_runner.charSpeed,((movable) cOb).getYSpeed(),((movable) cOb).getZSpeed());
	    					}
	        	  		}
	        	  	
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
							
							
						}
	    			
	        	  	break;
	        	  	
				
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				drawer.inactivity_timer = 0;
				for (drawable cOb : drawer.getDrawables()) {
					if (cOb.getType().equals("enviro-move")) {
						((movable) cOb).setSpeed(0,0,((movable)cOb).getZSpeed());
					}
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("keytyped");
				
				switch(e.getKeyChar()) {
					
					case('w'):
						for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								((movable) cOb).addSpeed(0,0,-Alecraft_runner.charSpeed);
							}
						}
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
						}
					break;
					
					case('s'):
						for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								((movable) cOb).addSpeed(0,0,Alecraft_runner.charSpeed);
							}
						}
		        	  	for (drawable cOb : drawer.getDrawables()) {
							if (cOb.getType().equals("enviro-move")) {
								try {
									cOb.Update(0.000000000000001);
								}catch(ClassCastException c) {}
								
							}
						}
		        	break;
		        	
			}
			}
			
		});
		
	}

	
}
