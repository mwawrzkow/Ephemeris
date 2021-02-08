package physics;

import javax.swing.text.Position;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController.Transform;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mwks.game.states.Input;

public class CameraObject {
	PerspectiveCamera cam;
	Input input;

	public CameraObject(PerspectiveCamera cam, Input input) {
		this.cam = cam;
		this.input = input;
	}

	public void FocusOnObject(ModelInstance mi) {
		this.getCamera().lookAt(mi.transform.getTranslation(new Vector3()));

	}

	public void RotateAroundObject(ModelInstance modelInstance, float distance, double phi, double Theta) {
		this.cam.position.idt(new Vector3(0, 0, 0));
		float x = (float) (distance * Math.cos(phi) * Math.sin(Theta));
		float y = (float) (distance * Math.sin(phi) * Math.sin(Theta));
		float z = (float) (distance * Math.cos(Theta));
		this.cam.position.x = x;
		this.cam.position.y = y;
		this.cam.position.z = z;
		this.cam.lookAt(modelInstance.transform.getTranslation(new Vector3()));
	}

	public void FollowObject(ModelInstance modelInstance, float distance) {
		Vector3 vec = modelInstance.transform.getTranslation(new Vector3());
		this.cam.position.x = vec.x + distance;
		this.cam.position.y = vec.y + distance;
		this.cam.position.z = vec.z + distance;
		this.cam.lookAt(vec);
		this.cam.update();
	}

	public PerspectiveCamera getCamera() {
		return cam;
	}

	public int getFocusedObject() {
		return input.getFocusedObject();
	}

	public float getDistance() {
		return input.getDistance();
	}

}
