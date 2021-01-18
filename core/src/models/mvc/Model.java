package models.mvc;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.mwks.game.states.Input;
import com.mwks.game.states.Update;

import models.observersubject.ObserverSubject;
import models.observersubject.Subject;
import physics.PEngine;

public class Model extends ObserverSubject {
	private Input input; 
	private Update update;
	private PEngine engine;
	public PerspectiveCamera cam;
	public Model() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(25f, 25f, 25f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
		engine = new PEngine(); 
		this.input = new Input(engine); 
		update = new Update(); 
	}
	@Override
	public void NotifyObserver(Subject s) {
		// TODO Auto-generated method stub
		
	}
	public void render(ModelBatch mb) {
		for(ModelInstance modelInstance: engine.getPrimitives())
		 mb.render(modelInstance, engine.getEnvironment());
	}
	public PerspectiveCamera getCam() {
		return cam; 
	}

}
