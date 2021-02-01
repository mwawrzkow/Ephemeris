package physics.objects.abstracts;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Primitive{
	float mass = (float) (5.972); 
	private Vector3 position; 
	public enum type{ 
		S, 
		D
	}
	protected ModelInstance instance;
	public Primitive(ModelInstance i) { 
		instance = i; 
		setPosition(getInstance().transform.getTranslation(new Vector3())); 
	}
 public ModelInstance getInstance() { 
	 return instance; 
 }
 public Vector3 getLastPosition() {
	 return getPosition();
 }
public float getMass() { 
		return mass;
	}
public void setMass(float mass) {
	this.mass = mass; 
}
public Vector3 getPosition() {
	return position;
}
public void setPosition(Vector3 position) {
	this.position = position;
}
}
