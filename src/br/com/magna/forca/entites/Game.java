package br.com.magna.forca.entites;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private static List<String> words = new ArrayList<String>();
	private List<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private String word;
	private char[] table;
	private String attempts = "";
	private int round = 0;
	public boolean isWordGuessed = false;
	
	static {
		fillWordList();
	}
	
	public void printPlayersStats() {
		for(Player p : players) {
			System.out.println(p);
		}
	}
	
	public void printAttempts() {
		for(int i = 0; i < attempts.length(); i++) {
			System.out.print(attempts.charAt(i) + " ");
		}
		System.out.println();
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

	public boolean guessWord(String attempt) {
		if(String.valueOf(attempt.toCharArray()).equals(word)) {
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
				System.exit(0);
			}
		}
	}
	
	public void startWordTable() {
		for(int i = 0; i < word.length(); i++) {
			table[i] = '_';
		}
	}
	
	public void printWordTable() {
		for(int i = 0; i < word.length(); i++) {
			System.out.print(table[i] + " ");
		}
		System.out.println("\n");
	}
	
	public void chooseWord() {
		Random r = new Random();
		int randomWord = r.nextInt(words.size());
		
		word = words.get(randomWord);
		table = new char[word.length()];
	}

	public void addPlayer(Player p) {
		players.add(p);
	}
	
	private static void fillWordList() {
		try(Scanner scan = new Scanner(new File("util/words.txt"))){
			while(scan.hasNext()) {
				String word = scan.next().toLowerCase();
				words.add(word);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
