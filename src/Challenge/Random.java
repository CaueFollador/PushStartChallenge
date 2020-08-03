package Challenge;

import java.util.List;

public class Random {

	/*==M�TODO RECEBE A LISTA DE PE�AS E O TABULEIRO PARA FAZER A MONTAGEM==*/
	public int[][] resolucao(List<Peca> lista, int[][] tabuleiro) {
		int contador = 0;
		
		/*Enquanto a lista n�o estiver vazia e o contador de tentativas n�o atingir 50...*/
		while (!lista.isEmpty() && contador != 50) {
			contador += 1;
			
			/*...O sistema sorteia uma pe�a da lista para transformar em matriz e encaixar no tabuleiro*/
			int randomPeca = new java.util.Random().nextInt(lista.size());
			Peca peca = lista.get(randomPeca);
			int[][] pecaNova = new int[peca.altura][peca.largura];
			boolean pecaColocada = false;

			/*--Valida��o para encaixar a pe�a--*/
			while (!pecaColocada) {
				for (int i = 0; i < tabuleiro.length; i++) {
					if (pecaColocada) {
						break;
					}
					for (int j = 0; j < tabuleiro[i].length; j++) {
						if (checaCasaDisponivel(i, j, tabuleiro, pecaNova)) {
							tabuleiro = addPeca(i, j, peca.id, tabuleiro, pecaNova);
							lista.remove(randomPeca);
							pecaColocada = true;
							contador = 0;/*O contador de tentativas zera a cada processo bem sucedido*/
							break;
						}
					}
				}
			}
		}
		if (contador == 50) {
			System.out.println("Combina��o de pe�as inv�lida !");
			return null;
		}
		return tabuleiro;

	}

	/*======M�TODO PARA ADICIONAR A PE�A NO TABULEIRO AP�S SER VALIDADA=====*/
	int[][] addPeca(int i, int j, Integer id, int[][] tabuleiro, int[][] pecaNova) {
		for (int x = 0; x < pecaNova.length; x++) {
			for (int y = 0; y < pecaNova[x].length; y++) {
				tabuleiro[x + i][y + j] = id;
			}
		}
		return tabuleiro;
	}
	/*=====M�TODO PARA VERIFICAR SE A PE�A ENCAIXA NO TABULEIRO=====*/
	boolean checaCasaDisponivel(int i, int j, int[][] tabuleiro, int[][] pecaNova) {
		for (int x = 0; x < pecaNova.length; x++) {
			for (int y = 0; y < pecaNova[x].length; y++) {
				if (((x + i) >= tabuleiro.length) || ((y + j) >= tabuleiro[0].length) || tabuleiro[x + i][y + j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
}
