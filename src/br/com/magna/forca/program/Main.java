package br.com.magna.forca.program;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.magna.forca.entites.Game;
import br.com.magna.forca.entites.Player;

public class Main {
	static void clear() {for(int i = 0; i<50; i++)System.out.println();}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Game game = new Game();
		
		System.out.print("Quantidade de jogadores: ");
		int playerQuantity = scan.nextInt();
		
		for(int i = 0; i < playerQuantity; i++) {
			System.out.print("Digite o nome do " + (i + 1) + "º jogador: ");
			String name = scan.next();
			
			Player player = new Player(name);
			game.addPlayer(player);
		}
		
		game.start();

		while(game.getRound() < 1) {
			clear();
			game.printWordTable();
			game.printAttempts();
			game.printPlayersStats();
			
			System.out.println(game.getCurrentPlayer().getName() + ", digite uma letra");
			System.out.print("> ");
			char attempt = scan.next().charAt(0);
			
			game.guessLetter(attempt);
			game.nextPlayer();
		}
		
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
		
		game.printWordTable();
		System.out.println(game.getCurrentPlayer().getName() + " é o vencedor!");
		System.exit(0);
	}
}
