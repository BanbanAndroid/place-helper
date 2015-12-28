package com.place.helper.worker;

import java.util.ArrayList;

import javax.inject.Named;

/**
 * @author banban
 *
 */
@Named
public class PlaceUrlWorker implements Runnable {

	private ArrayList<String> placeUrls;
	
	public PlaceUrlWorker() {
		// TODO Auto-generated constructor stub
	}
	
	public PlaceUrlWorker(ArrayList<String> placeUrls) {
		super();
		this.placeUrls = placeUrls;
	}
	
	public ArrayList<String> getPlaceUrls() {
		return placeUrls;
	}
	public void setPlaceUrls(ArrayList<String> placeUrls) {
		this.placeUrls = placeUrls;
	}
	
	public void run() {
		
	}
}
