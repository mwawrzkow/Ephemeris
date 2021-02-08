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
	private PEngine engine;
	public PerspectiveCamera cam;

	public Model() {
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		cam.position.set(150f, 150f, 0f);
		cam.lookAt(0, 0, 0);
		cam.near = 10f;
		cam.far = 300000f;
		cam.update();

		engine = new PEngine();
		this.input = new Input(engine);
		engine.init(input, cam);
		Gdx.input.setInputProcessor(this.input);
	}

	@Override
	public void NotifyObserver(Subject s) {

	}

	public void render(ModelBatch mb) {
		for (ModelInstance modelInstance : engine.getModelInstances())
			mb.render(modelInstance, engine.getEnvironment());
	}

	public PerspectiveCamera getCam() {
		return cam;
	}

}
