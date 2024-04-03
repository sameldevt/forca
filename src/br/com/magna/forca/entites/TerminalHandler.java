package br.com.magna.forca.entites;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TerminalHandler {
	public static final String LOGO = "util/logo.txt";
	public static final String SKULL = "util/skull.txt";
	public static final String WIN = "util/win.txt";
	
	public static void clear() {
		for(int i = 0; i<50; i++)
			System.out.println();
	}
	
	public static void print(String imagePath) {
		try(Scanner scan = new Scanner(new File(imagePath))){
			while(scan.hasNext()) {
				System.out.println(scan.nextLine());
			}
			System.out.println();
		}	
		catch(IOException e) {
			e.printStackTrace();
		}
	}	
}
