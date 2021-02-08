package physics.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import physics.objects.abstracts.Primitive;

public class DynamicObject extends Primitive {
	private Vector3 acceleration = new Vector3(0, 0, 0);

	public DynamicObject(ModelInstance i) {
		super(i);
		moveToReallPosition(new Vector3(0, 0, 0));
		setMass((float) (25000));
	}

	public void rotateTowardsObject(Primitive e) {
		Vector3 vel = e.getInstance().transform.getTranslation(new Vector3());
		float angle = MathUtils.atan2(vel.x, vel.z) * MathUtils.radiansToDegrees;
		getInstance().transform.setToRotation(Vector3.Y, angle);
	}

	public void setAbsolutePositiontoZero() {
		getInstance().transform.translate(new Vector3(0, 0, 0));
		super.setAbsolutePosition(getInstance().transform.getTranslation(new Vector3()));
	}

	public void setRelativePositoZero() {
		getInstance().transform.translate(new Vector3(0, 0, 0));
		super.setRelativePosition(getInstance().transform.getTranslation(new Vector3()));
	}

	public void moveToPosition(Vector3 vec, boolean relative) {
		if (!relative)
			setAbsolutePosition(vec);
		else
			setRelativePosition(vec);
	}

	public void moveToReallPosition(Vector3 vec) {
		setAbsolutePosition(vec);
	}

	public void SymulatePhysics() {
		Vector3 pos = getPosition(false);
		pos.x += acceleration.x * 100.0f;
		pos.y += acceleration.y;
		pos.z += acceleration.z;
		moveToPosition(pos, false);

	}

	public Vector3 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector3 acc) {
		this.acceleration = acc;
	}
}
