package br.com.magna.forca.entites;

public class Player {
	private String name;
	private boolean isPlayerDead = false;
	private int hearts = 4;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getHearts() {
		return hearts;
	}
	
	public boolean isPlayerDead() {
		return isPlayerDead;
	}
	
	public void removeHeart() {
		hearts -= 1;
		if(hearts <= 0) {
			isPlayerDead = true;
		}
	}
	
	@Override
	public String toString() {
		if(isPlayerDead) {
			return name + " - " + "morto";
		}
		
		return name + " - " + hearts + " vidas";
	}
}
