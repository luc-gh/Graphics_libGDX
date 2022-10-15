package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;  //instância da classe graphics para as funções de ShapeRenderer

/*
* O contexto OpenGL é útil para trabalhar com os desenhos de tela píxel a píxel, mas para uma maior eficiência, em
* alguns casos é mais fácil trabalhar com desenhos em alto nível: retângulos e círculos já predefinidos.
* A função ShapeRenderer permite essa abordagem.
*/

public class Graphics extends ApplicationAdapter {  //Main class (classe principal)
	ShapeRenderer shapeRenderer;  //instância da função para chamadas

	@Override
	public void create(){  //Função (da classe principal) responsável pelas definições e ajustes padrões da aplicação
		shapeRenderer = new ShapeRenderer();  //construtor da função nesta classe (para a aplicação)
	}

	@Override
	public void render(){  //Função (da classe principal) responsável pela renderização da tela
		Gdx.gl.glClearColor(.25f,.25f,.25f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);  //inicialização de desenho (shape) com tipo Filled (preenchido)
		shapeRenderer.setColor(0,1,0,1);   //definição da cor do desenho, que será verde
		shapeRenderer.circle(200,100,75);  //definição do formato do desenho, que será um círculo
		shapeRenderer.end();  //finalização de construção do desenho
	}

	@Override
	public void dispose(){  //Função responsável pela desconstrução (ou fechamento) dos elementos renderizados na tela
		shapeRenderer.dispose();  //desconstrução dos desenhos presentes em tela
	}
}
