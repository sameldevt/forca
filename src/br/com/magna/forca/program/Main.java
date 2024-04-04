package br.com.magna.forca.program;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.magna.forca.entites.Game;
import br.com.magna.forca.entites.Player;
import br.com.magna.forca.entites.TerminalHandler;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Game game = new Game();
		
		int choice = 0;
		while(true) {		
			TerminalHandler.clear();
			TerminalHandler.print(TerminalHandler.LOGO);
			System.out.println("1 - Jogar");
			System.out.println("2 - Finalizar o jogo");
			System.out.print("> ");
			try {
				choice = scan.nextInt();		
			}
			catch(InputMismatchException e) {
				System.out.println();
				scan.next();
				continue;
			}
			switch(choice) {
				case 1: {
					int playerQuantity = 0;
					while(true) {
						TerminalHandler.clear();
						TerminalHandler.print(TerminalHandler.LOGO);
						System.out.println("Quantidade de jogadores");
						System.out.print("> ");
						
						try {
							playerQuantity = scan.nextInt();
							break;
						}
						catch(InputMismatchException e) {
							System.out.println();
							scan.next();
							continue;
						}
					}
					
					for(int i = 0; i < playerQuantity; i++) {
						System.out.println("Digite o nome do " + (i + 1) + "º jogador");
						System.out.print("> ");
						String name = scan.next();
						
						Player player = new Player(name);
						game.addPlayer(player);
					}
					
					while(true) {
						TerminalHandler.clear();
						TerminalHandler.print(TerminalHandler.LOGO);
						System.out.println("Escolha a dificuldade");
						System.out.println("1 - Facil");
						System.out.println("2 - Médio");
						System.out.println("3 - Difícil");
						System.out.print("> ");
						int difficult;
						try {
							difficult = scan.nextInt();
							if(difficult > 3 || difficult < 1) {
								System.out.println();
								scan.nextLine();
								continue ;
							}
							game.chooseDifficult(difficult);
							break;
						}
						catch(InputMismatchException e) {
							System.out.println();
							scan.next();
							continue ;
						}
					}
					
					while(true) {
						TerminalHandler.clear();
						TerminalHandler.print(TerminalHandler.LOGO);
						System.out.println("Digite o total de vidas dos jogadores");
						System.out.print("> ");
						int totalHearts;
						try {
							totalHearts = scan.nextInt();
							game.chooseTotalPlayerHearts(totalHearts);
							break;
						}
						catch(InputMismatchException e) {
							System.out.println();
							scan.next();
							continue;
						}
					}
					break;
				}
				case 2: {
					TerminalHandler.clear();
					System.exit(0);
				}
				default: continue;
			}
			break;
		}
	
		game.start();

		while(game.getRound() < 1) {
			TerminalHandler.clear();
			TerminalHandler.print(TerminalHandler.LOGO);
			game.printStats();
			
			System.out.println(game.getCurrentPlayer().getName() + ", digite uma letra");
			System.out.print("> ");
			char attempt = scan.next().charAt(0);
			
			if(game.guessLetter(attempt)) {
				TerminalHandler.clear();
				TerminalHandler.print(TerminalHandler.WIN);
				game.printWordTable();
				System.out.println(game.getCurrentPlayer().getName() + " é o vencedor!");
				System.exit(0);
			}
			game.nextPlayer();
		}
		
		while(!game.isWordGuessed) {
			TerminalHandler.clear();
			TerminalHandler.print(TerminalHandler.LOGO);
			game.printStats();
			
			System.out.println(game.getCurrentPlayer().getName() + ", deseja chutar uma letra ou a palavra inteira?");
			System.out.println("1 - letra");
			System.out.println("2 - palavra");
			System.out.print("> ");
			
			try {
				choice = scan.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println();
				scan.next();
				continue;
			}
			
			if(choice == 1) {
				System.out.println("Digite uma letra");
				System.out.print("> ");
				char attempt = scan.next().charAt(0);
				if(game.guessLetter(attempt)) {
					break;
				}
				
				game.nextPlayer();
			}
			else if(choice == 2) {
				System.out.println("Digite a palavra");
				System.out.print("> ");
				String attempt = scan.next();
				if(game.guessWord(attempt)) {
					break;
				}
				game.nextPlayer();
			}
			else {
				continue;
			}
			
		}
		
		scan.close();
		TerminalHandler.clear();
		TerminalHandler.print(TerminalHandler.WIN);
		game.printWordTable();
		System.out.println(game.getCurrentPlayer().getName() + " é o vencedor!");
		System.exit(0);
	}
}
