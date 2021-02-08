package physics.objects.abstracts;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Primitive {
	float mass = (float) (5.972 * 1e24);
	private Vector3 position;
	protected Vector3 relativePositon;
	protected ModelInstance instance;

	public enum type {
		S, D
	}

	public Primitive(ModelInstance i) {
		instance = i;
		relativePositon = new Vector3();
		position = new Vector3();
		setPosition(getInstance().transform.getTranslation(new Vector3()), false);
	}

	public ModelInstance getInstance() {
		return instance;
	}

	public Vector3 getLastPosition() {
		return getPosition(false);
	}

	public float getMass() {
		return mass;
	}

	public void UpdatePosition() {
		getInstance().transform.set(this.relativePositon, new Quaternion());
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public Vector3 getPosition(boolean Relative) {
		return (Relative) ? relativePositon : position;
	}

	public void setPosition(Vector3 position, boolean Relative) {
		if (Relative) {
			this.relativePositon = position;

		} else {
			this.position = position;
		}
	}

	public void setRelativePosition(Vector3 position) {
		setPosition(position, true);
	}

	public void setAbsolutePosition(Vector3 position) {
		setPosition(position, false);
	}

	public void RespectRelativePosition(Primitive position) {
		this.setPosition(new Vector3(
				this.position.x - position.getPosition(false).x,
				this.position.y - position.getPosition(false).y,
				this.position.y - position.getPosition(false).z),
				true);
		this.UpdatePosition();
	}
}
