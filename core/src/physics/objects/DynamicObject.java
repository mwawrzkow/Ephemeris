package physics.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import physics.objects.abstracts.Primitive;

public class DynamicObject extends Primitive{
	private Vector3 acceleration = new Vector3(0,0,0); 
	public DynamicObject(ModelInstance i) {
		super(i);
		setPosition(new Vector3(0,0,0));
		setMass((float) (250));
		// TODO Auto-generated constructor stub
	}
	 public void rotateTowardsObject(Primitive e) {
		 Vector3 vel = e.getInstance().transform.getTranslation(new Vector3());
		 float angle = MathUtils.atan2(vel.x, vel.z) * MathUtils.radiansToDegrees;
		 getInstance().transform.setToRotation(Vector3.Y, angle);
	 }
	 public void setPositiontoZero() {
		 getInstance().transform.translate(new Vector3(0,0,0));
		 super.setPosition(getInstance().transform.getTranslation(new Vector3()));
	 }
	 public void moveToPosition(Vector3 vec)
	 {
		 setPosition(vec);
		 getInstance().transform.translate(vec);
	 }
	 public void SymulatePhysics()
	 { 
		 Vector3 pos = getPosition();
		 pos.x += acceleration.x;
		 pos.y += acceleration.y;
		 pos.z += acceleration.z;
		 moveToPosition(pos); 
		 
	 }
	 public Vector3 getAcceleration() { 
		 return acceleration; 
	 }
	 public void setAcceleration(Vector3 acc) {
		 this.acceleration = acc; 
	 }
}
