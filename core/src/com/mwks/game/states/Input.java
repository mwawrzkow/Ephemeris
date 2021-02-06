package com.mwks.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import physics.PEngine;

public class Input implements InputProcessor {
	PEngine engine;
	int FocusedObject = 0;
	float distance = 10; 
	Vector2 mousePos = new Vector2(); 
	boolean isRotateEnabled = false; 
	public Input(PEngine e) { 
		engine = e; 
	}
	public float getDistance() {
		return distance;
	}
	public int getFocusedObject() {
		return FocusedObject; 
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch(keycode) {
		case Keys.NUM_1:
			FocusedObject =0; 
			engine.getCamera().FollowObject(engine.getModelInstances().get(0),distance);
			return true; 
		case Keys.NUM_2:
			FocusedObject = 1; 
			engine.getCamera().FollowObject(engine.getModelInstances().get(1),distance);	
//			engine.getCamera().getCamera().lookAt(engine.getPrimitives().get(0).transform.getTranslation(new Vector3()));
			return true;
		}
		return false;
	}
	
	private void CameraFollow(ModelInstance modelInstance, float distance) { 
		
	}

	@Override
	public boolean keyUp(int keycode) {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.LEFT)
		{ 
			isRotateEnabled = !isRotateEnabled; 
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	private void rotateAroundObject(int screenX, int screenY) {
		Vector3 vec = new Vector3((mousePos.x - screenX)/Gdx.graphics.getWidth(), (mousePos.y - screenY)/Gdx.graphics.getHeight(), 0); 
		float angle = (float) Math.atan2((vec.x),vec.y) * MathUtils.radiansToDegrees/100; 
		engine.getCamera().getCamera().rotate(new Matrix4());;
		engine.getCamera().RotateAroundObject(engine.getModelInstances().get(FocusedObject),distance,vec ,angle  );
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		if(isRotateEnabled) {
			rotateAroundObject(screenX,screenY);
		}else {
		mousePos.x = screenX; 
		mousePos.y = screenY;
		} 

		return false;
	}
	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		distance += amountY* 100 * Gdx.graphics.getDeltaTime(); 
		this.engine.getCamera().FollowObject(engine.getModelInstances().get(FocusedObject), distance);
//		engine.getCamera().getCamera().lookAt(engine.getPrimitives().get(0).transform.getTranslation(new Vector3()));
		return false;
	}
}
