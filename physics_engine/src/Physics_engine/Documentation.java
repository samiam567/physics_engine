package Physics_engine;

public class Documentation {
/*
 *  	v4 collision algorithm
 * the v4 collision algorithm uses the object_draw class to cycle through each object, which override the checkForCollisions method. The key to this collision method and what 
 * sets it apart from the others is what I call subCalculations. These are "sub-frames" which aren't rendered but in which the objects move only a fraction of the distance of 
 * their speed and the computer still checks for collisions. These sub-calculations allow the collisions to be WAYY more accurate and precise without slowing the engine down
 * since the subCalculations aren't rendered.
 * The number of subCalculations in-between every rendered frame is the frame-step and is automatically controlled by how fast the computer is running
 * 
 * 
 * 
	rotation V2
The rotation is calculated in the updatePoints() method of physics_object (line 381)  and the axis subclasses of the coordinate_axis class. 
Every object is drawn on its coordinate_axis. (created in the Square class for example on line 17)   
The updatePoints() method uses both the vectors created by the coordinate_axis and new vectors that It creates to translate angles of rotation (xRotation is ABOUT the x axis, yRotation is ABOUT the y axis, etc.) 
to translate it into the distances in the x, y, and z directions that each point must move to create the rotation. 
The method that does most of the heavy lifting in this process is the calculateComponents() method in the Vector class. 
This method uses three angles: thetaXY, thetaZX, and theta ZY, which are the angles from the positive xAxis toward the yAxis, the positive xAxis toward the zAxis, and 
the positive zAxis toward the yAxis respectively. The method uses these angles to find the x,y,and z components of the vector. The problem is that the equations I derived for 
this purpose only work in the first quadrant. This is where the findQuadrant() method comes in. It takes the three angles and finds based on each angle the two possibilities for
which quadrant the vector is in. (based on the 8 3d quadrants)  The method then uses an array to cross-reference these possibilities to find which quadrant the vector is actually in. 
Once the findQuadrant() method is finished with that, the calculateComponents() method takes over and uses a switch statement to apply the correct changes to the components 
and put the vector into the correct quadrant. 
	
	rotation V3
the V3 rotation algorithm uses the calculatePointValues() method to assign each point in an object a vector. The updatePoints methood uses 
these vectors to translate angles of rotation (xRotation is ABOUT the x axis, yRotation is ABOUT the y axis, etc.) 
to translate it into the distances in the x, y, and z directions that each point must move to create the rotation. 
The method that does most of the heavy lifting in this process is the calculateComponents() method in the Vector class. 
This method uses three angles: thetaXY, thetaZX, and theta ZY, which are the angles from the positive xAxis toward the yAxis, the positive xAxis toward the zAxis, and 
the positive zAxis toward the yAxis respectively. The method uses these angles to find the x,y,and z components of the vector. The problem is that the equations I derived for 
this purpose only work in the first quadrant. This is where the findQuadrant() method comes in. It takes the three angles and finds based on each angle the two possibilities for
which quadrant the vector is in. (based on the 8 3d quadrants)  The method then uses an array to cross-reference these possibilities to find which quadrant the vector is actually in. 
Once the findQuadrant() method is finished with that, the calculateComponents() method takes over and uses a switch statement to apply the correct changes to the components 
and put the vector into the correct quadrant. 	

	rotation V4
the V4 rotation algorithm works much the same as the V3 algorithm, but it uses 3 vectors per point (one for each axis of rotation) which are summed using Vector.vectorADD(Vector[] vectors).
I made this change to fix the error that was found during testing of 3d rotation that the vectors that are being created are impossible because of contradicting angle measurements.

** This algorithm is not in use due to unexpected complications which are currently in the testing phase.
	
The reduceThetas() method just reduces the angle to a 0-2pi range (like how 4pi rad is the same as 2pi rad just it does around the circle twice)	

	
	LINE COUNT:
	5620+

	
	
	ROTATION V6:
	IT WORKS!!!
	-uses three 2D vectors to calculate rotation in each dimension individually
	
	
	
	
my life story :P
lol i wrote this a bit ago and didn't really know where to put it so here's a cringe fest for ya


	Over 4 months ago I decided to undertake the biggest and most complicated program I had ever dreamed of making before. I
	 decided to make a program to simulate the laws of physics that can be used to easily develop graphical games and programs. 
	 I started out small; trying to make a square move across the screen. 
	 It was very difficult trying to design the fastest way of rendering objects before I knew what objects I would even be drawing. 
	 After testing multiple methods, I decided to go with a rendering object that has an ArrayList of objects which the object updates and renders one by one. Once I finished that, I decided to design a collision algorithm. 
	 What I didn’t realize is that by the time I had developed a working algorithm, I would have written, tested, and revised four fundamentally different algorithms, with V1 and V4 being the only ones that worked. (V1 had to be changed even though it technically worked because it only had a ~90% accuracy. 
	 V4 has a setting in the Settings class that has to be made smaller based on the top anticipated speed of an object which allows it to have 100% accuracy). 
	 I have since made a V5 collision algorithm that has 100% accuracy for any object made up of points. My next task was to add 3d capabilities to the engine, which brought the issue of rotation in 3d. 
	 I developed and tested four different rotation algorithms, and while v3 and v4 work in 2d, I still have not been able to correctly implement a working 3d rotation algorithm and am still continuing to test and improve upon the v3 and v4 algorithms. 
	 I also have used the engine to create multiple games including JetPack_JoyRide. I started out with a square, and some rectangles that you have to dodge. After peer review I decided to add coins and stuff to buy, and polished up the graphics a lot. Through testing I found that 
	 I needed performance enhancements which I achieved with the V4.0 update of the physics_engine which added a much larger class hierarchy which reduces RAM usage by making the classes more lightweight and makes the whole thing run faster for the same reason. 
	 I also added multithreading to the whole engine which added capabilities for multiple environments and support for lots of other cool features such as a Minimap. I also added a particle effect with small rotating squares for the jetpack fire as suggested by a peer review
	
	
	
	
	
	
	
	WHAT TO DO IF AN OBJECT ISN'T SHOWING::
	
	-check if you added it to the drawer
	-check if the drawer is started
	-check what the x,y, and z positions of the object are
	-if it is pointed, check that the positions of the points are inside the boundaries of the frame (Physics_frame.checkIsInFrame(pointed cObject)) 
	
	
	
	
	
	
	
	
	
	
	
	
	
*/	
}
