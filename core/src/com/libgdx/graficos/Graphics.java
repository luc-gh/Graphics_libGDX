package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;    //Classe que facilita manipulações de câmera

/*
* Em cenários simples, o código anterior pode ser útil, mas em situações complexas, é recomendável utilizar os recursos da
* classe Camera e suas subclasses OrthographicCamera e PerspectiveCamera, que permitem uma manipulação de cena conforme os
* desenhos. Este código é uma réplica do anterior, porém utilizando tais recursos:
*/

public class Graphics extends ApplicationAdapter {  //Main class (classe principal)
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;  //variável de referência para as manipulações com a subclasse

	float infEsqX = 0, infEsqY = 0, largRet, altRet, velocidade = 100f, vel;  //variáveis

	@Override
	public void create(){
		//Cria uma nova instância da subclasse com as dimensões da janela de exibição, especificadas:
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());  //os parâmetros são as dimensões

		//Define a posição da câmera como o centro da cena. Para calcular o ponto, poderiam ser usadas quaisquer coordenadas.
		camera.position.set(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f,0);  //eixos x, y e z (não há)

		//Calcula tudo o que for necessário diante das especificações definidas (nesse caso, dimensões e posição):
		camera.update();

		shapeRenderer = new ShapeRenderer();
		largRet = Gdx.graphics.getWidth() / 2f;  //Definição da largura padrão dos retângulos
		altRet = Gdx.graphics.getHeight() / 2f;  //Definição da altura padrão dos retângulos
	}

	@Override
	public void render(){
		vel = velocidade * Gdx.graphics.getDeltaTime();  //Definição da velocidade baseada no frame rate (p/ facilitar)

		//O método translate pode mover a câmera de tela. No caso, será movida para cima, baixo, direita e esquerda.
		//Porém, é possível utilizar outras referências para mover a câmera, com a posição de um jogador.

		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){  //tecla W ou CIMA
			camera.translate(0,-vel);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){  //tecla S ou BAIXO
			camera.translate(0,vel);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){  //tecla D ou DIREITA
			camera.translate(-vel,0);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){  //tecla A ou ESQUERDA
			camera.translate(vel,0);
		}

		Gdx.gl.glClearColor(.25f,.25f,.25f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		//O comando a seguir define que shapeRenderer usará as definições de Camera para desenhar tudo
		shapeRenderer.setProjectionMatrix(camera.combined);  //o argumento passado é uma Matrix4, que representa a câmera

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);  //desenhos preenchidos
		shapeRenderer.setColor(0,0,1,1);  //azul
		shapeRenderer.rect(infEsqX, infEsqY, largRet, altRet);  //retângulo criado na posição inferior esquerda (0,0)
		shapeRenderer.setColor(1,0,0,1);  //vermelho
		shapeRenderer.rect(infEsqX + largRet, infEsqY, largRet, altRet);  //criado na posição superior esquerda
		shapeRenderer.setColor(0,1,0,1);  //verde
		shapeRenderer.rect(infEsqX + largRet, infEsqY + altRet, largRet, altRet);  //posição superior direita
		shapeRenderer.setColor(1,1,0,1);  //amarelo
		shapeRenderer.rect(infEsqX, infEsqY + altRet, largRet, altRet);  //posição inferior direita
		shapeRenderer.end();
	}

	@Override
	public void dispose(){
		shapeRenderer.dispose();
	}
}
