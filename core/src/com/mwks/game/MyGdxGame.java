package com.mwks.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import models.mvc.View;
import models.observersubject.Observer;
import models.observersubject.Subject;

public class MyGdxGame extends ApplicationAdapter implements Observer {
    
    public Model model;
    public ModelInstance instance;
    public ModelBatch modelBatch;
    
    public int x= 0; 
    View view; 
    @Override
    public void create () {
    	modelBatch = new ModelBatch();
        view = new View(); 
    }

    @Override
    public void render () {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);       
        view.render(modelBatch);
    }

    @Override
    public void dispose () {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void resume () {
    }

    @Override
    public void resize (int width, int height) {
    	view.getCam().viewportHeight = height; 
    	view.getCam().viewportWidth = width;
    }

    @Override
    public void pause () {
    }

	@Override
	public void NotifyObserver(Subject s) {
		// TODO Auto-generated method stub
		
	}
}
