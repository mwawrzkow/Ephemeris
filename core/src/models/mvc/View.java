package models.mvc;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

import models.observersubject.ObserverSubject;
import models.observersubject.Subject;

public class View extends ObserverSubject {
	private Model model;

	public View() {
		model = new Model();
		this.AddObserver(model);
	}

	public void render(ModelBatch mb) {
		model.getCam().update();
		mb.begin(model.getCam());
		model.render(mb);
		mb.end();
	}

	@Override
	public void NotifyObserver(Subject s) {
		// TODO Auto-generated method stub

	}

	public PerspectiveCamera getCam() {
		return model.getCam();
	}
}
