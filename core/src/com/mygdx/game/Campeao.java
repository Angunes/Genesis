package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Campeao extends Sprite{
	public Texture imgCampeao;
	public int estado;
	
	Campeao(Texture t){
		imgCampeao = t;
		super.setTexture(t);
	}
	
	public void pular(){
		//corpo.y += 200*Gdx.graphics.getDeltaTime();
	
	}
}