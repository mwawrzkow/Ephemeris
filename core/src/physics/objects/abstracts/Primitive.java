package physics.objects.abstracts;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Primitive{
	public enum type{ 
		S, 
		D
	}
	private ModelInstance instance;
	public Primitive(ModelInstance i) { 
		instance = i; 
	}
 public ModelInstance getInstance() { 
	 return instance; 
 }
}
