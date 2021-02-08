package physics;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.mwks.game.loader3d.Loader3d;
import com.mwks.game.states.Input;

import physics.core.MathCalculus;
import physics.objects.DynamicObject;
import physics.objects.abstracts.Primitive;

public class PEngine {
	private class PhysicUpdate implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			while (true) {
				
				for (Primitive e : primitives)
					updatePrimitive(e);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CameraFollow();
			}
		}

		private void CameraFollow() {
			camInstance.FollowObject(primitives.get(camInstance.getFocusedObject()).getInstance(),
					camInstance.getDistance());
		}

		private void updatePrimitive(Primitive E) {
			if (E instanceof DynamicObject) {
				Vector3 force = createForceVector(((DynamicObject) E));
				(((DynamicObject) E)).setAcceleration(force);
				((DynamicObject) E).SymulatePhysics();
				((DynamicObject)E).rotateTowardsObject(primitives.get(0));	
			}
			if (E.equals(primitives.get(camInstance.getFocusedObject())))
				return;
			E.UpdatePosition();
			E.RespectRelativePosition(primitives.get(camInstance.getFocusedObject()));
		}

		private Vector3 createForceVector(DynamicObject o) {
			Vector3 pos0 = o.getLastPosition(), pos1 = primitives.get(0).getLastPosition();
			double distance = Math.sqrt(
					Math.pow(pos0.x - pos1.x, 2) + Math.pow(pos0.y - pos1.y, 2) + Math.pow(pos0.y - pos1.y, 2)) * 1e3;
			double mass_kg = primitives.get(0).getMass();
			double distancesquere = Math.pow(distance, 2);
			double oneDimAtt = (6.67430 * 1e-11) * mass_kg / distancesquere; // Force applied to vector
			Vector3 onesForceVector = MathCalculus.makeOnesVector(MathCalculus.findLineBetweenPoints(pos0, pos1));
			onesForceVector.x = (float) (onesForceVector.x * oneDimAtt);
			onesForceVector.y = (float) (onesForceVector.y * oneDimAtt);
			onesForceVector.z = (float) (onesForceVector.z * oneDimAtt);
			return onesForceVector;

		}

	}

	Thread physicUpdate;
	Environment environment;
	ArrayList<Primitive> primitives;
	Loader3d loader3d;
	CameraObject camInstance;

	public PEngine() {

		loader3d = new Loader3d();
		primitives = new ArrayList<Primitive>();
		initializeEnv();
		try {
			initializeArray();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void init(Input input, PerspectiveCamera Cam) {
		camInstance = new CameraObject(Cam, input);
		physicUpdate = new Thread(new PhysicUpdate());
		physicUpdate.start();
	}

	private void initializeEnv() {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.7f, 0.7f, 0.7f, -1f, -0.8f, -0.2f));
		environment.add(new DirectionalLight().set(0f, 0f, 0f, 0f, 0f, 0f));
	}

	private void initializeArray() throws IOException {
		primitives.add(loader3d.CreateObject("objs/Globe.obj"));
		for(int i = 0; i <= 10; i++)
		{
			primitives.add((DynamicObject) loader3d.CreateDynamicObject("objs/test/satellite_obj.obj"));
			((DynamicObject) primitives.get(primitives.size() - 1)).rotateTowardsObject(primitives.get(0));
			((DynamicObject) primitives.get(primitives.size() - 1)).moveToPosition(new Vector3(36000+(i *10), 0, 0), false);
		}
	}

	public ArrayList<ModelInstance> getModelInstances() {
		ArrayList<ModelInstance> modelInstance = new ArrayList<ModelInstance>();
		for (Primitive p : primitives)
			modelInstance.add(p.getInstance());
		return modelInstance;
	}

	public ArrayList<Primitive> getPrimitives() {
		return primitives;
	}

	public Environment getEnvironment() {
		return this.environment;
	}

	public CameraObject getCamera() {
		return camInstance;
	}
}
