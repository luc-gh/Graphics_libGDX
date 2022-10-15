package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;  //biblioteca interna de libGDX que possui funções úteis de matemática

/*
* O código anterior usa o método shapeRenderer.begin() para instruir ao libGDX (e à OpenGL) sobre a categoria de desenho à ser
* construído (no caso, foi um círculo preenchido [filled]) e usa o método shapeRenderer.end() para garantir que o desenho
* produzido foi renderizado corretamente. Porém, para vários desenhos, a eficiência do código pode ser baixa:
*/

public class Graphics extends ApplicationAdapter {  //Main class (classe principal)
	ShapeRenderer shapeRenderer;

	@Override
	public void create(){
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(.25f,.25f,.25f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for(int i = 0; i < 10; i++) {  //loop (10 vezes): para criar 10 círculos de cores e posições diferentes
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);  //gera cor aleatória
			//Gera largura e altura aleatórias:
			shapeRenderer.circle(MathUtils.random(Gdx.graphics.getWidth()), MathUtils.random(Gdx.graphics.getHeight()), 70);
			shapeRenderer.end();
		}

		System.out.println(Gdx.graphics.getFramesPerSecond());  //Imprime a taxa de frames por segundo
	}

	//OBS.: caso seu pc tenha um desempenho maior, você pode gerar muito mais círculos (o teste original criava 10k por frame)

	@Override
	public void dispose(){
		shapeRenderer.dispose();
	}
}
