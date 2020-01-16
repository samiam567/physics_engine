package Alecraft;

import Physics_engine.object_draw;

public class AirBlock extends Block {

	public AirBlock(object_draw drawer1, int x, int y, int z) {
		super(drawer1, x, y, z);
		
	}
	
	
	public static Block setSettings(Block blockToChange) {
		blockToChange.isVisible = false;
		blockToChange.isTangible = false;
		blockToChange.blockType = BlockTypes.Air;
		return blockToChange;
	}

}
