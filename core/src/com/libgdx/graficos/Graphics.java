package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

/*
* O código anterior trabalha com o conceito de 'coordenadas por píxeis', trabalhando com unidades e direções baseadas neles.
* Entretanto, neste código, o conceito usado será o de "coordenadas globais": se define um ponto de origem com base em
* qualquer referência da tela, como, por exemplo, o ponto de início de um jogador; além disso, as unidades passam a ser
* baseadas quaisquer bases, como metros, pés, píxeis ou até não possuírem base definida.
* Este código cria uma câmera cuja janela de exibição tem dimensões 200x100x0, como ponto de origem (0,0,0), e cria retângulos
* de dimensões 100x50 em unidades globais, relativas ao ponto de origem:
*/

public class Graphics extends ApplicationAdapter {  //Main class (classe principal)
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;

    //variáveis
	float largRet = 100f, altRet = 50f, vel, zVel, rVel, velocidade = 100f, zoomVel = 1f, rotacaoVel = 20f;

	@Override
	public void create(){
		camera = new OrthographicCamera(200, 100);  //parâmetros: viewportWidth, viewportHeight
		camera.position.set(0,0,0);  //parâmetros: posições x, y, z
		camera.update();

		shapeRenderer = new ShapeRenderer();
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
            camera.zoom += zVel;  //afastamento  (-zoom)
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_ADD)){   // MAIS (+) do teclado numérico
            camera.zoom -= zVel;  //aproximação  (+zoom)
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
        Com o movimento sendo proporcionado tendo como base coordenadas globais, a rotação de câmera perde a referência
        dos eixos, deformando os retângulos ao girá-los. Dependendo da aplicação, manter o sistema anterior ou aplicar
        este pode fazer a diferença.
        */

		Gdx.gl.glClearColor(.25f,.25f,.25f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);

		//Agora as referências mudam:
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0,0,1,1);
		shapeRenderer.rect(-largRet, -altRet, largRet, altRet*1);  //(x,y,largura,altura)
		shapeRenderer.setColor(1,0,0,1);
		shapeRenderer.rect(0, -altRet, largRet, altRet);
		shapeRenderer.setColor(0,1,0,1);
		shapeRenderer.rect(0, 0, largRet, altRet);
		shapeRenderer.setColor(1,1,0,1);
		shapeRenderer.rect(-largRet, 0, largRet, altRet);
		shapeRenderer.end();
	}

	@Override
	public void dispose(){
		shapeRenderer.dispose();
	}
}
