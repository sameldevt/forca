package br.com.magna.forca.entites;

public class Player {
	private String name;
	private boolean isDead = false;
	private int hearts;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHearts(int hearts) {
		this.hearts = hearts;
	}

	public int getHearts() {
		return hearts;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void removeHeart() {
		hearts -= 1;
		if(hearts <= 0) {
			isDead = true;
		}
	}
	
	@Override
	public String toString() {
		if(isDead) {
			return name + " - " + "morto";
		}
		
		return name + " - " + hearts + " vidas";
	}
}
