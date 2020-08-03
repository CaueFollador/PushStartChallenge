package Challenge;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Challenge {

	public static void main(String[] args) {
		
		/*=====RECEBE OS PAR�METROS DO TABULEIRO=====*/
		Scanner entrada = new Scanner (System.in);
		System.out.println("Qual ser� a altura do tabuleiro?");
		int altura = entrada.nextInt();
		System.out.println("Qual ser� a largura do tabuleiro?");
		int largura = entrada.nextInt();
		
		/*-----Devolve o tabuleiro ao usu�rio-----*/
		int[][] tabuleiro = new int[altura][largura];
		System.out.println("-----TABULEIRO CRIADO-----");
		for(int[] t: tabuleiro) {
			System.out.println(Arrays.toString(t));
		}
		System.out.println("--------------------------");
		
		
		/*vari�veis e listas de valida��o das pe�as que ser�o geradas pelo usu�rio*/
		int areaTabuleiro = altura * largura;
		int areaTotalPecas = 0;
		List<Integer> idEmUso = new ArrayList<>();
		List<Peca> listaDePecas = new ArrayList<>();
		int erro = 0;
		int validador = 1;
		
		/*=====COME�ANDO A CRIA��O DAS PE�AS=====*/
		while(validador !=0) {
			erro = 0;
			//--valida��o de ID--//
			System.out.println("Qual ser� o n�mero de ID da pe�a?");
			int pecaId = entrada.nextInt();
			if(idEmUso.contains(pecaId)) {
				while(idEmUso.contains(pecaId)) {					
					System.out.println("n�mero de ID j� est� sendo usado!");
					System.out.println("Qual ser� o n�mero de ID da pe�a?");
					pecaId = entrada.nextInt();
				}
			}
			idEmUso.add(pecaId);
			
			/*--valida��o de altura da pe�a--*/
			System.out.println("Qual ser� a altura da pe�a?");
			int pecaAltura = entrada.nextInt();
			if(pecaAltura > altura) {
				while(pecaAltura > altura) {
					System.out.printf("A altura da sua pe�a n�o pode ser maior que a altura do tabuleiro (%d blocos)\n",altura);
					System.out.println("Qual ser� a altura da pe�a?");
					pecaAltura = entrada.nextInt();
				}
			}
			
			/*--valida��o de largura da pe�a--*/
			System.out.println("Qual ser� a largura da pe�a?");
			int pecaLargura = entrada.nextInt();
			if(pecaLargura > largura) {
				while(pecaLargura > largura) {
					System.out.printf("A largura da sua pe�a n�o pode ser maior que a largura do tabuleiro (%d blocos)\n",largura);
					System.out.println("Qual ser� a largura da pe�a?");
					pecaLargura = entrada.nextInt();
				}
			}
			
			/*--valida��o de quantidade de pe�as a serem criadas--*/
			System.out.printf("Quantas pe�as de ID %d voc� quer criar?\n",pecaId);
			int pecaQtd = entrada.nextInt();
			for(int i = 1; i<= pecaQtd; i++) {
				int qtdPecasCriadas = 0;
				Peca novaPeca = new Peca(pecaId, pecaLargura, pecaAltura);
				areaTotalPecas += (pecaLargura * pecaAltura);
				if(areaTabuleiro >= areaTotalPecas ) {
					listaDePecas.add(novaPeca);
					qtdPecasCriadas ++;
				} else {
					System.out.println("O sistema p�de criar somente " + qtdPecasCriadas + " pe�as.");
					if(qtdPecasCriadas == 0 && listaDePecas.contains(novaPeca)) {
						listaDePecas.remove(novaPeca);
					}
					areaTotalPecas -= (pecaLargura * pecaAltura);
					erro = 1;
					break;	
				}
			}
			if(erro != 0) {
				System.out.printf("Restam %d blocos para serem preenchidos\n",(areaTabuleiro-areaTotalPecas));
				validador = 0;
			}
			if((areaTabuleiro-areaTotalPecas)!=0){
				System.out.println("1- Criar outra pe�a | 0- Sair");
				validador = entrada.nextInt();				
			} else {
				System.out.println("N�mero m�ximo de pe�as foi atingido!");
				validador = 0;
			}
		}

		
//		Collections.sort(listaDePecas);

		/*=====CHAMANDO A FUN��O PARA ENCAIXAR AS PE�AS NO TABULEIRO====*/
		Random organizer = new Random();
		int [][] resolucao = organizer.resolucao(listaDePecas, tabuleiro);
		
			if(resolucao != null) {
			System.out.println("\n\n======RESULTADO======\n");
			for(int[] t: tabuleiro) {
				System.out.println(Arrays.toString(t));
			}
		}
		entrada.close();
	}
} 
