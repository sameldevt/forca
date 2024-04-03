package br.com.magna.forca.entites;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
	static void clear() {for(int i = 0; i<50; i++)System.out.println();}
	
	public void setPlayerQuantity() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Quantidade de jogadores: ");
		int playerQuantity = scan.nextInt();
		
		for(int i = 0; i < playerQuantity; i++) {
			System.out.print("Digite o nome do " + (i + 1) + "º jogador: ");
			String name = scan.next();
			
			Player player = new Player(name);
			HangmanGame.addPlayer(player);
		}
		
		scan.close();
		firstGameLoop();
	}
	
	public void firstGameLoop() {
		Scanner scan = new Scanner(System.in);
		while(HangmanGame.round < 1) {
			clear();
			game.printWordTable();
			game.printAttempts();
			game.printPlayersStats();
			
			System.out.println(HangmanGame.currentPlayer.getName() + ", digite uma letra");
			System.out.print("> ");
			char attempt = scan.next().charAt(0);
			
			game.guessLetter(attempt);
			game.nextPlayer();
		}
	}
	
	public void secondGameLoop() {
		while(!game.isWordGuessed) {
			clear();
			game.printWordTable();
			game.printAttempts();
			game.printPlayersStats();
			
			System.out.println(game.getCurrentPlayer().getName() + ", deseja chutar uma letra ou a palavra inteira?");
			System.out.println("1 - letra");
			System.out.println("2 - palavra");
			System.out.print("> ");
			int choice;
			try {
				choice = scan.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println();
				scan.next();
				continue;
			}
			
			if(choice == 1) {
				System.out.print("Digite uma letra: ");
				char attempt = scan.next().charAt(0);
				game.guessLetter(attempt);
				game.nextPlayer();
			}
			else if(choice == 2) {
				System.out.print("Digite a palavra: ");
				String attempt = scan.next();
				if(game.guessWord(attempt)) {
					System.out.println(game.getCurrentPlayer().getName() + " é o vencedor!");
					System.exit(0);
				}
			}
			else {
				game.nextPlayer();
				continue;
			}
			
		}
	}
	
	public void printWinner() {
		game.printWordTable();
		System.out.println(game.getCurrentPlayer().getName() + " é o vencedor!");
		System.exit(0);
	}
}
