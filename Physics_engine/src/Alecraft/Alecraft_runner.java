package Alecraft;

import Physics_engine.Physics_frame;
import Physics_engine.Settings;
import Physics_engine.object_draw;
import Physics_engine.physicsRunner;

public class Alecraft_runner extends physicsRunner {

	private static final int xSize = 5, ySize = 5, zSize = 5;
	public static final int blockSize = 250;
	static int charSpeed = 10;
	public static Block[][][] blocks;
	public static Block[] blockList;
	public static int mouseXPointingPos, mouseYPointingPos, mouseZPointingPos;
	public static Cursor cursor;
	
	
	public static void main(String[] args) {
		frame = new Physics_frame();
		drawer = new object_draw(frame);
		run();
	}
	
	private static void generateBlocks(int xSize, int ySize, int zSize) {
		 blocks = new Block[xSize][ySize][zSize];
		 blockList = new Block[xSize*ySize*zSize];
		 int groundLevel = zSize/2;	 
		 
		 System.out.println(zSize);
		 //making groundBlocks
		 int i = 0;
		 for (int z = 0; z < groundLevel; z++) {
			 System.out.print(z);
			 for (int y = 0; y < ySize-1; y++) {
				 for (int x = 0; x < xSize-1; x++) {
					 blockList[i] = new GroundBlock(drawer,x,z,y);
					 i++;
				 }
			 }
		 }
		 
		 for (int z = groundLevel; z < zSize-1; z++) {
			 System.out.print(z);
			 for (int y = 0; y < ySize-1; y++) {
				 for (int x = 0; x < xSize-1; x++) {
					 blockList[i] = new AirBlock(drawer,x,z,y);
					 i++;
				 }
			 }
		 }
		 
	}
	

	public static void run() {
		Settings.fixedFPS_FStep = false;
		frame.setVisible(false);
		System.out.println("loading...");
		generateBlocks(xSize,ySize,zSize);
		cursor = new Cursor(drawer,10);
		drawer.start();
		frame.setVisible(true);
	}
	
	
	
}
