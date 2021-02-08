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
import physics.objects.abstracts.Primitive;

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
		switch (keycode) {
		case Keys.NUM_1:
			CameraFollow(0, engine.getPrimitives().get(0), distance);
			return true;
		case Keys.NUM_2:
			CameraFollow(1, engine.getPrimitives().get(1), distance);
			return true;
//			1	
		case Keys.CONTROL_LEFT:
			FocusedObject = 0;
			engine.getCamera().FocusOnObject(engine.getModelInstances().get(0));
			return true;
		}
		return false;
	}

	private void CameraFollow(int idx, Primitive primitive, float distance) {
		FocusedObject = idx;
		primitive.setPosition(new Vector3(), true);
		primitive.UpdatePosition();
		engine.getCamera().FollowObject(primitive.getInstance(), distance);
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) {
			isRotateEnabled = !isRotateEnabled;
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	private void rotateAroundObject(int screenX, int screenY) {
		Vector3 vec = new Vector3((mousePos.x - screenX) / Gdx.graphics.getWidth(),
				(mousePos.y - screenY) / Gdx.graphics.getHeight(), 0);
		float angle = (float) Math.atan2((vec.x), vec.y) * MathUtils.radiansToDegrees / 100;
		engine.getCamera().getCamera().rotate(new Matrix4());

		engine.getCamera().RotateAroundObject(engine.getModelInstances().get(FocusedObject), distance,
				calculatePhi((Gdx.graphics.getWidth() / 2 - screenX)),
				calculateTheta((Gdx.graphics.getHeight() / 2 - screenY)));
		engine.getCamera().getCamera().update();
	}

	private double calculatePhi(double x) {
		x /= -Gdx.graphics.getWidth();
		x -= 0.5;
		return (x * Math.PI);
	}

	private double calculateTheta(double y) {
		y /= -Gdx.graphics.getHeight();
		y -= 0.5;
		return (y * Math.PI);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if (isRotateEnabled) {
			rotateAroundObject(screenX, screenY);
		} else {
			mousePos.x = screenX;
			mousePos.y = screenY;
		}

		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		distance += amountY * 100 * Gdx.graphics.getDeltaTime();
		this.engine.getCamera().FollowObject(engine.getModelInstances().get(FocusedObject), distance);
		return false;
	}
}
