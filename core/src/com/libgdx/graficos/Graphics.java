package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;  //Biblioteca necessária para receber a entrada de usuário
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/*
* O movimento de câmera (ou do plano de tela do jogo) pode ser feito conforme os desenhos criados na tela, fazendo estes se
* moverem de maneira lógica. Suponha que desejamos um movimento de tela como o do mundo do Super Mario.
* Para criar um movimento assim, basta manipular as posições dos desenhos alterando suas coordenadas:
*/

public class Graphics extends ApplicationAdapter {  //Main class (classe principal)
	ShapeRenderer shapeRenderer;

	float infEsqX = 0, infEsqY = 0, largRet, altRet, velocidade = 100f;  //variáveis

	@Override
	public void create(){
		shapeRenderer = new ShapeRenderer();
		largRet = Gdx.graphics.getWidth() / 2f;  //Definição da largura padrão dos retângulos
		altRet = Gdx.graphics.getHeight() / 2f;  //Definição da altura padrão dos retângulos
	}

	@Override
	public void render(){
		if(Gdx.input.isKeyPressed(Input.Keys.W)){  //tecla W
			infEsqY += velocidade * Gdx.graphics.getDeltaTime();  //move os retângulos p/ cima (reduz as coordenadas de Y)
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.S)){  //tecla S
			infEsqY -= velocidade * Gdx.graphics.getDeltaTime();  //move os retângulos p/ baixo (aumenta as coord. de Y)
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){  //tecla D
			infEsqX += velocidade * Gdx.graphics.getDeltaTime();  //move os retângulos p/ direita (reduz as coord. de X)
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.A)){  //tecla A
			infEsqX -= velocidade * Gdx.graphics.getDeltaTime();  //move os retângulos p/ esquerda (aumenta as coord. de X)
		}

		Gdx.gl.glClearColor(.25f,.25f,.25f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Criação dos retângulos (as coordenadas são redefinidas a cada frame [porque a função render faz exatamente isso])
		/*
		* Define-se uma cor (setColor) e depois são definidos:
		* coordenada X, coordenada Y, largura e altura
		*/
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
