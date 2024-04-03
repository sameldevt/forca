package br.com.magna.forca.entites;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordBank {
	private static List<String> words = new ArrayList<String>();

	static {
		fillWordList();
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
	
	public static List<String> getWords(){
		return words;
	}
}
