package br.com.magna.forca.entites;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordHandler {
	public static String EASY = "util/words/easy.txt";
	public static String MEDIUM = "util/words/medium.txt";
	public static String HARD = "util/words/hard.txt";
	
	public static List<String> fillWordList(String path) {
		List<String> words = new ArrayList<String>();
		try(Scanner scan = new Scanner(new File(path))){
			while(scan.hasNext()) {
				String word = scan.next().toLowerCase();
				words.add(word);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return words;
	}
}
