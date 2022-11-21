package org.italy.trains;

import java.time.LocalDate;

public class Ticket {
	private int pKm;
	private int pAge;
	
	private static final double KM_PRICE = 0.21;
	private static final int UNDER_DISCOUNT = 20;
	private static final int OVER_DISCOUNT = 40;
	
	//Milestone 3
	private String date;
	private boolean flex;
	
	private static final int STANDARD_DURATION = 30;
	private static final int FLEX_DURATION = 90;
	
	public Ticket(int pAge, int pKm, boolean flex) throws Exception {
		setpAge(pAge);
//		isValidAge(pAge);
		setpKm(pKm);
//		isValidKm(pKm);
		setFlex(flex);
		setDate();
	}

	public int getpAge() {
		return pAge;
	}
	public void setpAge(int pAge) throws Exception {
		if (!isValidAge(pAge)) {			
			throw new Exception("Inserisci un età valida");
		}
		this.pAge = pAge;
	}

	public int getpKm() {
		return pKm;
	}
	public void setpKm(int pKm) throws Exception {
		
		if (!isValidKm(pKm)){			
			throw new Exception("Inserisci una distanza valida");
		}
		this.pKm = pKm;
	}
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate() {
		LocalDate today = LocalDate.now();
		String formDate = today.toString();
		this.date = formDate;
	}

	public boolean isFlex() {
		return flex;
	}
	public void setFlex(boolean flex) {
		this.flex = flex;
	}

	//	Controllo inserimento dati
	public boolean isValidAge(int pAge) throws Exception {
		if ((pAge < 1) || (pAge > 120)) {			
			throw new Exception("Inserisci un età valida");
		} 
		return true;
	} 	
	public boolean isValidKm(double pKm) throws Exception {
		if (pKm == 0) {			
			throw new Exception("Inserisci un età valida");
		}
		return true;
	}
	
//	Calcolo prezzo
	public double calcPrice() {
		double price = ((double)pKm) * KM_PRICE;
		double discount = calcDiscount(price, pAge);
		if (isFlex()) {
			price += (price * 10) / 100;
		}
		return price - discount;
	}
	
//	Calcolo Sconto
	private double calcDiscount(double price, int age) {
		double discount;
		if (pAge <= 18) {			
			discount = (price * UNDER_DISCOUNT) / 100;
		} else if (pAge >= 65 ) {
			discount = (price * OVER_DISCOUNT) / 100;
		} else {
			discount = 0;
		}
		return discount;
	}
	
// Calcolo data scadenza
	private String calcExpDate(String date) {
		LocalDate today = LocalDate.now();
		LocalDate expDate;
		if (isFlex()) {
		    expDate = today.plusDays(FLEX_DURATION);
		} else {			
			expDate = today.plusDays(STANDARD_DURATION);
		}
		String formExpDate = expDate.toString();
		return formExpDate;
	}

	private String translateBool(){	
	String res;
	if (isFlex()) { 
		res = "Si";
	} else {
		res = "No";
	}
	return res;
}
	@Override
	public String toString() {
		return "\nDistanza da percorrere: " + getpKm()  + " km"
				+ "\nEtà passeggero: " + getpAge()  + " anni"
				+ "\nPrezzo: " + calcPrice() + " euro"
				+ "\nFlessibile: " + translateBool()
				+ "\nData acquisto: " + getDate() 
				+ "\nScadenza: " + calcExpDate(date) 
				+ "\n----------------------------------\n";
	}
}
