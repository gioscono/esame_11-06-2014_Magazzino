package it.polito.tdp.warehouse.bean;

public class Locale implements Comparable<Locale>{
	
	
	private String id;
	private int spazio;
	
	public Locale(String id, int i) {
		this.id = id;
		this.spazio = i;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSpazio() {
		return spazio;
	}

	public void aumentaSpazio(int spazio) {
		this.spazio += spazio;
	}

	public void decrementaSpazio(int spazio) {
		this.spazio -= spazio;
	}

	@Override
	public String toString() {
		return this.id;
	}

	@Override
	public int compareTo(Locale altro) {
		// TODO Auto-generated method stub
		return -(this.spazio-altro.spazio);
	}
	
	
	
}
