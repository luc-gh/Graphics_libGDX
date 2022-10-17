package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

/*
* A classe Camera também oferece funcionalidades mais avançadas de manipulação de tela, com zoom e rotação:
*/

public class Graphics extends ApplicationAdapter {  //Main class (classe principal)
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;

    //variáveis
	float infEsqX = 0, infEsqY = 0, largRet, altRet, velocidade = 100f, vel, zVel, rVel;
    float zoomVel = 1f, rotacaoVel = 20f;

	@Override
	public void create(){
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());  //os parâmetros são as dimensões
		camera.position.set(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f,0);  //posição inicial
		camera.update();

		shapeRenderer = new ShapeRenderer();
		largRet = Gdx.graphics.getWidth() / 2f;
		altRet = Gdx.graphics.getHeight() / 2f;
	}

	@Override
	public void render(){
		vel = velocidade * Gdx.graphics.getDeltaTime();
        zVel = zoomVel * Gdx.graphics.getDeltaTime();
        rVel = rotacaoVel * Gdx.graphics.getDeltaTime();
        //OBS.: as atribuições acima devem ocorrer aqui para que seu efeito seja efetivo a cada frame

        //Move a câmera para cima ou para baixo:
		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
			camera.translate(0,-vel);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			camera.translate(0,vel);
		}

        //Move a câmera para esquerda ou direita:
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			camera.translate(-vel,0);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			camera.translate(vel,0);
		}

        //Ampliação ou redução de câmera (zoom):
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_SUBTRACT)){  // MENOS (-) do teclado numérico
            camera.zoom -= zVel;  //afastamento  (-zoom)
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_ADD)){   // MAIS (+) do teclado numérico
            camera.zoom += zVel;  //aproximação  (+zoom)
        }

        //Rotação de câmera:
        if(Gdx.input.isKeyPressed(Input.Keys.J)){  //tecla J
            camera.rotate(-rVel);   //sentido horário (clockwise)
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.F)){  //tecla F
            camera.rotate(rVel);  //sentido anti-horário (counterclockwise)
        }

        /*
        OBS.:
        O movimento de câmera é afetado pelos efeitos de zoom e rotação, isto é, ao girar a câmera, por exemplo, gira-se
        também os eixos x, y e z, que são as referências da câmera, e com isso, ao mover os objetos para 'cima', eles
        irão movimentar-se em direção ao topo do eixo y, e como este está deslocado, o movimento parecerá estranho.
        */

		Gdx.gl.glClearColor(.25f,.25f,.25f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);  //o argumento passado é a câmera

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0,0,1,1);
		shapeRenderer.rect(infEsqX, infEsqY, largRet, altRet);  //retângulo criado na posição inferior esquerda (0,0)
		shapeRenderer.setColor(1,0,0,1);
		shapeRenderer.rect(infEsqX + largRet, infEsqY, largRet, altRet);  //criado na posição superior esquerda
		shapeRenderer.setColor(0,1,0,1);
		shapeRenderer.rect(infEsqX + largRet, infEsqY + altRet, largRet, altRet);  //posição superior direita
		shapeRenderer.setColor(1,1,0,1);
		shapeRenderer.rect(infEsqX, infEsqY + altRet, largRet, altRet);  //posição inferior direita
		shapeRenderer.end();
	}

	@Override
	public void dispose(){
		shapeRenderer.dispose();
	}
}
