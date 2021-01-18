package physics;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.mwks.game.loader3d.Loader3d;

import physics.objects.abstracts.Primitive;

public class PEngine {
	Environment environment;
	ArrayList<Primitive> primitives;
	Loader3d loader3d;
	private class PhysicUpdate implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(Primitive e: primitives)
				updatePrimitive(e);
		} 
		 
	}
	private void updatePrimitive(Primitive E) { 
		
	}
	public PEngine() {
		loader3d = new Loader3d(); 
		 primitives = new ArrayList<Primitive>();
		 initializeEnv();
		 try {
		 initializeArray();
		 }catch (IOException ioe) {
			 ioe.printStackTrace();
		 }
	}
	
	private void initializeEnv() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.7f, 0.7f, 0.7f, -1f, -0.8f, -0.2f));
        environment.add(new DirectionalLight().set(-0.7f, -0.7f, -0.7f, 1f, 0.8f, 0.2f));
	}
	private void initializeArray() throws IOException{ 
		primitives.add(loader3d.CreateObject("objs/test/satellite_obj.obj"));
	}
	public ArrayList<ModelInstance>getPrimitives(){
		ArrayList<ModelInstance> modelInstance = new ArrayList<ModelInstance>();
		for(Primitive p: primitives)
			modelInstance.add(p.getInstance());
		return modelInstance; 
		
		
	}
	public Environment getEnvironment() { 
		return this.environment; 
	}
}
