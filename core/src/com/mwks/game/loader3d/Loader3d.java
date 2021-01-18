package com.mwks.game.loader3d;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

import physics.objects.abstracts.Primitive;

public class Loader3d {
	ModelLoader loader; 
	public Loader3d() { 
		loader = new ObjLoader();
	}
	public Primitive CreateObject(String location) throws IOException {
		 
		//~~~~ objs/test/satellite_obj.obj
		File file = new File(ClassLoader.getSystemResource(location).getFile());
		Model model = loader.loadModel(new FileHandle(file));
		Primitive e = new Primitive(new ModelInstance(model));
		return e; 
		 
	}
}
