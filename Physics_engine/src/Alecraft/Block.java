package Alecraft;

import Physics_engine.Rectangular_prism;
import Physics_engine.Settings;
import Physics_engine.Vector3D;
import Physics_engine.object_draw;
import Physics_engine.point;

enum BlockTypes {Generic,Air,Ground};
public class Block extends Rectangular_prism {
	int gameX, gameY, gameZ;
	BlockTypes blockType;
	public Block(object_draw drawer1, int x, int y, int z) {
		super(drawer1, x * Alecraft_runner.blockSize, y * Alecraft_runner.blockSize, z * Alecraft_runner.blockSize, Alecraft_runner.blockSize, Alecraft_runner.blockSize, Alecraft_runner.blockSize, 10);
		hasNormalCollisions = false;
		gameX = x;
		gameY = y;
		gameZ = z;
		setPos( x * Alecraft_runner.blockSize, y * Alecraft_runner.blockSize,z * Alecraft_runner.blockSize);
		
		setType("enviro-move");
		setPointOfRotation(new point(drawer,Settings.width/2,Settings.height/2,0));
		isFilled = true;
		
		isShaded = true;
		transparency = 1;
		Block.setSettings(this);
		drawer.add(this);
	}
	
	public static Block setSettings(Block blockToChange) {
		blockToChange.blockType = BlockTypes.Generic;
		return blockToChange;
	}

	public static Block changeBlockType(Block blockToChange, BlockTypes newBlockType) {
		if (newBlockType.equals(BlockTypes.Air)) {
			AirBlock.setSettings(blockToChange);
		}else if (newBlockType.equals(BlockTypes.Ground)) {
			GroundBlock.setSettings(blockToChange);
		}else if (newBlockType.equals(BlockTypes.Generic)) {
			Block.setSettings(blockToChange);
		}else {
			System.out.println("ERROR - That block type needs to be programmed into changeBlockType() in Block class");
		}
		return blockToChange;
	}
	


}
