package it.polito.tdp.warehouse.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.warehouse.bean.SimMenoOccupato;
import it.polito.tdp.warehouse.bean.Statistiche;
import it.polito.tdp.warehouse.bean.WMovement;
import it.polito.tdp.warehouse.bean.WMovement.directionEnum;
import it.polito.tdp.warehouse.bean.WObject;
import it.polito.tdp.warehouse.db.WarehouseDAO;

public class Model {
	
	private WarehouseDAO dao;
	private Map<Integer, WObject> oggetti; 
	private List<WMovement> movimenti;
	
	public Model(){
		this.dao = new WarehouseDAO();
		this.oggetti= new HashMap<>();
		movimenti = new ArrayList<>();
	}
	
	public void riempiMappa(){
		if(this.oggetti.isEmpty()){
			oggetti = dao.getAllObjects();
		}
	}
	
	public List<WMovement> riempiMovimenti(){
		if(movimenti.isEmpty()){
			movimenti = dao.getAllMovements();
		}
		return movimenti;
	}
	
	public int calcolaOccupazioneMassima(){
		this.riempiMappa();
		this.riempiMovimenti();
		int max = Integer.MIN_VALUE;
		int ris = 0;
		for(WMovement m : movimenti){
			if(m.getDirection()== directionEnum.IN){
				ris+=oggetti.get(m.getOnjectId()).getSize();
			}else{
				ris-=oggetti.get(m.getOnjectId()).getSize();
			}
			if(ris>max){
				max=ris;
			}
			
		}
		return max;
	}

	public Statistiche avviaSimulazione(int locali, String strategia) {
		
		if(strategia.equals("Meno occupato")){
			SimMenoOccupato sim = new SimMenoOccupato(this.movimenti, this.oggetti);
			sim.inizializza(locali);
			return sim.run();
		}
		return null;
	}


}
