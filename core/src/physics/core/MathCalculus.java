package physics.core;

import com.badlogic.gdx.math.Vector3;

public class MathCalculus {
	static public Vector3 findLineBetweenPoints(Vector3 p0, Vector3 p1)
	{ 
		Vector3 res = new Vector3(p1.x-p0.x, p1.y-p0.y, p1.z-p0.z);
		return res; 
		
	}
	static public Vector3 makeOnesVector(Vector3 vec)
	{
		Vector3 res; 
		res = new Vector3(); 
		float max=  vec.x + vec.y+ vec.z; 
		// x+y+z = 100%		
		res.x = (float) (vec.x/max); 
		res.y = (float) (vec.y/max); 
		res.z = (float) (vec.z/max); 
		return res; 
	}
}
