package Challenge;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Challenge {

	public static void main(String[] args) {
		
		/*=====RECEBE OS PARÂMETROS DO TABULEIRO=====*/
		Scanner entrada = new Scanner (System.in);
		System.out.println("Qual será a altura do tabuleiro?");
		int altura = entrada.nextInt();
		System.out.println("Qual será a largura do tabuleiro?");
		int largura = entrada.nextInt();
		
		/*-----Devolve o tabuleiro ao usuário-----*/
		int[][] tabuleiro = new int[altura][largura];
		System.out.println("-----TABULEIRO CRIADO-----");
		for(int[] t: tabuleiro) {
			System.out.println(Arrays.toString(t));
		}
		System.out.println("--------------------------");
		
		
		/*variáveis e listas de validação das peças que serão geradas pelo usuário*/
		int areaTabuleiro = altura * largura;
		int areaTotalPecas = 0;
		List<Integer> idEmUso = new ArrayList<>();
		List<Peca> listaDePecas = new ArrayList<>();
		int erro = 0;
		int validador = 1;
		
		/*=====COMEÇANDO A CRIAÇÃO DAS PEÇAS=====*/
		while(validador !=0) {
			erro = 0;
			//--validação de ID--//
			System.out.println("Qual será o número de ID da peça?");
			int pecaId = entrada.nextInt();
			if(idEmUso.contains(pecaId)) {
				while(idEmUso.contains(pecaId)) {					
					System.out.println("número de ID já está sendo usado!");
					System.out.println("Qual será o número de ID da peça?");
					pecaId = entrada.nextInt();
				}
			}
			idEmUso.add(pecaId);
			
			/*--validação de altura da peça--*/
			System.out.println("Qual será a altura da peça?");
			int pecaAltura = entrada.nextInt();
			if(pecaAltura > altura) {
				while(pecaAltura > altura) {
					System.out.printf("A altura da sua peça não pode ser maior que a altura do tabuleiro (%d blocos)\n",altura);
					System.out.println("Qual será a altura da peça?");
					pecaAltura = entrada.nextInt();
				}
			}
			
			/*--validação de largura da peça--*/
			System.out.println("Qual será a largura da peça?");
			int pecaLargura = entrada.nextInt();
			if(pecaLargura > largura) {
				while(pecaLargura > largura) {
					System.out.printf("A largura da sua peça não pode ser maior que a largura do tabuleiro (%d blocos)\n",largura);
					System.out.println("Qual será a largura da peça?");
					pecaLargura = entrada.nextInt();
				}
			}
			
			/*--validação de quantidade de peças a serem criadas--*/
			System.out.printf("Quantas peças de ID %d você quer criar?\n",pecaId);
			int pecaQtd = entrada.nextInt();
			for(int i = 1; i<= pecaQtd; i++) {
				int qtdPecasCriadas = 0;
				Peca novaPeca = new Peca(pecaId, pecaLargura, pecaAltura);
				areaTotalPecas += (pecaLargura * pecaAltura);
				if(areaTabuleiro >= areaTotalPecas ) {
					listaDePecas.add(novaPeca);
					qtdPecasCriadas ++;
				} else {
					System.out.println("O sistema pôde criar somente " + qtdPecasCriadas + " peças.");
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
				System.out.println("1- Criar outra peça | 0- Sair");
				validador = entrada.nextInt();				
			} else {
				System.out.println("Número máximo de peças foi atingido!");
				validador = 0;
			}
		}

		
//		Collections.sort(listaDePecas);

		/*=====CHAMANDO A FUNÇÃO PARA ENCAIXAR AS PEÇAS NO TABULEIRO====*/
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
