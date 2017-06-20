package it.polito.tdp.warehouse.bean;

import it.polito.tdp.warehouse.bean.WMovement.directionEnum;

public class Evento implements Comparable<Evento>{

	private int onjectId;
	private int time;
	private directionEnum type;
	private Locale l;
	
	public Evento(directionEnum direction, int onjectId, int time) {
		this.type=direction;
		this.onjectId = onjectId;
		this.time = time;
	}
	
	

	public Evento(int onjectId, int time, directionEnum type, Locale l) {
		super();
		this.onjectId = onjectId;
		this.time = time;
		this.type = type;
		this.l = l;
	}



	public int getOnjectId() {
		return onjectId;
	}

	public void setOnjectId(int onjectId) {
		this.onjectId = onjectId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public directionEnum getType() {
		return type;
	}

	public void setType(directionEnum type) {
		this.type = type;
	}

	public Locale getL() {
		return l;
	}

	public void setL(Locale l) {
		this.l = l;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.time-o.time;
	}

	@Override
	public String toString() {
		return this.type+"-"+this.onjectId+"-"+this.l+"-"+this.time;
	}
	
	
	
	

}
