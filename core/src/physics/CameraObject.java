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
	private Vector3 returnPosArroundObj(Vector3 posObject, Float angleDegrees, Float radius, Float height) {
	    Float angleRadians = angleDegrees * MathUtils.degreesToRadians;
	    Vector3 position = new Vector3();
	    position.set(radius * MathUtils.sin(angleRadians), height, radius * MathUtils.cos(angleRadians));
	    position.add(posObject); //add the position so it would be arround object
	    return position;
	}
	public void RotateAroundObject(ModelInstance modelInstance, float distance, Vector3 rot,float angle) {
//		this.cam.rotate(new Vector3(0,0,1), angle*Gdx.graphics.getDeltaTime());
		this.cam.position.idt(new Vector3(0,0,0));
		
//		this.cam.rotate(Vector3.Y,angle);
		this.cam.rotateAround(modelInstance.transform.getTranslation(new Vector3()), Vector3.Y, angle);
//		this.cam.rotateAround(modelInstance.transform.getTranslation(new Vector3()), Vector3.X, angle);
//		this.cam.rotateAround(modelInstance.transform.getTranslation(new Vector3()), Vector3.Z, angle);
		this.cam.lookAt(modelInstance.transform.getTranslation(new Vector3()));
	}
	public void FollowObject(ModelInstance modelInstance,float distance ) {
		Vector3 vec =  modelInstance.transform.getTranslation(new Vector3());
		this.cam.position.x = vec.x+distance;
		this.cam.position.y = vec.y+distance;
		this.cam.position.z = vec.z+distance;
		
		this.cam.lookAt(vec);
		this.cam.update();
//		this.cam.direction.x = vec.x;
//		this.cam.direction.y = vec.y;
//		this.cam.direction.z = vec.z;
		
		
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
