package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;  //biblioteca interna de libGDX que possui funções úteis de matemática

/*
* Apesar do código anterior aparentar ser ineficiente, basta uma simples modificação para alterar as coisas:
* Com o conceito de agrupamento (Batching), pode-se agrupar chamadas semelhantes juntas, para ter maior eficiência.
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

		//Para agrupar de maneira mais organizada, basta uma alteração singela:

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for(int i = 0; i < 10; i++) {  //o loop agora só altera os parâmetros do desenho, ao invés de recriá-los várias vezes
			shapeRenderer.setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);  //gera cor aleatória
			//Gera largura e altura aleatórias:
			shapeRenderer.circle(MathUtils.random(Gdx.graphics.getWidth()), MathUtils.random(Gdx.graphics.getHeight()), 70);
		}
		shapeRenderer.end();  //todos os desenhos são renderizados de uma só vez, juntos

		System.out.println(Gdx.graphics.getFramesPerSecond());  //Imprime a taxa de frames por segundo
	}

	//OBS.: caso seu pc tenha um desempenho maior, você pode gerar muito mais círculos (o teste original criava 10k por frame)

	@Override
	public void dispose(){
		shapeRenderer.dispose();
	}
}
