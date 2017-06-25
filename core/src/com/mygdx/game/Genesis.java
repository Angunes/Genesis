package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Genesis extends ApplicationAdapter {
	SpriteBatch batch;
	Texture b1,b2,b3,b4;
	Texture imgFundo;
	Texture detail1;
	Texture detail2;
	Texture imgCampeao;
	
	public boolean extension;
	Random gen = new Random();
	final public int largura = 20;
	final public int altura = 15;
	final public int bHor = 60;
	final public int bVer = 40;
	Bloco blocos[][] = new Bloco[bVer][bHor];
	public int hoe = 0;
	public int hoe2 = 0;
	Campeao campeao;
	
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

		imgCampeao = new Texture("obs1.png");
		
        
		campeao = new Campeao(imgCampeao);
		
		campeao.setPosition(0,0);
		
	    
		batch = new SpriteBatch();
		b1 = new Texture("b1.png");
		b2 = new Texture("b2.png");
		b3 = new Texture("b3.png");
		b4 = new Texture("b4.png");
		imgFundo = new Texture("field.png");
		detail1 = new Texture("detail1-S.png");
		detail2 = new Texture("detail2-S.png");
		for(int i=0;i<bVer;i++){
			for(int j=0;j<bHor;j++){
				blocos[i][j] = new Bloco(1,i+j);
				if(j!=0){
					blocos[i][j].lastType = blocos[i][j-1].type;
				}
				blocos[i][j].posX = i*20;
				blocos[i][j].posY = j*15;
				
				extension = gen.nextBoolean();
			}
		}
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(imgFundo, 0, 0);
		
		batch.draw(campeao.imgCampeao, 0, 0);
		campeao.setPosition(campeao.getX()+1, campeao.getY()+1);
		campeao.draw(batch);
		gravidade();
		gen();

		draw();
		batch.end();
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                campeao.translateX(-1f);
            else
            	campeao.translateX(-10.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
            	campeao.translateX(1f);
            else
            	campeao.translateX(10.0f);
        }
		/*
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			campeao.corpo.x -= 200 * Gdx.graphics.getDeltaTime();
			//correndo
			campeao.estado = 1;
		}
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)){
	    	campeao.corpo.x += 200 * Gdx.graphics.getDeltaTime();
	    	//correndo
	    	campeao.estado = 1;
	    }
	    if(Gdx.input.isKeyPressed(Keys.UP)){
	    	campeao.pular();
	    	//pulando
	    	campeao.estado = 2;
	    }*/
	    
	    
		
	}
	
	@Override
	public void dispose () {
		imgCampeao.dispose();
		batch.dispose();
		b1.dispose();
		b2.dispose();
		b3.dispose();
		b4.dispose();
	}
	
	public void gravidade(){
		//if(campeao.corpo.y > altura){
			//campeao.corpo.y = 50*Gdx.graphics.getDeltaTime();
		//}
	}
	
	public void dig(int x){
		
		for(int i=x;i<40;i++){
			blocos[hoe][i].type = 0;
			blocos[hoe][i].tip = x;
		}
		hoe++;
	}
	
	public void simpleDig(int x,int y){//coluna desejada e apartir de qual bloco 
		for(int i=x;i<40;i++){
			blocos[y][i].type = 0;
		}	  
	}
	
	public void weed(){
		dig(gen.nextInt(20)+1);
	}
	
	public void draw(){
		for(int i=0;i<bVer;i++){
			for(int j=0;j<bHor;j++){
				if(blocos[i][j].type==1){
					batch.draw(b1, blocos[i][j].posX, blocos[i][j].posY, largura, altura);
				}
				if(blocos[i][j].type==2){
					batch.draw(b2, blocos[i][j].posX, blocos[i][j].posY, largura, altura);
				}
				if(blocos[i][j].type==3){
					batch.draw(b3, blocos[i][j].posX, blocos[i][j].posY, largura, altura);
				}
				if(blocos[i][j].type==4){
					batch.draw(b4, blocos[i][j].posX, blocos[i][j].posY, largura, altura);
				}
			}
		}
	}
	
	public void road(){//problema
		for(int i=0;i<bVer;i++){
			for(int j=0;j<bHor;j++){
				if(blocos[i][j].type==2){
					if( gen.nextBoolean()){
						batch.draw(detail1, blocos[i][j].posX, blocos[i][j].posY, blocos[i][j].posX, blocos[i][j].posX, largura, altura);	
						j = bHor;
					}
					else{
						batch.draw(detail2, blocos[i][j].posX, blocos[i][j].posY, blocos[i][j].posX, blocos[i][j].posX, largura, altura);	
						j = bHor;
					}
				}
			}
		}
	}
	public void setMoss(){
		int x=bVer;
		
		for(int i=bHor;i!=0;i--){
			while(blocos[i][x].type == 0 || blocos[i][x].type == 1){
				
				x--;
			}
		}
	}
	

	public void gen(){
		//coloca o relevo
		Random bioma = new Random();
		Random tamanho = new Random();
		int comparador;
		int gerado = 0;
		int tam = 0;
		int ant = 0;
		
		if(hoe<40){
		cut();
		
		}
		if(hoe2==1){
			do{
				tam += tamanho.nextInt(7) + 1; //gera um numero aleatorio de 1 a 10 para ser o tamanho do bioma
				comparador = bioma.nextInt(4); // gera um numero de 0 a 3 para determinar qual sera o bioma
					if( comparador == 0){ //se for o bioma 0
						for(int i = ant; i <tam; i++){
							putTerrain(i,1);
							
							ant = tam;
							
						}
					}
					if( comparador == 1){//igual o if interior
						for(int i = ant; i <tam; i++){
							putTerrain(i,2);
							
							ant = tam;
							
						}
					}
					if( comparador == 2){
						for(int i = ant; i <tam; i++){
							putTerrain(i,3);
							
							ant = tam;
						}
					}
					if( comparador == 3){
						for(int i = ant; i <tam; i++){
							putTerrain(i,4);
							
							ant = tam;
							
						}
					}
				}while(tam < 30); //enquanto n�o preencher todo o mapa

			hoe2++;
		}
		if(hoe2 == 2){
			//putHole(gen.nextInt(30)+1,3);
			hoe2++;
		}
		if(hoe2 == 3){
			setCliff(22,17);
			hoe2++;
		}
		//coloca os detalhes nos blocos caminhaveis
/*		if(hoe==40){
			road();
		}*/
		//montanhas
		if(hoe==40){
			//cliffs();
			//changeTerrain();
			hoe++;
			hoe2=1;
		}
		
	}
	
	public void cut(){
		dig(10);
	}
	
	public void putTerrain(int y,int t){//coluna desejada e apartir de qual bloco 
		/*int aux = 0;
		aux = bioma(y);*/
		for(int i=0;i<10;i++){
			blocos[y][i].type = t;
		}
	}
	/* fun��o que coloca aleatoriamente buracos no cenario  */
	public void hole(){
		int nHoles=0;
		int tam=0;
		nHoles = gen.nextInt(3);
		tam = gen.nextInt(5)+1;
		
		if(nHoles == 2){
			for(int i=gen.nextInt(30);i<tam;i++){
				
			}
		}
	}
	public void putHole(int y,int t){//coluna desejada e apartir de qual bloco 
		/*int aux = 0;
		aux = bioma(y);*/
		for(int j=0;j<t;j++){
			for(int i=0;i<10;i++){
				blocos[y+j][i].type = 0;
			}	
		}
		
	}
	
	public void setCliff(int top,int col){//altura do topo e coluna em que se encontra
		System.out.println("topo em : "+col);
/*		      subindo montanha                  */
		for(int j=col-(top-9);j<=top;j++){
			System.out.println("subida em : "+j);
			for(int i=0;i<j;i++){
				blocos[j][i].type = blocos[j][0].type;
			}
		}
/*           topo                               */
		for(int i=0;i<=top;i++){
			blocos[col][i].type = blocos[col][0].type;
		}
/*           descendo                           */
		for(int j=col+(top-10);j>col;j--){

			for(int i=0;i<top-(j-col)+1;i++){
				System.out.println("relevo : "+i);
				blocos[j][i].type = blocos[j][0].type;
			}
		}
	}
	
	
}
