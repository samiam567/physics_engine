package Physics_engine;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

public class New_object_listeners {
	public object_draw drawer;
	public KeyListener keyListener;
	public MouseListener mouseListener;
	public MouseMotionListener mouseMotionListener;
	
	public massive objectBeingChanged;
	public boolean createFreeForm = false, mouseIsPressed = false,switchColorsWithMouseEnter = false;
	
	
	private int counter = 0;
	private double freeFormPointZ = 0;
	private ScoreBoard freeFormBoard;
	
	public New_object_listeners(object_draw drawer1) {
		drawer = drawer1;
		
		
		freeFormBoard = new ScoreBoard(drawer,Settings.width * 0.5, Settings.height * 0.1, "FreeForm Creation Started \n New-Point Z Coordinate:",freeFormPointZ);

		
		mouseMotionListener = new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {	
				
				if (! createFreeForm) objectBeingChanged.setRotation((objectBeingChanged.getCenterY() - e.getY())/100,(e.getX() - objectBeingChanged.getCenterX() )/100, objectBeingChanged.getZRotation());
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
		
			}
			
		};
		
		
		//mouseListener +==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		mouseListener =  new MouseListener() {

				public void mouseClicked(MouseEvent arg0) {
					drawer.inactivity_timer = 0;
					
					System.out.println("Click at: " + arg0.getX() + "," + arg0.getY());
					if (createFreeForm) {	
						((PointSet)objectBeingChanged).addPoint(new point(drawer,arg0.getX(),arg0.getY(),freeFormPointZ));
						System.out.println("Point added");
						((PointSet)objectBeingChanged).initialize();
						
						if (((PointSet)objectBeingChanged).getPoints().length == 1) {
							drawer.add((PointSet)objectBeingChanged);
						}
					}else {
						Point mousePoint = new Point(arg0.getX(), arg0.getY());
						point mousePoint1 = new point(drawer,arg0.getX(), arg0.getY(),0);
						
						for (massive cObject : drawer.getTangibles()) {
							cObject.updatePolygons();
							if (mousePoint1.isIn((Physics_3DPolygon) cObject) && (cObject.getObjectName() != "boundries") ) {
								System.out.println(cObject.getObjectName() + " has been selected");
								objectBeingChanged = cObject;
								break;
							}
						}
						
						if (arg0.getButton() == 1) {
							try {
								objectBeingChanged.setPos(arg0.getX(),arg0.getY(),objectBeingChanged.getCenterZ());
							}catch(NullPointerException n) {
								System.out.println("no object selected");
							}
						}else if (arg0.getButton() == 3){
											
							try {
								System.out.println("menu");		
								menu();
							}catch(NullPointerException n) {
								System.out.println("no object selected");
							}
							
						}else if (arg0.getButton() == 2) {
							objectBeingChanged = null;
							System.out.println("selected object has been cleared");
						}else {
							System.out.println(arg0.getButton());
						}
					}
					
				}

				public void mouseEntered(MouseEvent arg0) {
					drawer.inactivity_timer = 0;
				
					if(switchColorsWithMouseEnter)drawer.frame.setColor(Color.green);
				}

				
				public void mouseExited(MouseEvent arg0) {
					drawer.inactivity_timer = 0;
					if(switchColorsWithMouseEnter)drawer.frame.setColor(Color.gray);
				}

				
				public void mousePressed(MouseEvent arg0) {
					drawer.inactivity_timer = 0;
					
					System.out.println("Press at: " + arg0.getX() + "," + arg0.getY());
					
					mouseIsPressed = true;
					
					
					point mousePoint1 = new point(drawer,arg0.getX(), arg0.getY(),0);
					
					if (createFreeForm) {	
						
						if (arg0.getButton() == 1) {
							((PointSet)objectBeingChanged).addPoint(new point(drawer,arg0.getX(),arg0.getY(),0));
							System.out.println("Point added");
							((PointSet)objectBeingChanged).initialize();
							
							if (((PointSet)objectBeingChanged).getPoints().length == 1) {
								drawer.add((PointSet)objectBeingChanged);
							}
						}else if (arg0.getButton() == 3){
							freeFormMenu();			
						}else if (arg0.getButton() == 2) {
							createFreeForm = false;
	        	  			((PointSet)objectBeingChanged).initialize();
							System.out.println("free-form Created");
							((PointSet)objectBeingChanged).finish();
							drawer.remove(freeFormBoard);
							objectBeingChanged.setRotation(0, 0, 0);
						}else {
							System.out.println(arg0.getButton());
						}
						
						
					}else {
				
						for (massive cObject : drawer.getTangibles()) {
							cObject.updatePolygons();
							if ((mousePoint1.isIn((Physics_3DPolygon) cObject) && (cObject.getObjectName() != "boundries"))) {
								System.out.println(cObject.getObjectName() + " has been selected");
								objectBeingChanged = cObject;
								break;
							}
						}
						/*
						if (arg0.getButton() == 1) {
							try {
								objectBeingChanged.setPos(arg0.getX(),arg0.getY(),objectBeingChanged.getCenterZ());
							}catch(NullPointerException n) {
								System.out.println("no object selected");
							}
						}else if (arg0.getButton() == 3){
											
							try {
								System.out.println("menu");		
								menu();
							}catch(NullPointerException n) {
								System.out.println("no object selected");
							}
							
						}else if (arg0.getButton() == 2) {
							objectBeingChanged = null;
							System.out.println("selected object has been cleared");
						}else {
							System.out.println(arg0.getButton());
						}
						*/
					}					
				
				}


				public void mouseReleased(MouseEvent arg0) {
				/*
					drawer.inactivity_timer = 0;
					mouseIsPressed = false;
					
					if (! createFreeForm) {
						try {
							objectBeingChanged.setPos(arg0.getX(),arg0.getY(),objectBeingChanged.getCenterZ());
						}catch(NullPointerException n) {
							System.out.println("no object selected");
						}
					}
			*/
				}
		
				};
				
				
				//==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+==+
		
				
				//key listener
				keyListener = new KeyListener() {
					   
					@Override
			         public void keyPressed(KeyEvent e) {
			        	  drawer.inactivity_timer = 0;      	     	

			        	
			        	  switch (e.getKeyCode()) {
			        	  
			        	  	
			        	  	case(78): //n
			        	  		newObject();
			        	  	break;
			        	  	
			        	  	case(32): //Space
			        	  		drawer.pause();
			        	  
			        	  	break;
			        	  	
			        	  	case(10): //Enter
			        	  		drawer.resume();
			        	  		if (createFreeForm) {
			        	  			createFreeForm = false;
			        	  			((PointSet)objectBeingChanged).initialize();
									System.out.println("free-form Created");
									((PointSet)objectBeingChanged).finish();
									drawer.remove(freeFormBoard);
									objectBeingChanged.setRotation(0, 0, 0);
			        	  		}
			        	  	break;
			        	  		        	  	
			        	  	case(47): // /
			        	  		drawer.pause();
			        	  	
			        	  		drawer.resume();
			        	  	break;
			        	  		
			        	  }
			        	  
					}
					@Override
					public void keyReleased(KeyEvent arg0) {
						drawer.inactivity_timer = 0;		
					
		
					}
					@Override
					public void keyTyped(KeyEvent arg0) {
						
					}
					
			      };
		add();
	
	
	}

	
	public void add() {	
		drawer.addMouseListener(mouseListener);
		drawer.addKeyListener(keyListener);
		drawer.addMouseMotionListener(mouseMotionListener);
		
		JOptionPane.showMessageDialog(drawer.frame, "Press \"n\" to create a new object \n click on an object to select it \n right-click once an object is selected to change properties of that object \n use the middle mouse button to deselect an object","Physics Simulator Instructions", 1);
	}
	
	public void remove() {
		drawer.removeMouseListener(mouseListener);
		drawer.removeKeyListener(keyListener);
		drawer.removeMouseMotionListener(mouseMotionListener);
	}
	
	
	public void menu() {
		
		String thingToDo = (String) JOptionPane.showInputDialog(drawer.frame,  "What property of " + objectBeingChanged.getObjectName() + " do you want to change?", "Edit object: " + objectBeingChanged.getObjectName(), 3, null, Physics_engine_toolbox.stuffToDo, null);
		
		switch(thingToDo) {
			case("color"):
				objectBeingChanged.setColor(Physics_engine_toolbox.getColorFromUser(drawer.frame));
			break;
			
			case("friction Coefficient"):
				objectBeingChanged.setFrictionCoefficient(Physics_engine_toolbox.getDoubleFromUser(drawer.frame));
			break;
			
			case("mass"):
				objectBeingChanged.setMass(Physics_engine_toolbox.getDoubleFromUser(drawer.frame,"what is the new mass?"));
			break;
			
			case("name"):
				objectBeingChanged.setName(JOptionPane.showInputDialog(drawer.frame, "What is the new name?"), 1);
			break;
			
			case("delete"):
				drawer.remove((physics_object)objectBeingChanged);
				objectBeingChanged = null;
			break;
			
		
			
			default:
				double x = Physics_engine_toolbox.getDoubleFromUser(drawer.frame,"What is the x " + thingToDo);
				double y = Physics_engine_toolbox.getDoubleFromUser(drawer.frame,"What is the y " + thingToDo);
				double z = Physics_engine_toolbox.getDoubleFromUser(drawer.frame,"What is the z " + thingToDo);
				
				switch(thingToDo) {
					case("position"):
						objectBeingChanged.setPos(x,y,z);
					break;
					
					case("speed"):
						objectBeingChanged.setSpeed(x,y,z);
					break;
					
					case("acceleration"):
						objectBeingChanged.setAccel(x,y,z);
					break;
					
					case("rotation"):
						objectBeingChanged.setRotation(x,y,z);
					break;
					
					case("angular velocity"):
						objectBeingChanged.setAngularVelocity(x,y,z);
					break;
					
					case("angular acceleration"):
						objectBeingChanged.setAccel(x,y,z);
					break;
					
					case("size"):
						objectBeingChanged.setSize(x,y,z);
					break;
					
					default:
						System.out.println("ERROR: That hasn't been programmed yet!");
					break;
				}
				
			break;
		}
	}
	
	public void newObject() {
		String objectToMake = (String) JOptionPane.showInputDialog(drawer.frame,  "What object do you want to create?", "Create a new object", 3, null, Physics_engine_toolbox.typesOfObjects, null);
//		 {"square","rectangle","box","triangle","sphere"};
		
		switch (objectToMake) {
			case("square"):
				objectBeingChanged = new Square(drawer,-200,-200,0,50,10);
				break;
		
			case("rectangle"):
				objectBeingChanged = new rectangle(drawer,-200,-200,0,50,50,10);
				break;
				
			case("box"):
				objectBeingChanged = new Box(drawer,-200,-200,0,50,10);
				break;
				
			case("triangle"):
				objectBeingChanged = new Triangle(drawer,-200,-200,0,50,50,10);
				break;
				
			case("sphere"):
				objectBeingChanged = new Sphere(drawer,-200,-200,0,50,10,Settings.thetaStep);
				break;
			
			case("circle"):
				objectBeingChanged = new PolarObject(drawer,-200,-200,0,50,"circle",Settings.thetaStep,2*Math.PI);
				break;
				
			case("free-form"):
				createFreeForm = true;
				objectBeingChanged = new PointSet(drawer);
				drawer.add(freeFormBoard);
				System.out.println("Freeform creation started");
				break;
			
			default:
				System.out.println("ERROR: That hasn't been programmed yet!");
				break;
		}
		
		
		
		if (! (objectToMake == "free-form") ) {
			drawer.add((physics_object) objectBeingChanged);
			JOptionPane.showMessageDialog(drawer, "Click where you want to put the object. \n make sure you don't click where another object already is");
		}else {
			JOptionPane.showMessageDialog(drawer, "Click where you want to put points.\nthe shape will be drawn in the order you create the points\nmake sure you press ENTER when you are finished!");
		}
		
		
	}
	
	public void freeFormMenu() {
		String[] freeFormOptions = {"zPos of new points"};
		
		String ffThingToDo = (String) JOptionPane.showInputDialog(drawer.frame,  "FreeForm Options", "Edit object: " + objectBeingChanged.getObjectName(), 3, null, freeFormOptions, null);
		
		switch(ffThingToDo) {
			case("zPos of new points"):
				freeFormPointZ =  Physics_engine_toolbox.getDoubleFromUser(drawer.frame,"What is the " + ffThingToDo + "?");
				freeFormBoard.setScore(freeFormPointZ);
			break;
			
			default:
				System.out.println("ERROR: That hasn't been programmed yet!");
			break;
		}
		
	}
}
