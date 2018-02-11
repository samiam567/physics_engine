package victorian_integral;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Physics_engine.*;

public class Factory_Minigame extends physics_object {
	public rectangle handle;
	public rectangle head;
	public String obj_name;
	private KeyListener listener;
	
	public Factory_Minigame (object_draw drawer) {		
		handle = new rectangle(500, 200, 0, 800, 100,1);
		handle.isFilled = true;
		handle.setParentObject(this);
//		handle.setPointOfRotationPlace(pointOfRotationPlaces.parentsPlace);
		handle.setName("handle",1);
		handle.setColor(Color.blue);
		
		
		
		head = new rectangle (400, 100, 0, 100, 350,1);
		head.isFilled = true;
		head.setParentObject(this);
//		head.setPointOfRotationPlace(pointOfRotationPlaces.parentsPlace);
		head.setName("head",1);
		head.setColor(Color.YELLOW);

		
		object_draw.objects.add(this);
		object_draw.objects.add(handle);
		object_draw.objects.add(head);
		

		
		listener = new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
	//			handle.setRotation(0,0,1);
				
				setAngularVelocity(0,0,0.1);
				
				System.out.println("s");
				int key = e.getKeyCode();
				drawer.inactivity_timer = 0;
						
			
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) 
			{
			//	handle.setRotation(0,0,0);
			//	head.setRotation(0,0,0);
				setAngularVelocity(0,0,0);
				
				//increasing the player's score
				double newScore;
				try {
					newScore = Victorian_adventure.Score.getTargetScore();
					newScore+= 50;
					Victorian_adventure.Score.setTargetScore(newScore);
				}catch(NullPointerException n){
				
				}
	
			} 


			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		drawer.addKeyListener(listener);
	}
	
	public void end() {
//drawer.removeKeyListener(listener);
		object_draw.objects.remove(head);
		object_draw.objects.remove(handle);
		object_draw.objects.remove(this);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(handle.getColor());
		handle.paint(g);
		g.setColor(head.getColor());
		head.paint(g);
		
	}
}