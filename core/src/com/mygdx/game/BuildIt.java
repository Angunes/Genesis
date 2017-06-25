package com.mygdx.game;

public class BuildIt {
	
	public int hoe = 0;
	
	
	final public int bHor = 60;
	final public int bVer = 40;
	Bloco blocos[][] = new Bloco[bVer][bHor];
	
	public void dig(int x){
		
		for(int i=x;i<40;i++){
			blocos[hoe][i].type = 0;
			blocos[hoe][i].tip = x;
		}
		hoe++;
	}
	public void simpleDig(int x,int y){
		for(int i=x;i<40;i++){
			blocos[y][i].type = 0;
		}	  
	}
	
	public void growTerrain(int posx,int tipo){
		
	}
	
}
