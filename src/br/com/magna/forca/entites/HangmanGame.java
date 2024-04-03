package br.com.magna.forca.entites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanGame {
	private List<Player> players = new ArrayList<Player>();
	private List<String> words = WordBank.getWords();
	
	public static Player currentPlayer;
	private String word;
	private String attempts = "";
	
	private char[] table;
	public static int round = 0;
	public boolean isWordGuessed = false;
	
	public void newGame() {
		Random r = new Random();
		int randomWord = r.nextInt(words.size());
		
		word = words.get(randomWord);
		table = new char[word.length()];
		
		currentPlayer = players.get(0);
		
		for(int i = 0; i < word.length(); i++) {
			table[i] = '_';
		}
	}
	

	public static void addPlayer(Player p) {
		players.add(p);
	}
	
	private void updateRound() {
		if(players.indexOf(currentPlayer) == players.size() - 1) {
			round++;			
		}
	}
	
	public int getRound() {
		return round;
	}
	
	public void printStats() {
		for(int i = 0; i < word.length(); i++) {
			System.out.print(table[i] + " ");
		}
		System.out.println("\n");
		
		for(int i = 0; i < attempts.length(); i++) {
			System.out.print(attempts.charAt(i) + " ");
		}
		System.out.println();
		
		for(Player p : players) {
			System.out.println(p);
		}		
	}
	
	public boolean guessWord(String attempt) {
		if(String.valueOf(attempt.toCharArray()).equals(word)) {
			return true;
		}
		
		currentPlayer.removeHeart();
		updateRound();
		return false;
	}
	
	public boolean guessLetter(char attempt) {
		if(!word.contains(String.valueOf(attempt)) || attempts.contains(String.valueOf(attempt))) {
			currentPlayer.removeHeart();
			if(!attempts.contains(String.valueOf(attempt))) {
				attempts+= attempt;
			}
			return false;
		}
		
		for(int i = 0; i < table.length; i++) {
			if(word.charAt(i) == attempt) {
				table[i] = attempt;
				if(!attempts.contains(String.valueOf(attempt))) {
					attempts+= attempt;
				}
				updateRound();
			}
		}
		
		if(!attempts.contains(String.valueOf(attempt))) {
			attempts+= attempt;
		}
		
		if(String.valueOf(table).equals(word)) {
			isWordGuessed = true;
		}
		
		updateRound();
		return false;
	}
	
	public void nextPlayer() {
		try {
			Player nextPlayer = players.get(players.indexOf(currentPlayer) + 1);
			while(nextPlayer.isPlayerDead()) {
				nextPlayer = players.get(players.indexOf(nextPlayer) + 1);
			}
			
			currentPlayer = nextPlayer;
		}catch(IndexOutOfBoundsException e) {
			currentPlayer = players.get(0);
			if(currentPlayer.isPlayerDead()) {
				System.out.println("Todos os jogadores estão mortos!");
				System.out.println("Fim de jogo");
				System.exit(0);
			}
		}
	}
}
