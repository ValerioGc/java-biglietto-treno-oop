package org.italy.trains;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Ticket {
	private int pKm;
	private int pAge;
	
//	private static final double KM_PRICE = 0.21;
//	private static final int UNDER_DISCOUNT = 20;
//	private static final int OVER_DISCOUNT = 40;
	
	private static final BigDecimal KM_PRICE = new BigDecimal(.21);
	private static final BigDecimal UNDER_DISCOUNT = new BigDecimal(20);
	private static final BigDecimal OVER_DISCOUNT = new BigDecimal(40);
	
	//Milestone 3
	private LocalDate date;
	private boolean flex;
	
	private static final int STANDARD_DURATION = 30;
	private static final int FLEX_DURATION = 90;
	
	public Ticket(int pAge, int pKm, boolean flex) throws Exception {
		setpAge(pAge);
		setpKm(pKm);
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
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate() {
		LocalDate date = LocalDate.now();
		this.date = date;
	}

	public boolean isFlex() {
		return flex;
	}
	public void setFlex(boolean flex) {
		this.flex = flex;
	}

	//	Controllo inserimento dati
	private boolean isValidAge(int pAge) {
		return ((pAge >= 1) && (pAge < 120));
	} 	
	private boolean isValidKm(int pKm) {
		return pKm >= 0;
	}
	
//	Calcolo prezzo
	public float calcPrice() {
		BigDecimal pKm2 = new BigDecimal(pKm);
		
		BigDecimal price = pKm2.multiply(KM_PRICE);
	
		BigDecimal discount = calcDiscount(price, pAge);
		BigDecimal divisor = new BigDecimal(100);
		BigDecimal overp = new BigDecimal(10);
		if (isFlex()) {
			price = price.add(price.multiply(overp).divide(divisor));
		}
		return price.subtract(discount).floatValue();
	}
	
//	Calcolo Sconto
	private BigDecimal calcDiscount(BigDecimal price, int age) {
		BigDecimal kmDB = new BigDecimal(0);
		BigDecimal divisor = new BigDecimal(100);
		if (pAge <= 18) {			
			kmDB = price.multiply(UNDER_DISCOUNT).divide(divisor);
		} 
		if (pAge >= 65 ) {
			kmDB =  price.multiply(OVER_DISCOUNT).divide(divisor);;
		} 
		return kmDB;
	}
	
////	Calcolo prezzo
//	public double calcPrice() {
//		double price = ((double)pKm) * KM_PRICE;
//		double discount = calcDiscount(price, pAge);
//		if (isFlex()) {
//			price += (price * 10) / 100;
//		}
//		return price - discount;
//	}
//	
////	Calcolo Sconto
//	private double calcDiscount(double price, int age) {
//		double discount;
//		if (pAge <= 18) {			
//			discount = (price * UNDER_DISCOUNT) / 100;
//		} else if (pAge >= 65 ) {
//			discount = (price * OVER_DISCOUNT) / 100;
//		} else {
//			discount = 0;
//		}
//		return discount;
//	}
	
// Calcolo data scadenza
	private LocalDate calcExpDate(LocalDate date) {
		LocalDate expDate;
		if (isFlex()) {
		    expDate = date.plusDays(FLEX_DURATION);
		} else {			
			expDate = date.plusDays(STANDARD_DURATION);
		}
		return expDate;
	}
// Translate boolean value 
	private String translateBoolFlex(){	
		return isFlex()? "Si":"No";
	}

	
	@Override
	public String toString() {
		return "\nDistanza da percorrere: " + getpKm()  + " km"
				+ "\nEtà passeggero: " + getpAge()  + " anni"
				+ "\nPrezzo: " + calcPrice() + " euro"
				+ "\nFlessibile: " + translateBoolFlex()
				+ "\nData acquisto: " + getDate() 
				+ "\nScadenza: " + calcExpDate(date)
				+ "\n----------------------------------\n";
	}
}
