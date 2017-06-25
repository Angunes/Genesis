package com.mygdx.game;

public class Bloco {
	public int type;
	public int ident;
	public int tip;
	public int lastType;
	
	public int posX;
	public int posY;
	
	public Bloco(int type, int ident) {
		super();
		this.type = type;
		this.ident = ident;
		this.tip = 0;
	}
	
	public void setTip(int v){
		tip = v;
	}
	public void setPosX(int x){
		posX = x;
	}
	public void setPosY(int y){
		posY = y;
	}
}
