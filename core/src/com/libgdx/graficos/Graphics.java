package com.libgdx.graficos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

//Como vsto noutros códigos, é possível criar jogos com gráficos simples com libGDX.
//Mas o framework é mais poderoso que isso, utilizando métodos e funções para gráficos mais avançados, com OpenGL.
/*
* OpenGL (Open Graphics Library) é uma especificação API (conjuntos de funções a serem chamadas) que implementa a habilidade
* de desenhar na tela com marcações gráficas, o que permite desenhar cenas mais complexas a cada frame, de maneira 'simples'.
*
* Esse código usa a variável Gdx.gl para chamar a função glClearColor(), que define uma cor de fundo, e a função gl.Clear(),
* que realmente desenha o plano de fundo com a cor definida com a função anterior. São funções da classe GL20, muito útil
* para fazer chamadas de funções de baixo nível de OpenGL.
*/

public class Graphics extends ApplicationAdapter {  //Main class (classe principal)
	@Override
	public void render(){  //Função (da classe principal) responsável pela renderização da tela
		Gdx.gl.glClearColor(.25f,.25f,.25f,1);  //RGBA (valores tipo float, de 0 a 1)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  //Máscara Buffer-Bit (0x00004000), da classe GL20
	}
}
