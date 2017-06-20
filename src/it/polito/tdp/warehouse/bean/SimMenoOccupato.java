package it.polito.tdp.warehouse.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.warehouse.bean.WMovement.directionEnum;

public class SimMenoOccupato {

	
	private List<WMovement> movimenti;
	private Map<Integer, WObject> oggetti;
	private List<Locale> locali ;
	private double tempo;
	private Map<Locale, Double> mappaFattCarico;
	private PriorityQueue<Evento> queue;
	private Map<Integer, WMovement> mappaMovimenti;
	
	private int disastri ;
	
	public SimMenoOccupato(List<WMovement> movimenti, Map<Integer, WObject> oggetti) {
		this.movimenti = movimenti;
		this.oggetti = oggetti;
		this.locali =  new ArrayList<Locale>();
		this.disastri = 0;
		this.queue = new PriorityQueue<>();
		mappaFattCarico= new HashMap<>();
		mappaMovimenti = new HashMap<>();
	}

	public void inizializza(int loca) {
		
		
		for(int i =0; i<loca; i++){
			String id = "Locale_"+i;
			Locale l = new Locale(id, 300);
			locali.add(l);
			mappaFattCarico.put(l,0.0);
		}
		
		for(WMovement wm : movimenti){
			if(wm.getDirection()== directionEnum.IN){
				Evento e = new Evento(wm.getDirection(), wm.getOnjectId(), wm.getTime());
				queue.add(e);
			}
			mappaMovimenti.put(wm.getId(), wm);
		}
		
	}

	public Statistiche run() {
		
		while(!queue.isEmpty()){
			
			Evento e = queue.poll();
			System.out.println(e);
			boolean collocato = false;
			switch(e.getType()){
			
			case IN:
				Collections.sort(locali);
				
					if(locali.get(0).getSpazio()-oggetti.get(e.getOnjectId()).getSize()>0){
						e.setL(locali.get(0));
						locali.get(0).decrementaSpazio(oggetti.get(e.getOnjectId()).getSize());
						collocato = true;
						calcolaTempoRiemp(locali.get(0));
						calcolaMaxFattCarico(locali.get(0));
						queue.add(new Evento(e.getOnjectId(), e.getTime()*10+3000,directionEnum.OUT,  locali.get(0)));
				}
					else{
					disastri++;
				}
				break;
			case OUT:
				//System.out.println(e);
				System.out.println(e.getL());
				e.getL().aumentaSpazio(oggetti.get(e.getOnjectId()).size);
				calcolaTempoRiemp(e.getL());
				calcolaMaxFattCarico(e.getL());
			}
			
			
		}
			
		Statistiche s = new Statistiche(tempo, mappaFattCarico, disastri);
		return s;
		
		
	}

	private void calcolaMaxFattCarico(Locale l) {
		
		double fatt= (300.00-l.getSpazio())/300.00;
		if(fatt>mappaFattCarico.get(l)){
			mappaFattCarico.put(l, fatt);
		}
		
		
	}

	private void calcolaTempoRiemp(Locale l) {
		double fattCarico = (300-l.getSpazio())/300;
		tempo += 60+500*fattCarico;
		
	}


	
	
	
	
	

}
