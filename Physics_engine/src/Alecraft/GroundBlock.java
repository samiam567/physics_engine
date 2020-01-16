package Alecraft;

import Physics_engine.object_draw;

public class GroundBlock extends Block {

	public GroundBlock(object_draw drawer1, int x, int y, int z) {
		super(drawer1, x, y, z);
		isVisible = true;
		isTangible = false;
		blockType = BlockTypes.Ground;
		
	}

}
