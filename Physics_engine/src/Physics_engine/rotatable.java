package Physics_engine;

public interface rotatable extends movable {
	double xRotation=0,yRotation=0,zRotation=0,angularVelocityX=0, angularVelocityY=0, angularVelocityZ=0, angularAccelX=0, angularAccelY=0, angularAccelZ=0;
	
	public void setRotation();

}
