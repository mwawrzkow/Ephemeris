package com.mwks.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
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
			engine.getCamera().FollowObject(engine.getPrimitives().get(0),distance);
			return true; 
		case Keys.NUM_2:
			FocusedObject = 1; 
			engine.getCamera().FollowObject(engine.getPrimitives().get(1),distance);			
			return true;
		}
		return false;
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

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		if(isRotateEnabled) {
			Vector3 vec = new Vector3((mousePos.x - screenX)/Gdx.graphics.getWidth(), (mousePos.y - screenY)/Gdx.graphics.getHeight(), 0); 
			float angle = (float) Math.atan2((vec.x),vec.y) * MathUtils.radiansToDegrees/100; 
			engine.getCamera().RotateAroundObject(engine.getPrimitives().get(FocusedObject),distance,vec ,angle  );
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
		this.engine.getCamera().FollowObject(engine.getPrimitives().get(FocusedObject), distance);
		return false;
	}
}
