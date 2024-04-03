package br.com.magna.forca.entites;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

	private static List<String> words;
	private List<Player> players = new LinkedList<Player>();
	private Player currentPlayer;
	private String secretWord;
	private char[] table;
	private String attempts = "";
	private int round = 0;
	public boolean isWordGuessed = false;

	public void printStats() {
		for(int i = 0; i < secretWord.length(); i++) {
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
	
	public void printWordTable() {
		for(int i = 0; i < secretWord.length(); i++) {
			System.out.print(table[i] + " ");
		}
		System.out.println("\n");
	}

	private void setCurrentPlayer() {
		currentPlayer = players.get(0);
	}
	
	public void start() {
		chooseWord();
		setCurrentPlayer();
		startWordTable();
	}
	
	public boolean guessLetter(char attempt) {
		if(!secretWord.contains(String.valueOf(attempt)) || attempts.contains(String.valueOf(attempt))) {
			currentPlayer.removeHeart();
			if(!attempts.contains(String.valueOf(attempt))) {
				attempts+= attempt;
			}
			updateRound();
			return false;
		}
		
		for(int i = 0; i < table.length; i++) {
			if(secretWord.charAt(i) == attempt) {
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
		
		if(String.valueOf(table).equals(secretWord)) {
			isWordGuessed = true;
			return true;
		}
		
		updateRound();
		return false;
	}

	public boolean guessWord(String attempt) {
		if(String.valueOf(attempt.toCharArray()).equals(secretWord)) {
			return true;
		}
		
		currentPlayer.removeHeart();
		updateRound();
		return false;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public int getRound() {
		return round;
	}
	
	public void updateRound() {
		if(players.indexOf(currentPlayer) == players.size() - 1) {
			round++;			
		}
	}
	
	public void verifyPlayers() {
		boolean allPlayersDead = true;
		
		for(Player p : players) {
			if(!p.isDead()) {
				allPlayersDead = false;
				break;
			}
		}
		
		if(allPlayersDead) {
			endGame();
		}
	}
	
	public void nextPlayer() {
		try {
			verifyPlayers();
			currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
			while(currentPlayer.isDead()) {
				nextPlayer();
			}
		}catch(IndexOutOfBoundsException e) {
			currentPlayer = players.getFirst();
			if(currentPlayer.isDead()) {
				nextPlayer();
			}
		}
	}
	
	public void endGame() {
		TerminalHandler.clear();
		TerminalHandler.print(TerminalHandler.SKULL);
		System.out.println("Todos os jogadores estão mortos!");
		System.out.println("Fim de jogo");
		System.exit(0);
	}
	
	public void startWordTable() {
		for(int i = 0; i < secretWord.length(); i++) {
			table[i] = '_';
		}
	}
	
	public void chooseTotalPlayerHearts(int totalHearts) {
		for(Player p : players) {
			p.setHearts(totalHearts);
		}
	}
	
	public void chooseDifficult(int difficult) {
		switch(difficult) {
			case 1:{ 
				words = WordHandler.fillWordList(WordHandler.EASY);
				break;
			}
			case 2:{ 
				words = WordHandler.fillWordList(WordHandler.MEDIUM);
				break;
			}
			case 3: {
				words = WordHandler.fillWordList(WordHandler.HARD);
				break;
			}
		}	
	}
	
	public void chooseWord() {
		Random r = new Random();
		int randomWord = r.nextInt(words.size());
		
		secretWord = words.get(randomWord);
		table = new char[secretWord.length()];
	}

	public void addPlayer(Player p) {
		players.add(p);
	}
}
