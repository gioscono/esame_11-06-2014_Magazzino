package it.polito.tdp.warehouse.bean;

import java.util.Map;

public class Statistiche {

	private double tempo;
	private Map<Locale, Double> mappaFattCarico;
	private int disastri;
	
	public Statistiche(double tempo, Map<Locale, Double> mappaFattCarico, int disastri) {
		this.tempo=tempo;
		this.mappaFattCarico = mappaFattCarico;
		this.disastri = disastri;
	}

	public double getTempo() {
		return tempo;
	}

	public Map<Locale, Double> getMappaFattCarico() {
		return mappaFattCarico;
	}

	public int getDisastri() {
		return disastri;
	}

	
	
	
}
